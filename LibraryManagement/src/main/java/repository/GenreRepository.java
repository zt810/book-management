package repository;

import entity.Genre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository implements IRepository<Genre> {
    private final String tenFile = "database/genres.csv";

    @Override
    public void ghiFile(List<Genre> danhSach) {
        try {
            FileWriter fw = new FileWriter(this.tenFile);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder noiDung = new StringBuilder();
            for (Genre item : danhSach) {
                noiDung.append(item.getGenreId())
                        .append(",")
                        .append(item.getGenreName())
                        .append("\n");
            }
            bw.write(noiDung.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Genre> docFile() {
        List<Genre> danhSach = new ArrayList<>();
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
                String ten = duLieu[1];
                Genre theLoai = new Genre(ma, ten);
                danhSach.add(theLoai);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return danhSach;
    }
}
