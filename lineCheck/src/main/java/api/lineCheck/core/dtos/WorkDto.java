package api.lineCheck.core.dtos;
import jakarta.validation.constraints.*;
import java.util.List;

public record WorkDto(
        @NotNull(message = "funcionário não encontrado")
        String accountId,
        @NotNull(message = "serviço não encontrado")
        String serviceId,
        @NotNull(message = "dias da semana inválidos")
        List<String> daysOfTheWeeks,
        @NotNull(message = "horário inválido, o horário deve ter 8 caracteres no formato HH:mm:ss")
        @Size(min = 8, max = 8, message = "horário deve ter 8 caracteres no formato HH:mm:ss")
        String startJourneyModel,
        @NotNull(message = "horário inválido, o horário deve ter 8 caracteres no formato HH:mm:ss")
        @Size(min = 8, max = 8, message = "horário deve ter 8 caracteres no formato HH:mm:ss")
        String startLineModel,
        @NotNull(message = "horário inválido, o horário deve ter 8 caracteres no formato HH:mm:ss")
        @Size(min = 8, max = 8, message = "horário deve ter 8 caracteres no formato HH:mm:ss")
        String endLineModel,
        @NotNull(message = "logistica não encontrada")
        String logistic,
        @NotNull(message = "veiculo não encontrado")
        String vehicle,
        @NotNull(message = "fábrica não encontrada")
        String manufacture
) {}
