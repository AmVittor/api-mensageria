package chatte.api.v1.API.Chatte.controller;

import chatte.api.v1.API.Chatte.entities.Usuario;
import chatte.api.v1.API.Chatte.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UsuarioControllerTest {

    Usuario u = new Usuario();

    public void setU(Usuario u) {
        this.u = u;
        u.setNome(Mockito.anyString());
        u.setEmail(Mockito.anyString());
        u.setSenha(Mockito.anyString());
        u.setCargo(Mockito.anyString());
        u.setEmpresa(Mockito.anyString());
        u.setAdm(Mockito.anyBoolean());
        u.setDtNascimento(Mockito.anyString());
    }

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void buscarTodos() {
    }

    @Test
    void logar() {
    }

    @Test
    void deslogar() {
    }

    @Test
    void cadastrar() {

    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}