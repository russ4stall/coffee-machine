CREATE TABLE logemail (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  log varchar(255) NOT NULL,
  createdOn datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;