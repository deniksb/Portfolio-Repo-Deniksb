-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 29, 2022 at 02:56 PM
-- Server version: 10.5.12-MariaDB-cll-lve
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u203017424_bootcampportal`
--

-- --------------------------------------------------------

--
-- Table structure for table `additionalrecources`
--

CREATE TABLE `additionalrecources` (
  `id` int(11) NOT NULL,
  `title` varchar(191) NOT NULL,
  `description` varchar(3000) NOT NULL,
  `link` varchar(191) NOT NULL,
  `imageName` varchar(191) NOT NULL DEFAULT 'shell-linux.jpg',
  `Program` varchar(50) NOT NULL DEFAULT 'Backend'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `additionalrecources`
--

INSERT INTO `additionalrecources` (`id`, `title`, `description`, `link`, `imageName`, `Program`) VALUES
(1, 'Git and Gradle', 'Learn the basics of Git and Gradle and start coding!', 'https://youtube.com/playlist?list=PLNEKUicYzqCktNFuZeQOXgaSduBN-LYmA', 'git-gradle.jpg', 'Backend'),
(2, 'Bash and Shell Scripting', 'Learn about working with the console and get more control and power while programming!', 'https://drive.google.com/drive/folders/1LXP8WhtaO5MK11eCZ_rUZEmzpkMmagYD', 'shell-linux.jpg', 'Backend'),
(3, 'OOP tips and tricks for good coding', 'Learn how to be an effective and clean programmer when dealing with OOP!', 'https://youtube.com/playlist?list=PLNEKUicYzqCnzOj_w-QRCjyi0gz0_rh_R', 'clean-code.jpg', 'Backend'),
(4, 'RAID', 'Learn about RAIDs aka XOR magic.', 'https://youtube.com/playlist?list=PLNEKUicYzqCnRDHE298t4qcFtal92X_iL', 'raid-cover.jpg', 'Backend'),
(7, 'Demos', 'Demos done during the lectures.', 'https://git.trading212.io/trading212-bootcamp-cohort-apr-2022/demos', 'gitlab.png', 'Backend'),
(9, 'Databases Intro', 'Additional Materials for introduction to databases', 'https://docs.google.com/document/d/1_iTUA1CeOthzNr4zxa-XaM8yezYJMPLiVp-8bWHCQjY/edit?usp=sharing', 'databases-intro.webp', 'Backend'),
(23, 'Web Development - Individual Project Kick-Off', 'Additional materials to self-prep for the web development individual project assignment.', 'https://docs.google.com/document/d/1XLI1fbFSkLJkBfAOW_nRR812qgHfxtAI09wkJ4Fk1jg/edit?usp=sharing', 'horde.jpeg', 'Backend');

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `id` int(11) NOT NULL,
  `username` varchar(191) NOT NULL,
  `password` varchar(191) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`id`, `username`, `password`) VALUES
(2, 'bootcamp.admin', 'Bc123456$$');

-- --------------------------------------------------------

--
-- Table structure for table `carouselitems`
--

CREATE TABLE `carouselitems` (
  `id` int(11) NOT NULL,
  `title` varchar(191) NOT NULL,
  `description` varchar(191) NOT NULL,
  `link` varchar(191) NOT NULL,
  `imageName` varchar(191) NOT NULL DEFAULT 'trading.png',
  `Program` varchar(50) NOT NULL DEFAULT 'Backend'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `carouselitems`
--

INSERT INTO `carouselitems` (`id`, `title`, `description`, `link`, `imageName`, `Program`) VALUES
(1, 'My uncanny path to becoming a programmer', 'Very interesting story! Click to read!', 'https://danluu.com/learning-to-program/#:~:text=At%20that%20point%2C%20I%20did,every%202%20or%203%20days', 'trading.png', 'Backend'),
(6, 'The value of tech twitter', 'Nice thread by Gergely Orosz', 'https://twitter.com/GergelyOrosz/status/1526859755645452288?s=20&t=No4NMbeKjv-io6iyE-hPpQ', 'trading.png', 'Backend');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `additionalrecources_id` int(11) NOT NULL,
  `Program` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Backend'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `content`, `additionalrecources_id`, `Program`) VALUES
(1, 'mnogo me kefi tova', 9, 'Backend'),
(3, '🧝🧝🧝🧝🧝🧝🧝', 9, 'Backend'),
(4, 'ujastno e ', 9, 'Backend'),
(5, 'ti mrudna li e brak?', 7, 'Backend'),
(6, 'jina 4akal ku4e na islqma', 1, 'Backend'),
(7, 'vesko why are u liek dis', 9, 'Backend');

-- --------------------------------------------------------

--
-- Table structure for table `moduleitems`
--

CREATE TABLE `moduleitems` (
  `id` int(11) NOT NULL,
  `title` varchar(191) NOT NULL,
  `linkAll` varchar(191) NOT NULL,
  `linkEx` varchar(191) NOT NULL,
  `linkSlides` varchar(191) NOT NULL,
  `linkOther` varchar(191) NOT NULL,
  `Program` varchar(50) NOT NULL DEFAULT 'Backend'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `moduleitems`
--

INSERT INTO `moduleitems` (`id`, `title`, `linkAll`, `linkEx`, `linkSlides`, `linkOther`, `Program`) VALUES
(1, 'Intro to programming', 'https://drive.google.com/drive/folders/1R88D_NGm6CBG1_V5reZyWsnKTptYsME3', 'https://drive.google.com/drive/folders/1zemYqaG4-T7yAIGu1W8sXDWMsMMzPc8Z', 'https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS', 'https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS', 'Backend'),
(2, 'OOP', 'https://drive.google.com/drive/folders/1wt26zClrr-GKfT_gjiTo4nLu6EIIiy5w', 'https://drive.google.com/drive/folders/1wt26zClrr-GKfT_gjiTo4nLu6EIIiy5w', '#', 'https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS', 'Backend'),
(3, 'Databases', 'https://drive.google.com/drive/u/1/folders/1lTrweeFKB3CSE6Zfhwp6ovLR8JEU9T9N', 'https://drive.google.com/drive/u/1/folders/1lTrweeFKB3CSE6Zfhwp6ovLR8JEU9T9N', '', 'https://www.youtube.com/watch?v=HXV3zeQKqGY', 'Backend');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `additionalrecources`
--
ALTER TABLE `additionalrecources`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `adminlogin`
--
ALTER TABLE `adminlogin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `carouselitems`
--
ALTER TABLE `carouselitems`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `moduleitems`
--
ALTER TABLE `moduleitems`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `additionalrecources`
--
ALTER TABLE `additionalrecources`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `adminlogin`
--
ALTER TABLE `adminlogin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `carouselitems`
--
ALTER TABLE `carouselitems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `moduleitems`
--
ALTER TABLE `moduleitems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
