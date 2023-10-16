package chatte.api.v1.API.Chatte.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Usuario {

    @Schema(
            description = "Id do usuário na base de dados",
            example = "43",
            type = "Integer"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Schema(
            description = "Nome do usuário",
            example = "Diego",
            type = "String"
    )
    @NotNull
    @NotBlank
    private String nome;

    @Schema(
            description = "email do usuário",
            example = "diego@gmail.com",
            type = "String"
    )
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Schema(
            description = "Senha que o usuário utiliza para logar na aplicação",
            example = "SenhaForte123",
            type = "String"
    )
    @Pattern(regexp = "(^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$)", message = "Indique uma senha válida")
    private String senha;

        @Schema(
                description = "Cargo do usuário dentro da empresa",
                example = "Desenvolvedor",
                type = "String"
        )
        @NotNull
        @NotBlank
        private String cargo;

        @Schema(
                description = "Status do usuário enquanto utiliza a aplicação",
                example = "Disponível",
                type = "String"
        )
        private String statusUsuario = Status.DISPONIVEL.getDescricao();

        @Schema(
                description = "Empresa na qual o usuário trabalho",
                example = "Chatte",
                type = "String"
        )
        @NotNull
        @NotBlank
        private String empresa;

        @Schema(
                description = "Flag para saber se o usuário é ou não um administrador do sistema",
                example = "true",
                type = "Boolean"
        )
        @NotNull
        private Boolean isAdm;

        private String dtNascimento;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String cargo, String statusUsuario, String empresa, Boolean isAdm, String dtNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.statusUsuario = statusUsuario;
        this.empresa = empresa;
        this.isAdm = isAdm;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(String nome, String email, String cargo, String empresa, Boolean isAdm, String dtNascimento) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.empresa = empresa;
        this.isAdm = isAdm;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(Integer idUsuario, String nome, String email, String cargo, String statusUsuario, String empresa, Boolean isAdm, String dtNascimento) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.statusUsuario = statusUsuario;
        this.empresa = empresa;
        this.isAdm = isAdm;
        this.dtNascimento = dtNascimento;
    }

    public void enviarMensagem() {

        }

        public void editarMensagem() {

        }

        public void excluirMensagem() {

        }

        public void ingressarGrupo() {

        }

        public void deixarGrupo() {

        }

        public void anexarArquivo() {

        }

        public void removerUsuario() {
        }

        public void criarGrupo() {

        }

        public void adicionarUsuario() {
        }

        public Integer getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String pegarSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        public String getEmpresa() {
            return empresa;
        }

        public void setEmpresa(String empresa) {
            this.empresa = empresa;
        }

        public String getSenha() {
            return senha;
        }

        public String getStatusUsuario() {
            return statusUsuario;
        }

        public void setStatusUsuario(String statusUsuario) {
            this.statusUsuario = statusUsuario;
        }

        public Boolean isAdm() {
            return isAdm;
        }

        public void setAdm(Boolean isAdm) {
            this.isAdm = isAdm;
        }

        public String getDtNascimento() {
            return dtNascimento;
        }

        public void setDtNascimento(String dtNascimento) {
            this.dtNascimento = dtNascimento;
        }
    }
