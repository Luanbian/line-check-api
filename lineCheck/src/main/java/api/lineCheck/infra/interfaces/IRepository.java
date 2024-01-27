package api.lineCheck.infra.interfaces;

public interface IRepository<T> {
    void create(T data);
}
