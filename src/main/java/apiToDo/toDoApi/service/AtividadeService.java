package apiToDo.toDoApi.service;

import apiToDo.toDoApi.model.AtividadeModel;
import apiToDo.toDoApi.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<AtividadeModel> listarTodas() {
        return atividadeRepository.findAll();
    }

    public Optional<AtividadeModel> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    public AtividadeModel cadastrar(AtividadeModel atividadeModel) {
        return atividadeRepository.save(atividadeModel);
    }

    public AtividadeModel atualizar(AtividadeModel atividadeModel) {
        return atividadeRepository.save(atividadeModel);
    }

    public void deletar(Long id) {
        atividadeRepository.deleteById(id);
    }

}
