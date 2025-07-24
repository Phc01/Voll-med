package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public Page<DadosListagemPaciente> listarPaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return pacienteRepository.findAll(pageable)
                .map(DadosListagemPaciente::new);
    }

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        pacienteRepository.save(new Paciente(dados));
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());

        paciente.atualizarInformacoesPaciente(dados);
    }

}
