/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/11/27 19:45:15                          */
/*==============================================================*/


drop table if exists auth_login;

drop table if exists balance_detail;

drop table if exists coach;

drop table if exists coach_exam_place;

drop table if exists dict_item;

drop table if exists dict_type;

drop table if exists exam_place;

drop table if exists exam_place_course;

drop table if exists exam_place_course_order;

drop table if exists image;

drop table if exists plate_region;

drop table if exists school;

drop table if exists student;

drop table if exists user;

drop table if exists withdraw;

/*==============================================================*/
/* Table: auth_login                                            */
/*==============================================================*/
create table auth_login
(
   id                   bigint not null auto_increment,
   identifier           varchar(100) not null comment '第三方平台的唯一标志',
   access_token         varchar(100) not null comment '访问令牌',
   type                 varchar(15) not null comment '登录类型，比如weixin、qq、weibo等',
   expired_time         datetime comment '过期时间',
   user_id              bigint not null,
   primary key (id)
);

alter table auth_login comment '第三方授权登录';

/*==============================================================*/
/* Table: balance_detail                                        */
/*==============================================================*/
create table balance_detail
(
   id                   bigint not null auto_increment,
   created_time         datetime not null default CURRENT_TIMESTAMP,
   user_id              bigint not null,
   detail               decimal(10,2) not null comment '变化',
   info                 varchar(100) comment '描述说明',
   balance              decimal(10,2) not null comment '余额',
   primary key (id)
);

alter table balance_detail comment '余额';

/*==============================================================*/
/* Table: coach                                                 */
/*==============================================================*/
create table coach
(
   id                   bigint not null auto_increment,
   seller_level         int comment '分销员等级',
   primary key (id)
);

alter table coach comment '教练';

/*==============================================================*/
/* Table: coach_exam_place                                      */
/*==============================================================*/
create table coach_exam_place
(
   coach_id             bigint not null,
   place_id             bigint not null,
   primary key (coach_id, place_id)
);

alter table coach_exam_place comment '教练_考场';

/*==============================================================*/
/* Table: dict_item                                             */
/*==============================================================*/
create table dict_item
(
   id                   bigint not null auto_increment,
   name                 varchar(15) not null,
   value                varchar(15) not null,
   sn                   int not null default 0,
   type_id              bigint not null,
   enabled              int not null default 1,
   primary key (id),
   unique key AK_uk_value (value, type_id),
   unique key AK_uk_name (name, type_id)
);

alter table dict_item comment '数据字典条目';

/*==============================================================*/
/* Table: dict_type                                             */
/*==============================================================*/
create table dict_type
(
   id                   bigint not null auto_increment,
   name                 varchar(15) not null,
   value                varchar(15) not null,
   intro                varchar(100),
   primary key (id),
   unique key AK_uk_name (name),
   unique key AK_uk_value (value)
);

alter table dict_type comment '数据字典类型';

/*==============================================================*/
/* Table: exam_place                                            */
/*==============================================================*/
create table exam_place
(
   id                   int not null auto_increment,
   name                 varchar(15) not null,
   province_id          bigint not null comment '考场是哪个城市的',
   city_id              bigint not null comment '考场是哪个城市的',
   address              varchar(100) comment '考场的具体地址',
   longitude            decimal(10,7) comment '经度',
   latitude             decimal(10,7) comment '纬度',
   primary key (id)
);

alter table exam_place comment '考场';

/*==============================================================*/
/* Table: exam_place_course                                     */
/*==============================================================*/
create table exam_place_course
(
   id                   bigint not null auto_increment,
   created_time         datetime not null default CURRENT_TIMESTAMP,
   name                 varchar(50) not null,
   price                decimal(10,2) not null,
   type                 int not null comment '课程类型：0是课程合集，2是科目2，3是科目3',
   intro                varchar(100),
   video                varchar(100) not null comment '视频',
   cover                varchar(100) comment '封面',
   place_id             bigint not null,
   primary key (id)
);

alter table exam_place_course comment '考场课程';

/*==============================================================*/
/* Table: exam_place_course_order                               */
/*==============================================================*/
create table exam_place_course_order
(
   id                   bigint not null auto_increment,
   created_time         datetime not null default CURRENT_TIMESTAMP,
   sum                  decimal(10,2) not null,
   course_id            bigint not null,
   student_id           bigint,
   primary key (id)
);

alter table exam_place_course_order comment '考场课程购买订单';

/*==============================================================*/
/* Table: image                                                 */
/*==============================================================*/
create table image
(
   id                   bigint not null auto_increment,
   uri                  varchar(100) not null,
   owner_id             bigint not null comment '拥有者的id',
   owner_type           int not null comment '拥有者的类型，比如考场、驾校',
   primary key (id)
);

alter table image comment '图片';

/*==============================================================*/
/* Table: plate_region                                          */
/*==============================================================*/
create table plate_region
(
   id                   bigint not null auto_increment,
   name                 varchar(15) not null,
   parent_id            bigint,
   plate                varchar(5) not null comment '车牌，比如省份是粤、琼、赣，城市是A、B、C、D',
   pinyin               varchar(50) not null,
   primary key (id),
   unique key AK_uk_name (parent_id, name)
);

alter table plate_region comment '省份、城市';

/*==============================================================*/
/* Table: school                                                */
/*==============================================================*/
create table school
(
   id                   bigint not null auto_increment,
   name                 varchar(25) not null,
   address              varchar(100) comment '驾校的具体地址',
   longitude            decimal(10,7) comment '经度',
   latitude             decimal(10,7) comment '纬度',
   province_id          bigint not null comment '考场是哪个城市的',
   city_id              bigint not null,
   intro                varchar(100),
   primary key (id)
);

alter table school comment '驾校';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   bigint not null,
   primary key (id)
);

alter table student comment '学员';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment,
   created_time         datetime not null default CURRENT_TIMESTAMP comment '注册时间',
   created_ip           varchar(50) not null comment '注册时的IP',
   phone                varchar(15) not null default '' comment '手机',
   password             varchar(15) not null default '' comment '密码',
   login_time           datetime not null default CURRENT_TIMESTAMP comment '最后一次登录的时间',
   login_ip             varchar(50) not null comment '最后一次登录的IP',
   login_times          int not null default 1 comment '登录次数',
   status               int not null default 0 comment '用户状态：0是正常，1是冻结',
   type                 int not null comment '用户类型：0是学员，1是教练',
   inviter_id           bigint comment '注册邀请人',
   invite_code          varchar(50) comment '注册邀请码',
   balance              decimal(10,2) not null default 0 comment '余额',
   nick_name            varchar(15) not null comment '昵称',
   intro                varchar(100) comment '个人简介',
   province_id          bigint,
   city_id              bigint,
   school_id            bigint,
   avatar               varchar(100),
   primary key (id),
   unique key AK_uk_phone (phone),
   unique key AK_uk_seller_code (invite_code)
);

alter table user comment '可以登录的用户（教练、学员）';

/*==============================================================*/
/* Table: withdraw                                              */
/*==============================================================*/
create table withdraw
(
   id                   bigint not null auto_increment,
   created_time         datetime not null default CURRENT_TIMESTAMP comment '申请提现的时间',
   end_time             datetime comment '提现的结束时间',
   sum                  decimal(10,2) not null comment '提现金额',
   status               int not null comment '提现状态：0是申请中，1是成功，2是失败',
   info                 varchar(100) comment '提现的描述，比如提现失败的原因',
   type                 int not null comment '提现方式：0是银行卡，0是微信，1是支付宝',
   account              varchar(50) not null comment '账户信息（姓名、账号、支行）',
   user_id              bigint not null,
   primary key (id)
);

alter table withdraw comment '提现';

alter table auth_login add constraint FK_Reference_2 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table balance_detail add constraint FK_Reference_15 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table coach add constraint FK_Reference_17 foreign key (id)
      references user (id) on delete restrict on update restrict;

alter table coach_exam_place add constraint FK_Reference_22 foreign key (coach_id)
      references coach (id) on delete restrict on update restrict;

alter table coach_exam_place add constraint FK_Reference_23 foreign key (place_id)
      references exam_place (id) on delete restrict on update restrict;

alter table dict_item add constraint FK_Reference_16 foreign key (type_id)
      references dict_type (id) on delete restrict on update restrict;

alter table exam_place add constraint FK_Reference_25 foreign key (city_id)
      references plate_region (id) on delete restrict on update restrict;

alter table exam_place add constraint FK_Reference_5 foreign key (province_id)
      references plate_region (id) on delete restrict on update restrict;

alter table exam_place_course add constraint FK_Reference_8 foreign key (place_id)
      references exam_place (id) on delete restrict on update restrict;

alter table exam_place_course_order add constraint FK_Reference_12 foreign key (course_id)
      references exam_place_course (id) on delete restrict on update restrict;

alter table exam_place_course_order add constraint FK_Reference_13 foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table plate_region add constraint FK_Reference_6 foreign key (parent_id)
      references plate_region (id) on delete restrict on update restrict;

alter table school add constraint FK_Reference_19 foreign key (province_id)
      references plate_region (id) on delete restrict on update restrict;

alter table school add constraint FK_Reference_24 foreign key (city_id)
      references plate_region (id) on delete restrict on update restrict;

alter table student add constraint FK_Reference_18 foreign key (id)
      references user (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_20 foreign key (school_id)
      references school (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_21 foreign key (province_id)
      references plate_region (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_26 foreign key (city_id)
      references plate_region (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_7 foreign key (inviter_id)
      references user (id) on delete restrict on update restrict;

alter table withdraw add constraint FK_Reference_14 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

