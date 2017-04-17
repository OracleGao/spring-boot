CREATE TABLE `t_message` (
  `m_id` INT NOT NULL AUTO_INCREMENT,
  `from_user_name` VARCHAR(128) NULL,
  `to_user_name` VARCHAR(128) NULL,
  `content` VARCHAR(2000) NULL,
  PRIMARY KEY (`m_id`));