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