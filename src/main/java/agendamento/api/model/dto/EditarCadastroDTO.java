package agendamento.api.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditarCadastroDTO(

        Long id,
        @NotNull
        @Future
        LocalDateTime data,
        String observacao


) {

}
