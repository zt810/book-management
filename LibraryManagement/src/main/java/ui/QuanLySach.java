package ui;

import entity.Book;
import entity.Genre;
import error.KhongTimThayException;
import lib.NhapDuLieu;
import service.BookService;
import service.GenreService;
import ui.validator.SachValidator;

import java.time.Instant;
import java.util.List;

public class QuanLySach {
    private static final BookService bookService = BookService.getInstance();
    private static final GenreService genreService = GenreService.getInstance();

    public static void hienThiMenu() {
        int luaChon;
        do {
            System.out.println("========================================");
            System.out.println("          QUAN LY SACH");
            System.out.println("========================================");
            System.out.println("  1. Them sach");
            System.out.println("  2. Cap nhat sach");
            System.out.println("  3. Xoa sach");
            System.out.println("  4. Hien thi tat ca sach");
            System.out.println("  5. Loc theo the loai");
            System.out.println("  6. Tim kiem theo tieu de");
            System.out.println("  0. Quay lai");
            System.out.println("========================================");
            System.out.print("Nhap lua chon: ");
            luaChon = NhapDuLieu.nhapSoNguyen();
            switch (luaChon) {
                case 1:
                    themSach();
                    break;
                case 2:
                    capNhatSach();
                    break;
                case 3:
                    xoaSach();
                    break;
                case 4:
                    hienThiTatCa();
                    break;
                case 5:
                    locTheoTheLoai();
                    break;
                case 6:
                    timKiemTheoTieuDe();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (luaChon != 0);
    }

    private static void themSach() {
        System.out.println("--- Them sach moi ---");
        long ma = Instant.now().toEpochMilli();
        System.out.print("Nhap tieu de: ");
        String tieuDe = SachValidator.kiemTraTieuDe();
        System.out.print("Nhap tac gia: ");
        String tacGia = SachValidator.kiemTraTacGia();
        System.out.print("Nhap nam xuat ban: ");
        int namXB = SachValidator.kiemTraNamXuatBan();
        Genre theLoai = QuanLyTheLoai.chonTheLoai();
        if (theLoai == null) {
            System.out.println("Khong the them sach khi chua co the loai.");
            return;
        }
        Book sachMoi = new Book(ma, tieuDe, tacGia, namXB, theLoai.getGenreId());
        bookService.them(sachMoi);
        System.out.println("Them sach thanh cong!");
    }

    private static void capNhatSach() {
        try {
            System.out.println("--- Cap nhat sach ---");
            hienThiTatCa();
            System.out.print("Nhap ma sach can cap nhat: ");
            long ma = NhapDuLieu.nhapSoNguyenDai();
            Book sachCu = bookService.layTheoMa(ma);
            System.out.println("Sach hien tai: " + sachCu);
            System.out.print("Nhap tieu de moi: ");
            String tieuDe = SachValidator.kiemTraTieuDe();
            System.out.print("Nhap tac gia moi: ");
            String tacGia = SachValidator.kiemTraTacGia();
            System.out.print("Nhap nam xuat ban moi: ");
            int namXB = SachValidator.kiemTraNamXuatBan();
            Genre theLoai = QuanLyTheLoai.chonTheLoai();
            if (theLoai == null) {
                System.out.println("Cap nhat that bai do khong chon duoc the loai.");
                return;
            }
            Book sachMoi = new Book(ma, tieuDe, tacGia, namXB, theLoai.getGenreId());
            bookService.capNhat(ma, sachMoi);
            System.out.println("Cap nhat thanh cong!");
        } catch (KhongTimThayException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void xoaSach() {
        try {
            System.out.println("--- Xoa sach ---");
            hienThiTatCa();
            System.out.print("Nhap ma sach can xoa: ");
            long ma = NhapDuLieu.nhapSoNguyenDai();
            Book sach = bookService.layTheoMa(ma);
            System.out.println("Ban chac chan muon xoa: " + sach + " ? (1: Co / 0: Khong)");
            int xacNhan = NhapDuLieu.nhapSoNguyen();
            if (xacNhan == 1) {
                bookService.xoa(ma);
                System.out.println("Xoa thanh cong!");
            } else {
                System.out.println("Huy xoa.");
            }
        } catch (KhongTimThayException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void hienThiTatCa() {
        List<Book> danhSach = bookService.layTatCa();
        hienThiDanhSach(danhSach);
    }

    private static void locTheoTheLoai() {
        System.out.println("--- Loc sach theo the loai ---");
        Genre theLoai = QuanLyTheLoai.chonTheLoai();
        if (theLoai == null) {
            return;
        }
        List<Book> danhSach = bookService.layTheoTheLoai(theLoai.getGenreId());
        System.out.println("Sach thuoc the loai [" + theLoai.getGenreName() + "]:");
        hienThiDanhSach(danhSach);
    }

    private static void timKiemTheoTieuDe() {
        System.out.println("--- Tim kiem sach theo tieu de ---");
        System.out.print("Nhap tu khoa: ");
        String tuKhoa = NhapDuLieu.nhapChuoi();
        List<Book> ketQua = bookService.timTheoTieuDe(tuKhoa);
        if (ketQua.isEmpty()) {
            System.out.println("Khong tim thay sach nao phu hop.");
            return;
        }
        System.out.println("Ket qua tim kiem:");
        hienThiDanhSach(ketQua);
    }

    private static void hienThiDanhSach(List<Book> danhSach) {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        int stt = 1;
        for (Book sach : danhSach) {
            String tenTheLoai = layTenTheLoai(sach.getGenreId());
            System.out.println(stt + ". " + sach + " | The loai: " + tenTheLoai);
            stt++;
        }
    }

    private static String layTenTheLoai(long maTheLoai) {
        for (Genre item : genreService.layTatCa()) {
            if (maTheLoai == item.getGenreId()) {
                return item.getGenreName();
            }
        }
        return "Khong xac dinh";
    }
}
