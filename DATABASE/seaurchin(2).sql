-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2024 at 01:00 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `seaurchin`
--

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_id` int(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `latitude` decimal(11,8) NOT NULL,
  `longitude` decimal(12,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `address`, `latitude`, `longitude`) VALUES
(2, 'VVVM+Q38, Calape, 6328 Bohol, Philippines', 9.89479980, 123.88222470),
(5, 'VR3G+45F, Maribojoc - Antequera - Catagbacan Rd, Loon, Bohol, Philippines', 9.85243740, 123.82596940),
(7, 'Desamparados Brgy Rd, Calape, Bohol, Philippines', 9.88216350, 123.86746550),
(9, 'Desamparados Brgy Rd, Calape, Bohol, Philippines', 9.88216350, 123.86746550),
(10, 'Desamparados Brgy Rd, Calape, Bohol, Philippines', 9.88216350, 123.86746550),
(19, 'VRRC+GPM, Calape, Bohol, Philippines', 9.89173000, 123.82211970),
(20, 'VVVM+48G, Calape, Bohol, Philippines', 9.89409190, 123.88385670),
(21, 'VR2G+8MX, Tagbilaran North Road, Loon, Bohol, Philippines', 9.85243350, 123.82596810),
(22, 'VR2G+8MX, Tagbilaran North Road, Loon, Bohol, Philippines', 9.85240680, 123.82597190),
(23, 'VVVM+Q38, Calape, 6328 Bohol, Philippines', 9.89438220, 123.88277030),
(24, 'VRVC+FM4, Calape, Bohol, Philippines', 9.89288460, 123.81915020),
(25, 'VRVC+FM4, Calape, Bohol, Philippines', 9.89288460, 123.81915020),
(26, 'VRRC+CFR, Calape, 6328 Bohol, Philippines', 9.89254860, 123.81919570),
(27, '4 Pangangan Coastal Rd, Calape, Bohol, Philippines', 9.88831500, 123.82462220),
(28, 'VRRF+76P, Calape, Bohol, Philippines', 9.88933490, 123.82174780),
(29, 'VVVM+Q38, Calape, 6328 Bohol, Philippines', 9.89482870, 123.88279150),
(30, 'VVVM+Q38, Calape, 6328 Bohol, Philippines', 9.89482870, 123.88279150),
(31, 'Former Calape Cultural Center Building, Tagbilaran North Road, Calape, Bohol, Philippines', 9.89440900, 123.88106690),
(32, 'VVVM+Q38, Calape, 6328 Bohol, Philippines', 9.89476590, 123.88257860);

-- --------------------------------------------------------

--
-- Table structure for table `seaurchin_lists`
--

CREATE TABLE `seaurchin_lists` (
  `name_id_urchin` int(100) NOT NULL,
  `name_urchin` varchar(100) NOT NULL,
  `scientific_name` varchar(100) NOT NULL,
  `local_name` varchar(100) NOT NULL,
  `description_urchin` varchar(200) NOT NULL,
  `status` enum('Edible','Non - Edible','Undefined') DEFAULT 'Undefined'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seaurchin_lists`
--

INSERT INTO `seaurchin_lists` (`name_id_urchin`, `name_urchin`, `scientific_name`, `local_name`, `description_urchin`, `status`) VALUES
(32, 'Collector Urchin', 'Tripneustes Gratilla', 'Swaki, Maritangtang, Kudenkuden', 'Collector urchins are dark in color, usually bluish-purple with white spines. The pedicles are also white, with a dark or black base. Individuals found at Green Island had orange-tipped spines. The sp', 'Edible'),
(36, 'Porcupine sea urchin', 'Diadema setosum', 'Tuyom', 'Body is covered with long spines, with many shorter spines in between. Spines are black or dark purple, with the long ones often black-and-white banded, or white in color. Five bright white or blue sp', 'Edible');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('0','1') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `role`) VALUES
(1, 'rj123', 'rj123@gmail.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(2, 'kenKate@username', 'kenKate@email.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(3, 'admin123.username', 'admin123@gmail.com', 'd41d8cd98f00b204e9800998ecf8427e', '0'),
(4, '', 'another.admin@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', '0'),
(5, 'rj', 'rj12345', '701f879047e6dfc468c0f6ade88ac494', '1'),
(6, 'michelle estorpe', 'michelle123@gmail', 'd41d8cd98f00b204e9800998ecf8427e', '1'),
(7, '', 'rj.admin@gmail', '25d55ad283aa400af464c76d713c07ad', '0'),
(8, 'user', 'user@gmail.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(9, 'alfredo', 'abreo@gmail', '25d55ad283aa400af464c76d713c07ad', '1'),
(10, 'rj', 'rj@gmail', '25d55ad283aa400af464c76d713c07ad', '1'),
(11, '', '', 'd41d8cd98f00b204e9800998ecf8427e', '1'),
(12, 'mayor', 'mayor', '25d55ad283aa400af464c76d713c07ad', '1'),
(13, 'mayoradmin', 'admin', '25d55ad283aa400af464c76d713c07ad', '0'),
(14, 'Admin', 'admin@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0'),
(15, 'alfredoadmin', 'alfredoabreo', '25d55ad283aa400af464c76d713c07ad', '0'),
(16, 'Admin', 'abreo', '25d55ad283aa400af464c76d713c07ad', '0'),
(17, 'User', 'user@gmail.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(18, 'user', 'user@email.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(19, 'christopher', 'user@gmail.com', '25d55ad283aa400af464c76d713c07ad', '1'),
(20, 'calape', 'calape@gmail.com', 'fcea920f7412b5da7be0cf42b8c93759', '0'),
(21, 'user', 'user123@gmail.com', '25d55ad283aa400af464c76d713c07ad', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_id`);

--
-- Indexes for table `seaurchin_lists`
--
ALTER TABLE `seaurchin_lists`
  ADD PRIMARY KEY (`name_id_urchin`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `location_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `seaurchin_lists`
--
ALTER TABLE `seaurchin_lists`
  MODIFY `name_id_urchin` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
