package api.lineCheck.presentation.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("email já cadastrado no sistema");
    }
}
