# ToDo API

API REST para gerenciamento de atividades (lista de tarefas), desenvolvida com Spring Boot. Permite cadastrar, listar, buscar, atualizar e remover atividades, controlando o status de cada uma, incluindo a marcação automática de atividades atrasadas.

## Tecnologias

- Java 17
- Spring Boot 3.2.2
  - Spring Web
  - Spring Data JPA
  - Bean Validation
- H2 Database (em memória)
- Lombok
- Maven (wrapper incluso)

## Estrutura do projeto

```
src/main/java/apiToDo/toDoApi
├── ToDoApiApplication.java      # Classe principal
├── controller
│   └── AtividadeController.java # Endpoints REST
├── service
│   └── AtividadeService.java    # Regras de negócio
├── repository
│   └── AtividadeRepository.java # Acesso a dados (JpaRepository)
├── model
│   └── AtividadeModel.java      # Entidade Atividade
└── enums
    └── Status.java              # Status da atividade
```

## Como executar

Pré-requisito: JDK 17 ou superior.

```bash
git clone <url-do-repositorio>
cd api-toDo
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

Para rodar os testes:

```bash
./mvnw test
```

> O banco H2 é em memória: os dados são perdidos ao reiniciar a aplicação.

## Modelo de dados

**Atividade**

| Campo         | Tipo        | Descrição                                   |
|---------------|-------------|---------------------------------------------|
| `id`          | Long        | Identificador gerado automaticamente        |
| `tarefa`      | String      | Descrição da tarefa (obrigatória)           |
| `dataCriacao` | LocalDate   | Data de criação (`yyyy-MM-dd`)              |
| `dataLimite`  | LocalDate   | Prazo para conclusão (`yyyy-MM-dd`)         |
| `status`      | Status      | Situação atual da atividade                 |

**Status**: `EM_ABERTO`, `EXECUTANDO`, `CONCLUIDA`, `ATRASADO`

## Endpoints

Base URL: `/atividades`

| Método   | Rota               | Descrição                    | Respostas                     |
|----------|--------------------|------------------------------|-------------------------------|
| `GET`    | `/atividades`      | Lista todas as atividades    | `200 OK`                      |
| `GET`    | `/atividades/{id}` | Busca uma atividade por id   | `200 OK`, `404 Not Found`     |
| `POST`   | `/atividades`      | Cadastra uma nova atividade  | `201 Created`                 |
| `PUT`    | `/atividades/{id}` | Atualiza uma atividade       | `200 OK`, `404 Not Found`     |
| `DELETE` | `/atividades/{id}` | Remove uma atividade         | `204 No Content`, `404 Not Found` |

### Exemplos

Cadastrar:

```bash
curl -X POST http://localhost:8080/atividades \
  -H "Content-Type: application/json" \
  -d '{"tarefa": "Estudar Spring Boot", "dataLimite": "2026-12-31"}'
```

Resposta:

```json
{
  "id": 1,
  "tarefa": "Estudar Spring Boot",
  "dataCriacao": "2026-11-19",
  "dataLimite": "2026-12-31",
  "status": "EM_ABERTO"
}
```

Atualizar:

```bash
curl -X PUT http://localhost:8080/atividades/1 \
  -H "Content-Type: application/json" \
  -d '{"tarefa": "Estudar Spring Boot", "dataLimite": "2026-12-31", "status": "EXECUTANDO"}'
```

Listar e remover:

```bash
curl http://localhost:8080/atividades
curl -X DELETE http://localhost:8080/atividades/1
```

## Regras de negócio

- Toda nova atividade é criada com o status `EM_ABERTO`, independentemente do valor enviado.
- A `tarefa` não pode ser nula ou vazia (lança `IllegalArgumentException`).
- Se `dataCriacao` não for informada, é preenchida com a data atual.
- Se a `dataLimite` for anterior à data atual no cadastro ou na atualização, o status passa a ser `ATRASADO`.
- Ao listar, toda atividade com `dataLimite` vencida e status diferente de `CONCLUIDA` é retornada como `ATRASADO`.
- Na atualização, o `id` da URL sempre prevalece sobre o id enviado no corpo.
