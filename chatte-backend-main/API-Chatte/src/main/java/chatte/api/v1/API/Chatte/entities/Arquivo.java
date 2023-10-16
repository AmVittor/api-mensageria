package chatte.api.v1.API.Chatte.entities;

import io.swagger.v3.oas.annotations.media.Schema;

public class Arquivo {

    @Schema(
            description = "Nome que o arquivo foi salvo",
            example = "Usuários Google",
            type = "String"
    )
    private String nome;

    @Schema(
            description = "Extensão do arquivo",
            example = "txt",
            type = "String"
    )
    private String extensao;

    @Schema(
            description = "Tamanho do arquivo em bytes",
            example = "3053",
            type = "Byte"
    )
    private Byte tamanho;

}
