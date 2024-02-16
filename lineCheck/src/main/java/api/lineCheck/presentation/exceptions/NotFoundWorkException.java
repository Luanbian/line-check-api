package api.lineCheck.presentation.exceptions;

public class NotFoundWorkException extends RuntimeException {
    public NotFoundWorkException() {
        super("Linha não encontrada, verifique se o id está correto");
    }
}
