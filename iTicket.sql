CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `creation_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ticket_id` (`ticket_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `summary` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `priority` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `creationDate` datetime NOT NULL,
  `product_owner_id` int(11) NOT NULL,
  `developer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_owner_id` (`product_owner_id`),
  KEY `developer_id` (`developer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `dateOfBirth` datetime NOT NULL,
  `DTYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

INSERT INTO `user` (`id`, `username`, `password`, `firstName`, `lastName`, `email`, `dateOfBirth`, `DTYPE`) VALUES
(1, 'NO_DEVELOPER', 'NO_DEVELOPER', 'NO_DEVELOPER', 'NO_DEVELOPER', 'NO_DEVELOPER@localhost', '2014-09-16 00:00:00', 'DEVELOPER');

ALTER TABLE `comment`
  ADD CONSTRAINT `ticketComment` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userComment` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticketDeveloper` FOREIGN KEY (`developer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ticketProductOwner` FOREIGN KEY (`product_owner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
