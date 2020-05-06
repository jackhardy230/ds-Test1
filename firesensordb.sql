-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2020 at 09:49 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `firesensordb`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Table structure for table `sensor`
--

CREATE TABLE `sensor` (
  `sensorid` varchar(255) NOT NULL,
  `floor_no` int(11) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `room_no` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sensor`
--

INSERT INTO `sensor` (`sensorid`, `floor_no`, `is_active`, `room_no`) VALUES
('sensor4', 6, b'1', '56'),
('sensor5', 81, b'0', '81'),
('sensor6', 67, b'1', '98');

-- --------------------------------------------------------

--
-- Table structure for table `sensor_data`
--

CREATE TABLE `sensor_data` (
  `sensordataid` varchar(255) NOT NULL,
  `co2level` int(11) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `smoke_level` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `sensor_sensorid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sensor_data`
--

INSERT INTO `sensor_data` (`sensordataid`, `co2level`, `date`, `smoke_level`, `time`, `sensor_sensorid`) VALUES
('sensor4:02/05/2020:19:36:44', 8, '02/05/2020', 2, '19:36:44', 'sensor4'),
('sensor4:02/05/2020:19:37:14', 3, '02/05/2020', 1, '19:37:14', 'sensor4'),
('sensor4:02/05/2020:21:16:01', 10, '02/05/2020', 10, '21:16:01', 'sensor4'),
('sensor4:02/05/2020:21:16:42', 9, '02/05/2020', 3, '21:16:42', 'sensor4'),
('sensor4:02/05/2020:21:17:15', 7, '02/05/2020', 5, '21:17:15', 'sensor4'),
('sensor4:02/05/2020:21:17:49', 10, '02/05/2020', 8, '21:17:49', 'sensor4'),
('sensor4:02/05/2020:21:18:27', 2, '02/05/2020', 0, '21:18:27', 'sensor4'),
('sensor4:02/05/2020:21:18:57', 7, '02/05/2020', 8, '21:18:57', 'sensor4'),
('sensor4:02/05/2020:21:19:36', 8, '02/05/2020', 3, '21:19:36', 'sensor4'),
('sensor4:02/05/2020:21:20:10', 0, '02/05/2020', 9, '21:20:10', 'sensor4'),
('sensor4:02/05/2020:21:20:43', 7, '02/05/2020', 6, '21:20:43', 'sensor4'),
('sensor4:02/05/2020:21:21:21', 1, '02/05/2020', 1, '21:21:21', 'sensor4'),
('sensor4:02/05/2020:21:21:52', 0, '02/05/2020', 2, '21:21:52', 'sensor4'),
('sensor4:02/05/2020:21:22:22', 8, '02/05/2020', 5, '21:22:22', 'sensor4'),
('sensor4:02/05/2020:21:22:56', 5, '02/05/2020', 3, '21:22:56', 'sensor4'),
('sensor4:02/05/2020:21:23:26', 7, '02/05/2020', 7, '21:23:26', 'sensor4');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `type`, `password`) VALUES
(1, 'aaa', 'admin', 'aaa'),
(2, 'bbb', 'admin', 'bbb');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`sensorid`);

--
-- Indexes for table `sensor_data`
--
ALTER TABLE `sensor_data`
  ADD PRIMARY KEY (`sensordataid`),
  ADD KEY `FKpudyjowh2npea1vxon3qcdtjr` (`sensor_sensorid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sensor_data`
--
ALTER TABLE `sensor_data`
  ADD CONSTRAINT `FKpudyjowh2npea1vxon3qcdtjr` FOREIGN KEY (`sensor_sensorid`) REFERENCES `sensor` (`sensorid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
