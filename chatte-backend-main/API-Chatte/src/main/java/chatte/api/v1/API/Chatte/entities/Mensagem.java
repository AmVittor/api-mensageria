package chatte.api.v1.API.Chatte.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMensagem;

    @Schema(
            description = "Corpo da mensagem",
            example = "Bom dia, tudo bem ?",
            type = "String"
    )
    private String conteudo;

    @Schema(
            description = "Horário no qual a mensagem foi enviada",
            example = "2022/04/04 15:30",
            type = "LocalDate"
    )
    private final String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private Boolean isPrivado;

    private Integer fkUsuario;

    private Integer fkGrupo;

    public Mensagem(Integer idMensagem, String conteudo, Boolean isPrivado, Integer fkUsuario, Integer fkGrupo) {
        this.idMensagem = idMensagem;
        this.conteudo = conteudo;
        this.isPrivado = isPrivado;
        this.fkUsuario = fkUsuario;
        this.fkGrupo = fkGrupo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getHorario() {
        return horario;
    }

    public Boolean isPrivado() {
        return isPrivado;
    }

    public void setPrivado(Boolean privado) {
        isPrivado = privado;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkGrupo() {
        return fkGrupo;
    }

    public void setFkGrupo(Integer fkGrupo) {
        this.fkGrupo = fkGrupo;
    }
}
