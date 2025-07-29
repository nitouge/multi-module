CREATE TABLE `user`
(
    `id`       int(11)                         NOT NULL AUTO_INCREMENT,
    `username` varchar(40) CHARACTER SET utf8  NOT NULL,
    `password` varchar(255) CHARACTER SET utf8 NOT NULL,
    `age`      int(4)                          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4