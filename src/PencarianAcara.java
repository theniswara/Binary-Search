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
