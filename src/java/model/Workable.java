package model;

import java.util.List;

public interface Workable<T> {
    List<T> getAll();

    int add(T x);

    int delete(int id);

    int update(int id, T newItem);

    void searchById(int id);
}
