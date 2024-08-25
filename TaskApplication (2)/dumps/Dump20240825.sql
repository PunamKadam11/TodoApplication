-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: taskdb
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `priority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` int DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_on` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority`
--

LOCK TABLES `priority` WRITE;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` VALUES (1,1,NULL,NULL,NULL,NULL,'High'),(2,1,NULL,NULL,NULL,NULL,'Medium'),(3,1,NULL,NULL,NULL,NULL,'Low');
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9ob63rkqg8ppaon1l37w8id2p` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (3,'Done'),(2,'Progress'),(1,'Todo');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` int DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_on` datetime(6) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `priority_id` int DEFAULT NULL,
  `status_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23pwolpebddlvnpucweas18g0` (`priority_id`),
  KEY `FK46j0icbqegggl4tyxyocdok6s` (`status_id`),
  KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`),
  CONSTRAINT `FK23pwolpebddlvnpucweas18g0` FOREIGN KEY (`priority_id`) REFERENCES `priority` (`id`),
  CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK46j0icbqegggl4tyxyocdok6s` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,1,NULL,'2024-08-25 17:10:29.378302',NULL,NULL,'2024-08-25','Create and finalize the database schema for the new application.','2024-09-05','Design Database Schema','2024-08-25',1,1,1),(2,1,NULL,'2024-08-25 17:10:39.195803',NULL,NULL,'2024-08-25','Create and finalize the database schema for the new application.','2024-09-05','Design Database Schema','2024-08-25',1,1,2),(3,1,NULL,'2024-08-25 17:10:49.474936',NULL,NULL,'2024-08-25','Create and finalize the database schema for the new application.','2024-09-05','Design Database Schema','2024-08-25',3,1,2),(4,1,NULL,NULL,NULL,'2024-08-25 17:16:36.986211',NULL,'Finalize and submit the project documentation including all design specifications and user guides.','2024-08-30','update Project.','2024-08-25',1,1,5),(5,0,NULL,'2024-08-25 17:11:27.275064',NULL,'2024-08-25 17:17:02.330472','2024-08-25','Develop and test the authentication module for user login.','2024-09-10','Implement Authentication','2024-08-25',2,1,2),(6,1,NULL,'2024-08-25 17:11:57.694093',NULL,NULL,'2024-08-25','Complete the UI/UX design for the main application interface.','2024-09-01','UI/UX Design','2024-08-25',3,2,2),(7,1,NULL,'2024-08-25 17:12:43.066470',NULL,NULL,'2024-08-25','Create and run unit tests for the core functionalities.','2024-09-15','Write Unit Tests','2024-08-25',1,1,4),(8,1,NULL,'2024-08-25 17:12:57.914437',NULL,NULL,'2024-08-25','Deploy the application to the staging environment for final testing.','2024-09-20','Deploy to Staging','2024-08-25',2,3,2),(9,1,NULL,'2024-08-25 17:13:19.689105',NULL,NULL,'2024-08-25','Conduct user acceptance testing with end users to ensure requirements are met.','2024-09-25','User Acceptance Testing','2024-08-25',3,2,4),(10,1,NULL,'2024-08-25 17:13:38.625859',NULL,NULL,'2024-08-25','Address and resolve any bugs reported during testing.','2024-09-18','Fix Bugs','2024-08-25',2,1,2),(11,1,NULL,'2024-08-25 17:13:54.143820',NULL,NULL,'2024-08-25','Document the release notes and version history for the release.','2024-09-22','Prepare Release Notes','2024-08-25',1,3,1),(12,1,NULL,'2024-08-25 17:13:59.254081',NULL,NULL,'2024-08-25','Document the release notes and version history for the release.','2024-09-22','Prepare Release Notes','2024-08-25',1,3,1),(13,1,NULL,'2024-08-25 17:14:09.205737',NULL,NULL,'2024-08-25','Document the release notes and version history for the release.','2024-09-22','Prepare Release Notes','2024-08-25',1,3,1),(14,1,NULL,'2024-08-25 17:14:16.138176',NULL,NULL,'2024-08-25','Train the support team on the new features and functionalities.','2024-10-01','Conduct Training','2024-08-25',3,1,2),(15,1,NULL,'2024-08-25 17:14:29.597012',NULL,NULL,'2024-08-25','Update all project documentation with the latest changes.','2024-09-30','Update Documentation','2024-08-25',1,2,6);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$H.yq9tRDhuA9ER6frhEXr..LufsdgdLVd61KltuitER3bIFdEchrm','ADMIN','admin@ctpl.in'),(2,'$2a$10$DW8pjKLcRN8kwiUh.m9GoejRKA2LNwV0YY2JCR8wNddjrI5AE0wQK','USER','michael.williams@example.com'),(4,'$2a$10$ISE1tuzF5aijT1ou2FdB/O4KCYFhcRYoz7BfmJZK1Jd4/xEQY8s7S','USER','emily.brown@example.com'),(5,'$2a$10$EgDRSjw1gtrI/J07rPxrGuDKl/CHdcxC83YJK8HQ3GjdgxgAA9enS','USER','jane.smith@example.com'),(6,'$2a$10$4oKhYDNspmqxDWfIWTDcS.Qz7vqFcmvnkfWn.kX9NG/pvqBqhU4va','USER','john.doe@example.com'),(7,'$2a$10$3LCtlIKzl9LYLUUCQZdfqOHqX375IQLY227p6YNDlpys6tda36JR2','USER','maria.martinez@example.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-25 21:51:29
