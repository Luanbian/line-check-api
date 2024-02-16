package api.lineCheck.presentation.exceptions;

public class ActionNotPermittedException extends RuntimeException {
    public ActionNotPermittedException() {
        super("Ação não permitida, você não pode alterar dados de outro funcionário");
    }
}
