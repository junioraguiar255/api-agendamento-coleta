package agendamento.api.model;


import agendamento.api.model.dto.AgendamentoCadastroDTO;
import agendamento.api.model.dto.EditarCadastroDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "agendamento")
@Entity(name = "Agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "raio_localizacao")
    private Double raioLocalizacao;//para armazenar o raio de localização em que a coleta será realizada.

    public Agendamento(AgendamentoCadastroDTO dados) {
        this.data = dados.data();
        this.observacoes = dados.observacao();
        this.localizacao = dados.localizacao();
        this.raioLocalizacao = dados.raioLocalizacao();
    }

    public void atualizarDados(EditarCadastroDTO dados) {
        this.observacoes = dados.observacao();
        this.data = dados.data();
    }
}
