# 🚀 Workflow Automation Test Framework

[![CI/CD Pipeline](https://shields.io)](https://github.com)
[![Live Dashboard](https://shields.io)](https://agussumaryanto.github.io/FinalAutomationTest/)

Proyek ini adalah *framework* otomatisasi pengujian komprehensif yang dirancang untuk memvalidasi kualitas perangkat lunak secara ujung-ke-ujung (*end-to-end*). Seluruh konfigurasi pustaka, dependensi, *test framework*, Cucumber Runtime, dan Allure dikelola secara terpusat di dalam satu file pengelolaan build `build.gradle`.

---

## 📊 Test Report Dashboards & Tautan Laporan
Seluruh hasil pengujian otomatis dieksekusi melalui *pipeline* CI/CD dan laporannya tersimpan rapi di dalam *branch* `gh-pages`. Anda dapat mengakses dasbor utama serta laporan detail melalui tautan berikut:

* **🔗 Dasbor Utama:** [Dashboard Test Report Result](https://agussumaryanto.github.io/FinalAutomationTest/)
* **🗂️ Repositori Laporan:** [GitHub Pages Branch Source](https://github.com/agussumaryanto/FinalAutomationTest/tree/gh-pages)

### 🔬 Rincian Hasil Pengujian Berdasarkan Lapisan (Layer)

| Kategori Pengujian | Cucumber Report | Allure Report |
| :--- | :--- | :--- |
| **⚙️ API Testing** | [Cucumber API Test Report](https://agussumaryanto.github.io/FinalAutomationTest/api/api_test_report.html) | [Allure API Test Report](https://agussumaryanto.github.io/FinalAutomationTest/allure-api/) |
| **🌐 Web UI Testing** | [Cucumber Web Test Report](https://agussumaryanto.github.io/FinalAutomationTest/api/webUi_test_report.html) | [Allure Web Test Report](https://agussumaryanto.github.io/FinalAutomationTest/allure-web/) |

---

## 🛠️ Spesifikasi Teknologi & Lingkungan Uji

| Atribut Komponen | Lapisan API Testing | Lapisan Web UI Testing |
| :--- | :--- | :--- |
| **Language** | Java | Java |
| **Target URL** | [dummyapi.io](https://dummyapi.io/) | [demoblaze.com](https://www.demoblaze.com) |
| **Class Name** | `TestRunnerApi` | `TestRunnerWeb` |
| **Testing Framework** | JUnit | JUnit |
| **BDD Framework** | Cucumber | Cucumber |
| **Build Tool** | Gradle | Gradle |
| **Testing Library** | RestAssured | Selenium WebDriver |
| **Assertions Library** | AssertJ (`assertj-core`) | AssertJ (`assertj-core`) |
| **Reporting Library** | Cucumber Report & Allure Report | Cucumber Report & Allure Report |

---

## 🏗️ Alur Pengembangan & Struktur Arsitektur Framework

Pengembangan framework ini menerapkan praktik terbaik (*best practices*) industri melalui tahapan terstruktur berikut:

### 1. Desain Skenario Uji (BDD Gherkin)
Kasus uji (*test cases*) ditulis menggunakan format Gherkin di dalam file berkstensi `.feature` pada direktori `resources`, mencakup skenario positif maupun negatif:
* **API Test:** `api.feature`
* **Web UI Test:**
  * `signup.feature` ➔ Pengujian fitur pendaftaran akun baru (*signup*).
  * `login.feature` ➔ Pengujian fitur masuk log (*login*).
  * `logout.feature` ➔ Pengujian fitur keluar log (*logout*).
  * `product.feature` ➔ Pengujian penjelajahan produk & tambah ke keranjang (*browsing & add to cart*).
  * `payment.feature` ➔ Pengujian pengisian formulir pembayaran & pelunasan (*payment/checkout*).
  * `e2e_purchase.feature` ➔ Pengujian alur penuh ujung-ke-ujung (Login ➔ Pilih Produk ➔ Checkout ➔ Logout).

### 2. Implementasi Langkah (*Step Definitions*)
Berperan sebagai penerjemah langkah Gherkin untuk memanggil aksi pada objek halaman:
* **API Testing Suite:**
  * `ApiStepdefs.java` ➔ Implementasi langkah-langkah pengujian dari berkas `api.feature`.
* **Web UI Testing Suite:**
  * `CommonSteps.java` ➔ Berisi langkah umum seperti penekanan tombol dan navigasi halaman.
  * `AuthSteps.java` ➔ Mengelola alur Login, Logout, Signup, beserta verifikasinya.
  * `ProductSteps.java` ➔ Mengelola manajemen produk, pencarian katalog, pemilihan, hingga masuk keranjang.
  * `CheckoutSteps.java` ➔ Mengelola alur proses *checkout* dan validasi pembayaran.

### 3. Komponen Page Object Model (POM)
Pemisahan elemen antarmuka dan logika aksi untuk memastikan kode pengujian mudah dirawat (*maintainable*):
* **POM API Testing:**
  * `ApiBasePage.java` ➔ Kelas induk (*base class*) untuk menangani permintaan HTTP GET, POST, PUT, dan DELETE.
  * `TagApiPage.java` ➔ Representasi objek aksi untuk endpoint `/tag`.
  * `UserApiPage.java` ➔ Representasi objek aksi untuk endpoint `/user`.
* **POM Web UI Testing:**
  * `HomePage.java` ➔ Elemen dan aksi pada halaman beranda utama.
  * `LoginPage.java` ➔ Interaksi dengan formulir komponen masuk log.
  * `SignUpPage.java` ➔ Interaksi dengan formulir komponen registrasi akun.
  * `ProductPage.java` ➔ Interaksi dengan elemen informasi di halaman produk detail.
  * `CartPage.java` ➔ Manajemen interaksi dengan halaman keranjang belanja pengguna.
  * `CheckoutPage.java` ➔ Manajemen pengisian form *checkout* hingga konfirmasi akhir pembelian.

### 4. Siklus Hidup Pengujian & Pelari (*Hooks & Runners*)
* **Test Hooks (`APIHooks.java` & `WebHooks.java`):** Menyediakan anotasi kondisi sebelum (*before*) dan sesudah (*after*) kelas dijalankan agar status pengujian kembali bersih, terisolasi, dan saling independen.
* **Test Runners (`TestRunnerApi.java` & `TestRunnerWeb.java`):** Bertindak sebagai motor penggerak untuk menghubungkan berkas `.feature` (BDD) dengan logika kodenya di *step definitions* sekaligus memicu pembuatan laporan akhir.

### 5. Pengelolaan Konfigurasi & Utilitas (*Utils*)
* **`config.properties`:** Berkas eksternal terpusat untuk menyimpan parameter global seperti variabel `baseURL`, preferensi peramban (*browser pilihan*), serta batas waktu tunggu global (*timeout*).
* **Helper UI Utilities:**
  * `DriverFactory.java` ➔ Mengelola pembuatan instansiasi objek WebDriver (Chrome/Firefox) beserta konfigurasi jalurnya (*driver path*).
  * `ConfigReader.java` ➔ Berperan membaca dan mengekstrak nilai konfigurasi dari berkas `config.properties`.
  * `WaitHelper.java` ➔ Menyediakan mekanisme penantian *explicit wait* dan *implicit wait* agar interaksi UI berjalan stabil dan terhindar dari kegagalan acak akibat masalah latensi (*flaky tests*).

---

## ⚙️ Integrasi Berkelanjutan & Otomatisasi (CI/CD Pipeline)

Eksekusi pengujian telah sepenuhnya diotomatisasi menggunakan ekosistem **GitHub Actions** melalui konfigurasi berkas alur kerja `FinalAutomationTest.yml` di dalam folder `.github/workflows`:

1. **Pemicu Otomatis:** Setiap aktivitas pembaruan kode atau *push* langsung ke repositori akan memicu jalannya pengujian otomatis.
2. **Lingkungan Headless:** Seluruh suite pengujian Web UI dijalankan di peladen agen GitHub Actions memanfaatkan peramban **Headless Chrome** (Chrome berjalan di latar belakang tanpa antarmuka grafis).
3. **Penyusunan Artefak:** Setelah eksekusi selesai, pipeline CI akan mengekstrak seluruh data hasil uji untuk dikompilasi menjadi laporan Allure dan Cucumber yang sahih sebelum diperbarui langsung ke dasbor live web Anda.

---

## 🎛️ Konfigurasi Tambahan (Opsional)
* **`.gitattributes`:** Digunakan untuk mengoptimalkan visualisasi statistik persentase bahasa pemrograman yang terbaca di sistem GitHub, sekaligus menekan munculnya pesan peringatan (*warnings*) yang tidak perlu saat skrip dijalankan.
