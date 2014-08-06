
CREATE TABLE email (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  createdOn datetime NOT NULL,
  unsubscribed bit(1) DEFAULT b'0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pot (
  id int NOT NULL AUTO_INCREMENT,
  type varchar(45) NOT NULL,
  brewedOn date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
