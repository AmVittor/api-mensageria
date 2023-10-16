package chatte.api.v1.API.Chatte.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MensagemResponse {

    private Integer idMensagem;

    private String conteudo;

    private final String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private Boolean isPrivado;

    private Integer fkUsuario;

    private Integer fkGrupo;

    private String nmUsuario;

    public MensagemResponse(Integer idMensagem, String conteudo, Boolean isPrivado, Integer fkUsuario, Integer fkGrupo, String nmUsuario) {
        this.idMensagem = idMensagem;
        this.conteudo = conteudo;
        this.isPrivado = isPrivado;
        this.fkUsuario = fkUsuario;
        this.fkGrupo = fkGrupo;
        this.nmUsuario = nmUsuario;
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

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

}
