package api.lineCheck.presentation.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Token inválido");
    }
}
