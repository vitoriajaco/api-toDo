package apiToDo.toDoApi.controller;


import apiToDo.toDoApi.model.AtividadeModel;
import apiToDo.toDoApi.service.AtividadeService;
import apiToDo.toDoApi.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeModel>> listarTodas() {
        List<AtividadeModel> atividades = atividadeService.mostrarTodasAtividades();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeModel> buscarPorId(@PathVariable Long id) {
        Optional<AtividadeModel> atividade = atividadeService.buscarAtividadePorId(id);
        return atividade.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeModel> cadastrar(@RequestBody AtividadeModel atividadeModel) {
        // Garante que toda nova atividade comece com status EM_ABERTO
        atividadeModel.setStatus(Status.EM_ABERTO);
        AtividadeModel criada = atividadeService.cadastrarAtividade(atividadeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeModel> alterar(@PathVariable Long id, @RequestBody AtividadeModel atividadeModel) {
        Optional<AtividadeModel> existente = atividadeService.buscarAtividadePorId(id);
        if (!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atividadeModel.setId(id); // Garante que o ID do path seja usado
        AtividadeModel atualizada = atividadeService.alterarAtividade(atividadeModel);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<AtividadeModel> existente = atividadeService.buscarAtividadePorId(id);
        if (!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atividadeService.deletarAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
