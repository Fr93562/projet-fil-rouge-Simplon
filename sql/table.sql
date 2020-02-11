DROP TABLE IF EXISTS user ;
CREATE TABLE user (id_user BIGINT AUTO_INCREMENT NOT NULL,
username_user VARCHAR(32),
email_user VARCHAR(255),
password_user VARCHAR(45),
date_inscription DATE,
ranking_id_ranking BIGINT,
id_type_user BIGINT,
PRIMARY KEY (id_user)) ENGINE=InnoDB;

DROP TABLE IF EXISTS type_user ;
CREATE TABLE type_user (id_type_user BIGINT AUTO_INCREMENT NOT NULL,
type_type_user VARCHAR(15),
PRIMARY KEY (id_type_user)) ENGINE=InnoDB;

DROP TABLE IF EXISTS faq ;
CREATE TABLE faq (id_faq BIGINT AUTO_INCREMENT NOT NULL,
question_faq VARCHAR(255),
response_faq VARCHAR(255),
priority_faq VARCHAR(255),
PRIMARY KEY (id_faq)) ENGINE=InnoDB;

DROP TABLE IF EXISTS ranking ;
CREATE TABLE ranking (id_ranking BIGINT AUTO_INCREMENT NOT NULL,
point_ranking BIGINT,
PRIMARY KEY (id_ranking)) ENGINE=InnoDB;

DROP TABLE IF EXISTS question ;
CREATE TABLE question (id_question BIGINT AUTO_INCREMENT NOT NULL,
question_question VARCHAR(255),
level_question VARCHAR(45),
answer_question VARCHAR(255),
choice1_question VARCHAR(255),
choice2_question VARCHAR(255),
choice3_question VARCHAR(255),
id_ressource BIGINT,
id_langage BIGINT,
id_categorie BIGINT,
PRIMARY KEY (id_question)) ENGINE=InnoDB;

DROP TABLE IF EXISTS ressource ;
CREATE TABLE ressource (id_ressource BIGINT AUTO_INCREMENT NOT NULL,
text_ressource TEXT,
link_ressource VARCHAR(255),
PRIMARY KEY (id_ressource)) ENGINE=InnoDB;

DROP TABLE IF EXISTS categorie ;
CREATE TABLE categorie (id_categorie BIGINT AUTO_INCREMENT NOT NULL,
type_categorie VARCHAR(30),
PRIMARY KEY (id_categorie)) ENGINE=InnoDB;

DROP TABLE IF EXISTS langage ;
CREATE TABLE langage (id_langage BIGINT AUTO_INCREMENT NOT NULL,
langage_langage VARCHAR(30),
PRIMARY KEY (id_langage)) ENGINE=InnoDB;

DROP TABLE IF EXISTS user_langage ;
CREATE TABLE user_langage (id_user BIGINT NOT NULL,
id_langage BIGINT NOT NULL,
PRIMARY KEY (id_user,
 id_langage)) ENGINE=InnoDB;

ALTER TABLE user ADD CONSTRAINT FK_user_ranking_id_ranking FOREIGN KEY (ranking_id_ranking) REFERENCES ranking (id_ranking);

ALTER TABLE user ADD CONSTRAINT FK_user_id_type_user FOREIGN KEY (id_type_user) REFERENCES type_user (id_type_user);
ALTER TABLE question ADD CONSTRAINT FK_question_id_ressource FOREIGN KEY (id_ressource) REFERENCES ressource (id_ressource);
ALTER TABLE question ADD CONSTRAINT FK_question_id_langage FOREIGN KEY (id_langage) REFERENCES langage (id_langage);
ALTER TABLE question ADD CONSTRAINT FK_question_id_categorie FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie);
ALTER TABLE user_langage ADD CONSTRAINT FK_user_langage_id_user FOREIGN KEY (id_user) REFERENCES user (id_user);
ALTER TABLE user_langage ADD CONSTRAINT FK_user_langage_id_langage FOREIGN KEY (id_langage) REFERENCES langage (id_langage);
