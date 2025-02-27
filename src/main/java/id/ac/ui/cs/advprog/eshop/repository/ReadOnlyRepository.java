package id.ac.ui.cs.advprog.eshop.repository;

public interface ReadOnlyRepository<T, ID> {
    Iterable<T> findAll();
    T findById(ID id);
}