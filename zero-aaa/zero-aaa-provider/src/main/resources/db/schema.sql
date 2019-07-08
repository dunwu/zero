DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
  id VARCHAR(32) NOT NULL COMMENT 'ID',
  name VARCHAR(32) NOT NULL COMMENT '姓名',
  birthday DATE DEFAULT NULL COMMENT '生日',
  sex INT(1) NOT NULL COMMENT '性别',
  email VARCHAR(100) DEFAULT '' COMMENT '邮箱',
  mobile VARCHAR(20) DEFAULT '' COMMENT '手机号',
  profession VARCHAR(32) DEFAULT '' COMMENT '职业',
  province VARCHAR(10) DEFAULT '' COMMENT '省',
  city VARCHAR(10) DEFAULT '' COMMENT '市',
  district VARCHAR(10) DEFAULT '' COMMENT '区',
  deleted INT(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  create_user VARCHAR(32) DEFAULT '' COMMENT '创建者',
  update_user VARCHAR(32) DEFAULT '' COMMENT '更新者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_email (email),
  UNIQUE KEY uk_mobile (mobile),
  KEY k_name (name)
) ENGINE=INNODB COMMENT '用户信息表';

DROP TABLE IF EXISTS login_info;
CREATE TABLE login_info (
  id VARCHAR(32) NOT NULL COMMENT 'ID',
  user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
  nickname VARCHAR(32) DEFAULT '' COMMENT '昵称',
  loginname VARCHAR(32) NOT NULL COMMENT '登录名',
  password VARCHAR(32) NOT NULL COMMENT '密码',
  email VARCHAR(100) DEFAULT '' COMMENT '邮箱',
  mobile VARCHAR(20) DEFAULT '' COMMENT '手机号',
  last_login_ip VARCHAR(32) NOT NULL COMMENT '上一次登录IP',
  last_login_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上一次上线时间',
  last_logout_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上一次离线时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_id (user_id),
  UNIQUE KEY uk_email (email),
  UNIQUE KEY uk_mobile (mobile),
  UNIQUE KEY uk_loginname (loginname)
) ENGINE=INNODB COMMENT '登录信息表';
