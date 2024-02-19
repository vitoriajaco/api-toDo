package apiToDo.toDoApi.service;

import apiToDo.toDoApi.model.AtividadeModel;
import apiToDo.toDoApi.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<AtividadeModel> mostrarTodasAtividades(){
        return atividadeRepository.findAll();
    }

}
