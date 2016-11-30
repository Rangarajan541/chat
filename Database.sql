-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: chat
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kick`
--

DROP TABLE IF EXISTS `kick`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kick` (
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kick`
--

LOCK TABLES `kick` WRITE;
/*!40000 ALTER TABLE `kick` DISABLE KEYS */;
/*!40000 ALTER TABLE `kick` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `killid`
--

DROP TABLE IF EXISTS `killid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `killid` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `killid`
--

LOCK TABLES `killid` WRITE;
/*!40000 ALTER TABLE `killid` DISABLE KEYS */;
INSERT INTO `killid` VALUES (0),(1),(2);
/*!40000 ALTER TABLE `killid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (0,'System','hihihi has joined!','2016-11-30 14:27:18'),(1,'System','hihihi has left','2016-11-30 14:28:12'),(2,'System','keyboard has joined!','2016-11-30 14:28:22'),(3,'keyboard','asd','2016-11-30 14:28:27'),(4,'System','keyboard has left','2016-11-30 14:29:20'),(5,'System','keyboard has joined!','2016-11-30 14:29:27'),(6,'keyboard','asd','2016-11-30 14:29:30'),(7,'keyboard',':)','2016-11-30 14:29:42'),(8,'System','keyboard has left','2016-11-30 14:30:07'),(9,'System','keyboard has joined!','2016-11-30 14:30:13'),(10,'keyboard','asd','2016-11-30 14:30:19'),(11,'keyboard','I\'m such a bad person for trying to swear. Sorry!','2016-11-30 14:33:43'),(12,'System','Ranga has joined!','2016-11-30 14:34:07'),(13,'keyboard','/Ranga','2016-11-30 14:34:18'),(14,'Ranga','my turn','2016-11-30 14:34:23'),(15,'Ranga','/keyboard','2016-11-30 14:34:26'),(16,'System','keyboard has been kicked out by admin!','2016-11-30 14:34:28'),(17,'System','navie has joined!','2016-11-30 14:36:14'),(18,'navie','I\'m such a bad person for trying to swear. Sorry!','2016-11-30 14:36:17'),(19,'System','navie has left','2016-11-30 14:36:26'),(20,'System','Ranga has joined!','2016-11-30 14:38:51'),(21,'System','Naveed has joined!','2016-11-30 14:39:27'),(22,'Ranga','bye bye','2016-11-30 14:39:39'),(23,'Ranga','/Naveed','2016-11-30 14:39:44'),(24,'System','Naveed has been kicked out by admin!','2016-11-30 14:39:45'),(25,'System','Ranga has joined!','2016-11-30 14:41:21'),(26,'System','Douce has joined!','2016-11-30 14:41:33'),(27,'System','Douce has left','2016-11-30 14:41:37'),(28,'System','Bad Guy has joined!','2016-11-30 14:41:50'),(29,'Ranga','bye bye :)','2016-11-30 14:41:56'),(30,'Ranga',':*','2016-11-30 14:42:00'),(31,'Ranga','/Bad Guy','2016-11-30 14:42:04'),(32,'System','Bad Guy has been kicked out by admin!','2016-11-30 14:42:05'),(33,'System','Good guy has joined!','2016-11-30 14:42:31'),(34,'Ranga','Look on the right, he\'s typing :)','2016-11-30 14:42:50'),(35,'System','Ranga has left','2016-11-30 14:44:31'),(36,'Good guy','asd','2016-11-30 14:44:36'),(37,'System','Good guy has left','2016-11-30 14:44:39'),(38,'System','asdad has joined!','2016-11-30 14:44:54'),(39,'System','asdad has left','2016-11-30 14:56:13');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online`
--

DROP TABLE IF EXISTS `online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `online` (
  `user` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online`
--

LOCK TABLES `online` WRITE;
/*!40000 ALTER TABLE `online` DISABLE KEYS */;
/*!40000 ALTER TABLE `online` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01  5:00:12
