package ui;

import entity.Genre;
import error.DaTonTaiException;
import error.KhongTimThayException;
import lib.NhapDuLieu;
import service.GenreService;
import ui.validator.TheLoaiValidator;

import java.time.Instant;
import java.util.List;

public class QuanLyTheLoai {
    private static final GenreService genreService = GenreService.getInstance();

    public static void hienThiMenu() {
        int luaChon;
        do {
            System.out.println("========================================");
            System.out.println("       QUAN LY THE LOAI SACH");
            System.out.println("========================================");
            System.out.println("  1. Them the loai");
            System.out.println("  2. Cap nhat the loai");
            System.out.println("  3. Xoa the loai");
            System.out.println("  4. Hien thi tat ca the loai");
            System.out.println("  0. Quay lai");
            System.out.println("========================================");
            System.out.print("Nhap lua chon: ");
            luaChon = NhapDuLieu.nhapSoNguyen();
            switch (luaChon) {
                case 1:
                    themTheLoai();
                    break;
                case 2:
                    capNhatTheLoai();
                    break;
                case 3:
                    xoaTheLoai();
                    break;
                case 4:
                    hienThiTatCa();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (luaChon != 0);
    }

    private static void themTheLoai() {
        try {
            System.out.println("--- Them the loai moi ---");
            long ma = Instant.now().toEpochMilli();
            System.out.print("Nhap ten the loai: ");
            String ten = TheLoaiValidator.kiemTraTenTheLoai();
            Genre theLoai = new Genre(ma, ten);
            genreService.them(theLoai);
            System.out.println("Them the loai thanh cong!");
        } catch (DaTonTaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void capNhatTheLoai() {
        try {
            System.out.println("--- Cap nhat the loai ---");
            hienThiTatCa();
            System.out.print("Nhap ma the loai can cap nhat: ");
            long ma = NhapDuLieu.nhapSoNguyenDai();
            Genre theLoaiCu = genreService.layTheoMa(ma);
            System.out.println("The loai hien tai: " + theLoaiCu);
            System.out.print("Nhap ten moi: ");
            String tenMoi = TheLoaiValidator.kiemTraTenTheLoai();
            Genre theLoaiMoi = new Genre(ma, tenMoi);
            genreService.capNhat(ma, theLoaiMoi);
            System.out.println("Cap nhat thanh cong!");
        } catch (KhongTimThayException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void xoaTheLoai() {
        try {
            System.out.println("--- Xoa the loai ---");
            hienThiTatCa();
            System.out.print("Nhap ma the loai can xoa: ");
            long ma = NhapDuLieu.nhapSoNguyenDai();
            Genre theLoai = genreService.layTheoMa(ma);
            System.out.println("Ban chac chan muon xoa: " + theLoai + " ? (1: Co / 0: Khong)");
            int xacNhan = NhapDuLieu.nhapSoNguyen();
            if (xacNhan == 1) {
                genreService.xoa(ma);
                System.out.println("Xoa thanh cong!");
            } else {
                System.out.println("Huy xoa.");
            }
        } catch (KhongTimThayException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void hienThiTatCa() {
        List<Genre> danhSach = genreService.layTatCa();
        if (danhSach.isEmpty()) {
            System.out.println("Chua co the loai nao.");
            return;
        }
        System.out.println("--- Danh sach the loai ---");
        int stt = 1;
        for (Genre item : danhSach) {
            System.out.println(stt + ". " + item);
            stt++;
        }
    }

    public static Genre chonTheLoai() {
        List<Genre> danhSach = genreService.layTatCa();
        if (danhSach.isEmpty()) {
            System.out.println("Chua co the loai nao. Vui long them the loai truoc.");
            return null;
        }
        System.out.println("--- Chon the loai ---");
        int stt = 1;
        for (Genre item : danhSach) {
            System.out.println(stt + ". " + item);
            stt++;
        }
        System.out.print("Nhap so thu tu the loai: ");
        int viTri = NhapDuLieu.nhapSoNguyen();
        if (viTri < 1 || viTri > danhSach.size()) {
            System.out.println("Lua chon khong hop le!");
            return null;
        }
        return danhSach.get(viTri - 1);
    }
}
