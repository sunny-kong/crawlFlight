-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 test.flightinfo 结构
DROP TABLE IF EXISTS `flightinfo`;
CREATE TABLE IF NOT EXISTS `flightinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flightno` varchar(50) COLLATE utf8mb4_czech_ci NOT NULL,
  `parentname` varchar(50) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  `departuretime` timestamp NULL DEFAULT NULL,
  `landingtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `price` double DEFAULT NULL,
  `departurecity` varchar(50) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  `landingcity` varchar(50) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  `optiontime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `flightno_departuretime_landingtime_price` (`flightno`,`departuretime`,`landingtime`,`price`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

-- 正在导出表  test.flightinfo 的数据：~1 rows (大约)
DELETE FROM `flightinfo`;
/*!40000 ALTER TABLE `flightinfo` DISABLE KEYS */;
INSERT INTO `flightinfo` (`id`, `flightno`, `parentname`, `departuretime`, `landingtime`, `price`, `departurecity`, `landingcity`, `optiontime`) VALUES
	(255, 'JD5119', '携程', '2016-02-06 09:05:00', '2016-02-06 12:40:00', 1325, '呼和浩特(HET)', '乌鲁木齐(URC)', '2015-12-11 17:44:16');
/*!40000 ALTER TABLE `flightinfo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
