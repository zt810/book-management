package ui.validator;

import lib.NhapDuLieu;

import java.util.regex.Pattern;

public class SachValidator {
    private static final String REGEX_TIEU_DE = "^[\\p{L}0-9][\\p{L}0-9\\s\\-\\.]{0,149}$";
    private static final String REGEX_TAC_GIA = "^[\\p{L}][\\p{L}\\s\\.]{1,99}$";

    public static String kiemTraTieuDe() {
        while (true) {
            String tieuDe = NhapDuLieu.nhapChuoi();
            if (Pattern.matches(REGEX_TIEU_DE, tieuDe)) {
                return tieuDe;
            }
            System.out.println("Tieu de khong hop le! Vui long nhap lai:");
        }
    }

    public static String kiemTraTacGia() {
        while (true) {
            String tacGia = NhapDuLieu.nhapChuoi();
            if (Pattern.matches(REGEX_TAC_GIA, tacGia)) {
                return tacGia;
            }
            System.out.println("Ten tac gia khong hop le! Vui long nhap lai:");
        }
    }

    public static int kiemTraNamXuatBan() {
        while (true) {
            int nam = NhapDuLieu.nhapSoNguyen();
            if (nam > 0 && nam <= 2026) {
                return nam;
            }
            System.out.println("Nam xuat ban khong hop le! Vui long nhap lai:");
        }
    }
}
