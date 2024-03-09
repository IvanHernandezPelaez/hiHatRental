-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: hi_hat_rental
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Categoría 1 actualizada'),(2,'Categoría 2'),(4,'Categoría 4');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `producto_id` bigint DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhylof04iou23s0pb9ab6pbd4j` (`producto_id`),
  CONSTRAINT `FKhylof04iou23s0pb9ab6pbd4j` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (1,1,'url 1010'),(2,1,'url 2020'),(3,2,'url 1010'),(4,2,'url 2020'),(5,3,'url 1010'),(6,3,'url 2020'),(9,NULL,'url 111'),(10,NULL,'url 222'),(11,NULL,'url 333'),(12,NULL,'url 444'),(13,NULL,'url 555'),(14,5,'url 111'),(15,5,'url 222'),(16,5,'url 333'),(17,5,'url 444'),(18,5,'url 555'),(19,6,'url 111'),(20,6,'url 222'),(21,6,'url 333'),(22,6,'url 444'),(23,6,'url 555'),(24,7,'url 111'),(25,7,'url 222'),(26,7,'url 333'),(27,7,'url 444'),(28,7,'url 555'),(29,8,'url 111'),(30,8,'url 222'),(31,8,'url 333'),(32,8,'url 444'),(33,8,'url 555'),(34,NULL,'url 111'),(35,NULL,'url 222'),(36,NULL,'url 333'),(37,NULL,'url 444'),(38,NULL,'url 555'),(39,NULL,'url 111'),(40,NULL,'url 222'),(41,NULL,'url 333'),(42,NULL,'url 444'),(43,NULL,'url 555'),(44,NULL,'url 1010'),(45,NULL,'url 2020'),(46,NULL,'url 3030'),(47,NULL,'url 4040'),(48,NULL,'url 5050'),(49,NULL,'url 1010'),(50,NULL,'url 2020'),(51,3,'url 3030'),(52,3,'url 4040'),(53,3,'url 5050'),(54,NULL,'url 1010'),(55,NULL,'url 2020'),(56,3,'url 3030'),(57,3,'url 4040'),(58,3,'url 5050'),(59,2,'url 3030'),(60,2,'url 4040'),(61,2,'url 5050'),(62,1,'url 3030'),(63,1,'url 4040'),(64,1,'url 5050'),(65,10,'url 2021'),(66,10,'url 2022'),(67,10,'url 3030'),(68,10,'url 4040'),(69,10,'url 5050'),(70,11,'url 1111'),(71,11,'url 1112'),(72,11,'url 1113'),(73,11,'url 1114'),(74,11,'url 1115'),(75,NULL,'url 2021'),(76,NULL,'url 2022'),(77,12,'url 2021'),(78,12,'url 2022'),(79,12,'url 1113'),(80,12,'url 1114'),(81,12,'url 1115'),(82,NULL,'url 2021'),(83,NULL,'url 2022'),(84,13,'url 2021'),(85,13,'url 2022'),(86,13,'url 1113'),(87,13,'url 1114'),(88,13,'url 1115'),(89,NULL,'url 2021'),(90,NULL,'url 2022'),(91,14,'url 2021'),(92,14,'url 2022'),(93,14,'url 1113'),(94,14,'url 1114'),(95,14,'url 1115'),(96,NULL,'url 2021'),(97,NULL,'url 2022'),(98,15,'url 2021'),(99,15,'url 2022'),(100,15,'url 1113'),(101,15,'url 1114'),(102,15,'url 1115'),(103,NULL,'url 2021'),(104,NULL,'url 2022'),(105,16,'url 2021'),(106,16,'url 2022'),(107,16,'url 1113'),(108,16,'url 1114'),(109,16,'url 1115'),(110,NULL,'url 2021'),(111,NULL,'url 2022'),(112,17,'url 2021'),(113,17,'url 2022'),(117,NULL,'url 2021'),(118,NULL,'url 2022'),(119,18,'url 2021'),(120,18,'url 2022'),(124,NULL,'url 2021'),(125,NULL,'url 2022'),(126,19,'url 2021'),(127,19,'url 2022'),(131,NULL,'url 2021'),(132,NULL,'url 2022'),(133,20,'url 2021'),(134,20,'url 2022'),(138,21,'url 1010'),(139,21,'url 2020'),(140,21,'url 3030'),(141,21,'url 4040'),(142,21,'url 5050'),(143,22,'url 1010'),(144,22,'url 2020'),(145,22,'url 3030'),(146,22,'url 4040'),(147,22,'url 5050'),(148,23,'url 1010'),(149,23,'url 2020'),(153,24,'url 0001'),(154,24,'url 0001'),(155,24,'url 0003'),(156,24,'url 0004'),(157,24,'url 0005'),(158,25,'url 0001'),(159,25,'url 0001'),(160,25,'url 0003'),(161,25,'url 0004'),(162,25,'url 0009'),(163,26,'url 1111'),(164,26,'url 2222'),(165,26,'url 3333'),(166,26,'url 4444'),(167,26,'url 5555');
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `categoria_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`),
  CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (4,1,'Descripcion Actualizada','Producto actualizado'),(4,2,'Descripcion Actualizada','Producto actualizado'),(4,3,'Descripcion Actualizada','Producto actualizado'),(4,5,'Descripcion 4','Producto 5'),(4,6,'Descripcion 4','Producto 6'),(4,7,'Descripcion 4','Producto 7'),(4,8,'Descripcion 9','Producto 9'),(4,9,'Descripcion 999','Producto 999'),(4,10,'Descripcion Actualizada','Producto actualizado'),(4,11,'Descripcion 4','Producto 11'),(4,12,'Descripcion Actualizada','Producto actualizado'),(4,13,'Descripcion Actualizada','Producto actualizado'),(4,14,'Descripcion Actualizada','Producto actualizado'),(4,15,'Descripcion Actualizada','Producto actualizado'),(4,16,'Descripcion Actualizada','Producto actualizado'),(4,17,'Descripcion Actualizada','Producto actualizado'),(4,18,'Descripcion Actualizada','Producto actualizado'),(4,19,'Descripcion Actualizada','Producto actualizado'),(4,20,'Descripcion Actualizada','Producto actualizado'),(4,21,'Descripcion Actualizada','Producto actualizado'),(4,22,'Descripcion Actualizada','Producto actualizado'),(4,23,'Descripcion Actualizada','Producto actualizado'),(4,24,'Descripcion Actualizada','Producto actualizado'),(1,25,'Descripcion Actualizada','Producto actualizado'),(4,26,'Descripcion 25','Producto 25');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-09 15:46:06
