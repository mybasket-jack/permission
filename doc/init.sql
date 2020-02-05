-- 用户表
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(20)  NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int NOT NULL DEFAULT 0 COMMENT '上级部门ID',
  `level` varchar(200)  NOT NULL DEFAULT '' COMMENT '层级',
  `remark` varchar(200)  NULL DEFAULT '' COMMENT '备注',
  `seq` int NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序，由小到大',
  `operator` varchar(20)  NOT NULL DEFAULT '' COMMENT '操作者',
  `operator_time` datetime(0) NOT NULL DEFAULT now() COMMENT '最后一次操作时间',
  `operator_ip` varchar(20)  NOT NULL DEFAULT '' COMMENT '最后一次更新操作操作者IP',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- 用户表
drop table if EXISTS `sys_user`;
create table `sys_user` (
	`id` int not null auto_increment comment '用户ID',
	`username` varchar(20) not null default '' comment '用户名称',
	`telephpne` varchar(13) not null default '' comment '手机号',
	`mail` varchar(20) not null default '' comment '邮箱',
	`password` varchar(40) not null default '' comment '加密后的密码',
	`remark` varchar(200) not null default '' comment '备注',
  `dept_id` int not null default 0 comment '用户所在部门ID',
	`status` int not null default 1 comment '状态 1：正常 0：冻结 2：删除',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后一次更新者的IP',
	primary key(`id`)
)engine= innodb character set =utf8 collate = utf8_general_ci;

-- 权限模块表
drop table if exists `sys_acl_module`;
create table `sys_acl_module` (
	`id` int not null auto_increment comment '权限模块ID',
	`name` varchar(20) not null default '' comment '权限模块名称',
	`parent_id` int not null default 0 comment '上一级权限ID',
	`level` varchar(20) not null default '' comment '权限模块层级',
	`status` int not null default 1 comment '状态 1：正常 0：冻结',
	`seq` int not null  default 0 comment '权限模块在当前层级下的顺序，由小到大',
	`remark` varchar(200) not null default '' comment '备注',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

-- 权限表
drop table if exists `sys_acl`;
create table `sys_acl` (
	`id` int not null auto_increment comment '权限ID',
	`code` varchar(20) not null default '' comment '权限码',
	`name` varchar(20) not null default '' comment '权限名称',
	`acl_module_id` int not null default 0 comment '权限所在模块的ID',
	`url` varchar(20) not null default '' comment '请求的URL, 可以填正则表达式',
	`type` int not null default 3 comment '状态 1：菜单 2：按钮 3：其他',
	`status` int not null default 1 comment '状态 1：正常 0：冻结',
	`seq` int not null  default 0 comment '权限在当前模块下的顺序，由小到大',
	`remark` varchar(200) not null default '' comment '备注',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

-- 角色表
drop table if exists `sys_role`;
create table `sys_role` (
	`id` int not null auto_increment comment '角色ID',
	`name` varchar(20) not null default '' comment '角色名称',
	`type` int not null default 1 comment '角色类型 1：管理员 2：其他',
	`status` int not null default 1 comment '状态 1：正常 0：冻结',
	`remark` varchar(200) not null default '' comment '备注',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

-- 角色用户关联表
drop table if exists `sys_role_user`;
create table `sys_role_user` (
	`id` int not null auto_increment comment '主键ID',
	`role_id` int(20) not null default '' comment '角色ID',
	`user_id` int not null default 1 comment '用户ID',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

// 角色权限表
drop table if exists `sys_role_acl`;
create table `sys_role_acl` (
	`id` int not null auto_increment comment '主键ID',
	`role_id` int not null default '' comment '角色ID',
	`acl_id` int not null default 1 comment '权限ID',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

-- 	权限相关更新记录表
drop table if exists `sys_log`;
create table `sys_log` (
	`id` int not null auto_increment comment '主键ID',
	`type` int not null default 0 comment '权限更新的类型 1：类型 2：用户 3：权限模块 4： 权限 5： 角色 6： 角色用户关系 7： 角色权限关系',
	`target_id` int not null default 1 comment '基于type后指定表的对象ID',
	`old_value` text not null default '' comment '修改之前的值',
	`new_value` text not null default '' comment '更新后的值 ',
	`operator` varchar(20) not null default '' comment '操作者',
	`operator_time` datetime not null default now() comment '最后一次更新操作时间',
	`operator_ip` varchar(20) not null default '' comment '最后更新操作者的IP',
	`status` int not null default 0 comment '当前是否复原过 1：复原过 0：没有',
	primary key(id)
)engine=innodb character set=utf8 collate = utf8_general_ci;

-- 权限xiang
