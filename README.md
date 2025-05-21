# Penjelasan


## Implementasi Iteratif

```java
// Ini adalah kelas untuk algoritma pencarian biner dengan cara iteratif (loop)
public class BinarySearch {
    // Metode binary search yang mencari nilai dalam array
    // Menerima array yang sudah urut dan nilai target yang dicari
    // Mengembalikan posisi nilai yg ditemukan atau -1 kalau tidak ketemu
    public static int binarySearch(int[] arr, int target) {
        // Mulai pencarian dari ujung kiri array
        int low = 0;
        // Dan dari ujung kanan array
        int high = arr.length - 1;

        // Selama masih ada area yg belum dicek
        while (low <= high) {
            // Cari posisi tengah dari area yg sedang dicek
            // Cara ini lebih aman dari (low+high)/2 yg bisa overflow
            int mid = low + (high - low) / 2;

            // Kalau ketemu targetnya langsung return posisinya
            if (arr[mid] == target) {
                return mid;
            }

            // Kalau nilai tengah lebih besar dari target, 
            // berarti target ada di setengah kiri
            if (arr[mid] > target) {
                // Update batas kanan pencarian
                high = mid - 1;
            }
            // Kalau nilai tengah lebih kecil, target ada di setengah kanan
            else {
                // Update batas kiri pencarian
                low = mid + 1;
            }
        }

        // Kalau sampai sini berarti tidak ketemu
        return -1;
    }

    // Metode utama untuk menjalankan dan tes algoritma
    public static void main(String[] args) {
        // Array contoh yang sudah urut untuk kita cari
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        // Nilai yang mau kita cari
        int target = 23;

        // Panggil metode binary search untuk mencari
        int result = binarySearch(arr, target);

        // Cek hasilnya dan tampilkan pesan yang sesuai
        if (result == -1) {
            System.out.println("Elemen " + target + " tidak ditemukan dalam array");
        } else {
            System.out.println("Elemen " + target + " ditemukan pada indeks " + result);
        }
    }
}
```

## Implementasi Rekursif

```java
// Kelas untuk algoritma binary search dengan pendekatan rekursif
public class BinarySearchRecursive {
    // Metode rekursif untuk binary search
    // Parameter low dan high menandai batas area yang sedang dicari
    public static int binarySearchRecursive(int[] arr, int target, int low, int high) {
        // Kondisi dasar - kalau low > high berarti target tidak ada di array
        if (low > high) {
            return -1;
        }

        // Cari posisi tengah
        int mid = low + (high - low) / 2;

        // Kalau nilai tengah = target, ketemu!
        if (arr[mid] == target) {
            return mid;
        }

        // Kalau target lebih kecil, cari di bagian kiri
        // dengan memanggil fungsi ini lagi (rekursif)
        if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, low, mid - 1);
        }

        // Kalau target lebih besar, cari di bagian kanan
        return binarySearchRecursive(arr, target, mid + 1, high);
    }

    // Metode pembungkus agar lebih mudah digunakan
    // Pengguna tidak perlu tahu tentang parameter low dan high
    public static int binarySearch(int[] arr, int target) {
        // Panggil metode rekursif dengan nilai awal 0 dan panjang array-1
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    // Metode utama untuk menjalankan dan tes algoritma
    public static void main(String[] args) {
        // Array contoh yang sudah urut untuk kita cari
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        // Nilai yang mau kita cari
        int target = 23;

        // Panggil metode pencarian
        int result = binarySearch(arr, target);

        // Tampilkan hasilnya
        if (result == -1) {
            System.out.println("Elemen " + target + " tidak ditemukan dalam array");
        } else {
            System.out.println("Elemen " + target + " ditemukan pada indeks " + result);
        }
    }
}
```

## Studi Kasus 1: Pencarian Buku di Perpustakaan

```java
import java.util.Scanner;

// Kelas yang mewakili buku dengan properti dasarnya
class Buku {
    // Properti dari sebuah buku
    String isbn;       // ISBN untuk identifikasi unik 
    String judul;      // Judul buku
    String penulis;    // Nama penulisnya
    int tahunTerbit;   // Tahun buku diterbitkan

    // Konstruktor untuk membuat objek buku baru
    public Buku(String isbn, String judul, String penulis, int tahunTerbit) {
        this.isbn = isbn;           // Simpan ISBN
        this.judul = judul;         // Simpan judul
        this.penulis = penulis;     // Simpan penulis
        this.tahunTerbit = tahunTerbit; // Simpan tahun terbit
    }

    // Override toString agar bisa menampilkan buku dengan rapi
    @Override
    public String toString() {
        // Format string dengan properti buku
        return String.format("ISBN: %s\nJudul: %s\nPenulis: %s\nTahun Terbit: %d",
                            isbn, judul, penulis, tahunTerbit);
    }
}

// Kelas utama untuk sistem pencarian buku perpustakaan
public class PencarianBuku {
    public static void main(String[] args) {
        // Data buku yang sudah diurutkan berdasarkan ISBN
        // Binary search butuh data terurut untuk bekerja!
        Buku[] daftarBuku = {
            new Buku("9780071606301", "Java: The Complete Reference", "Herbert Schildt", 2007),
            new Buku("9780132222204", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780132778046", "Head First Java", "Kathy Sierra & Bert Bates", 2005),
            new Buku("9780134685991", "Effective Python", "Brett Slatkin", 2019),
            new Buku("9780135957059", "Clean Code", "Robert C. Martin", 2008),
            new Buku("9780137081073", "The Clean Coder", "Robert C. Martin", 2011),
            new Buku("9780262033848", "Introduction to Algorithms", "Cormen, Leiserson, Rivest & Stein", 2009),
            new Buku("9780321356680", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780596009205", "Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2004)
        };

        // Buat scanner untuk membaca input
        Scanner scanner = new Scanner(System.in);

        // Tampilkan judul program
        System.out.println("=== SISTEM PENCARIAN BUKU PERPUSTAKAAN ===");
        // Minta user memasukkan ISBN
        System.out.print("Masukkan nomor ISBN buku yang dicari: ");
        String isbnCari = scanner.nextLine();

        // Cari bukunya pakai binary search
        int index = cariBukuByISBN(daftarBuku, isbnCari);

        // Tampilkan hasilnya
        System.out.println("\nHASIL PENCARIAN:");
        if (index != -1) {
            // Kalau ketemu, tampilkan info buku
            System.out.println("Buku ditemukan!");
            System.out.println(daftarBuku[index]);
        } else {
            // Kalau tidak ketemu, kasih tau usernya
            System.out.println("Buku dengan ISBN " + isbnCari + " tidak ditemukan.");
        }

        // Tutup scanner
        scanner.close();
    }

    // Metode binary search untuk mencari buku berdasarkan ISBN
    public static int cariBukuByISBN(Buku[] daftarBuku, String isbn) {
        // Siapkan batas pencarian
        int low = 0;
        int high = daftarBuku.length - 1;

        // Selama masih ada area yang belum dicek
        while (low <= high) {
            // Cari posisi tengah
            int mid = low + (high - low) / 2;

            // Bandingkan ISBN buku di tengah dengan ISBN yang dicari
            // compareTo ngasih nilai negatif kalau string pertama < kedua,
            // 0 kalau sama, positif kalau string pertama > kedua
            int comparison = daftarBuku[mid].isbn.compareTo(isbn);

            // Kalau ISBN sama (comparison = 0), ketemu!
            if (comparison == 0) {
                return mid;
            }

            // Kalau ISBN di tengah lebih besar, lanjut cari di kiri
            if (comparison > 0) {
                high = mid - 1;
            }
            // Kalau ISBN di tengah lebih kecil, lanjut cari di kanan
            else {
                low = mid + 1;
            }
        }

        // Sampai sini berarti buku tak ditemukan
        return -1;
    }
}
```

## tudi Kasus 2: Pencarian Tanggal dalam Jadwal Acara

```java
import java.time.LocalDate; // Untuk menangani tipe data tanggal
import java.time.format.DateTimeFormatter; // Untuk memformat tampilan tanggal
import java.util.Scanner; // Untuk membaca input dari pengguna

// Kelas Acara menyimpan informasi tentang suatu acara
class Acara {
    LocalDate tanggal; // Menyimpan tanggal acara
    String nama;       // Nama dari acara
    String lokasi;     // Lokasi acara
    String deskripsi;  // Deskripsi acara

    // Konstruktor untuk inisialisasi data acara
    public Acara(LocalDate tanggal, String nama, String lokasi, String deskripsi) {
        this.tanggal = tanggal;
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    // Override method toString untuk menampilkan informasi acara secara rapi
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format tanggal: contoh 21 Mei 2025
        return String.format("Tanggal: %s\nNama Acara: %s\nLokasi: %s\nDeskripsi: %s",
                             tanggal.format(formatter), nama, lokasi, deskripsi);
    }
}

public class PencarianAcara {
    public static void main(String[] args) {
        // Data acara sudah diurutkan berdasarkan tanggal secara ascending (naik)
        Acara[] jadwalAcara = {
            new Acara(LocalDate.of(2025, 5, 10), "Workshop Java", "Ruang Pelatihan A", "Workshop dasar pemrograman Java"),
            new Acara(LocalDate.of(2025, 5, 15), "Seminar AI", "Aula Utama", "Seminar tentang perkembangan Artificial Intelligence"),
            new Acara(LocalDate.of(2025, 5, 20), "Kompetisi Coding", "Lab Komputer", "Kompetisi coding untuk mahasiswa"),
            new Acara(LocalDate.of(2025, 5, 25), "Tech Talk", "Auditorium", "Diskusi tentang teknologi terbaru"),
            new Acara(LocalDate.of(2025, 6, 1), "Career Fair", "Gedung Serbaguna", "Pameran karir bidang IT"),
            new Acara(LocalDate.of(2025, 6, 5), "Webinar Cloud Computing", "Online", "Webinar tentang teknologi cloud"),
            new Acara(LocalDate.of(2025, 6, 10), "Hackathon", "Co-Working Space", "Hackathon 24 jam"),
            new Acara(LocalDate.of(2025, 6, 15), "Workshop Database", "Ruang Pelatihan B", "Workshop database SQL dan NoSQL"),
            new Acara(LocalDate.of(2025, 6, 20), "Game Development Talk", "Ruang Multimedia", "Diskusi tentang pengembangan game")
        };

        Scanner scanner = new Scanner(System.in); // Membuat scanner untuk input dari user

        System.out.println("=== SISTEM PENCARIAN ACARA ===");
        System.out.println("Format tanggal: yyyy-MM-dd (contoh: 2025-05-20)");
        System.out.print("Masukkan tanggal yang ingin dicari: ");
        String tanggalInput = scanner.nextLine(); // Membaca input tanggal dari user

        try {
            // Parsing input dari string ke objek LocalDate
            LocalDate tanggalCari = LocalDate.parse(tanggalInput);

            // Memanggil metode pencarian binary search berdasarkan tanggal
            int index = cariAcaraByTanggal(jadwalAcara, tanggalCari);

            System.out.println("\nHASIL PENCARIAN:");
            if (index != -1) {
                // Jika ditemukan, tampilkan info acara
                System.out.println("Acara ditemukan pada tanggal " + tanggalInput + "!");
                System.out.println(jadwalAcara[index]);
            } else {
                // Jika tidak ditemukan
                System.out.println("Tidak ada acara yang terjadwal pada tanggal " + tanggalInput + ".");
            }
        } catch (Exception e) {
            // Menangani kesalahan format tanggal
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
        }

        scanner.close(); // Menutup scanner untuk mencegah kebocoran resource
    }

    // Fungsi untuk mencari acara berdasarkan tanggal menggunakan binary search
    public static int cariAcaraByTanggal(Acara[] jadwalAcara, LocalDate tanggal) {
        int low = 0; // Batas bawah pencarian
        int high = jadwalAcara.length - 1; // Batas atas pencarian

        while (low <= high) {
            int mid = low + (high - low) / 2; // Hitung indeks tengah

            // Bandingkan tanggal pada posisi tengah dengan tanggal yang dicari
            if (jadwalAcara[mid].tanggal.isEqual(tanggal)) {
                return mid; // Jika cocok, kembalikan indeks
            }

            // Jika tanggal tengah lebih besar dari target, cari di kiri
            if (jadwalAcara[mid].tanggal.isAfter(tanggal)) {
                high = mid - 1;
            }
            // Jika tanggal tengah lebih kecil dari target, cari di kanan
            else {
                low = mid + 1;
            }
        }

        // Jika tidak ditemukan, kembalikan -1
        return -1;
    }
}

```

## Studi Kasus 3: Pencarian Nilai dalam Dataset Besar dengan Binary Search Generik

```java
import java.util.Arrays; // Digunakan untuk operasi terhadap array, seperti menampilkan atau mengurutkan
import java.util.Comparator; // (Tidak digunakan dalam kode ini, dapat dihapus)
import java.util.Scanner; // Digunakan untuk mengambil input dari pengguna

// Interface generic yang mendefinisikan metode pembanding
interface Searchable<T> {
    int compare(T value); // Metode yang akan digunakan untuk membandingkan nilai
}

public class GenericBinarySearch {

    // Metode binary search generic yang dapat digunakan untuk berbagai tipe data
    public static <T> int binarySearch(T[] array, Searchable<T> searchable) {
        int low = 0;
        int high = array.length - 1;

        // Perulangan untuk melakukan pencarian selama batas bawah tidak melebihi batas atas
        while (low <= high) {
            int mid = low + (high - low) / 2; // Menentukan indeks tengah

            int comparison = searchable.compare(array[mid]); // Melakukan perbandingan data tengah dengan nilai target

            if (comparison == 0) {
                return mid; // Jika sama, maka data ditemukan
            } else if (comparison < 0) {
                high = mid - 1; // Jika target lebih kecil, lanjutkan pencarian di sebelah kiri
            } else {
                low = mid + 1; // Jika target lebih besar, lanjutkan pencarian di sebelah kanan
            }
        }

        return -1; // Mengembalikan -1 jika data tidak ditemukan
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk membaca input pengguna

        System.out.println("=== SISTEM PENCARIAN DATASET ===");
        System.out.println("Pilih jenis data yang ingin dicari:");
        System.out.println("1. Integer");
        System.out.println("2. Double");
        System.out.println("3. String");
        System.out.print("Pilihan Anda (1-3): ");

        int pilihan = scanner.nextInt(); // Membaca pilihan pengguna
        scanner.nextLine(); // Membersihkan buffer newline setelah nextInt

        switch (pilihan) {
            case 1:
                // Pencarian untuk tipe data Integer
                Integer[] dataInteger = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // Data yang sudah terurut

                System.out.println("\nData Integer: " + Arrays.toString(dataInteger));
                System.out.print("Masukkan nilai integer yang dicari: ");
                int targetInt = scanner.nextInt(); // Membaca nilai yang ingin dicari

                // Memanggil binary search dengan implementasi compare untuk Integer
                int indexInt = binarySearch(dataInteger, new Searchable<Integer>() {
                    @Override
                    public int compare(Integer value) {
                        return targetInt - value; // Mengembalikan selisih antara target dan nilai saat ini
                    }
                });

                // Menampilkan hasil pencarian
                if (indexInt != -1) {
                    System.out.println("Nilai " + targetInt + " ditemukan pada indeks " + indexInt);
                } else {
                    System.out.println("Nilai " + targetInt + " tidak ditemukan dalam dataset");
                }
                break;

            case 2:
                // Pencarian untuk tipe data Double
                Double[] dataDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9}; // Data yang sudah terurut

                System.out.println("\nData Double: " + Arrays.toString(dataDouble));
                System.out.print("Masukkan nilai double yang dicari: ");
                double targetDouble = scanner.nextDouble(); // Membaca nilai yang ingin dicari

                // Memanggil binary search dengan menggunakan Double.compare
                int indexDouble = binarySearch(dataDouble, new Searchable<Double>() {
                    @Override
                    public int compare(Double value) {
                        return Double.compare(targetDouble, value); // Perbandingan nilai double
                    }
                });

                // Menampilkan hasil pencarian
                if (indexDouble != -1) {
                    System.out.println("Nilai " + targetDouble + " ditemukan pada indeks " + indexDouble);
                } else {
                    System.out.println("Nilai " + targetDouble + " tidak ditemukan dalam dataset");
                }
                break;

            case 3:
                // Pencarian untuk tipe data String
                String[] dataString = {"alpha", "beta", "delta", "gamma", "omega", "sigma", "theta", "zeta"};
                Arrays.sort(dataString); // Mengurutkan array string agar dapat digunakan dengan binary search

                System.out.println("\nData String: " + Arrays.toString(dataString));
                System.out.print("Masukkan string yang dicari: ");
                String targetString = scanner.nextLine(); // Membaca string yang ingin dicari

                // Memanggil binary search dengan implementasi compareTo untuk string
                int indexString = binarySearch(dataString, new Searchable<String>() {
                    @Override
                    public int compare(String value) {
                        return targetString.compareTo(value); // Menggunakan compareTo untuk perbandingan string
                    }
                });

                // Menampilkan hasil pencarian
                if (indexString != -1) {
                    System.out.println("String \"" + targetString + "\" ditemukan pada indeks " + indexString);
                } else {
                    System.out.println("String \"" + targetString + "\" tidak ditemukan dalam dataset");
                }
                break;

            default:
                // Jika input pilihan tidak sesuai
                System.out.println("Pilihan tidak valid!");
        }

        scanner.close(); // Menutup scanner untuk mencegah kebocoran resource
    }
}

```