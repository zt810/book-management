package service;

import entity.Book;
import error.KhongTimThayException;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService implements IService<Book> {
    private final BookRepository bookRepository = new BookRepository();
    private List<Book> danhSachSach;

    private static final BookService instance = new BookService();

    private BookService() {
        this.danhSachSach = bookRepository.docFile();
    }

    public static BookService getInstance() {
        return instance;
    }

    @Override
    public void them(Book sach) {
        this.danhSachSach.add(sach);
        this.bookRepository.ghiFile(this.danhSachSach);
    }

    @Override
    public void capNhat(long ma, Book sach) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        this.danhSachSach.set(viTri, sach);
        this.bookRepository.ghiFile(this.danhSachSach);
    }

    @Override
    public void xoa(long ma) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        this.danhSachSach.remove(viTri);
        this.bookRepository.ghiFile(this.danhSachSach);
    }

    @Override
    public List<Book> layTatCa() {
        return this.danhSachSach;
    }

    @Override
    public Book layTheoMa(long ma) throws KhongTimThayException {
        int viTri = this.timViTriTheoMa(ma);
        return this.danhSachSach.get(viTri);
    }

    @Override
    public int timViTriTheoMa(long ma) throws KhongTimThayException {
        for (int i = 0; i < this.danhSachSach.size(); i++) {
            if (ma == this.danhSachSach.get(i).getBookId()) {
                return i;
            }
        }
        throw new KhongTimThayException("Khong tim thay sach voi ma: " + ma);
    }

    public List<Book> layTheoTheLoai(long maTheLoai) {
        List<Book> ketQua = new ArrayList<>();
        for (Book sach : this.danhSachSach) {
            if (maTheLoai == sach.getGenreId()) {
                ketQua.add(sach);
            }
        }
        return ketQua;
    }

    public List<Book> timTheoTieuDe(String tuKhoa) {
        List<Book> ketQua = new ArrayList<>();
        for (Book sach : this.danhSachSach) {
            if (sach.getTitle().toLowerCase().contains(tuKhoa.toLowerCase())) {
                ketQua.add(sach);
            }
        }
        return ketQua;
    }
}
