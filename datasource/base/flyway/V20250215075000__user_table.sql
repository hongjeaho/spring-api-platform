create table user
(
    seq          BIGINT                              NOT NULL AUTO_INCREMENT COMMENT '사용자 일련번호',
    email        VARCHAR(100)                        NOT NULL COMMENT '사용자 이메일',
    name         VARCHAR(30)                         NOT NULL COMMENT '사용자명',
    id           VARCHAR(30)                         NOT NULL COMMENT '사용자 아이디',
    password     VARCHAR(100)                        NOT NULL COMMENT '비밀번호',
    created_by   BIGINT                              NOT NULL COMMENT '생성자',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일',
    updated_by   BIGINT                              NOT NULL COMMENT '수정자',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정일',
    PRIMARY KEY (seq),
    UNIQUE KEY UK_USER_ID (id),
    UNIQUE KEY UK_USER_EMAIL (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table user_role
(
    seq          BIGINT                              NOT NULL AUTO_INCREMENT COMMENT 'Role 일련번호',
    name         VARCHAR(100)                        NOT NULL COMMENT 'Role명',
    created_by   BIGINT                              NOT NULL COMMENT '생성자',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일',
    updated_by   BIGINT                              NOT NULL COMMENT '수정자',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정일',
    PRIMARY KEY (seq),
    UNIQUE KEY UK_GROUP_NAME (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table user_role_mapping
(
  seq          BIGINT                              NOT NULL AUTO_INCREMENT COMMENT '사용자 Role 일련번호',
  user_seq     BIGINT                              NOT NULL COMMENT '사용자 일련번호',
  role_seq     BIGINT                              NOT NULL COMMENT '사용자 일련번호',
  created_by   BIGINT                              NOT NULL COMMENT '생성자',
  created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일',

  PRIMARY KEY (seq),
  UNIQUE KEY UK_ROLE_MAPPING_SEQ (user_seq, role_seq),
  FOREIGN KEY (user_seq) REFERENCES user (seq),
  FOREIGN KEY (role_seq) REFERENCES user_role (seq)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

# password is 12345
insert into user (email, name, id, password, created_by, updated_by)
values ('admin@gmail.com', '어드민', 'admin',
        '$2a$10$zUBEUh5T1ehLA98ly2n2NeiEevUhyK1axz.2evM.5uyP8cvTWKNay', 1, 1);

insert into user_role (name, created_by, updated_by)
values ('IMPLEMENTER', 1, 1);
insert into user_role (name, created_by, updated_by)
values ('DECISION', 1, 1);

insert into user_role_mapping (user_seq, role_seq, created_by)
values (1, 1, 1);
insert into user_role_mapping (user_seq, role_seq, created_by)
values (1, 2, 1);
