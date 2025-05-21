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