package chatte.api.v1.API.Chatte.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(UsuarioGrupo.class)
public class UsuarioGrupo implements Serializable {
    @Id
    private Integer fkUsuario;

    @Id
    private Integer fkGrupo;

    private String dtEntrada;

    public UsuarioGrupo(Integer fkUsuario, Integer fkGrupo, String dtEntrada) {
        this.fkUsuario = fkUsuario;
        this.fkGrupo = fkGrupo;
        this.dtEntrada = dtEntrada;
    }

    public UsuarioGrupo() {
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

    public String getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(String dtEntrada) {
        this.dtEntrada = dtEntrada;
    }
}
