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
INSERT INTO `killid` VALUES (0),(1);
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
INSERT INTO `messages` VALUES (0,'System','sdasdasdasd has joined!','2016-12-04 11:09:14'),(1,'System','sdasdasdasd clicked the button 48 times in 10 seconds. How many times can you?','2016-12-04 11:09:25'),(2,'System','sdasdasdasd has left [Closed]','2016-12-04 11:09:29'),(3,'System','sadasd has joined!','2016-12-04 11:11:04'),(4,'System','sadasd clicked the button 47 times in 10 seconds. How many times can you?','2016-12-04 11:11:13'),(5,'System','sadasd clicked the button 0 times in 10 seconds. How many times can you?','2016-12-04 11:11:23'),(6,'System','sadasd clicked the button 48 times in 10 seconds. How many times can you?','2016-12-04 11:12:22'),(7,'System','sadasd clicked the button 0 times in 10 seconds. How many times can you?','2016-12-04 11:12:32'),(8,'System','sadasd has left [Closed]','2016-12-04 11:18:11'),(9,'System','asdasdasd has joined!','2016-12-04 11:18:22'),(10,'asdasdasd','jghjghjghj','2016-12-04 11:18:25'),(11,'System','asdasdasd clicked the button 51 times in 10 seconds. How many times can you?','2016-12-04 11:18:35'),(12,'System','asdasdasd clicked the button 0 times in 10 seconds. How many times can you?','2016-12-04 11:18:44'),(13,'System','asdasdasd has left [Closed]','2016-12-04 11:19:20'),(14,'System','asdasdasd has joined!','2016-12-04 11:19:25'),(15,'asdasdasd','dsasdasd\'','2016-12-04 11:19:29'),(16,'System','asdasdasd clicked the button 50 times in 10 seconds. How many times can you?','2016-12-04 11:19:39'),(17,'asdasdasd','dasdas\nd\nasdsaa\ndsa\ndasd','2016-12-04 11:57:33'),(18,'System','asdasdasd has left [Closed]','2016-12-04 11:58:34'),(19,'System','dasdasd has joined!','2016-12-04 11:58:41'),(20,'dasdasd','Hello','2016-12-04 11:58:45'),(21,'dasdasd','what\'s up?','2016-12-04 11:58:48'),(22,'System','dasdasd clicked the button 57 times in 10 seconds. How many times can you?','2016-12-04 11:59:00'),(23,'dasdasd','I\'m such a bad person for trying to swear. Sorry!','2016-12-04 11:59:42'),(24,'System','dasdasd has left [Closed]','2016-12-04 11:59:46'),(25,'System','Keyboard has joined!','2016-12-04 13:08:09'),(26,'System','Keyboard clicked the button 50 times in 10 seconds. How many times can you?','2016-12-04 13:09:30'),(27,'System','ljlkjkjk has joined!','2016-12-04 13:11:19'),(28,'System','ljlkjkjk clicked the button 52 times in 10 seconds. How many times can you?','2016-12-04 13:11:30'),(29,'System','ljlkjkjk has left [Closed]','2016-12-04 13:11:53'),(30,'System','Keyboard has left [Program master shutdown]','2016-12-04 13:12:27');
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

--
-- Table structure for table `suggestions`
--

DROP TABLE IF EXISTS `suggestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestions` (
  `name` varchar(20) DEFAULT NULL,
  `message` varchar(5000) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestions`
--

LOCK TABLES `suggestions` WRITE;
/*!40000 ALTER TABLE `suggestions` DISABLE KEYS */;
INSERT INTO `suggestions` VALUES ('asdasd','Reporting :asdasdas dasdlkajdskj aksjdlkasjdlkasd','2016-12-03 13:56:36'),('dasdasdasd','asdasdasd','2016-12-03 13:57:56'),('dasdasdasd','Reporting :sdasdasdasdasd','2016-12-03 13:58:00'),('asdasdasd','','2016-12-03 14:27:52'),('dasdasdas','','2016-12-03 15:38:34');
/*!40000 ALTER TABLE `suggestions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-04 18:45:04
