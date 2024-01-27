package api.lineCheck.infra.interfaces;

public interface ICreateRepository<T> {
    void create(T data);
}
