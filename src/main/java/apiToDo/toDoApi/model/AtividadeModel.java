package apiToDo.toDoApi.model;



import apiToDo.toDoApi.enums.Status;
import org.springframework.data.annotation.Id;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class AtividadeModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String tarefa;
    @Enumerated
    private Status status;

   private List<AtividadeModel> listaDeAtividadeModels;


    public AtividadeModel(Long id, String tarefa, Status status) {
        this.id = id;
        this.tarefa = tarefa;
        this.status = status;
    }
    public AtividadeModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
