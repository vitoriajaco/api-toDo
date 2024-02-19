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

    public List<AtividadeModel> mostrarTodasAtividades(){
        return atividadeRepository.findAll();
    }
    public Optional<AtividadeModel> buscarAtividadePorId(Long id){
        return atividadeRepository.findById(id);
    }

    public AtividadeModel cadastrarAtividade(AtividadeModel atividadeModel){
        atividadeModel.getId();
        atividadeModel.getTarefa();
        atividadeModel.getStatus();
        return atividadeRepository.save(atividadeModel);
    }

    public AtividadeModel alterarAtividade(AtividadeModel atividadeModel){
        atividadeModel.getId();
        atividadeModel.setTarefa(atividadeModel.getTarefa());
        atividadeModel.setStatus(atividadeModel.getStatus());
        return atividadeRepository.save(atividadeModel);
    }
    public void deletarAtividade(Long id){
        atividadeRepository.deleteById(id);
    }

}
