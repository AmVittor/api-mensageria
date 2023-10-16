package chatte.api.v1.API.Chatte.repositories;

import chatte.api.v1.API.Chatte.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import chatte.api.v1.API.Chatte.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndSenha(String email, String senha);

    @Query("SELECT new Grupo(g.idGrupo, g.nome, g.dataCriacao) FROM Grupo g INNER JOIN UsuarioGrupo ug on g.idGrupo = ug.fkGrupo INNER JOIN Usuario u on u.idUsuario = ug.fkUsuario WHERE u.idUsuario = ?1")
    List<Grupo> findAllGroups(Integer id);
}