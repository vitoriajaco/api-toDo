package apiToDo.toDoApi.service;

import apiToDo.toDoApi.enums.Status;
import apiToDo.toDoApi.model.AtividadeModel;
import apiToDo.toDoApi.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<AtividadeModel> listarTodas() {
        List<AtividadeModel> atividades = atividadeRepository.findAll();

        atividades.forEach(a -> {
            if (a.getDataLimite() != null &&
                    a.getDataLimite().isBefore(LocalDate.now()) &&
                    a.getStatus() != Status.CONCLUIDA) {
                a.setStatus(Status.ATRASADO);
            }
        });

        return atividades;
    }

    public Optional<AtividadeModel> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    public AtividadeModel cadastrar(AtividadeModel atividadeModel) {
        if (atividadeModel.getTarefa() == null || atividadeModel.getTarefa().trim().isEmpty()) {
            throw new IllegalArgumentException("A tarefa não pode ser nula ou vazia.");
        }
        if (atividadeModel.getDataCriacao() == null) {
            atividadeModel.setDataCriacao(LocalDate.now());
        }
        if (atividadeModel.getDataLimite() != null && atividadeModel.getDataLimite().isBefore(LocalDate.now())) {
            atividadeModel.setStatus(Status.ATRASADO);
        }
        return atividadeRepository.save(atividadeModel);
    }

    public AtividadeModel atualizar(AtividadeModel atividadeModel) {

        if (atividadeModel.getTarefa() == null || atividadeModel.getTarefa().trim().isEmpty()) {
            throw new IllegalArgumentException("A tarefa não pode ser nula ou vazia.");
        }

        if (atividadeModel.getDataCriacao() == null) {
            atividadeModel.setDataCriacao(LocalDate.now());
        }

        if (atividadeModel.getDataLimite() != null && atividadeModel.getDataLimite().isBefore(LocalDate.now())) {
            atividadeModel.setStatus(Status.ATRASADO);
        }
        return atividadeRepository.save(atividadeModel);
    }

    public void deletar(Long id) {
        atividadeRepository.deleteById(id);
    }

}
