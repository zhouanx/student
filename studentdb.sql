/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.28 : Database - studentdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studentdb`;

/*Table structure for table `classes` */

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `name` varchar(20) NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `classes` */

insert  into `classes`(`id`,`name`) values 
(1,'高一(1)班'),
(2,'高一(2)班'),
(3,'高一(3)班'),
(4,'高二(1)班'),
(5,'高二(2)班'),
(6,'高二(3)班');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学员编号',
  `name` varchar(20) NOT NULL COMMENT '学员姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  `gender` varchar(2) NOT NULL COMMENT '性别',
  `telephone` varchar(20) NOT NULL COMMENT '电话',
  `email` varchar(20) NOT NULL COMMENT 'Email',
  `classId` int(11) NOT NULL COMMENT '班级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`age`,`gender`,`telephone`,`email`,`classId`) values 
(1,'张三',18,'男','13838439789','zhangsansuai@163.com',1),
(2,'李四',18,'女','13949228156','lisiniubi@163.com',1),
(3,'王五',19,'男','18763382757','wangwu187@163.com',2),
(4,'赵六',17,'女','18429583721','18429583721@163.com',2),
(5,'周文涛',17,'男','186394331111','1577701412@qq.com',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
