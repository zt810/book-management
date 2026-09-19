package service;

import error.KhongTimThayException;

import java.util.List;

public interface IService<T> {
    void them(T t);
    void capNhat(long ma, T t) throws KhongTimThayException;
    void xoa(long ma) throws KhongTimThayException;
    List<T> layTatCa();
    T layTheoMa(long ma) throws KhongTimThayException;
    int timViTriTheoMa(long ma) throws KhongTimThayException;
}
