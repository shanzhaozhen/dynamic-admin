/*
用户表
 */
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	username VARCHAR(30) NOT NULL COMMENT '用户名',
	password VARCHAR(255) NOT NULL COMMENT '密码',
    account_non_expired bit(1) NOT NULL COMMENT '账户是否过期,过期无法验证',
    account_non_locked bit(1) NOT NULL COMMENT '指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证',
    credentials_non_expired bit(1) NOT NULL COMMENT '指示是否已过期的用户的凭据(密码),过期的凭据防止认证',
    enabled bit(1) NOT NULL COMMENT '是否被禁用,禁用的用户不能身份验证',
	sex int(11) NULL DEFAULT NULL COMMENT '性别',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    nickname VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
    birthday datetime(0) NULL DEFAULT NULL COMMENT '生日',
    head_img_url varchar(255) NULL DEFAULT NULL COMMENT '头像',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	phoneNumber VARCHAR(11) NULL DEFAULT NULL COMMENT '手机号码',
	address VARCHAR(255) NULL DEFAULT NULL COMMENT '地址',
	introduction VARCHAR(255) NULL DEFAULT NULL COMMENT '个人介绍',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);


/*
角色表
 */
DROP TABLE IF EXISTS sys_role;

CREATE TABLE sys_role
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '角色名',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
权限表
 */
DROP TABLE IF EXISTS sys_permission;

CREATE TABLE sys_permission
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '权限名称',
	permission_name VARCHAR(30) NOT NULL COMMENT '权限标识',
	description VARCHAR(255) NOT NULL COMMENT '权限描述',
	type INT(11) NOT NULL COMMENT '权限类型',
	url VARCHAR(255) NOT NULL COMMENT '权限路由',
    pid BIGINT(20) NOT NULL COMMENT '父级ID',
	icon VARCHAR(255) NOT NULL COMMENT '图标',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
用户-角色关系表
 */
DROP TABLE IF EXISTS sys_user_role;

CREATE TABLE sys_user_role
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	user_id BIGINT(20) NOT NULL COMMENT '用户ID',
	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
角色-权限关系表
 */
DROP TABLE IF EXISTS sys_role_permission;

CREATE TABLE sys_role_permission
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
	permission_id BIGINT(20) NOT NULL COMMENT '权限ID',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
部门表
 */
DROP TABLE IF EXISTS sys_department;

CREATE TABLE sys_department
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '部门名称',
    pid BIGINT(20) NOT NULL COMMENT '父级ID',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);


/*
字典表
 */
DROP TABLE IF EXISTS sys_dictionary;

CREATE TABLE sys_dictionary
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '名称',
    pid BIGINT(20) NOT NULL COMMENT '父级ID',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);