package agendamento.api.controller;


import agendamento.api.model.Agendamento;
import agendamento.api.model.dto.AgendamentoCadastroDTO;
import agendamento.api.model.dto.EditarCadastroDTO;
import agendamento.api.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {


    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity agendarColeta(@RequestBody @Valid AgendamentoCadastroDTO dados) {
        var dto = agendamentoRepository.save(new Agendamento(dados));
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public Page<Agendamento> listarAgendamento(@PageableDefault(size = 50, sort = {"id"}) Pageable paginacao) {
        return agendamentoRepository.findAll(paginacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity editarAgendamento(@PathVariable Long id, @RequestBody @Valid EditarCadastroDTO dados) {

        if (!id.equals(dados.id())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID do caminho e o ID do corpo da requisição não correspondem.");
        }
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isPresent()) {
            Agendamento agendamento = optionalAgendamento.get();
            agendamento.atualizarDados(dados);
            agendamentoRepository.save(agendamento);
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAgendamento(@PathVariable Long id) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isPresent()) {
            agendamentoRepository.delete(optionalAgendamento.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity trazerAgendamentoEspecifico(@PathVariable Long id) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);

        if (optionalAgendamento.isPresent()) {
            Agendamento agendamento = optionalAgendamento.get();
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
