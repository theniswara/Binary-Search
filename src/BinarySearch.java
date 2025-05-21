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