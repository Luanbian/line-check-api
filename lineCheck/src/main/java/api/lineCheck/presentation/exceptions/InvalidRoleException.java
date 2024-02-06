package api.lineCheck.presentation.exceptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException() {
        super("A função escolhida não existe no sistema");
    }
}
