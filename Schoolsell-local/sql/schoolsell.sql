/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 : Database - schoolsell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schoolsell` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `schoolsell`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `Name` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`Name`,`Password`) values ('root','123');

/*Table structure for table `bigkind` */

DROP TABLE IF EXISTS `bigkind`;

CREATE TABLE `bigkind` (
  `bigKindID` int(11) NOT NULL AUTO_INCREMENT,
  `bigKindName` varchar(20) NOT NULL,
  PRIMARY KEY (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `bigkind` */

insert  into `bigkind`(`bigKindID`,`bigKindName`) values (2,'运动'),(3,'生活'),(4,'户外'),(5,'户外'),(7,'学习');

/*Table structure for table `chatreport` */

DROP TABLE IF EXISTS `chatreport`;

CREATE TABLE `chatreport` (
  `chatid` int(11) NOT NULL AUTO_INCREMENT,
  `sendid` varchar(50) NOT NULL,
  `acceptid` varchar(50) NOT NULL,
  `context` varchar(1024) DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  `isbrowse` tinyint(1) NOT NULL,
  PRIMARY KEY (`chatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `chatreport` */

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `cName` varchar(50) NOT NULL,
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `kName` varchar(20) NOT NULL,
  `cPrice` double unsigned zerofill DEFAULT NULL,
  `bargain` tinyint(1) DEFAULT NULL,
  `useTime` varchar(10) DEFAULT NULL,
  `userID` varchar(20) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `picture` varchar(1024) DEFAULT NULL,
  `cDescription` varchar(1024) DEFAULT NULL,
  `isChecked` tinyint(1) NOT NULL,
  `isBrowse` tinyint(1) NOT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

insert  into `commodity`(`cName`,`cID`,`kName`,`cPrice`,`bargain`,`useTime`,`userID`,`userName`,`picture`,`cDescription`,`isChecked`,`isBrowse`) values ('康师傅牛肉面',2,'运动',0000000000000000000001,1,'100年','110','溥仪','544sdsads','好喝',1,1);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedbackID` int(11) NOT NULL AUTO_INCREMENT,
  `feedbackerName` varchar(20) NOT NULL,
  `feedbackDate` datetime NOT NULL,
  `feedbackerID` varchar(50) NOT NULL,
  `feedbackerPhone` int(11) DEFAULT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`feedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`feedbackID`,`feedbackerName`,`feedbackDate`,`feedbackerID`,`feedbackerPhone`,`description`) values (2,'张全蛋他爹','2018-08-06 08:52:37','1',110,'系统出现了bug');

/*Table structure for table `jubao` */

DROP TABLE IF EXISTS `jubao`;

CREATE TABLE `jubao` (
  `juBaoID` int(11) NOT NULL AUTO_INCREMENT,
  `juBaoUserName` varchar(20) NOT NULL,
  `juBaoDate` datetime NOT NULL,
  `juBaoUserID` varchar(50) NOT NULL,
  `juBaoUserPhone` int(11) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `cLink` varchar(1024) NOT NULL,
  PRIMARY KEY (`juBaoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `jubao` */

insert  into `jubao`(`juBaoID`,`juBaoUserName`,`juBaoDate`,`juBaoUserID`,`juBaoUserPhone`,`description`,`cLink`) values (2,'奥巴马','2018-08-06 09:08:24','123',10086,'该商品质量很差','31155532332123');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `buyerID` varchar(50) NOT NULL,
  `buyerName` varchar(20) NOT NULL,
  `buyerPhone` int(11) NOT NULL,
  `sellerID` varchar(50) NOT NULL,
  `sellerName` varchar(20) NOT NULL,
  `sellerPhone` int(11) NOT NULL,
  `cName` varchar(50) NOT NULL,
  `cID` int(11) NOT NULL,
  `money` double NOT NULL,
  `orderDate` datetime NOT NULL,
  `isEnd` tinyint(1) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `buyerAddress` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `FK_order` (`buyerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`orderID`,`buyerID`,`buyerName`,`buyerPhone`,`sellerID`,`sellerName`,`sellerPhone`,`cName`,`cID`,`money`,`orderDate`,`isEnd`,`endDate`,`buyerAddress`,`amount`) values (1,'11','小刘',115454,'22','小张',66444,'苹果电脑',1,1000,'2018-08-06 11:18:26',0,NULL,'江西赣州章贡区',1);

/*Table structure for table `shopcar` */

DROP TABLE IF EXISTS `shopcar`;

CREATE TABLE `shopcar` (
  `cName` varchar(20) NOT NULL,
  `cPicture` varchar(1024) NOT NULL,
  `cPrice` double NOT NULL,
  `cCount` int(11) NOT NULL,
  `cID` int(11) NOT NULL,
  `shopCarID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) NOT NULL,
  PRIMARY KEY (`shopCarID`),
  KEY `FK_shopcar` (`userID`),
  CONSTRAINT `FK_shopcar` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shopcar` */

/*Table structure for table `smallkind` */

DROP TABLE IF EXISTS `smallkind`;

CREATE TABLE `smallkind` (
  `smallKindID` int(11) NOT NULL AUTO_INCREMENT,
  `smallKindName` varchar(20) NOT NULL,
  `bigKindID` int(11) NOT NULL,
  PRIMARY KEY (`smallKindID`),
  KEY `FK_smallkind` (`bigKindID`),
  CONSTRAINT `FK_smallkind` FOREIGN KEY (`bigKindID`) REFERENCES `bigkind` (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `smallkind` */

insert  into `smallkind`(`smallKindID`,`smallKindName`,`bigKindID`) values (2,'编程类',7);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `realName` varchar(20) NOT NULL,
  `idCard` varchar(50) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `credibility` int(11) NOT NULL,
  `profilePhoto` varchar(1024) DEFAULT NULL,
  `isChecked` tinyint(1) NOT NULL,
  `isBrowse` tinyint(1) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`password`,`userName`,`realName`,`idCard`,`phoneNumber`,`address`,`credibility`,`profilePhoto`,`isChecked`,`isBrowse`) values ('1122@qq.com','123456','张丰','张全蛋','15563123',12233,'赣州',10,'4454dasd',1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
