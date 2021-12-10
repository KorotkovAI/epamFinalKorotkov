
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cashregister
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cashregister` ;

-- -----------------------------------------------------
-- Schema cashregister
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cashregister` DEFAULT CHARACTER SET utf8 ;
USE `cashregister` ;

-- -----------------------------------------------------
-- Table `cashregister`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`Roles` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`Roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `cashregister`.`Roles` (`name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cashregister`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`Users` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`Users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Users_Roles1`
    FOREIGN KEY (`roleName`)
    REFERENCES `cashregister`.`Roles` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `cashregister`.`Users` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `login_UNIQUE` ON `cashregister`.`Users` (`login` ASC) VISIBLE;

CREATE INDEX `fk_Users_Roles1_idx` ON `cashregister`.`Users` (`roleName` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cashregister`.`Goods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`Goods` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`Goods` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(90) NOT NULL,
  `amount` INT UNSIGNED NOT NULL,
  `price` DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `cashregister`.`Goods` (`name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cashregister`.`Shifts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`Shifts` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`Shifts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `isOpen` TINYINT(1) NOT NULL,
  `openTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `closeTime` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashregister`.`Checks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`Checks` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`Checks` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CheckSum` DOUBLE UNSIGNED NOT NULL,
  `CheckTime` TIMESTAMP NOT NULL,
  `isReturned` TINYINT(1) NOT NULL,
  `Shifts_id` INT UNSIGNED NOT NULL,
  `Users_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `Shifts_id`, `Users_id`),
  CONSTRAINT `fk_Checks_Shifts1`
    FOREIGN KEY (`Shifts_id`)
    REFERENCES `cashregister`.`Shifts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Checks_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `cashregister`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Checks_Shifts1_idx` ON `cashregister`.`Checks` (`Shifts_id` ASC) VISIBLE;

CREATE INDEX `fk_Checks_Users1_idx` ON `cashregister`.`Checks` (`Users_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cashregister`.`CheckGoods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cashregister`.`CheckGoods` ;

CREATE TABLE IF NOT EXISTS `cashregister`.`CheckGoods` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idGoods` INT UNSIGNED NOT NULL,
  `nameGoods` VARCHAR(45) NOT NULL,
  `amountGoods` INT UNSIGNED NOT NULL,
  `priceGoods` DOUBLE UNSIGNED NOT NULL,
  `Checks_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `Checks_id`),
  CONSTRAINT `fk_CheckGoods_Checks1`
    FOREIGN KEY (`Checks_id`)
    REFERENCES `cashregister`.`Checks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CheckGoods_Checks1_idx` ON `cashregister`.`CheckGoods` (`Checks_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cashregister`.`Roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`Roles` (`id`, `name`) VALUES (1, 'Casher');
INSERT INTO `cashregister`.`Roles` (`id`, `name`) VALUES (2, 'Admin');
INSERT INTO `cashregister`.`Roles` (`id`, `name`) VALUES (3, 'CommodityExpert');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cashregister`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`Users` (`id`, `login`, `password`, `name`, `surname`, `roleName`) VALUES (1, 'kot', '259koi', 'Oleksandr', 'Korotkov', 'Admin');
INSERT INTO `cashregister`.`Users` (`id`, `login`, `password`, `name`, `surname`, `roleName`) VALUES (2, 'mark', '4555qqq', 'Mark', 'Turk', 'Casher');
INSERT INTO `cashregister`.`Users` (`id`, `login`, `password`, `name`, `surname`, `roleName`) VALUES (3, 'petrov', 'petr555', 'Dmitriy', 'Petrov', 'CommodityExpert');
INSERT INTO `cashregister`.`Users` (`id`, `login`, `password`, `name`, `surname`, `roleName`) VALUES (4, 'sidr', 'sirbt4', 'Ivan', 'Sidorov', 'Casher');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cashregister`.`Goods`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (1, 'Cigarettes Parlament silver', 10, 50.5);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (2, 'Cigarettes Parlament black', 5, 51.9);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (3, 'Coca-cola 0,5L', 3, 25.9);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (4, 'Coca-cola 0,33L', 2, 12.5);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (5, 'Fanta 0,5L', 0, 12.5);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (6, 'Fanta 0,33L', 1, 19.8);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (7, 'Aqua Water 0.5L', 45, 12.1);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (8, 'Carlsberg beer 0.33L', 8, 45.9);
INSERT INTO `cashregister`.`Goods` (`id`, `name`, `amount`, `price`) VALUES (9, 'Magazene Ukraine today', 5, 8.8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cashregister`.`Shifts`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`Shifts` (`id`, `isOpen`, `openTime`, `closeTime`) VALUES (1, 0, '2021-11-16 10:42:47', '2021-11-16 22:45:47');
INSERT INTO `cashregister`.`Shifts` (`id`, `isOpen`, `openTime`, `closeTime`) VALUES (2, 1, DEFAULT, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cashregister`.`Checks`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`Checks` (`id`, `CheckSum`, `CheckTime`, `isReturned`, `Shifts_id`, `Users_id`) VALUES (1, 74.7, '2021-11-16 11:45:47', 0, 1, 2);
INSERT INTO `cashregister`.`Checks` (`id`, `CheckSum`, `CheckTime`, `isReturned`, `Shifts_id`, `Users_id`) VALUES (2, 45.9, '2021-11-16 12:45:47', 0, 1, 2);
INSERT INTO `cashregister`.`Checks` (`id`, `CheckSum`, `CheckTime`, `isReturned`, `Shifts_id`, `Users_id`) VALUES (3, 12.5, '2021-11-16 14:45:47', 0, 1, 4);
INSERT INTO `cashregister`.`Checks` (`id`, `CheckSum`, `CheckTime`, `isReturned`, `Shifts_id`, `Users_id`) VALUES (4, 12.5, '2021-11-16 17:45:47', 0, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cashregister`.`CheckGoods`
-- -----------------------------------------------------
START TRANSACTION;
USE `cashregister`;
INSERT INTO `cashregister`.`CheckGoods` (`id`, `idGoods`, `nameGoods`, `amountGoods`, `priceGoods`, `Checks_id`) VALUES (1, 1, 'Cigarettes Parlament silver', 1, 50.5, 1);
INSERT INTO `cashregister`.`CheckGoods` (`id`, `idGoods`, `nameGoods`, `amountGoods`, `priceGoods`, `Checks_id`) VALUES (2, 7, 'Aqua Water 0.5L', 2, 12.1, 1);
INSERT INTO `cashregister`.`CheckGoods` (`id`, `idGoods`, `nameGoods`, `amountGoods`, `priceGoods`, `Checks_id`) VALUES (3, 8, 'Carlsberg beer 0.33L', 1, 45.9, 2);
INSERT INTO `cashregister`.`CheckGoods` (`id`, `idGoods`, `nameGoods`, `amountGoods`, `priceGoods`, `Checks_id`) VALUES (4, 4, 'Coca-cola 0,33L', 1, 12.5, 3);

COMMIT;

