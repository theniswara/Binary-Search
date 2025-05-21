import java.time.LocalDate; // Import buat kerja sama tanggal
import java.time.format.DateTimeFormatter; // Buat format tampilan tanggal nanti
import java.util.Scanner; // Buat input dari user

// Class Acara untuk nyimpen info tentang acara
class Acara {
    LocalDate tanggal; // Tanggal acaranya
    String nama;       // Nama acaranya
    String lokasi;     // Lokasi acaranya
    String deskripsi;  // Deskripsi acaranya

    // Constructor untuk ngisi data waktu bikin objek Acara
    public Acara(LocalDate tanggal, String nama, String lokasi, String deskripsi) {
        this.tanggal = tanggal;
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    // Override toString biar pas print objek Acara, tampilnya lebih rapi
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format tanggal biar enak dibaca
        return String.format("Tanggal: %s\nNama Acara: %s\nLokasi: %s\nDeskripsi: %s",
                            tanggal.format(formatter), nama, lokasi, deskripsi); // Bikin string yang isinya info acara
    }
}

// Kelas utama buat jalanin program pencarian acara
public class PencarianAcara {
    public static void main(String[] args) {
        // Daftar acara yang udah diurutkan berdasarkan tanggal (penting buat binary search)
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

        Scanner scanner = new Scanner(System.in); // Buka scanner buat ambil input user

        System.out.println("=== SISTEM PENCARIAN ACARA ===");
        System.out.println("Format tanggal: yyyy-MM-dd (contoh: 2025-05-20)");
        System.out.print("Masukkan tanggal yang ingin dicari: ");
        String tanggalInput = scanner.nextLine(); // Ambil input tanggal dari user

        try {
            // Ubah input user (String) jadi LocalDate
            LocalDate tanggalCari = LocalDate.parse(tanggalInput);

            // Panggil metode buat cari acara dengan binary search
            int index = cariAcaraByTanggal(jadwalAcara, tanggalCari);

            System.out.println("\nHASIL PENCARIAN:");
            if (index != -1) {
                // Kalau ketemu, tampilkan info acaranya
                System.out.println("Acara ditemukan pada tanggal " + tanggalInput + "!");
                System.out.println(jadwalAcara[index]);
            } else {
                // Kalau nggak ketemu, kasih pesan
                System.out.println("Tidak ada acara yang terjadwal pada tanggal " + tanggalInput + ".");
            }
        } catch (Exception e) {
            // Kalau format input salah atau error, kasih peringatan
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
        }

        scanner.close(); // Tutup scanner (biar rapi dan aman)
    }

    // Fungsi buat cari acara berdasarkan tanggal pakai binary search
    public static int cariAcaraByTanggal(Acara[] jadwalAcara, LocalDate tanggal) {
        int low = 0;
        int high = jadwalAcara.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Hitung posisi tengah array

            // Cek apakah tanggal di posisi tengah sama dengan yang dicari
            if (jadwalAcara[mid].tanggal.isEqual(tanggal)) {
                return mid; // Ketemu, balikin index-nya
            }

            // Kalau tanggal di tengah lebih setelah tanggal yang dicari, geser ke kiri
            if (jadwalAcara[mid].tanggal.isAfter(tanggal)) {
                high = mid - 1;
            }
            // Kalau tanggal di tengah lebih sebelum tanggal yang dicari, geser ke kanan
            else {
                low = mid + 1;
            }
        }

        // Kalau nggak ketemu, balikin -1
        return -1;
    }
}
