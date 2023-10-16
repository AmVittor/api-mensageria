package chatte.api.v1.API.Chatte.entities;

import chatte.api.v1.API.Chatte.controller.UsuarioController;
import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioTest {

    Usuario u = new Usuario();
    Usuario u2 = new Usuario(
            "teste", "teste@gmail.com", "SenhaForte123@", "Tester",
            "DISPONIVEL", "Teste", false, "2001-11-14"
    );

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioRepository usuarioRepository;


    //TESTES DE CADASTRAR
    @Test
    void cadastrarWhenNameHasSpecialCharacter() {
        u.setNome("M@theus");
        ResponseEntity<Usuario> responseEntity = usuarioController.cadastrar(u);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void cadastrarWhenNameHasNumber() {
        u.setNome("M4theus");
        ResponseEntity<Usuario> responseEntity = usuarioController.cadastrar(u);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    //TESTES DE LOGAR
    @Test
    void logarWhenUsuarioIsNotNull() {
        u.setEmail("Theuzin.@gmail.com");
        u.setSenha("SenhaForte123@");

        when(usuarioRepository.findByEmailAndSenha(anyString(), anyString())).thenReturn(u);
        ResponseEntity<Boolean> test = usuarioController.logar(u.getEmail(), u.getSenha());
        assertEquals(200, test.getStatusCodeValue());
        assertEquals(Status.DISPONIVEL.getDescricao(), u.getStatusUsuario());
    }


    @Test
    void logarHasErrorWhenUsuarioIsNull() {
        when(usuarioRepository.findByEmailAndSenha(anyString(), anyString())).thenReturn(null);

        ResponseEntity<Boolean> test = usuarioController.logar("robertin", "senha123@");
        assertEquals(403, test.getStatusCodeValue());
    }


    //TESTES BUSCAR TODOS

    @Test
    void buscarTodosWhenListaIsNotEmpty() {
        List<Usuario> lista = new ArrayList<>();
        lista.add(u);
        when(usuarioRepository.findAll()).thenReturn(lista);

        ResponseEntity<List<Usuario>> test = usuarioController.buscarTodos();
        assertEquals(200, test.getStatusCodeValue());
        assertEquals(lista, test.getBody());
    }

    @Test
    void buscarTodosWhenListaIsEmpty() {
        List<Usuario> lista = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(lista);

        ResponseEntity<List<Usuario>> test = usuarioController.buscarTodos();
        assertEquals(204, test.getStatusCodeValue());
    }

    //TETES DESLOGAR
    @Test
    void deslogarWhenUsuarioIsNull() {
        when(usuarioRepository.findByEmailAndSenha(anyString(), anyString())).thenReturn(null);

        ResponseEntity<Boolean> test = usuarioController.deslogar("robertin", "senha123@");
        assertEquals(403, test.getStatusCodeValue());
    }

    @Test
    void deslogarWhenUsuarioIsNotNull() {
        u.setEmail("Theuzin.@gmail.com");
        u.setSenha("SenhaForte123@");

        when(usuarioRepository.findByEmailAndSenha(anyString(), anyString())).thenReturn(u);
        ResponseEntity<Boolean> test = usuarioController.deslogar(u.getEmail(), u.getSenha());
        assertEquals(200, test.getStatusCodeValue());
        assertEquals(Status.OFFLINE.getDescricao(), u.getStatusUsuario());
    }

    //TESTES ATUALIZAR

    @Test
    void atualizarWhenIdExists() {
        u2.setIdUsuario(1);

        when(usuarioRepository.existsById(anyInt())).thenReturn(true);

        ResponseEntity<Usuario> test = usuarioController.atualizar(u2, 1);

        assertEquals(200, test.getStatusCodeValue());
        assertEquals(u2, test.getBody());
    }

    @Test
    void atualizarWhenIdNotExists() {
        when(usuarioRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity<Usuario> test = usuarioController.atualizar(u2, 1);

        assertEquals(404, test.getStatusCodeValue());
    }

    //TESTES DELETAR

    @Test
    void deletarWhenIdNotExists() {
        when(usuarioRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity test = usuarioController.deletar(1);

        assertEquals(404, test.getStatusCodeValue());
        assertFalse(usuarioRepository.existsById(1));
    }

    @Test
    void deletarWhenIdExists() {
        u.setIdUsuario(1);

        when(usuarioRepository.existsById(anyInt())).thenReturn(true);
        ResponseEntity test = usuarioController.deletar(1);
        assertEquals(200, test.getStatusCodeValue());
        assertTrue(usuarioRepository.existsById(1));
    }

    @Test
    void enviarMensagem() {
    }

    @Test
    void editarMensagem() {
    }

    @Test
    void excluirMensagem() {
    }

    @Test
    void ingressarGrupo() {
    }

    @Test
    void deixarGrupo() {
    }

    @Test
    void anexarArquivo() {
    }

    @Test
    void removerUsuario() {
    }

    @Test
    void criarGrupo() {
    }

    @Test
    void adicionarUsuario() {
    }

}