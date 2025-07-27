package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idPaciente() == null) {
            return;
        }

        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente excluido");
        }
    }
}
