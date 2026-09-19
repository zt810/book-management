package repository;

import java.util.List;

public interface IRepository<T> {
    void ghiFile(List<T> danhSach);
    List<T> docFile();
}
