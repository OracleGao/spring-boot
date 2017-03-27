CREATE TABLE `t_message` (
  `m_id` INT NOT NULL AUTO_INCREMENT,
  `from_user_name` VARCHAR(128) NULL,
  `to_user_name` VARCHAR(128) NULL,
  `content` VARCHAR(2000) NULL,
  PRIMARY KEY (`m_id`));

  CREATE TABLE `t_user` (
  `u_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NULL,
  `date` DATETIME NULL DEFAULT 'CURRENT_TIMESTAMP',
  PRIMARY KEY (`u_id`));