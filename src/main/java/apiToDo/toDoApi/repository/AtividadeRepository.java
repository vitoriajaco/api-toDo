package apiToDo.toDoApi.repository;

import apiToDo.toDoApi.model.AtividadeModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AtividadeRepository extends JpaRepository <AtividadeModel, Long>{


}
