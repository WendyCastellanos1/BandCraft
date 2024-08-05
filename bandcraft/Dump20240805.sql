CREATE DATABASE  IF NOT EXISTS `band_craft` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `band_craft`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: band_craft
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `band_details`
--

DROP TABLE IF EXISTS `band_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `band_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `band_id` int NOT NULL,
  `member_talent_id` int NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `band_id_idx` (`band_id`),
  KEY `member_talent_id_idx` (`member_talent_id`),
  KEY `last_updated_id_idx` (`last_updated_id`),
  CONSTRAINT `band_id` FOREIGN KEY (`band_id`) REFERENCES `bands` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member_talent_id` FOREIGN KEY (`member_talent_id`) REFERENCES `member_talents` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This is where a band maps to one to many members bringing their talents to a specific event or a continuous grouping.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `band_details`
--

LOCK TABLES `band_details` WRITE;
/*!40000 ALTER TABLE `band_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `band_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bands`
--

DROP TABLE IF EXISTS `bands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `lead_member_id` int DEFAULT NULL,
  `is_single_use` tinyint NOT NULL DEFAULT '0',
  `is_active` tinyint NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `lead_member_id_idx` (`lead_member_id`),
  KEY `last_updated_id_idx` (`last_updated_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bands`
--

LOCK TABLES `bands` WRITE;
/*!40000 ALTER TABLE `bands` DISABLE KEYS */;
/*!40000 ALTER TABLE `bands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_types`
--

DROP TABLE IF EXISTS `event_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  `description` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `last_updated_id_idx` (`last_updated_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This is a look-up table for types of events that bands can be formed for.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_types`
--

LOCK TABLES `event_types` WRITE;
/*!40000 ALTER TABLE `event_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_type_id` int NOT NULL,
  `start_datetime` datetime NOT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `location` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `is_continuous` tinyint NOT NULL,
  `is_family_friendly` tinyint DEFAULT NULL,
  `no_minors_allowed` tinyint DEFAULT NULL,
  `only_minors_allowed` tinyint DEFAULT NULL,
  `is_gender_specific` tinyint DEFAULT NULL,
  `has_free_food` tinyint DEFAULT NULL,
  `is_indoors_has_ac` tinyint DEFAULT NULL,
  `is_indoors_no_ac` tinyint DEFAULT NULL,
  `is_outdoors_shade` tinyint DEFAULT NULL,
  `is_outdoors_no_shade` tinyint DEFAULT NULL,
  `sound_system_provided` tinyint NOT NULL,
  `sound_system_comments` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL,
  `contact_first_name` varchar(45) COLLATE utf8mb3_bin DEFAULT NULL,
  `contact_last_name` varchar(45) COLLATE utf8mb3_bin DEFAULT NULL,
  `contact_email` varchar(60) COLLATE utf8mb3_bin DEFAULT NULL,
  `contact_phone_alt` varchar(15) COLLATE utf8mb3_bin DEFAULT NULL,
  `contact_phone_cell` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `comments` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `event_type_id_idx` (`event_type_id`),
  KEY `last_updated_id_idx` (`last_updated_id`),
  CONSTRAINT `event_type_id` FOREIGN KEY (`event_type_id`) REFERENCES `event_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `last_updated_id` FOREIGN KEY (`last_updated_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This table captures events that bands will be "built" for and mapped to (based on member talents, interests, and suitability)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event_types`
--

DROP TABLE IF EXISTS `member_event_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_event_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `event_type_id` int NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `member_id_idx` (`member_id`),
  KEY `event_type_id_idx` (`event_type_id`),
  CONSTRAINT `event_type_id_met` FOREIGN KEY (`event_type_id`) REFERENCES `event_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member_id_met` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This is a junction table mapping members to event types they are interested in performing at, and whether they will volunteer if there is no pay available.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event_types`
--

LOCK TABLES `member_event_types` WRITE;
/*!40000 ALTER TABLE `member_event_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_event_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_talents`
--

DROP TABLE IF EXISTS `member_talents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_talents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `talent_id` int NOT NULL,
  `preference_ranking` tinyint NOT NULL DEFAULT '0',
  `can_improv` tinyint NOT NULL DEFAULT '0',
  `reads_chord_charts` tinyint DEFAULT '0',
  `sight_reads_this_talent` tinyint DEFAULT '0',
  `key1_preferred` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `key2_preferred` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `key1_harmony` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `key2_harmony` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `key1_avoid` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `key2_avoid` char(1) COLLATE utf8mb3_bin DEFAULT 'x',
  `has_processor` tinyint DEFAULT '0',
  `processor_comments` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `needs_loaner_instrument` tinyint DEFAULT '0',
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `member_id_idx` (`member_id`),
  KEY `talent_id_idx` (`talent_id`),
  KEY `last_updated_id_idx` (`id`,`last_updated_id`),
  CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `talent_id` FOREIGN KEY (`talent_id`) REFERENCES `talents` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This is a junction table between members and talents. It maps many members to many talents and resolves the two m:n relationships to two 1:m relationships. Beyond the mapping, additional fields capture data of interest that is specific to the mapping.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_talents`
--

LOCK TABLES `member_talents` WRITE;
/*!40000 ALTER TABLE `member_talents` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_talents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `first_name` varchar(30) COLLATE utf8mb3_bin NOT NULL,
  `last_name` varchar(60) COLLATE utf8mb3_bin NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb3_bin DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8mb3_bin NOT NULL,
  `gender_comment` varchar(60) COLLATE utf8mb3_bin DEFAULT NULL,
  `generation` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT 'decline',
  `phone_cell` varchar(15) COLLATE utf8mb3_bin NOT NULL,
  `phone_alt` varchar(15) COLLATE utf8mb3_bin DEFAULT NULL,
  `email_alt` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `registration_status` tinyint DEFAULT '-1' COMMENT 'based on MemberStatus enum',
  `is_active` tinyint NOT NULL DEFAULT '0',
  `date_returning` date DEFAULT NULL,
  `speaks_spanish` tinyint NOT NULL DEFAULT '0',
  `speaks_portuguese` tinyint NOT NULL DEFAULT '0',
  `bio` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `profile_photo` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'store the path to the photo',
  `social_media_url` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL,
  `is_banned` tinyint NOT NULL DEFAULT '-1',
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT '-1',
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `last_updated_id_idx` (`last_updated_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='The team member must receive an invitation to create an account and profile, but an admin must make active and not banned. The team member can only be mapped to a band if active. The team member can never be mapped to a band if banned. A team member ask to become inactive and state a return date, to be used for planning purposes. Languages spoken are collected for the purpose of indicating suitability to be mapped to certain events where language and culture are specific.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talents`
--

DROP TABLE IF EXISTS `talents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb3_bin NOT NULL,
  `description` varchar(300) COLLATE utf8mb3_bin NOT NULL,
  `url_small_photo` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL,
  `url_large_photo` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` tinyint NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='This is a look-up table of talents a member has that can be deployed in a band. Every member must be mapped to at least one talent.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talents`
--

LOCK TABLES `talents` WRITE;
/*!40000 ALTER TABLE `talents` DISABLE KEYS */;
INSERT INTO `talents` VALUES (1,'Electric Guitar','Solid body guitar with pick-ups, plugs into amplifier','',NULL,1,'2024-08-05 20:28:12',NULL,NULL),(2,'Acoustic Guitar','Hollow body guitar, with or without pre-amp and plug','',NULL,1,'2024-08-05 18:54:05','2024-08-05 20:38:59',NULL),(3,'Bass Guitar','Solid body 4 or 5 string bass guitar with pickups','',NULL,1,'2024-08-05 19:25:43',NULL,NULL),(4,'Cajon','A box with a snare on the inside, usually acoustic','',NULL,1,'2024-08-05 19:38:59',NULL,NULL);
/*!40000 ALTER TABLE `talents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_user_id_idx` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1,'ADMIN','2024-08-05 00:49:50');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_updated_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `date_updated_id_idx` (`last_updated_id`),
  CONSTRAINT `date_updated_id` FOREIGN KEY (`last_updated_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'wendy.castellanos1@gmail.com','$2a$10$ulbfZwP/DKiGyd318FFO1OIsEgrY/Rr1XHgoeEfLAMl/O60.5kMua','2024-08-05 00:46:50',NULL,NULL),(2,'opal.castellanos@gmail.com','$2a$10$C.xH5F6i5wqLViYCUpUE7ess4NF14Yuyeb9debOTllTTZsbWNlpOC','2024-08-05 03:21:40',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-05 15:18:02
