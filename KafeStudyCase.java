import java.util.Scanner;

public class KafeStudyCase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] menuKafe = {"Americano", "Latte", "Frappe taro", "Dimsum", "Mie Goreng", "Ricebowl", "Takoyaki", "Wonton Chili Oil", "Pangsit Ayam"};
        double[] hargaMenu = {21000, 26000, 32000, 25000, 24000, 30000, 28000, 27000, 15000};

        String[] namaPelanggan = new String[100];
        int[] nomorMeja = new int[100];
        String[][] daftarMenu = new String[100][100];
        int[][] jumlahItem = new int[100][100];
        double[][] hargaItem = new double[100][100];
        int jumlahPelanggan = 0;

        while (true) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = sc.nextInt();
            sc.nextLine();

            System.out.println("\n===== Menu Kafe =====");
            for (int i = 0; i < menuKafe.length; i++) {
                System.out.println((i + 1) + ". " + menuKafe[i] + " = Rp " + hargaMenu[i]);
            }
    
            if (pilihan == 1) {
                System.out.print("Nama Pelanggan: ");
                String nama = sc.nextLine();
                System.out.print("Nomor Meja: ");
                int meja = sc.nextInt();
                sc.nextLine();

                int indexPelanggan = -1;
                for (int i = 0; i < jumlahPelanggan; i++) {
                    if (namaPelanggan[i].equalsIgnoreCase(nama) && nomorMeja[i] == meja) {
                        indexPelanggan = i;
                        break;
                    }
                }

                if (indexPelanggan == -1) { 
                    indexPelanggan = jumlahPelanggan;
                    namaPelanggan[jumlahPelanggan] = nama;
                    nomorMeja[jumlahPelanggan] = meja;
                    jumlahPelanggan++;
                }

                int jumlahMenu = 0;
                for (int j = 0; j < daftarMenu[indexPelanggan].length; j++) {
                    if (daftarMenu[indexPelanggan][j] != null) {
                        jumlahMenu++;
                    }
                }

                while (true) {
                    System.out.print("\nPilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
                    int pilihanMenu = sc.nextInt();
                    if (pilihanMenu == 0) {
                        break;
                    }
                    if (pilihanMenu < 1 || pilihanMenu > menuKafe.length) {
                        System.out.println("Pilihan menu tidak valid. Silakan coba lagi.");
                        continue;
                    }
                    System.out.print("Jumlah Item: ");
                    int jumlah = sc.nextInt();
                    if (jumlah <= 0) {
                        System.out.println("Jumlah item harus lebih dari 0. Silakan ulangi.");
                        continue;
                    }
                    boolean itemFound = false;
                    for (int j = 0; j < jumlahMenu; j++) {
                        if (daftarMenu[indexPelanggan][j].equals(menuKafe[pilihanMenu - 1])) {
                            jumlahItem[indexPelanggan][j] += jumlah;
                            itemFound = true;
                            break;
                        }
                    }
                    if (!itemFound) {
                        daftarMenu[indexPelanggan][jumlahMenu] = menuKafe[pilihanMenu - 1];
                        jumlahItem[indexPelanggan][jumlahMenu] = jumlah;
                        hargaItem[indexPelanggan][jumlahMenu] = hargaMenu[pilihanMenu - 1];
                        jumlahMenu++;
                    }
                }

            } else if (pilihan == 2) {
                if (jumlahPelanggan == 0) {
                    System.out.println("Belum ada pesanan yang dicatat.");
                } else {
                    System.out.println("\n===== DAFTAR PESANAN =====");
                    for (int i = 0; i < jumlahPelanggan; i++) {
                        System.out.println("Nama Pelanggan: " + namaPelanggan[i]);
                        System.out.println("Nomor Meja: " + nomorMeja[i]);
                        System.out.println("Detail Pesanan:");
                        double totalHarga = 0;
                        for (int j = 0; j < daftarMenu[i].length; j++) {
                            if (daftarMenu[i][j] != null) {
                                double subTotal = jumlahItem[i][j] * hargaItem[i][j];
                                System.out.println("- " + daftarMenu[i][j] + " x " + jumlahItem[i][j] + " = Rp " + (int) subTotal);
                                totalHarga += subTotal;
                            }
                        }
                        System.out.println("Total Harga Pesanan: Rp " + (int) totalHarga);
                        System.out.println("================================");
                    }
                }
            } else if (pilihan == 3) {
                System.out.println("Terima kasih! Sampai jumpa.");
                break;
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        sc.close();
    }
}