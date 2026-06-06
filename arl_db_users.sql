

USE `arl_db`;

-- Safety net: create the table if the app hasn't built it yet.
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Seed login account.
--   username: admin
--   password: admin123   (stored as a BCrypt hash, NOT plain text)
INSERT INTO `users` (`username`, `password`)
VALUES ('admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2')
ON DUPLICATE KEY UPDATE `password` = VALUES(`password`);
