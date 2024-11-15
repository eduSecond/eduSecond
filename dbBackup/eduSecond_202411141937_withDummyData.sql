-- MySQL dump 10.13  Distrib 9.0.1, for macos15.0 (arm64)
--
-- Host: localhost    Database: eduSecond
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `tbl_admin`
--

DROP TABLE IF EXISTS `tbl_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_admin` (
  `adminId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `adminPw` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `lastLoginDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chat_message`
--

DROP TABLE IF EXISTS `tbl_chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_chat_message` (
  `messageId` int NOT NULL AUTO_INCREMENT,
  `roomId` int NOT NULL,
  `senderId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `message` text COLLATE utf8mb4_general_ci NOT NULL,
  `isRead` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '읽음여부',
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageId`),
  KEY `roomId` (`roomId`),
  KEY `senderId` (`senderId`),
  CONSTRAINT `tbl_chat_message_ibfk_1` FOREIGN KEY (`roomId`) REFERENCES `tbl_chatroom` (`roomId`),
  CONSTRAINT `tbl_chat_message_ibfk_2` FOREIGN KEY (`senderId`) REFERENCES `tbl_member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chatroom`
--

DROP TABLE IF EXISTS `tbl_chatroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_chatroom` (
  `roomId` int NOT NULL AUTO_INCREMENT,
  `productId` int NOT NULL,
  `sellerId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `buyerId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sellerActive` char(1) COLLATE utf8mb4_general_ci DEFAULT 'Y',
  `buyerActive` char(1) COLLATE utf8mb4_general_ci DEFAULT 'Y',
  PRIMARY KEY (`roomId`),
  KEY `productId` (`productId`),
  KEY `sellerId` (`sellerId`),
  KEY `buyerId` (`buyerId`),
  CONSTRAINT `tbl_chatroom_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tbl_product` (`productId`),
  CONSTRAINT `tbl_chatroom_ibfk_2` FOREIGN KEY (`sellerId`) REFERENCES `tbl_member` (`userId`),
  CONSTRAINT `tbl_chatroom_ibfk_3` FOREIGN KEY (`buyerId`) REFERENCES `tbl_member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_member`
--

DROP TABLE IF EXISTS `tbl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_member` (
  `userId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `userPw` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `userName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `userEmail` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `userPhone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `userAddress` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `userPostcode` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `userBirth` date NOT NULL,
  `enabled` char(1) COLLATE utf8mb4_general_ci DEFAULT 'Y' COMMENT '회원상태(Y:활성/N:비활성)',
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_notice`
--

DROP TABLE IF EXISTS `tbl_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_notice` (
  `noticeId` int NOT NULL AUTO_INCREMENT,
  `adminId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `content` text COLLATE utf8mb4_general_ci NOT NULL,
  `viewCount` int DEFAULT '0',
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`noticeId`),
  KEY `adminId` (`adminId`),
  CONSTRAINT `tbl_notice_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `tbl_admin` (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_order`
--

DROP TABLE IF EXISTS `tbl_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_order` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `productId` int NOT NULL,
  `buyerId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `orderQuantity` int NOT NULL DEFAULT '1',
  `unitPrice` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `orderStatus` varchar(50) not null default '구매대기',
  `recipientName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '수령인',
  `recipientPhone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '수령인 연락처',
  `recipientEmail` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '수령인 이메일',
  `shippingAddress` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '배송지 주소',
  `shippingPostcode` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '배송지 우편번호',
  `paymentMethod` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `paymentCompany` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `paymentNumber` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `deliveryStatus` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '배송전' NOT NULL,
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderId`),
  KEY `productId` (`productId`),
  KEY `buyerId` (`buyerId`),
  CONSTRAINT `tbl_order_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tbl_product` (`productId`),
  CONSTRAINT `tbl_order_ibfk_2` FOREIGN KEY (`buyerId`) REFERENCES `tbl_member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_product`
--

DROP TABLE IF EXISTS `tbl_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_product` (
  `productId` int NOT NULL AUTO_INCREMENT,
  `sellerId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `productName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `productDesc` text COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `quality` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `productStatus` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'AVAILABLE',
  `viewCount` int DEFAULT '0',
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `sellerId` (`sellerId`),
  CONSTRAINT `tbl_product_ibfk_1` FOREIGN KEY (`sellerId`) REFERENCES `tbl_member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_product_image`
--

DROP TABLE IF EXISTS `tbl_product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_product_image` (
  `imageId` int NOT NULL AUTO_INCREMENT,
  `productId` int NOT NULL,
  `imagePath` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `isMain` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N',
  PRIMARY KEY (`imageId`),
  KEY `productId` (`productId`),
  CONSTRAINT `tbl_product_image_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tbl_product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_review`
--

DROP TABLE IF EXISTS `tbl_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_review` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `orderId` int NOT NULL,
  `writerId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `content` text COLLATE utf8mb4_general_ci NOT NULL,
  `rating` int NOT NULL,
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `orderId` (`orderId`),
  KEY `writerId` (`writerId`),
  CONSTRAINT `tbl_review_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `tbl_order` (`orderId`),
  CONSTRAINT `tbl_review_ibfk_2` FOREIGN KEY (`writerId`) REFERENCES `tbl_member` (`userId`),
  CONSTRAINT `tbl_review_chk_1` CHECK (((`rating` >= 1) and (`rating` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_wishlist`
--

DROP TABLE IF EXISTS `tbl_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_wishlist` (
  `wishId` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `productId` int NOT NULL,
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`wishId`),
  KEY `userId` (`userId`),
  KEY `productId` (`productId`),
  CONSTRAINT `tbl_wishlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tbl_member` (`userId`),
  CONSTRAINT `tbl_wishlist_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `tbl_product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'eduSecond'
--
/*!50003 DROP PROCEDURE IF EXISTS `generate_members` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generate_members`()
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 100 DO
        INSERT INTO tbl_member (
            userId, userPw, userName, userEmail, userPhone, 
            userAddress, userPostcode, userBirth, enabled, regDate
        ) VALUES (
            CONCAT('user', i+1),
            CONCAT('Pass', i+1, '!@#'),
            CONCAT('사용자', i+1),
            CONCAT('user', i+1, '@test.com'),
            CONCAT('010-', LPAD(FLOOR(RAND()*9999), 4, '0'), '-', LPAD(FLOOR(RAND()*9999), 4, '0')),
            CONCAT('서울시 ', ELT(FLOOR(1+RAND()*5), '강남구', '서초구', '송파구', '강동구', '마포구'), ' 테스트동 ', i+1, '번지'),
            LPAD(FLOOR(RAND()*99999), 5, '0'),
            DATE_SUB(CURDATE(), INTERVAL FLOOR(20+RAND()*30) YEAR),
            ELT(1+FLOOR(RAND()*10), 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'N'),
            DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*365) DAY)
        );
        SET i = i + 1;
    END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `generate_orders` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generate_orders`()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE product_count INT;
    DECLARE member_count INT;
    
    -- 실제 존재하는 상품과 회원 수 확인
    SELECT COUNT(*) INTO product_count FROM tbl_product;
    SELECT COUNT(*) INTO member_count FROM tbl_member;
    
    WHILE i < 50 AND i < product_count DO  -- 상품 수보다 적게 생성
        INSERT INTO tbl_order (
            productId, buyerId, orderQuantity, recipientName,
            recipientPhone, recipientEmail, shippingAddress,
            shippingPostcode, paymentMethod, paymentCompany,
            paymentNumber, orderStatus, deliveryStatus
        ) 
        SELECT 
            p.productId,
            m.userId,
            1,
            m.userName,
            m.userPhone,
            m.userEmail,
            m.userAddress,
            m.userPostcode,
            ELT(1+FLOOR(RAND()*2), 'CARD', 'BANK'),
            ELT(1+FLOOR(RAND()*4), '신한', '국민', '우리', '하나'),
            LPAD(FLOOR(RAND()*999999999999), 12, '0'),
            'PAID',
            'READY'
        FROM 
            tbl_product p,
            tbl_member m 
        WHERE 
            p.productId = i + 1
            AND m.userId != p.sellerId  -- 판매자와 구매자가 다른 경우만
        LIMIT 1;
        
        SET i = i + 1;
    END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `generate_products` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generate_products`()
BEGIN
    DECLARE i INT DEFAULT 0;
    WHILE i < 100 DO
        INSERT INTO tbl_product (
            sellerId, productName, productDesc, price, quantity,
            quality, productStatus, viewCount, regDate
        ) VALUES (
            CONCAT('user', 1+FLOOR(RAND()*100)),
            CONCAT('상품', i+1, ' ', ELT(1+FLOOR(RAND()*5), '노트북', '스마트폰', '태블릿', '이어폰', '스마트워치')),
            CONCAT('이 상품은 상품', i+1, '입니다. 좋은 상품입니다.'),
            FLOOR(10000 + RAND()*990000),
            1+FLOOR(RAND()*5),
            ELT(1+FLOOR(RAND()*4), 'NEW', 'LIKE_NEW', 'GOOD', 'FAIR'),
            ELT(1+FLOOR(RAND()*3), 'AVAILABLE', 'RESERVED', 'SOLD'),
            FLOOR(RAND()*1000),
            DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*90) DAY)
        );
        SET i = i + 1;
    END WHILE;
END ;;
DELIMITER ;

DELIMITER //
CREATE PROCEDURE reductAfterPayment(IN p_productId INT, IN orderQuantity INT)
BEGIN
    DECLARE availableQuantity INT;
    -- 현재 재고 확인
    SELECT quantity INTO availableQuantity
    FROM tbl_product
    WHERE productId = p_productId;
    -- 재고가 충분한지 확인
    IF availableQuantity >= orderQuantity THEN
        -- 재고 감소
        UPDATE tbl_product
        SET quantity = quantity - orderQuantity
        WHERE productId = p_productId;
        -- 감소 후 재고 확인 및 상태 변경
        IF (availableQuantity - orderQuantity) = 0 THEN
            UPDATE tbl_product
            SET productStatus = 'SOLDOUT'
            WHERE productId = p_productId;
        END IF;
    ELSE
        -- 재고 부족 시 예외 발생
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient stock';
    END IF;
END //
DELIMITER ;

/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 18:32:33
