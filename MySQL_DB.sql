-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 19, 2018 at 07:30 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id2008008_trices`
--

-- --------------------------------------------------------

--
-- Table structure for table `registered_devices`
--

CREATE TABLE `registered_devices` (
  `device` varchar(255) NOT NULL,
  `type` varchar(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registered_devices`
--

INSERT INTO `registered_devices` (`device`, `type`) VALUES
('d0TLR60Pho8:APA91bEVk_gGmdeQmk4Nbl1KgO0eBm0-hNPf0LqoCO1i2YkTn6OYxgvMYL4WvjfFImUON9Mf8JQ3SC3ZE-2fp6wPA8goQ0mVlqmhcMHz136wwSe6nlS6xNeYXSeJLTxcoQ3pmS3k5H0L', 'sms');

-- --------------------------------------------------------

--
-- Table structure for table `sms_received`
--

CREATE TABLE `sms_received` (
  `id` int(11) UNSIGNED NOT NULL,
  `user` varchar(99) COLLATE utf8_unicode_ci NOT NULL,
  `phone_num` varchar(19) COLLATE utf8_unicode_ci NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `datestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `date_sent` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `record_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(49) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(99) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(99) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(2, 'atif.naseem22@gmail.com', 'atif.naseem22@gmail.com', 'password'),
(3, 'Ahmed', 'ahmed@gmail.com', 'password'),
(4, 'Waqas', 'waqas123@gmail.com', 'password'),
(5, 'Saad', 'saad@123.com', 'password'),
(6, 'Uzair', 'uzair123@gmail.com', 'password'),
(7, 'mammmm', 'mam@gmail.com', 'password'),
(8, 'hello', 'hello@gmail.com', 'password'),
(9, 'eye', 'eye2eye@gmail.com', 'password'),
(10, 'sdfdf', 'fdgfdgfdg', 'dfdfd'),
(11, 'Hello Everyone', 'hello123@gmail.com', 'passwordxyz'),
(12, 'XYZ', 'xyz@gmail.com', 'password'),
(13, 'xyz ABC', 'abc_xyz@gmail.com', 'password'),
(14, 'Atif Naseem', 'atif.naseem22@gmail.con', 'password'),
(15, 'Saud', 'saud@gmail.com', 'password'),
(16, 'Hassan', 'hassan@gmail.com', '123kkf'),
(17, 'shani', 'shani@gmail.com', '123'),
(18, 'saad', 'saad@gmail.com', '123'),
(19, 'Saad', 'saad123@gmail.com', '123'),
(20, 'waheed', 'waheed@gmail.com', '123'),
(21, 'Test User', 'kp@gmail.com', '1234'),
(22, 'Masood', 'syed@gmail.com', '1234'),
(23, 'Saad Maniyar', 'saad.bin.gaus@gmail.com', '1234'),
(24, 'rohan', 'rohandesai1315@gmail.com', 'Rohan$1315'),
(25, 'samra kanwal', 'kanwalsamra28@gmail.com', 'samra4567'),
(26, 'samra kanwal', 'samrakanwal29@gmail.com', 'samra4567'),
(27, 'Mahnoor Fatima', 'mahnoorfatima364@gmail.com', 'jahil'),
(28, 'Mahnoor', 'mahnoorfatima34@gmail.com', 'hello'),
(29, 'mahi', 'mano@hotmail.com', 'hell'),
(30, 'noori ', 'sammy@hotmail.com', '1234'),
(31, 'muhammad hamza', 'm.hamza@yahoo.com', 'Hamza'),
(32, 'arthur', 'artrhur', '123'),
(33, 'arthur', 'a@bol.com', '123'),
(34, 'umar', 'umar@hotmail.com', '12345678'),
(35, 'ubaid', 'ubaid.patel50@gmail.com', 'ypunuskhan'),
(36, 'u', 'yut@gmail.com', 'iu'),
(37, 'you', 'iu', 'i'),
(38, 'uy', 'patel', 'bhai'),
(39, 'ubaid', 'p@gmail.com', 'khan'),
(40, '45', '@gmail', '123'),
(41, 'xvg', 'gxvb', 'ccb'),
(42, 'ub', 'ai', '12'),
(43, 'ubaid', 'op', '123'),
(44, 'yes', 'hmm', '12'),
(45, ' 12', '123', '1234'),
(46, 'suhail', 'sahib', 'kasa'),
(47, 'ubaid', 'khan', '123'),
(48, '1', '12', '123'),
(49, 'farrukh', 'farrukhnaeem980@gmail.com', 'farrukh123'),
(50, 'saad', 'saadayoub49@gmail.com', '1234'),
(51, 'ahm', 'bil@gmail.com', '12345'),
(52, 'ui', 'aq', '123'),
(53, 'Behram', 'behramgk73@gmail.com', '12345'),
(54, 'cff', 'ccv', 'haris'),
(55, 'pancho', 'informatico27@gmail.com', 'sys6326'),
(56, 'abc abc', 'abc@gmail.com', '1234'),
(57, 'Nomapelo', 'nomapelompalala@gmail.com', '123456'),
(58, 'Nomapelo', 'nomapelo@ntshangacapital.co.za', 'likuthi123'),
(59, 'Nomapelo', 'nomapelo1@ntshangacapital.co.za', 'Likuthi123'),
(60, 'Nomapelo', 'phelo@gmail.com', 'likuthi123'),
(61, 'Nomapelo', 'phelo1@gmail.com', 'Likuthi123'),
(62, 'nosisa', 'znosisa@gmail.com', 'android1'),
(63, 'ubaid kimi', 'g@gmail', 'i'),
(64, 'u', 'b', 'u'),
(65, 'ninad', 'ninadjoshi212@gmail.com', 'shalaka'),
(66, 'shubh', 'shubh@007', 'ruchita'),
(67, 'samra', 'kanwalsamra@gmail.com', 'samra'),
(68, 'Tokhirjon', 'toxirbek.96@mail.ru', '9222292t'),
(69, 'jsjsj', 'gmail@jdj.com', 'jsksks7'),
(70, 'pi', 'pi@gmail.com', '9090'),
(71, 'Ali', 'Ather', 'radeon'),
(72, 'dennis', 'deynojosh@gmail.com', 'Deyno'),
(73, 'mmf', 'dfjkj', 'jdj'),
(74, 'mahadev', 'mahadev@gmail.com', '12345'),
(75, 'khairul', 'khairul@gmail.com', '12345678'),
(76, 'khairul', 'khairul97@gmail.com', '1234'),
(77, 'khairul', 'khairul@yahoo.com', '1234'),
(78, 'Sami', 'samy_gui95@yahoo.com', '123456'),
(79, 'Abhishek ', 'Pandey', 'abhishek'),
(80, 'qweqw', 'qweqwe', 'qwe'),
(81, 'Men', 'tonybek.96@gmail.com', '123456'),
(82, 'sbsbsb', 'hsbsbs', 'sbsbsbs'),
(83, 'tayce', 'tayce gmail.com', '123456'),
(84, '123', 'q', '123456'),
(85, 'Makolo Herbert', 'mako@gmail.com', '1234'),
(86, 'Hemansh', 'bk@gmail.com', '123456'),
(87, 'hamid', 'hamid.com', '123'),
(88, 'Tejas Phirke', 'tejasphirake30@gmail.com', '09021038378');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registered_devices`
--
ALTER TABLE `registered_devices`
  ADD PRIMARY KEY (`device`);

--
-- Indexes for table `sms_received`
--
ALTER TABLE `sms_received`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sms_received`
--
ALTER TABLE `sms_received`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
