package apiToDo.toDoApi.controller;

import apiToDo.toDoApi.model.AtividadeModel;
import apiToDo.toDoApi.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AtividadeController {

    @Autowired
    AtividadeService atividadeService;

    @GetMapping(path = "/listarAtividades")
    public List<AtividadeModel> mostrarTodasAtividades(){
        return atividadeService.mostrarTodasAtividades();

    }
    @GetMapping(path = "/buscarPorId/{id}")
    public Optional<AtividadeModel> buscarAtividadePorId(@PathVariable Long id){
        return atividadeService.buscarAtividadePorId(id);

    }

    @PostMapping(path= "/cadastrarAtividade")
    public AtividadeModel cadastrarAtividade (@RequestBody AtividadeModel atividadeModel){
        return atividadeService.cadastrarAtividade(atividadeModel);
    }

    @PutMapping(path="/alterarAtividade/{id}")
    public AtividadeModel alterarAtividade (@RequestBody AtividadeModel atividadeModel){
        return atividadeService.alterarAtividade(atividadeModel);
    }

    public void deletarAtividade (@PathVariable Long id){
        atividadeService.deletarAtividade(id);
    }
}
