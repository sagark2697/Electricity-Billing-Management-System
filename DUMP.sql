-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: fall2018dbmahadik.cu7msbjqemhu.us-east-2.rds.amazonaws.com    Database: Electricity_Billing_Details_Incr3
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='';

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `login_id` varchar(255) NOT NULL,
  `login_password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `zip_code` varchar(5) NOT NULL,
  `phone_no_primary` varchar(13) NOT NULL,
  `phone_no_secondary` varchar(13) DEFAULT NULL,
  `employee_number` varchar(50) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `employee_number` (`employee_number`),
  KEY `zip_code` (`zip_code`),
  CONSTRAINT `fk_Admin_1` FOREIGN KEY (`zip_code`) REFERENCES `Zipcode_Details` (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES ('andy2gmail.com','andy','Andy','Wachowski','9547 library lane','','31285','1780987-9787','','100'),('rocky.21@gmail.com','Rocky','Rocky','smith','9547 University lane','','35132','1(904)987-978','','101');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_email_id` varchar(255) NOT NULL,
  `login_password` varchar(255) NOT NULL,
  `ssn` varchar(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `zip_code` varchar(5) NOT NULL,
  `phone_no_primary` varchar(13) NOT NULL,
  `phone_no_secondary` varchar(13) DEFAULT NULL,
  `service_provider_name` varchar(50) NOT NULL,
  `register_date` date NOT NULL,
  `meter_type` enum('Electric','Solar') NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `ssn_UNIQUE` (`ssn`),
  UNIQUE KEY `login_email_id_UNIQUE` (`login_email_id`),
  KEY `zip_code` (`zip_code`),
  KEY `service_provider_name_idx` (`service_provider_name`),
  CONSTRAINT `fk_service_provider_name` FOREIGN KEY (`service_provider_name`) REFERENCES `Service_Provider` (`provider_name`),
  CONSTRAINT `fk_zipcode_1` FOREIGN KEY (`zip_code`) REFERENCES `Zipcode_Details` (`zip_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'sed.pede.nec@maurisanunc.co.uk','zsjdk@65','531349579','Lars','Keith','P.O. Box 795, 3438 Consectetuer Street','Ap #810-587 Odio. St.','31285','971-821-2399','1-822-395-977','ENGIE','2018-05-23','Electric'),(2,'Cras.lorem@gmail.com','abcd','769957804','Chadwick','Gallegos','3025 Quam. Road','7490 Leo Rd.','91106','478-274-7169','1-823-606-877','ENGIE','2008-10-09','Solar'),(3,'fermen0tum@gmail.com','zsjdk@65','792228819','Richard','Hahn','Ap #124-6114 Nonummy St.','P.O. Box 747, 7713 Proin St.','34912','253-588-3159','1-242-239-974','NextEra Energy','2005-03-27','Electric'),(4,'Vivamus@gmail.com','zsjdk@65','585314115','Madonna','Reynolds','4060 Dapibus Avenue','882-1519 Ipsum Avenue','35132','793-103-0076','1-620-331-907','Duke Energy','2016-09-08','Electric'),(5,'mauris@yahoo.com','zsjdk@65','445237639','Hiroko','Potts','Ap #413-2220 Sem, Ave','Ap #162-7256 A, Ave','97788','139-210-3455','1-405-770-116','Duke Energy','2006-07-16','Electric'),(6,'hdjkasd@gmail.com','jfshjb','458457896','kavya','poland','201 tri street',NULL,'97788','234-234-1323',NULL,'NextEra Energy','2010-10-10','Solar'),(7,'crans@gmail.com','kjgh@1234','546985201','Crany','Lewis','215 Trion Street','Apt L','34912','985-652-4116','1-658-541-548','Duke Energy','2012-05-10','Solar');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Electricity_Billing_Details`
--

DROP TABLE IF EXISTS `Electricity_Billing_Details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Electricity_Billing_Details` (
  `electricity_bill_id` int(11) NOT NULL,
  `bill_generated_date` date NOT NULL,
  `bill_due_date` date NOT NULL,
  `billing_cycle_start_date` date NOT NULL,
  `number_of_billing_days` int(100) NOT NULL,
  `payment_amount` double NOT NULL,
  `amount_due` double NOT NULL,
  `meter_reading_id` int(11) NOT NULL,
  `payment_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`electricity_bill_id`),
  KEY `fk_meter_reading_id_idx` (`meter_reading_id`),
  KEY `payment_id_idx` (`payment_id`),
  CONSTRAINT `fk_meter_reading_id` FOREIGN KEY (`meter_reading_id`) REFERENCES `Meter_Reading` (`meter_reading_id`) ON DELETE CASCADE,
  CONSTRAINT `payment_id` FOREIGN KEY (`payment_id`) REFERENCES `Payment` (`payment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Electricity_Billing_Details`
--

LOCK TABLES `Electricity_Billing_Details` WRITE;
/*!40000 ALTER TABLE `Electricity_Billing_Details` DISABLE KEYS */;
INSERT INTO `Electricity_Billing_Details` VALUES (10000,'2018-08-23','2018-09-17','2018-07-22',30,254,254,6,105),(10002,'2018-09-25','2018-10-10','2018-08-20',30,240.88,240.88,4,103),(10003,'2018-09-26','2018-10-15','2018-08-25',25,255.84,255.84,5,104),(10004,'2018-09-23','2018-10-17','2018-08-22',30,240,240,7,NULL),(10005,'2018-09-20','2018-10-15','2018-08-18',31,189.88,189.88,9,107),(10018,'2018-10-23','2018-11-17','2018-09-22',30,152,152,8,106),(100002,'2018-07-18','2018-08-15','2018-06-15',36,250.8,250.8,1,100),(100010,'2018-09-18','2018-10-10','2018-08-16',35,209,209,2,101),(100036,'2018-10-18','2018-11-15','2018-09-15',36,209.54,209.54,3,102),(100037,'2018-10-22','2018-11-12','2018-09-21',31,133.4,133.4,10,108),(100038,'2018-11-21','2018-12-11','2018-10-23',28,211.52,211.52,11,NULL);
/*!40000 ALTER TABLE `Electricity_Billing_Details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Meter_Reading`
--

DROP TABLE IF EXISTS `Meter_Reading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Meter_Reading` (
  `meter_number` int(10) NOT NULL,
  `peak_hour_reading` double DEFAULT '0',
  `off_peak_hour_reading` double NOT NULL DEFAULT '0',
  `meter_reading_date` date NOT NULL,
  `total_current_reading` double DEFAULT '0',
  `total_previous_reading` double DEFAULT '0',
  `customer_id` bigint(20) DEFAULT NULL,
  `meter_reading_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`meter_reading_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `fk_customer_id_2` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meter_Reading`
--

LOCK TABLES `Meter_Reading` WRITE;
/*!40000 ALTER TABLE `Meter_Reading` DISABLE KEYS */;
INSERT INTO `Meter_Reading` VALUES (1001,120,60,'2018-07-18',280,100,1,1),(1001,100,50,'2018-09-18',630,480,1,2),(1001,106,41,'2018-10-18',777,630,1,3),(1002,86,54,'2018-09-25',347,200,2,4),(1003,109,59,'2018-09-26',668,500,3,5),(1004,112,86,'2018-08-23',798,600,4,6),(1004,96,80,'2018-09-23',565,389,4,7),(1004,44,86,'2018-10-23',695,565,4,8),(1005,98,61,'2018-09-20',389,230,5,9),(1005,75,35,'2018-10-22',494,389,5,10),(1005,112,64,'2018-11-21',670,494,5,11);
/*!40000 ALTER TABLE `Meter_Reading` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `payment_id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `payment_amount` double DEFAULT NULL,
  `date_of_payment` date DEFAULT NULL,
  `card_number` varchar(20) NOT NULL,
  `cvv` int(3) NOT NULL,
  `card_expiry` varchar(5) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (100,1,250.8,'2018-08-10','1254698765789856',659,'05/23'),(101,1,209,'2018-09-26','6589451496523248',985,'12/25'),(102,1,209.54,'2018-11-06','5489365101489657',250,'04/24'),(103,2,240.88,'2018-10-04','6589015603694156',698,'11/25'),(104,3,255.84,'2018-10-10','6951023568420159',256,'06/25'),(105,4,254,'2018-09-10','6985410256329845',256,'12/26'),(106,4,152,'2018-11-16','6541023598754103',358,'05/22'),(107,5,189.88,'2018-10-25','6984152102356984',148,'04/23'),(108,5,133.4,'2018-11-01','1478954152301458',547,'05/24');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`gaurav_flash`@`%`*/ /*!50003 TRIGGER after_payment_insert
	AFTER INSERT ON Payment
    FOR EACH ROW
BEGIN
	DECLARE cust_id bigint(11);
    DECLARE pay_id int ;
		Select payment_id, customer_id INTO pay_id, cust_id from Payment where 
		date_of_payment BETWEEN  current_timestamp() - interval 1 second AND current_timestamp();
	UPDATE Electricity_Billing_Details eb1 set eb1.payment_id=pay_id where eb1.meter_reading_id 
    IN (Select t.meter_reading_id from 
   (Select * from Electricity_Billing_Details eb where eb.meter_reading_id IN (Select meter_reading_id 
     from Meter_Reading where customer_id=cust_id)
     AND eb.payment_id IS NULL) t);
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Service_Provider`
--

DROP TABLE IF EXISTS `Service_Provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service_Provider` (
  `provider_name` varchar(30) NOT NULL,
  `license_number` varchar(20) NOT NULL,
  `office_address_line_1` varchar(50) NOT NULL,
  `office_address_Line_2` varchar(50) DEFAULT NULL,
  `zip_code` varchar(5) NOT NULL,
  `head_office_number` varchar(13) NOT NULL,
  `helpline_number` varchar(13) NOT NULL,
  PRIMARY KEY (`provider_name`),
  UNIQUE KEY `license_number_UNIQUE` (`license_number`),
  KEY `zip_code_idx` (`zip_code`),
  CONSTRAINT `fk_zip_code` FOREIGN KEY (`zip_code`) REFERENCES `Zipcode_Details` (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service_Provider`
--

LOCK TABLES `Service_Provider` WRITE;
/*!40000 ALTER TABLE `Service_Provider` DISABLE KEYS */;
INSERT INTO `Service_Provider` VALUES ('Duke Energy','12345678','201 Tryon Street',NULL,'35132','(526)548-4569','(526)896-1236'),('Engie','54513257','78 street',NULL,'34912','(524)245-8968','(524)478-2145'),('NextEra Energy','78945612','601 Travis Street','','31285','(305)246-1300','(321)457-9634');
/*!40000 ALTER TABLE `Service_Provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tariff_Details`
--

DROP TABLE IF EXISTS `Tariff_Details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tariff_Details` (
  `zip_code` varchar(5) NOT NULL,
  `provider_name` varchar(30) NOT NULL,
  `peak_time_charge` double NOT NULL,
  `off_peak_time_charge` double NOT NULL,
  `peak_time` varchar(30) NOT NULL DEFAULT '0',
  `off_peak_time` varchar(30) NOT NULL DEFAULT '0',
  PRIMARY KEY (`zip_code`,`provider_name`),
  KEY `provider_name` (`provider_name`),
  CONSTRAINT `fk_Tariff_Details_1` FOREIGN KEY (`provider_name`) REFERENCES `Service_Provider` (`provider_name`),
  CONSTRAINT `fk_Tariff_Details_2` FOREIGN KEY (`zip_code`) REFERENCES `Zipcode_Details` (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tariff_Details`
--

LOCK TABLES `Tariff_Details` WRITE;
/*!40000 ALTER TABLE `Tariff_Details` DISABLE KEYS */;
INSERT INTO `Tariff_Details` VALUES ('31285','ENGIE',1.59,1,'8:00 AM-8:00 PM','8:01 PM- 7.59 AM'),('34912','NextEra Energy',1.86,0.9,'10:00 AM-10:PM','10:01 PM-9:59 AM'),('35132','Duke Energy',1.5,1,'10:00 AM-10:PM','10:00 AM-10:PM'),('91106','ENGIE',2.06,1.18,'8:00 AM-8:00 PM','8:01 PM- 7.59 AM'),('97788','Duke Energy',1.34,0.96,'10:00 AM-10:PM','10:00 AM-10:PM'),('97788','NextEra Energy',1.86,0.9,'10:00 AM-10:PM','10:01 PM-9:59 AM');
/*!40000 ALTER TABLE `Tariff_Details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Zipcode_Details`
--

DROP TABLE IF EXISTS `Zipcode_Details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Zipcode_Details` (
  `zip_code` varchar(5) NOT NULL,
  `state` varchar(50) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zipcode_Details`
--

LOCK TABLES `Zipcode_Details` WRITE;
/*!40000 ALTER TABLE `Zipcode_Details` DISABLE KEYS */;
INSERT INTO `Zipcode_Details` VALUES ('31285','Florida','Miami'),('34912','Kansas','Kansas City'),('35132','Alabama','Mobile'),('91106','Maryland','Gaithersburg'),('97788','Florida','Miami');
/*!40000 ALTER TABLE `Zipcode_Details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `customer_count_under_service_providers`
--

DROP TABLE IF EXISTS `customer_count_under_service_providers`;
/*!50001 DROP VIEW IF EXISTS `customer_count_under_service_providers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customer_count_under_service_providers` AS SELECT 
 1 AS `Provider Name`,
 1 AS `Total Number of Customers`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `customer_electricity_bills`
--

DROP TABLE IF EXISTS `customer_electricity_bills`;
/*!50001 DROP VIEW IF EXISTS `customer_electricity_bills`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customer_electricity_bills` AS SELECT 
 1 AS `electricity_bill_id`,
 1 AS `customer_id`,
 1 AS `bill_generated_date`,
 1 AS `bill_due_date`,
 1 AS `billing_cycle_start_date`,
 1 AS `number_of_billing_days`,
 1 AS `payment_amount`,
 1 AS `amount_due`,
 1 AS `meter_reading_id`,
 1 AS `payment_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `payment_defaulters`
--

DROP TABLE IF EXISTS `payment_defaulters`;
/*!50001 DROP VIEW IF EXISTS `payment_defaulters`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `payment_defaulters` AS SELECT 
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `amount_due`,
 1 AS `bill_due_date`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'Electricity_Billing_Details_Incr3'
--

--
-- Dumping routines for database 'Electricity_Billing_Details_Incr3'
--
/*!50003 DROP PROCEDURE IF EXISTS `generate_electricity_bill` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`gaurav_flash`@`%` PROCEDURE `generate_electricity_bill`(IN meter_num int(10), 
											IN peak_hr_reading double, 
											IN off_peak_hr_reading double,
                                            IN cust_id bigint(20))
BEGIN
declare total_prev_reading double;
declare meterid int(11);
declare service_provider varchar(50);
declare off_peak_charge double;
declare peak_charge double;
declare zipcode varchar(5);
declare amt_due double;
declare max_bill_id int(11);

Select total_current_reading INTO total_prev_reading from Meter_Reading where customer_id=cust_id 
AND month(meter_reading_date)=month(current_date())-1;

IF total_prev_reading IS NULL THEN
set total_prev_reading=0.0;
END IF;
INSERT INTO Meter_Reading(meter_number,
peak_hour_reading,off_peak_hour_reading,meter_reading_date, total_current_reading,
total_previous_reading,customer_id) values (meter_num, peak_hr_reading,
off_peak_hr_reading, current_date(),peak_hr_reading+off_peak_hr_reading+total_prev_reading,total_prev_reading,cust_id);

Select meter_reading_id INTO 
meterid from Meter_Reading where customer_id=cust_id AND meter_reading_date=current_date();

Select service_provider_name,zip_code INTO service_provider, zipcode FROM Customer where customer_id=cust_id;


Select off_peak_time_charge, peak_time_charge INTO off_peak_charge, peak_charge FROM Tariff_Details 
where provider_name=service_provider AND zip_code=zipcode;

call get_amount_due(cust_id,@amount_due);
select @amount_due INTO amt_due;
IF amt_due IS NULL THEN
set amt_due=0.0;
END IF ;

select Max(electricity_bill_id) INTO max_bill_id from Electricity_Billing_Details;

INSERT INTO Electricity_Billing_Details(electricity_bill_id,meter_reading_id,bill_generated_date,
bill_due_date,billing_cycle_start_date,
number_of_billing_days,payment_amount, amount_due) values
(max_bill_id+1,meterid, current_date(), current_date() + interval 20 day, 
current_date() - interval 30 day, 30, off_peak_hr_reading*off_peak_charge + peak_hr_reading* peak_charge, 
ifnull(amt_due,0.00)+(off_peak_hr_reading*off_peak_charge) + (peak_hr_reading* peak_charge));

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_amount_due` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`gaurav_flash`@`%` PROCEDURE `get_amount_due`(IN cust_id bigint(20), OUT amountDue double)
BEGIN
 Select amount_due INTO amountDue from Electricity_Billing_Details
 INNER JOIN Meter_Reading using(meter_reading_id) where month(meter_reading_date)
 =month(current_date())-1 AND customer_id=cust_id AND payment_id IS NULL;
 END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `customer_count_under_service_providers`
--

/*!50001 DROP VIEW IF EXISTS `customer_count_under_service_providers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`gaurav_flash`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_count_under_service_providers` AS select `c`.`service_provider_name` AS `Provider Name`,count(`c`.`customer_id`) AS `Total Number of Customers` from (`Customer` `c` join `Service_Provider` `sp` on((`sp`.`provider_name` = `c`.`service_provider_name`))) group by `sp`.`provider_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `customer_electricity_bills`
--

/*!50001 DROP VIEW IF EXISTS `customer_electricity_bills`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`gaurav_flash`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_electricity_bills` AS select `e`.`electricity_bill_id` AS `electricity_bill_id`,`m`.`customer_id` AS `customer_id`,`e`.`bill_generated_date` AS `bill_generated_date`,`e`.`bill_due_date` AS `bill_due_date`,`e`.`billing_cycle_start_date` AS `billing_cycle_start_date`,`e`.`number_of_billing_days` AS `number_of_billing_days`,`e`.`payment_amount` AS `payment_amount`,`e`.`amount_due` AS `amount_due`,`e`.`meter_reading_id` AS `meter_reading_id`,`e`.`payment_id` AS `payment_id` from ((`Electricity_Billing_Details` `e` join `Meter_Reading` `m` on((`e`.`meter_reading_id` = `m`.`meter_reading_id`))) join `Customer` `c` on((`m`.`customer_id` = `c`.`customer_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `payment_defaulters`
--

/*!50001 DROP VIEW IF EXISTS `payment_defaulters`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`gaurav_flash`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `payment_defaulters` AS select `c`.`first_name` AS `first_name`,`c`.`last_name` AS `last_name`,`e`.`amount_due` AS `amount_due`,`e`.`bill_due_date` AS `bill_due_date` from ((`Customer` `c` join `Meter_Reading` `mr` on((`mr`.`customer_id` = `c`.`customer_id`))) join `Electricity_Billing_Details` `e` on((`e`.`meter_reading_id` = `mr`.`meter_reading_id`))) where ((`e`.`bill_due_date` < curdate()) and isnull(`e`.`payment_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 23:24:44
