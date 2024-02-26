package api.lineCheck.presentation.exceptions;

public class LineConflictException extends RuntimeException {
    public LineConflictException () {
        super("Existe um conflito de horários e datas");
    }
}
