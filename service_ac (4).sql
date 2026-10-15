-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2026 at 06:10 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `service_ac`
--

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id`, `nama`, `no_telepon`, `alamat`) VALUES
('P001', 'Suriani', '0821313213', 'smd'),
('P002', 'Siti Aminah', '081345678901', 'Jl. Mulawarman No. 25'),
('P003', 'Dewi Lestari', '081256789012', 'Jl. Juanda No. 7'),
('P004', 'Agus Setiawan', '082167890123', 'Jl. Pangeran Antasari No. 18'),
('P005', 'Rina Wulandari', '085278901234', 'Jl. Gajah Mada No. 42'),
('P01', 'Ilham', '1234567890', 'dasas');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id_service` varchar(10) NOT NULL,
  `id_pelanggan` varchar(10) NOT NULL,
  `id_teknisi` varchar(10) NOT NULL,
  `jenis_ac` varchar(100) NOT NULL,
  `keluhan` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `status` enum('Menunggu','Diproses','Selesai') NOT NULL DEFAULT 'Menunggu',
  `biaya` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id_service`, `id_pelanggan`, `id_teknisi`, `jenis_ac`, `keluhan`, `tanggal`, `status`, `biaya`) VALUES
('S001', 'P001', 'T001', 'AC Split', 'AC tidak dingin', '2026-10-08', 'Diproses', 150000.00),
('S002', 'P002', 'T002', 'AC Inverter', 'AC mengeluarkan suara berisik', '2026-10-08', 'Menunggu', 200000.00),
('S003', 'P003', 'T004', 'AC Split', 'Cuci AC rutin 1 unit', '2026-10-10', 'Selesai', 75000.00),
('S004', 'P004', 'T003', 'AC Cassette', 'AC bocor air dari unit dalam', '2026-10-12', 'Diproses', 250000.00),
('S005', 'P005', 'T001', 'AC Split', 'Remote tidak berfungsi', '2026-10-14', 'Menunggu', 100000.00),
('S006', 'P001', 'T004', 'AC Split', 'Cuci AC 2 unit', '2026-10-15', 'Menunggu', 150000.00);

-- --------------------------------------------------------

--
-- Table structure for table `teknisi`
--

CREATE TABLE `teknisi` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `spesialisasi` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teknisi`
--

INSERT INTO `teknisi` (`id`, `nama`, `no_telepon`, `spesialisasi`) VALUES
('T001', 'Andi Pratama', '081456789012', 'AC Split'),
('T002', 'Rizky Maulana', '081567890123', 'AC Inverter'),
('T003', 'Hendra Wijaya', '082378901234', 'AC Cassette'),
('T004', 'Fajar Nugroho', '085289012345', 'AC Split dan Cuci AC');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`),
  ADD KEY `fk_service_pelanggan` (`id_pelanggan`),
  ADD KEY `fk_service_teknisi` (`id_teknisi`);

--
-- Indexes for table `teknisi`
--
ALTER TABLE `teknisi`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `fk_service_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id`),
  ADD CONSTRAINT `fk_service_teknisi` FOREIGN KEY (`id_teknisi`) REFERENCES `teknisi` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
