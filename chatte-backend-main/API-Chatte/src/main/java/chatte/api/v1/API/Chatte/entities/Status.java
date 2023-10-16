package chatte.api.v1.API.Chatte.entities;

public enum Status {
    DISPONIVEL("Disponível"),
    AUSENTE("Ausente"),
    OCUPADO("Ocupado"),
    OFFLINE("Offline"),
    ALMOCANDO("Almoçando");

    private final java.lang.String descricao;

    Status(java.lang.String descricao) {
        this.descricao = descricao;
    }

    public java.lang.String getDescricao() {
        return descricao;
    }

}

