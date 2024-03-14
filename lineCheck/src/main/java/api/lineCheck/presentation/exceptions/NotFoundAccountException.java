package api.lineCheck.presentation.exceptions;

public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException () {
        super("usuário não encontrado, verifique se o id está correto");
    }
}
