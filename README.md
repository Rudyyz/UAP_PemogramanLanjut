# InventoryManagement_Java
# 📦 UAP_RUDIMUSTAKIM & Avina Pinky Firu Ananda : Aplikasi Manajemen Inventaris Barang (Java Swing)

[![Lisensi](https://img.shields.io/badge/Lisensi-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Build](https://img.shields.io/badge/Build-Success-green.svg)]()

---

## 📖 Deskripsi Proyek

Proyek ini merupakan implementasi **Aplikasi Manajemen Inventaris Barang berbasis GUI**
menggunakan bahasa pemrograman **Java** dengan **Java Swing**.
Aplikasi ini dikembangkan untuk memenuhi **Ujian Akhir Praktikum (UAP) Pemrograman Lanjut**
dan mengimplementasikan seluruh materi Modul 1–6.

Aplikasi ini digunakan untuk mengelola data inventaris barang secara terstruktur,
mendukung **CRUD lengkap (Create, Read, Update, Delete)**,
pencarian data, pengurutan data, serta **penyimpanan data permanen**
menggunakan file **CSV**.

---

## 👤 Peran Pengguna

Program ini memiliki satu peran utama:
- 👨‍💻 **Admin / Pengguna**
    - Mengelola data inventaris barang
    - Menambah, mengubah, dan menghapus data barang
    - Melihat laporan inventaris

---

## ⚙️ Panduan Penggunaan

### 1. Prasyarat

Pastikan perangkat Anda telah terpasang:
- **Java Development Kit (JDK)** minimal versi **17**
- **Git** (opsional, untuk clone repositori)
- **IDE / Text Editor** seperti IntelliJ IDEA, VS Code, atau NetBeans

---

### 2. Menjalankan Aplikasi

1. **Clone repositori ini:**
    ```bash
    git clone https://github.com/Rudyyz/InventoryManagement_Java.git
    cd InventoryManagement_Java
    ```

2. **Buka project menggunakan IntelliJ IDEA**

3. **Pastikan file CSV tersedia di:**
    ```text
    src/main/resources/barang.csv
    ```

4. **Jalankan aplikasi melalui file:**
    ```text
    Main.java
    ```

---

## 🧩 Fitur Aplikasi

- 📊 **Dashboard**
    - Menu navigasi utama aplikasi
- 📋 **Manajemen Data Barang**
    - Tambah data barang
    - Tampilkan data dalam tabel
    - Edit data barang
    - Hapus data barang
- 🔍 **Searching**
    - Pencarian barang berdasarkan nama
- 🔃 **Sorting**
    - Pengurutan berdasarkan nama barang
    - Pengurutan berdasarkan stok barang
- 📈 **Laporan Inventaris**
    - Total jenis barang
    - Total stok barang
- 💾 **Persistensi Data**
    - Penyimpanan data menggunakan file CSV

---

## 🧪 Contoh Penggunaan Aplikasi

1. Pengguna membuka aplikasi → Dashboard tampil
2. Pengguna memilih **Tambah Barang**
3. Data barang disimpan dan otomatis masuk ke tabel
4. Pengguna dapat melakukan pencarian, pengurutan, edit, dan hapus data
5. Data tetap tersimpan meskipun aplikasi ditutup

---

## 💡 Konsep Pemrograman yang Diterapkan

| Konsep | Implementasi |
|------|--------------|
| **Encapsulation** | Penggunaan atribut `private` dengan getter |
| **Object-Oriented Programming** | Pemisahan Model, Service, Util, dan View |
| **Abstraction** | Pemisahan logika bisnis dan tampilan |
| **Collection Framework** | `ArrayList` untuk pengelolaan data |
| **Exception Handling** | Validasi input & error file |
| **File Handling** | Penyimpanan data menggunakan CSV |

---
## 📂 Struktur Project

```text
InventoryManagement_Java
├── pom.xml
├── README.md
├── .gitignore
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── example
│   │   │           ├── Main.java
│   │   │           ├── model
│   │   │           │   └── Barang.java
│   │   │           ├── service
│   │   │           │   └── BarangService.java
│   │   │           ├── util
│   │   │           │   └── FileUtil.java
│   │   │           └── view
│   │   │               ├── DashboardView.java
│   │   │               ├── ListBarangView.java
│   │   │               ├── FormBarangView.java
│   │   │               └── LaporanView.java
│   │   │
│   │   └── resources
│   │       └── barang.csv
│   │
│   └── test
│       └── (pengujian manual)
│
└── .idea
```

 ---
## 👨‍💻 Pengembang

- **Nama:** M. Rudi Mustakim
- **NIM:** 202410370110136
- - **GitHub Username:** Rudyyz
- **Nama:** Avina Pinky Firu Ananda
- **NIM:** 202410370110141
- **GitHub Username:** vinaaaa19
- **Deskripsi:** Implementasi Java Swing dan konsep OOP pada Aplikasi Manajemen Inventaris Barang

---

## 🏁 Lisensi

Proyek ini dilisensikan di bawah **MIT License**.  
Silakan digunakan, dimodifikasi, dan dikembangkan untuk kebutuhan pembelajaran
dan pengembangan lebih lanjut.

