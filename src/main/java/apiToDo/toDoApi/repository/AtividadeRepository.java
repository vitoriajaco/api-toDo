package apiToDo.toDoApi.repository;

import apiToDo.toDoApi.model.Atividade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AtividadeRepository extends JpaRepository <Atividade, Long>{


}
