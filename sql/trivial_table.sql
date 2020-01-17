DROP TABLE IF EXISTS user ;
CREATE TABLE user (id_user INT AUTO_INCREMENT NOT NULL,
username_user VARCHAR(32) NOT NULL,
email_user VARCHAR(255) NOT NULL,
password_user VARCHAR(45) NOT NULL,
date_inscription DATE,
langage_favori_user VARCHAR(255),
ranking_id_ranking INT,
id_type_user INT,
PRIMARY KEY (id_user)) ENGINE=InnoDB;

DROP TABLE IF EXISTS type_user ;
CREATE TABLE type_user (id_type_user INT AUTO_INCREMENT NOT NULL,
type_type_user VARCHAR(32) NOT NULL,
PRIMARY KEY (id_type_user)) ENGINE=InnoDB;

DROP TABLE IF EXISTS faq ;
CREATE TABLE faq (id_faq INT AUTO_INCREMENT NOT NULL,
question_faq VARCHAR(255) NOT NULL,
response_faq VARCHAR(255) NOT NULL,
priority_faq VARCHAR(255) NOT NULL,
PRIMARY KEY (id_faq)) ENGINE=InnoDB;

DROP TABLE IF EXISTS ranking ;
CREATE TABLE ranking (id_ranking INT AUTO_INCREMENT NOT NULL,
point_ranking INT,
PRIMARY KEY (id_ranking)) ENGINE=InnoDB;

DROP TABLE IF EXISTS question ;
CREATE TABLE question (id_question INT AUTO_INCREMENT NOT NULL,
question_question VARCHAR(255) NOT NULL,
type_question VARCHAR(45) NOT NULL,
level_question VARCHAR(45) NOT NULL,
answer_question VARCHAR(255) NOT NULL,
id_ressource INT,
PRIMARY KEY (id_question)) ENGINE=InnoDB;

DROP TABLE IF EXISTS ressource ;
CREATE TABLE ressource (id_ressource INT AUTO_INCREMENT NOT NULL,
text_ressource TEXT,
link_ressource VARCHAR(255),
PRIMARY KEY (id_ressource)) ENGINE=InnoDB;

ALTER TABLE user ADD CONSTRAINT FK_user_ranking_id_ranking FOREIGN KEY (ranking_id_ranking) REFERENCES ranking (id_ranking);
ALTER TABLE user ADD CONSTRAINT FK_user_id_type_user FOREIGN KEY (id_type_user) REFERENCES type_user (id_type_user);
ALTER TABLE question ADD CONSTRAINT FK_question_id_ressource FOREIGN KEY (id_ressource) REFERENCES ressource (id_ressource);