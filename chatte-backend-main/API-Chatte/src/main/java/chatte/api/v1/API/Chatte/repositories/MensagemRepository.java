package chatte.api.v1.API.Chatte.repositories;

import chatte.api.v1.API.Chatte.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    //query grupos
    //select
    //        conteudo,
    //        horario,
    //        nome
    //from [dbo].[mensagem] inner join [dbo].[usuario]
    //on fk_usuario = id_usuario
    //where fk_grupo = 3
}
