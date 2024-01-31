package api.lineCheck.presentation.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Suas credenciais estão inválidas");
    }
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
