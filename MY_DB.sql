-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: MY_DB
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Assessment`
--

DROP TABLE IF EXISTS `Assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Assessment` (
  `assessment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `marks` int NOT NULL,
  `assessment_type` enum('exam','assignment','quiz') NOT NULL,
  PRIMARY KEY (`assessment_id`),
  KEY `Assessment___fk` (`user_id`),
  KEY `Assessment___fk1` (`course_id`),
  CONSTRAINT `Assessment___fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`),
  CONSTRAINT `Assessment___fk1` FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Assessment`
--

LOCK TABLES `Assessment` WRITE;
/*!40000 ALTER TABLE `Assessment` DISABLE KEYS */;
INSERT INTO `Assessment` VALUES (1,5,9,44,'exam'),(2,5,9,33,'quiz'),(3,5,9,22,'assignment'),(4,5,1,11,'assignment'),(5,5,1,22,'quiz'),(6,5,1,33,'exam'),(7,4,9,33,'assignment'),(8,4,9,67,'quiz'),(9,4,9,88,'exam'),(10,11,5,1,'assignment'),(11,11,5,2,'quiz'),(12,11,5,3,'exam');
/*!40000 ALTER TABLE `Assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) NOT NULL,
  `semester` enum('1','2') NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'Tom','1'),(2,'jerry','1'),(3,'fs','1'),(4,'ds','1'),(5,'algorithm','2'),(6,'databases','2'),(7,'programmingII','2'),(8,'good course','1'),(9,'haha','1'),(10,'admin','1'),(11,'OOD','1'),(12,'data structure','1'),(13,'Software engineering','1'),(14,'front end','1'),(15,'courseNew','1'),(16,'algorithmII','2'),(17,'SE','2');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role` enum('teacher','student') NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'sfa','sfaf','fsaf','fsaf','student'),(2,'lily','1234','lily brown','1234567','teacher'),(3,'Tom','1234','Tom','1234','student'),(4,'Jerry','1234','Jerry','1234552','student'),(5,'student','student','student','1234','student'),(6,'teacher','teacher','teacher','2345','teacher'),(7,'Cam','1234','Cam','1234567','teacher'),(8,'Hailey','1234','Hailey','1234567','student'),(9,'student1','1234','student1','13413','student'),(10,'Cathy','1234','Cathy','2341','teacher'),(11,'Harry','1234','Harry','1234','student');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserCourse`
--

DROP TABLE IF EXISTS `UserCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserCourse` (
  `user_course_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`user_course_id`),
  KEY `UserCourse___fk` (`user_id`),
  KEY `UserCourse___fk1` (`course_id`),
  CONSTRAINT `UserCourse___fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `UserCourse___fk1` FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserCourse`
--

LOCK TABLES `UserCourse` WRITE;
/*!40000 ALTER TABLE `UserCourse` DISABLE KEYS */;
INSERT INTO `UserCourse` VALUES (1,2,5),(2,5,10),(3,5,9),(4,5,1),(5,5,2),(6,5,11),(7,6,7),(8,6,9),(9,1,9),(11,4,9),(12,6,1),(13,5,3),(14,5,7),(15,6,15),(16,5,15),(17,6,2),(18,11,1),(19,11,5),(21,11,2),(22,11,6),(23,11,11),(24,6,5),(25,2,7),(29,11,12);
/*!40000 ALTER TABLE `UserCourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-25 18:32:18
