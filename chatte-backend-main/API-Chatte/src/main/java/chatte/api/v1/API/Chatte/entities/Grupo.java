package chatte.api.v1.API.Chatte.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Grupo {

    @Schema(
            description = "Id do grupo na base de dados",
            example = "3",
            type = "Integer"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;

    @Schema(
            description = "Nome do grupo",
            example = "Squad produtos",
            type = "String"
    )
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String nome;

    @Schema(
            description = "Data de criação do grupo",
            example = "2022/04/05",
            type = "LocalDate"
    )
    private String dataCriacao;

    static {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public Grupo(Integer idGrupo, String nome, String dataCriacao) {
        this.idGrupo = idGrupo;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }

    public Grupo() {
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
