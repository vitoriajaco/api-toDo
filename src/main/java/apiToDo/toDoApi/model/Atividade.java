package apiToDo.toDoApi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import apiToDo.toDoApi.enums.Status;

import org.springframework.data.annotation.Id;

import java.util.List;


public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tarefa;
    private Status status;

   private List<Atividade> listaDeAtividades;

    public Atividade(Long id, String tarefa, Status status) {
        this.id = id;
        this.tarefa = tarefa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }


    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Atividade> getListaDeAtividades() {
        return listaDeAtividades;
    }


}
