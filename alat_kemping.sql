-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 03:28 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penyewaan_camping`
--

-- --------------------------------------------------------

--
-- Table structure for table `alat_kemping`
--

CREATE TABLE `alat_kemping` (
  `id` int NOT NULL,
  `nama_alat` varchar(100) NOT NULL,
  `harga_sewa` decimal(12,2) NOT NULL,
  `stok` int NOT NULL,
  `jenis` enum('TENDA','ALAT_MASAK') NOT NULL,
  `kapasitas` int DEFAULT NULL,
  `jenis_bahan_bakar` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `alat_kemping`
--

INSERT INTO `alat_kemping` (`id`, `nama_alat`, `harga_sewa`, `stok`, `jenis`, `kapasitas`, `jenis_bahan_bakar`) VALUES
(1, 'Tenda Dome', '50000.00', 5, 'TENDA', 4, NULL),
(2, 'Tenda Family', '80000.00', 2, 'TENDA', 8, NULL),
(3, 'Kompor Portable', '30000.00', 3, 'ALAT_MASAK', NULL, 'Gas'),
(8, 'tenda besar', '230000.00', 5, 'TENDA', 4, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alat_kemping`
--
ALTER TABLE `alat_kemping`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alat_kemping`
--
ALTER TABLE `alat_kemping`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
