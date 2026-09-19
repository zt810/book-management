package lib;

import java.util.Scanner;

public class NhapDuLieu {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int nhapSoNguyen() {
        while (true) {
            try {
                String giaTri = SCANNER.nextLine();
                return Integer.parseInt(giaTri);
            } catch (NumberFormatException e) {
                System.out.println("Du lieu khong hop le, vui long nhap lai!");
            }
        }
    }

    public static long nhapSoNguyenDai() {
        while (true) {
            try {
                String giaTri = SCANNER.nextLine();
                return Long.parseLong(giaTri);
            } catch (NumberFormatException e) {
                System.out.println("Du lieu khong hop le, vui long nhap lai!");
            }
        }
    }

    public static String nhapChuoi() {
        return SCANNER.nextLine();
    }
}
