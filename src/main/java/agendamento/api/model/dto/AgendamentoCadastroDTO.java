package agendamento.api.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoCadastroDTO(

        @NotNull
        @Future
        LocalDateTime data,

        String observacao,
        String localizacao,
        Double raioLocalizacao


) {
}
