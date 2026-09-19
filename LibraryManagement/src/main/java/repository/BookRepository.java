package repository;

import entity.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IRepository<Book> {
    private final String tenFile = "database/books.csv";

    @Override
    public void ghiFile(List<Book> danhSach) {
        try {
            FileWriter fw = new FileWriter(this.tenFile);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder noiDung = new StringBuilder();
            for (Book item : danhSach) {
                noiDung.append(item.getBookId())
                        .append(",")
                        .append(item.getTitle())
                        .append(",")
                        .append(item.getAuthor())
                        .append(",")
                        .append(item.getPublishYear())
                        .append(",")
                        .append(item.getGenreId())
                        .append("\n");
            }
            bw.write(noiDung.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Book> docFile() {
        List<Book> danhSach = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.tenFile);
            BufferedReader br = new BufferedReader(fr);
            String dong;
            while ((dong = br.readLine()) != null) {
                if (dong.trim().isEmpty()) {
                    continue;
                }
                String[] duLieu = dong.split(",");
                long ma = Long.parseLong(duLieu[0]);
                String tieuDe = duLieu[1];
                String tacGia = duLieu[2];
                int namXuatBan = Integer.parseInt(duLieu[3]);
                long maTheLoai = Long.parseLong(duLieu[4]);
                Book sach = new Book(ma, tieuDe, tacGia, namXuatBan, maTheLoai);
                danhSach.add(sach);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return danhSach;
    }
}
