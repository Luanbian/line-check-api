package api.lineCheck.infra.interfaces.persistances;

public interface IRepository<T> {
    void create(T data);
}
