/*
用户表
 */
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	username VARCHAR(30) NOT NULL UNIQUE COMMENT '用户名',
	password VARCHAR(255) NOT NULL COMMENT '密码',
    account_non_expired bit(1) NOT NULL COMMENT '账户是否过期,过期无法验证',
    account_non_locked bit(1) NOT NULL COMMENT '指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证',
    credentials_non_expired bit(1) NOT NULL COMMENT '指示是否已过期的用户的凭据(密码),过期的凭据防止认证',
    enabled bit(1) NOT NULL COMMENT '是否被禁用,禁用的用户不能身份验证',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    nickname VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	sex int(11) NULL DEFAULT NULL COMMENT '性别',
    birthday DATE NULL DEFAULT NULL COMMENT '生日',
    avatar VARCHAR(255) NULL DEFAULT NULL COMMENT '头像',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	phone_number VARCHAR(11) NULL DEFAULT NULL COMMENT '手机号码',
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
	name VARCHAR(30) NOT NULL COMMENT '名称',
	identification VARCHAR(30) NOT NULL UNIQUE COMMENT '标识名称',
    description VARCHAR(255) COMMENT '描述',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
菜单表
 */
DROP TABLE IF EXISTS sys_menu;

CREATE TABLE sys_menu
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '菜单名称',
    path VARCHAR(255) COMMENT '菜单路由',
    pid BIGINT(20) NULL DEFAULT NULL COMMENT '父级ID',
	component VARCHAR(255) COMMENT '前端组件',
	redirect VARCHAR(255) COMMENT '重定向路径',
	title VARCHAR(255) COMMENT '显示名称',
	icon VARCHAR(255) COMMENT '图标',
    priority INT(11) NULL DEFAULT NULL COMMENT '排序等级',
    hidden bit(1) NOT NULL DEFAULT 0 COMMENT '菜单是否隐藏',
    always_show bit(1) NOT NULL DEFAULT 0 COMMENT '菜单是否总是显示',
    no_cache bit(1) NOT NULL DEFAULT 0 COMMENT '是否不打开缓存',
    affix bit(1) NOT NULL DEFAULT 0 COMMENT '是否打开固钉',
    breadcrumb bit(1) NOT NULL DEFAULT 1 COMMENT '是否打开面包屑',
    props VARCHAR(255) COMMENT '参数',
    description VARCHAR(255) COMMENT '菜单描述',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
资源表
 */
DROP TABLE IF EXISTS sys_resource;

CREATE TABLE sys_resource
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	name VARCHAR(30) NOT NULL COMMENT '资源名称',
    path VARCHAR(255) COMMENT '资源路由',
    type INT(11) NOT NULL COMMENT '资源类型',
	description VARCHAR(255) COMMENT '资源描述',
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
角色-菜单关系表
 */
DROP TABLE IF EXISTS sys_role_menu;

CREATE TABLE sys_role_menu
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
	menu_id BIGINT(20) NOT NULL COMMENT '权限ID',
	create_by BIGINT(20) NULL DEFAULT NULL COMMENT '创建人',
    created_date datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    last_modified_by BIGINT(20) NULL DEFAULT NULL COMMENT '修改人',
    last_modified_date datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (id)
);

/*
角色-资源关系表
 */
DROP TABLE IF EXISTS sys_role_resource;

CREATE TABLE sys_role_resource
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
	resource_id BIGINT(20) NOT NULL COMMENT '权限ID',
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