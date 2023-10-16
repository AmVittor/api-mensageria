package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.Grupo;
import chatte.api.v1.API.Chatte.entities.Mensagem;
import chatte.api.v1.API.Chatte.entities.MensagemResponse;
import chatte.api.v1.API.Chatte.entities.Usuario;
import chatte.api.v1.API.Chatte.repositories.GrupoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Grupo RestController", description = "Responsável por gerir grupos nesse projeto")
@RestController
@RequestMapping("/api/v1/grupos")
public class GrupoController {

    @Autowired
    GrupoRepository grupoRepository;

    @Operation(
            summary = "Método responsável por buscar todos os grupos",
            description = "Esse método é responsável por fazer uma busca na base de dados que retorna todos os grupos " +
                    "cadastrados." +
                    "\nPode retornar os seguintes status http: 200, 204"
    )
    @GetMapping
    public ResponseEntity<List<Grupo>> buscarTodos() {
        List<Grupo> lista = grupoRepository.findAll();

        if(lista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(lista);
    }

    @Operation(
            summary = "Método responsável por criar um novo grupo",
            description = "Esse método é responsável por inserir um novo grupo na base de dados, recebe como parâmetro " +
                    "um objeto do tipo Grupo" +
                    "\nPode retornar os seguintes status http: 201"
    )
    @PostMapping
    public ResponseEntity<Grupo> criar(@RequestBody @Valid Grupo grupo) {
        return ResponseEntity.status(201).body(grupoRepository.save(grupo));
    }

    @Operation(
            summary = "Método responsável por atualizar as informações de um grupo",
            description = "Esse método é reponsável por atualizar as informações do grupo na base de dados, " +
                    "recebe como parâmetro um objeto do tipo Grupo e o id do grupo via url" +
                    "\nPode retornar os seguintes status http: 200, 404"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> atualizar(@RequestBody @Valid Grupo grupo, @PathVariable("id") Integer id) {
        if(grupoRepository.existsById(id)) {
            grupoRepository.save(grupo);
            return ResponseEntity.status(200).body(grupo);
        }

        return ResponseEntity.status(404).build();
    }

    @Operation(
            summary = "Método responsável por deletar um grupo",
            description = "Esse método é responsável por deletar um grupo da base de dados, recebe como parâmetro o id " +
                    "do grupo que será deletado" +
                    "\nPode retornar os seguintes status http: 200, 404"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        if(grupoRepository.existsById(id)) {
            grupoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Usuario>> allUsers(@PathVariable Integer id) {
        if(grupoRepository.existsById(id)) {
            List<Usuario> users = grupoRepository.findAllUsers(id);

            return users.isEmpty() ?
                    ResponseEntity.status(204).build() :
                    ResponseEntity.ok(users);

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<MensagemResponse>> allMessages(@PathVariable Integer id) {
        if(grupoRepository.existsById(id)) {
            List<MensagemResponse> messages = grupoRepository.findAllMessages(id);

            return messages.isEmpty() ?
                    ResponseEntity.status(204).build() :
                    ResponseEntity.ok(messages);

        }
        return ResponseEntity.notFound().build();
    }
}
