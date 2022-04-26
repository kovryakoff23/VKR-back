package boot.service;

import java.util.List;

public interface ServiceMag<T> {
    T get(long var1);

    List<T> getAll();

    void save(T entity);

    void delete(long id);
}
