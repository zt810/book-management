package ui;

import lib.NhapDuLieu;

public class MenuChinh {
    public static void hienThi() {
        int luaChon;
        do {
            System.out.println();
            System.out.println("========================================");
            System.out.println("   HE THONG QUAN LY THU VIEN SACH");
            System.out.println("========================================");
            System.out.println("  1. Quan ly the loai sach");
            System.out.println("  2. Quan ly sach");
            System.out.println("  0. Thoat chuong trinh");
            System.out.println("========================================");
            System.out.print("Nhap lua chon: ");
            luaChon = NhapDuLieu.nhapSoNguyen();
            switch (luaChon) {
                case 1:
                    QuanLyTheLoai.hienThiMenu();
                    break;
                case 2:
                    QuanLySach.hienThiMenu();
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (luaChon != 0);
    }
}
