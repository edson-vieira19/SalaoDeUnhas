package br.com.edsonvieira.salaodeunhas;

public enum Produto {
    NENHUM("Nenhum"),
    ESMALTE("Esmalte"),
    ACETONA("Acetona"),
    BASE("Base"),
    OLEO_SECANTE("OLeo Secante"),
    GEL("Gel");

    private final String nome;

    Produto(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}
