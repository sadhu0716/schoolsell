/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.15 : Database - schoolsell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schoolsell` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

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
  `bigKindID` int(4) NOT NULL AUTO_INCREMENT,
  `bigKindName` varchar(20) NOT NULL,
  `bigKindPicture` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`bigKindID`),
  KEY `bigKindID` (`bigKindID`),
  KEY `bigKindID_2` (`bigKindID`),
  KEY `bigKindID_3` (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `bigkind` */

insert  into `bigkind`(`bigKindID`,`bigKindName`,`bigKindPicture`) values (1,'学习',NULL),(2,'运动',NULL),(3,'电子',NULL);

/*Table structure for table `chatreport` */

DROP TABLE IF EXISTS `chatreport`;

CREATE TABLE `chatreport` (
  `chatID` int(4) NOT NULL AUTO_INCREMENT,
  `sendID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `acceptID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(1024) DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  `isbrowse` tinyint(1) NOT NULL,
  PRIMARY KEY (`chatID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `chatreport` */

insert  into `chatreport`(`chatID`,`sendID`,`acceptID`,`context`,`date`,`isbrowse`) values (12,'tom','Joel','df','2018-08-17 14:16:54',0),(13,'tom','James','我是james','2018-08-17 14:18:43',0),(14,'tom','Jimmy','我是jimmy','2018-08-17 14:22:23',0),(15,'tom','John','123','2018-08-17 14:37:40',0),(16,'1122@qq.com','1122@qq.com','你好啊','2019/03/29 15:23:15',1),(17,'1122@qq.com','1122@qq.com','我是','2019/03/29 15:23:21',1),(18,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:35:12',1),(19,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:40:29',1),(20,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:42:08',1),(21,'1122@qq.com','3344@qq.com','我是1122','2019/03/29 15:44:47',1),(22,'1122@qq.com','3344@qq.com','你是谁？','2019/03/29 15:45:28',1),(23,'1122@qq.com','3344@qq.com','亲','2019/03/29 15:45:43',1),(24,'1122@qq.com','3344@qq.com','还在吗','2019/03/29 15:45:50',1);

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `cID` int(4) NOT NULL AUTO_INCREMENT,
  `cPrice` decimal(10,0) NOT NULL,
  `bargain` tinyint(1) NOT NULL,
  `useTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cDescription` varchar(1024) DEFAULT NULL,
  `isChecked` int(1) NOT NULL,
  `sellerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cName` varchar(20) NOT NULL,
  `kName` varchar(20) NOT NULL,
  `thumbnail` varchar(1024) NOT NULL,
  `cCount` int(4) NOT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

insert  into `commodity`(`cID`,`cPrice`,`bargain`,`useTime`,`cDescription`,`isChecked`,`sellerID`,`cName`,`kName`,`thumbnail`,`cCount`) values (49,'14',1,'四成新','3344的第一份商品',2,'3344@qq.com','一风景','编程','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\49.jpg',6),(50,'56',1,'四成新','3344的第二份商品',2,'3344@qq.com','二风景','英语','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\50.jpg',7),(51,'45',1,'六成新','3344的第三份商品',2,'3344@qq.com','三风景','篮球','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\51.jpg',5),(52,'76',1,'四成新','3344的第四份商品',1,'3344@qq.com','四风景','足球','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\52.jpg',6),(53,'88',1,'四成新','3344的第五份商品',1,'3344@qq.com','五风景','手机','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\53.jpg',9),(54,'86',1,'八成新','3344的第六份商品',1,'3344@qq.com','六风景','笔记本','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\54.jpg',4),(56,'56',1,'四成新','1122的第二份商品',1,'1122@qq.com','十风景','足球','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\56.jpg',4),(58,'78',1,'四成新','1122的',1,'1122@qq.com','七风景','手机','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\58.jpg',7);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedbackID` int(4) NOT NULL AUTO_INCREMENT,
  `feedbackDate` datetime(5) NOT NULL,
  `feedbackerID` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`feedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`feedbackID`,`feedbackDate`,`feedbackerID`,`description`) values (2,'2018-08-06 08:52:37.00000','1','系统出现了bug'),(3,'2018-08-21 15:24:17.00000','891993651@qq.com','你好啊谢谢');

/*Table structure for table `jubao` */

DROP TABLE IF EXISTS `jubao`;

CREATE TABLE `jubao` (
  `juBaoID` int(4) NOT NULL AUTO_INCREMENT,
  `juBaoDate` datetime(5) NOT NULL,
  `juBaoUserID` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `orderID` int(4) DEFAULT NULL,
  PRIMARY KEY (`juBaoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `jubao` */

insert  into `jubao`(`juBaoID`,`juBaoDate`,`juBaoUserID`,`description`,`orderID`) values (2,'2018-08-06 09:08:24.00000','123','该商品质量很差',NULL);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderID` int(4) NOT NULL AUTO_INCREMENT,
  `orderDate` datetime(5) NOT NULL,
  `isEnd` tinyint(1) NOT NULL,
  `endDate` datetime(5) DEFAULT NULL,
  `buyerAddress` varchar(50) NOT NULL,
  `amount` double(4,0) NOT NULL,
  `isOnline` tinyint(1) NOT NULL,
  `cID` int(4) NOT NULL,
  `buyerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sellerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`orderID`,`orderDate`,`isEnd`,`endDate`,`buyerAddress`,`amount`,`isOnline`,`cID`,`buyerID`,`sellerID`) values (1,'2018-08-06 11:18:26.00000',0,NULL,'江西赣州章贡区',1,0,0,'','');

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `pictureID` int(4) NOT NULL AUTO_INCREMENT,
  `cID` int(4) NOT NULL,
  `picture` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pictureID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Data for the table `picture` */

insert  into `picture`(`pictureID`,`cID`,`picture`) values (42,40,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.Wg4C7jCHHCHWfca28140d7b201d86c208196c177d9f4.jpg'),(43,40,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.GExuGFZcHQZ6d4adef7c0fa987872884674aba886049.jpg'),(44,41,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.3bOYzfX9jqs79694c9e0b59d92c6296ef95763e77a8f.jpg'),(45,41,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.yLqCjudfFATt43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(46,42,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..x3ZTM3myfMAy9694c9e0b59d92c6296ef95763e77a8f.jpg'),(47,42,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..OzG6benm6lTG43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(48,43,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..95kDSoOpZedafca28140d7b201d86c208196c177d9f4.jpg'),(49,43,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..dRTafAHVB0ATd4adef7c0fa987872884674aba886049.jpg'),(50,44,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..mCjKzYm6Sbdwbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(51,44,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..HSfgPz83UsPyfca28140d7b201d86c208196c177d9f4.jpg'),(52,45,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..5Ggd9uRDHzoLc783bc2d8324d880d206224fe7c4c1e8.jpg'),(53,45,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..EEvpXCMDV0Zo7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(54,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..4QtVetLt4fQA4bffa30b5fa60ff404d0a12fc15ac6fb.jpg'),(55,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..KAvBMdlOvEY29694c9e0b59d92c6296ef95763e77a8f.jpg'),(56,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..dD2XAL94Qo1j43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(57,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..OoTQc2YnsSXVbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(58,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..6843oLuwQgsmfca28140d7b201d86c208196c177d9f4.jpg'),(59,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..zknW0wD27Exxd4adef7c0fa987872884674aba886049.jpg'),(60,48,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..g75L76dqoB1P1d4d9f8e062650fbb8a98714697bf735.jpg'),(61,48,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..FrnlfX9lQIDY92a14dd5352e6348371dc34808b5e6b7.jpg'),(62,49,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.BrLi4jKIp32ufca28140d7b201d86c208196c177d9f4.jpg'),(63,49,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.QSKdtgeBHIe8d4adef7c0fa987872884674aba886049.jpg'),(64,50,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.oFw5sbgFfsjLbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(65,50,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.WYvzk2K1bCwRd4adef7c0fa987872884674aba886049.jpg'),(66,51,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.B0ed3m3YV7hLd4adef7c0fa987872884674aba886049.jpg'),(67,51,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.YwGA0LPx7SSL1d4d9f8e062650fbb8a98714697bf735.jpg'),(68,52,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.XtPTUSD9Qh2x43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(69,52,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.5EqbtSsdHcBS003d29ca7549f3efa335363a10934ee6.jpg'),(70,53,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.5RiODe9HVJsAbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(71,53,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.o8KfC11Ei0n0fca28140d7b201d86c208196c177d9f4.jpg'),(72,54,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.T5fHRnHsEAwe4bffa30b5fa60ff404d0a12fc15ac6fb.jpg'),(73,54,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.YfMwL5BdDqBf9694c9e0b59d92c6296ef95763e77a8f.jpg'),(74,55,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.d1csLE0IdhT292a14dd5352e6348371dc34808b5e6b7.jpg'),(75,55,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.0k2Evrr142ooc783bc2d8324d880d206224fe7c4c1e8.jpg'),(76,56,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.xxDMxiQj26Zobc9b2cb605df5c5315614c4a2bc1859a.jpg'),(77,56,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.kRIzrZk5K0Dufca28140d7b201d86c208196c177d9f4.jpg'),(78,57,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.z5XQ0EN0tDPS43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(79,57,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.BKD0l8UjCg3P003d29ca7549f3efa335363a10934ee6.jpg'),(80,58,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.2CgiACus9Fnzc783bc2d8324d880d206224fe7c4c1e8.jpg'),(81,58,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.RV4osuNJsYSj7662f60ffa6dc8d85aad9df21b0fbc1c.jpg');

/*Table structure for table `shopcar` */

DROP TABLE IF EXISTS `shopcar`;

CREATE TABLE `shopcar` (
  `cID` int(4) NOT NULL,
  `shopCarID` int(4) NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) NOT NULL,
  `amount` int(4) DEFAULT NULL,
  `isFinish` int(4) NOT NULL,
  PRIMARY KEY (`shopCarID`),
  KEY `FK_shopcar` (`userID`),
  CONSTRAINT `FK_shopcar` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `shopcar` */

insert  into `shopcar`(`cID`,`shopCarID`,`userID`,`amount`,`isFinish`) values (41,1,'1122@qq.com',1,0),(48,2,'1122@qq.com',2,0);

/*Table structure for table `smallkind` */

DROP TABLE IF EXISTS `smallkind`;

CREATE TABLE `smallkind` (
  `smallKindID` int(4) NOT NULL AUTO_INCREMENT,
  `smallKindName` varchar(20) NOT NULL,
  `bigKindID` int(4) NOT NULL,
  `smallKindPicture` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`smallKindID`),
  KEY `FK_smallkind` (`bigKindID`),
  CONSTRAINT `FK_smallkind` FOREIGN KEY (`bigKindID`) REFERENCES `bigkind` (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `smallkind` */

insert  into `smallkind`(`smallKindID`,`smallKindName`,`bigKindID`,`smallKindPicture`) values (5,'编程',1,NULL),(6,'英语',1,NULL),(7,'篮球',2,NULL),(8,'足球',2,NULL),(9,'手机',3,NULL),(10,'笔记本',3,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `realName` varchar(20) NOT NULL,
  `idNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phoneNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `credibility` int(2) NOT NULL,
  `profilePhoto` varchar(1024) DEFAULT NULL,
  `isChecked` int(1) NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `userID` (`userID`),
  KEY `userID_2` (`userID`),
  KEY `userID_3` (`userID`),
  KEY `userID_4` (`userID`),
  KEY `userID_5` (`userID`),
  KEY `userID_6` (`userID`),
  KEY `userID_7` (`userID`),
  KEY `userID_8` (`userID`),
  KEY `userID_9` (`userID`),
  KEY `userID_10` (`userID`),
  KEY `userID_11` (`userID`),
  KEY `userID_12` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`password`,`userName`,`realName`,`idNumber`,`phoneNumber`,`address`,`credibility`,`profilePhoto`,`isChecked`) values ('1122@qq.com','123','小王','王小小','367567877654565434','15378675645','432',10,'H:\\Code\\Schoolsell\\target\\classes\\static\\userImg\\1122@qq.com.jpg',-1),('3344@qq.com','123','小李','李丽丽','356545766876787656','15256543456','456',10,'H:\\Code\\Schoolsell\\target\\classes\\static\\userImg\\3344@qq.com.jpg',-1),('5566@qq.com','123','小李','李晓晓','390897788987896789','15909897890','563',10,'H:\\Code\\isRunning\\server\\Schoolsell\\target\\file:\\H:\\Code\\isRunning\\server\\Schoolsell\\target\\schoolsell-0.0.1-SNAPSHOT.jar!\\BOOT-INF\\classes!\\static\\userImg\\5566@qq.com.jpg',-1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
