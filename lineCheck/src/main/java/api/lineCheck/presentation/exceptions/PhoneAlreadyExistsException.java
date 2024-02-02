package api.lineCheck.presentation.exceptions;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException () {
        super("Telefone já cadastrado no sistema");
    }
}
