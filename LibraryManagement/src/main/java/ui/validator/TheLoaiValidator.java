package ui.validator;

import lib.NhapDuLieu;

import java.util.regex.Pattern;

public class TheLoaiValidator {
    private static final String REGEX_TEN = "^[\\p{L}][\\p{L}\\s]{1,49}$";

    public static String kiemTraTenTheLoai() {
        while (true) {
            String ten = NhapDuLieu.nhapChuoi();
            if (Pattern.matches(REGEX_TEN, ten)) {
                return ten;
            }
            System.out.println("Ten the loai khong hop le! Vui long nhap lai:");
        }
    }
}
