# drop
DROP TABLE IF EXISTS bank_card;
DROP TABLE IF EXISTS id_card;
DROP TABLE IF EXISTS person_job;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS job;

# person
CREATE TABLE person(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL
);

# bank_card
CREATE TABLE bank_card(
	id INT PRIMARY KEY AUTO_INCREMENT,
	no VARCHAR(30) NOT NULL UNIQUE,
	amout DECIMAL(18, 2) NOT NULL,
	person_id INT NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person(id)
);

# id_card
CREATE TABLE id_card(
	id INT PRIMARY KEY AUTO_INCREMENT,
	no VARCHAR(30) NOT NULL UNIQUE,
	address VARCHAR(50) NOT NULL,
	person_id INT NOT NULL UNIQUE,
	FOREIGN KEY (person_id) REFERENCES person(id)
);

# job
CREATE TABLE job(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL UNIQUE,
	duty VARCHAR(50) NOT NULL
);

# person_job
CREATE TABLE person_job(
	person_id INT,
	job_id INT,
	PRIMARY KEY (person_id, job_id),
	FOREIGN KEY (person_id) REFERENCES person(id),
	FOREIGN KEY (job_id) REFERENCES job(id)
);

# data
INSERT INTO person(name) VALUES ('Jack'), ('Rose'), ('Larry'), ('Mike'), ('Tom'), ('James');

INSERT INTO id_card(no, address, person_id) VALUES 
('9527', '北京', 4),
('8866', '广州', 1),
('2495', '上海', 5),
('4378', '成都', 2),
('5454', '杭州', 6),
('9923', '深圳', 3);

INSERT INTO bank_card(no, amout, person_id) VALUES 
('6223', 0, 1),
('75556', 2098.56, 2),
('5345', 1010000.56, 1),
('87876', 534423.34, 3),
('654645', 432.45, 1),
('5434534', 234765.19, 4),
('76853', 98945.39, 4),
('6456867', 435534.78, 1),
('4324654', 874343.99, 4),
('53455', 5.20, 2);

INSERT INTO job(name, duty) VALUES 
('程序员', '每一天都在写新的bug和修改昨天的bug'),
('保安', '公司全系统物理安全保障专员'),
('网管', '世界互联网信息终端及人类信息科技部信息集成应用导师'),
('厨师', '类口腔神经末梢感应实验中心及绿色环保邮寄肥转换加工基地负责人'),
('贴膜', '智能高端移动设备表面高化合物平面处理'),
('搬砖', '长方体混泥土瞬间移动师'),
('算命', '主观性逻辑推论及心理引导'),
('理发师', '人体无用副组织切除手术主刀');

INSERT INTO person_job(person_id, job_id) VALUES 
(1, 1),
(1, 3),
(1, 5),
(1, 7),
(2, 5),
(3, 1),
(3, 2),
(5, 3),
(5, 5),
(5, 7);
