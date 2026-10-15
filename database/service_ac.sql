-- =====================================================
-- DATABASE SISTEM MANAJEMEN JASA SERVICE AC
-- Import file ini lewat phpMyAdmin / HeidiSQL (Laragon)
-- atau: mysql -u root < service_ac.sql
-- Catatan: menjalankan ulang file ini akan mereset semua
-- tabel ke data awal (DROP TABLE lalu dibuat ulang).
-- =====================================================

CREATE DATABASE IF NOT EXISTS db_service_ac
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE db_service_ac;

-- Hapus tabel lama (urutan: anak dulu, baru induk)
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS teknisi;
DROP TABLE IF EXISTS pelanggan;

-- =====================================================
-- TABEL
-- =====================================================

CREATE TABLE pelanggan (
    id          VARCHAR(10)  NOT NULL,
    nama        VARCHAR(100) NOT NULL,
    no_telepon  VARCHAR(15)  NOT NULL,
    alamat      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE teknisi (
    id            VARCHAR(10)  NOT NULL,
    nama          VARCHAR(100) NOT NULL,
    no_telepon    VARCHAR(15)  NOT NULL,
    spesialisasi  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE service (
    id_service    VARCHAR(10)   NOT NULL,
    id_pelanggan  VARCHAR(10)   NOT NULL,
    id_teknisi    VARCHAR(10)   NOT NULL,
    jenis_ac      VARCHAR(100)  NOT NULL,
    keluhan       VARCHAR(255)  NOT NULL,
    tanggal       DATE          NOT NULL,
    status        ENUM('Menunggu', 'Diproses', 'Selesai')
                  NOT NULL DEFAULT 'Menunggu',
    biaya         DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (id_service),
    CONSTRAINT fk_service_pelanggan
        FOREIGN KEY (id_pelanggan) REFERENCES pelanggan (id),
    CONSTRAINT fk_service_teknisi
        FOREIGN KEY (id_teknisi) REFERENCES teknisi (id)
) ENGINE=InnoDB;

-- =====================================================
-- DATA AWAL (DUMMY)
-- =====================================================

INSERT INTO pelanggan (id, nama, no_telepon, alamat) VALUES
('P001', 'Budi Santoso', '0812345678', 'Jl. Pahlawan No. 10'),
('P002', 'Siti Aminah',  '0813456789', 'Jl. Mulawarman No. 25');

INSERT INTO teknisi (id, nama, no_telepon, spesialisasi) VALUES
('T001', 'Andi Pratama',  '0814567890', 'AC Split'),
('T002', 'Rizky Maulana', '0815678901', 'AC Inverter');

INSERT INTO service
    (id_service, id_pelanggan, id_teknisi, jenis_ac, keluhan, tanggal, status, biaya)
VALUES
('S001', 'P001', 'T001', 'AC Split',    'AC tidak dingin',               '2026-10-08', 'Diproses', 150000),
('S002', 'P002', 'T002', 'AC Inverter', 'AC mengeluarkan suara berisik', '2026-10-08', 'Menunggu', 200000);
