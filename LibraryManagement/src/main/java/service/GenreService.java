package service;

import entity.Genre;
import error.DaTonTaiException;
import error.KhongTimThayException;
import repository.GenreRepository;

import java.util.List;

public class GenreService implements IService<Genre> {
    private final GenreRepository genreRepository = new GenreRepository();
    private List<Genre> danhSachTheLoai;

    private static final GenreService instance = new GenreService();

    private GenreService() {
        this.danhSachTheLoai = genreRepository.docFile();
    }

    public static GenreService getInstance() {
        return instance;
    }

    @Override
    public void them(Genre theLoai) {
        for (Genre item : this.danhSachTheLoai) {
            if (item.getGenreName().equalsIgnoreCase(theLoai.getGenreName())) {
                throw new DaTonTaiException("The loai da ton tai");
            }
        }
        this.danhSachTheLoai.add(theLoai);
        this.genreRepository.ghiFile(this.danhSachTheLoai);
    }

    @Override
    public void capNhat(long ma, Genre theLoai) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        this.danhSachTheLoai.set(viTri, theLoai);
        this.genreRepository.ghiFile(this.danhSachTheLoai);
    }

    @Override
    public void xoa(long ma) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        this.danhSachTheLoai.remove(viTri);
        this.genreRepository.ghiFile(this.danhSachTheLoai);
    }

    @Override
    public List<Genre> layTatCa() {
        return this.danhSachTheLoai;
    }

    @Override
    public Genre layTheoMa(long ma) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        return this.danhSachTheLoai.get(viTri);
    }

    @Override
    public int timViTriTheoMa(long ma) throws KhongTimThayException {
        for (int i = 0; i < this.danhSachTheLoai.size(); i++) {
            if (ma == this.danhSachTheLoai.get(i).getGenreId()) {
                return i;
            }
        }
        throw new KhongTimThayException("Khong tim thay the loai voi ma: " + ma);
    }
}
