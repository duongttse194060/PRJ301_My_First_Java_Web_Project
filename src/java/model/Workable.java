package model;

import java.util.List;

public interface Workable<T> {

    List<T> getAll();

    int add(T x);

    int delete(int id);

    int update(T x);

    DtoHumanType searchById(int id);
}
