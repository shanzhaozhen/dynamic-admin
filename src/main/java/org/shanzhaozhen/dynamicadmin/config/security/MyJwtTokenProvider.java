package org.shanzhaozhen.dynamicadmin.config.security;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.shanzhaozhen.dynamicadmin.common.sys.JwtErrorConst;
import org.shanzhaozhen.dynamicadmin.vo.JWTUser;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyJwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(MyJwtTokenProvider.class);

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.remember_expiration}")
    private long remember_expiration;

    /**
     * iss：发行人
     * exp：到期时间
     * sub：主题
     * aud：用户
     * nbf：在此之前不可用
     * iat：发布时间
     * jti：JWT ID用于标识该JWT
     */
    // 创建token
    public String buildToken(String username, Map<String, Object> claims) {
        /**
         * 按照jwt的规定，最后请求的时候应该是 `Bearer token`
         */
        return tokenHead + Jwts.builder()
                /**
                 * 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值
                 * 一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                 */
                .setClaims(claims)
                .setSubject(username)
                .setIssuer(issuer)          //iss
                .setIssuedAt(new Date())            //iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))        //设置过期时间
                .signWith(SignatureAlgorithm.HS512, secret)         //设置签名使用的签名算法和签名使用的秘钥
                .compact();
    }


    /**
     * iss：发行人
     * exp：到期时间
     * sub：主题
     * aud：用户
     * nbf：在此之前不可用
     * iat：发布时间
     * jti：JWT ID用于标识该JWT
     */
    // 创建token
    public String createToken(Long userId, String username, List<String> roles) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);
        claims.put("username", username);
        claims.put("roles", roles);

        return this.buildToken(username, claims);
    }

    /**
     * iss：发行人
     * exp：到期时间
     * sub：主题
     * aud：用户
     * nbf：在此之前不可用
     * iat：发布时间
     * jti：JWT ID用于标识该JWT
     */
    // 创建token
    public String updateToken(String token) {
        Assert.hasText(token, "token不能为空");

        Claims oldClaims = this.getTokenBody(token);
        String username = oldClaims.getSubject();

        Map<String, Object> newClaims = new HashMap<>();

        newClaims.put("id", oldClaims.get("id", Long.class));
        newClaims.put("username", username);
        newClaims.put("roles", oldClaims.get("roles", List.class));

        return this.buildToken(username, newClaims);
    }

    /**
     * 校验token的签名
     * @param httpServletResponse
     * @param authToken
     * @return
     */
    public boolean validateToken(HttpServletResponse httpServletResponse, String authToken) throws IOException {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            /**
             * 验证通过同时更新token（延长过期时间）
             * 但是该方法不可取，每次更新旧的Token后，新旧的Token同时能使用问题就大了
             */
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_SIGNATURE);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_MALFORMED);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_EXPIRED);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_ILLEGALARGUMENT);
        } catch (JwtException ex) {
            logger.error("JWT error.");
            this.sendError(httpServletResponse, JwtErrorConst.JWT_ERROR);
        }
        return false;
    }


    public void sendError(HttpServletResponse httpServletResponse, JwtErrorConst jwtErrorConst) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONObject.toJSONString(new ResultObject(jwtErrorConst.getCode(), jwtErrorConst.getReason())));
    }

    /**
     * 从httpServletRequest获取token
     * @param httpServletRequest
     * @return
     */
    public String getJwtTokenFromRequest(HttpServletRequest httpServletRequest) {
        String jwtToken = httpServletRequest.getHeader(header);
        if (StringUtils.hasText(jwtToken) && jwtToken.startsWith(tokenHead)) {
            return jwtToken.substring((tokenHead).length());
        }
        return null;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        return Long.parseLong(String.valueOf(getTokenBody(token).get("id")));
    }

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    public List<String> getUserRoles(String token) {
        return getTokenBody(token).get("roles", List.class);
    }

    /**
     * 获取jwt用户体信息
     * @param token
     * @return
     */
    public JWTUser getJWTUser(String token) {
        Claims claims = getTokenBody(token);
        return JWTUser.builder()
                .id(Long.parseLong(String.valueOf(claims.get("id"))))
                .username(claims.getSubject())
                .authorities(claims.get("roles", List.class))
                .build();
    }

    // 是否已过期
    public boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private Claims getTokenBody(String token){

        token = token.replace(tokenHead, "");

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
