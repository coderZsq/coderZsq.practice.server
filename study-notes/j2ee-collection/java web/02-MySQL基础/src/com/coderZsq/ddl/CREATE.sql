DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
    `id` BIGINT(20),
    `name` VARCHAR(20),
    `email` VARCHAR(40),
    `age` INT(11)
);

CREATE TABLE t_student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE,
    email VARCHAR(40) NOT NULL,
    age DEFAULT 20
);