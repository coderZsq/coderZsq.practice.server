DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`       smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`    varchar(30)       NOT NULL DEFAULT '' COMMENT '标题',
    `type`     varchar(10)       NOT NULL DEFAULT '' COMMENT '类型',
    `words`    smallint unsigned NOT NULL DEFAULT '0' COMMENT '字数',
    `duration` smallint unsigned NOT NULL DEFAULT '0' COMMENT '阅读时长',
    `date`     datetime COMMENT '日期',
    `content`  text NOT NULL COMMENT '正文',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  COMMENT ='文章';

-- Cause: java.sql.SQLException: Incorrect string value: '\xF0\x9F\x8C\x9F \xE9...' for column 'content' at row 1
-- jdbc:mysql://localhost:3306/recorder?serverTimezone=GMT%2B8&characterEncoding=UTF-8

-- Cause: Out of sort memory, consider increasing server sort buffer size, Time: 0.008000s
# SHOW VARIABLES LIKE '%sort_buffer_size%';
# SET GLOBAL sort_buffer_size = 1024*1024;
# SET sort_buffer_size = 1024*1024;
# SELECT * FROM article ORDER BY date DESC

