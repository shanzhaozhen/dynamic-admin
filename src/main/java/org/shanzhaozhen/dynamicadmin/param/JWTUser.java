package org.shanzhaozhen.dynamicadmin.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTUser {

    private Long id;

    private String username;

    private List<String> authorities;

}
