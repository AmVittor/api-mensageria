package chatte.api.v1.API.Chatte.repositories;

import chatte.api.v1.API.Chatte.entities.Grupo;
import chatte.api.v1.API.Chatte.entities.Mensagem;
import chatte.api.v1.API.Chatte.entities.MensagemResponse;
import chatte.api.v1.API.Chatte.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {


    @Query("SELECT new Usuario(u.idUsuario, u.nome, u.email, u.cargo, u.statusUsuario, u.empresa, u.isAdm, u.dtNascimento) FROM Usuario u \n" +
            "INNER JOIN UsuarioGrupo ug \n" +
            "ON u.idUsuario = ug.fkUsuario\n" +
            "INNER JOIN Grupo g ON g.idGrupo = ug.fkGrupo WHERE ug.fkGrupo = ?1")
    List<Usuario> findAllUsers(Integer id);

    @Query("SELECT new chatte.api.v1.API.Chatte.entities.MensagemResponse(m.idMensagem, m.conteudo, m.isPrivado, m.fkUsuario, m.fkGrupo, u.nome) from Mensagem m INNER JOIN UsuarioGrupo ug ON m.fkUsuario = ug.fkUsuario INNER JOIN Grupo g ON g.idGrupo = m.fkGrupo AND g.idGrupo = ug.fkGrupo INNER JOIN Usuario u ON u.idUsuario = m.fkUsuario WHERE m.fkGrupo = ?1")
    List<MensagemResponse> findAllMessages(Integer id);
}
