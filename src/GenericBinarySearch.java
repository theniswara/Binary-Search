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
