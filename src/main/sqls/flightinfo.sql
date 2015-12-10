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
  `departuretime` date DEFAULT NULL,
  `landingtime` date NOT NULL,
  `price` double DEFAULT NULL,
  `departurecity` varchar(50) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  `landingcity` varchar(50) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

-- 正在导出表  test.flightinfo 的数据：~36 rows (大约)
DELETE FROM `flightinfo`;
/*!40000 ALTER TABLE `flightinfo` DISABLE KEYS */;
INSERT INTO `flightinfo` (`id`, `flightno`, `parentname`, `departuretime`, `landingtime`, `price`, `departurecity`, `landingcity`) VALUES
	(1, 'JD5119', '携程', '2016-02-05', '2016-02-05', 1304, 'HET', 'URC'),
	(2, 'CZ6181', '携程', '2016-02-05', '2016-02-05', 1400, 'HET', 'URC'),
	(3, 'MU4455', '携程', '2016-02-05', '2016-02-05', 1388, 'HET', 'URC'),
	(4, 'CA1265', '携程', '2016-02-05', '2016-02-05', 1400, 'HET', 'URC'),
	(5, 'JD5119', '携程', '2016-02-06', '2016-02-06', 1304, 'HET', 'URC'),
	(6, 'CZ6181', '携程', '2016-02-06', '2016-02-06', 1400, 'HET', 'URC'),
	(7, 'MU4455', '携程', '2016-02-06', '2016-02-06', 1388, 'HET', 'URC'),
	(8, 'CZ6928', '携程', '2016-02-06', '2016-02-06', 1000, 'HET', 'URC'),
	(9, 'MU4794', '携程', '2016-02-06', '2016-02-06', 981, 'HET', 'URC'),
	(10, 'JD5119', '携程', '2016-02-06', '2016-02-06', 1711, 'PEK', 'URC'),
	(11, 'CA1275', '携程', '2016-02-06', '2016-02-06', 1830, 'PEK', 'URC'),
	(12, 'ZH1275', '携程', '2016-02-06', '2016-02-06', 1802, 'PEK', 'URC'),
	(13, 'CZ6931', '携程', '2016-02-06', '2016-02-06', 1840, 'PEK', 'URC'),
	(14, 'HU7245', '携程', '2016-02-06', '2016-02-06', 2075, 'PEK', 'URC'),
	(15, 'ZH1477', '携程', '2016-02-06', '2016-02-06', 1802, 'PEK', 'URC'),
	(16, 'CA1477', '携程', '2016-02-06', '2016-02-06', 1830, 'PEK', 'URC'),
	(17, 'CZ6904', '携程', '2016-02-06', '2016-02-06', 2100, 'PEK', 'URC'),
	(18, 'MU4778', '携程', '2016-02-06', '2016-02-06', 2100, 'PEK', 'URC'),
	(19, 'CA1901', '携程', '2016-02-06', '2016-02-06', 2080, 'PEK', 'URC'),
	(20, 'ZH1901', '携程', '2016-02-06', '2016-02-06', 2047, 'PEK', 'URC'),
	(21, 'HU7145', '携程', '2016-02-06', '2016-02-06', 2075, 'PEK', 'URC'),
	(22, 'CZ6025', '携程', '2016-02-06', '2016-02-06', 2630, 'PEK', 'URC'),
	(23, 'CA1291', '携程', '2016-02-06', '2016-02-06', 2080, 'PEK', 'URC'),
	(24, 'ZH1291', '携程', '2016-02-06', '2016-02-06', 2047, 'PEK', 'URC'),
	(25, 'MU4776', '携程', '2016-02-06', '2016-02-06', 2100, 'PEK', 'URC'),
	(26, 'CZ6902', '携程', '2016-02-06', '2016-02-06', 2100, 'PEK', 'URC'),
	(27, 'MU4782', '携程', '2016-02-06', '2016-02-06', 1835, 'PEK', 'URC'),
	(28, 'CZ6910', '携程', '2016-02-06', '2016-02-06', 1840, 'PEK', 'URC'),
	(29, 'CA1295', '携程', '2016-02-06', '2016-02-07', 1830, 'PEK', 'URC'),
	(30, 'ZH1295', '携程', '2016-02-06', '2016-02-07', 1970, 'PEK', 'URC'),
	(31, 'HU7345', '携程', '2016-02-06', '2016-02-07', 2075, 'PEK', 'URC'),
	(32, 'JD5119', '携程', '2016-02-04', '2016-02-04', 1304, 'HET', 'URC'),
	(33, 'CZ6181', '携程', '2016-02-04', '2016-02-04', 1400, 'HET', 'URC'),
	(34, 'MU4455', '携程', '2016-02-04', '2016-02-04', 1388, 'HET', 'URC'),
	(35, 'CZ6928', '携程', '2016-02-04', '2016-02-04', 1000, 'HET', 'URC'),
	(36, 'MU4794', '携程', '2016-02-04', '2016-02-04', 981, 'HET', 'URC'),
	(37, 'JD5119', '携程', '2016-02-06', '2016-02-06', 1325, '呼和浩特(HET)', '乌鲁木齐(URC)');
/*!40000 ALTER TABLE `flightinfo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
