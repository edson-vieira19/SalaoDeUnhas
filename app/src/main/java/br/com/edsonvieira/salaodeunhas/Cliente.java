package br.com.edsonvieira.salaodeunhas;

public class Cliente {
    private String nome;
    private String endereco;
    private String celular;
    private String instagram;
    private boolean micose;
    private boolean unhaEncravada;
    private boolean manchas;
    private boolean unha_quebradica;
    private boolean descolamento;
    private boolean diabetes;
    private boolean fumante;
    private boolean atividadeDesgaste;
    private Produto produtoEscolhido;

    public Cliente(String nome, String endereco, String celular, String instagram,
                   boolean micose, boolean unhaEncravada, boolean manchas,
                   boolean unha_quebradica, boolean descolamento,
                   boolean diabetes, boolean fumante, boolean atividadeDesgaste,
                   Produto produtoEscolhido) {

        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.instagram = instagram;
        this.micose = micose;
        this.unhaEncravada = unhaEncravada;
        this.manchas = manchas;
        this.unha_quebradica = unha_quebradica;
        this.descolamento = descolamento;
        this.diabetes = diabetes;
        this.fumante = fumante;
        this.atividadeDesgaste = atividadeDesgaste;
        this.produtoEscolhido = produtoEscolhido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public boolean isMicose() {
        return micose;
    }

    public void setMicose(boolean micose) {
        this.micose = micose;
    }

    public boolean isUnhaEncravada() {
        return unhaEncravada;
    }

    public void setUnhaEncravada(boolean unhaEncravada) {
        this.unhaEncravada = unhaEncravada;
    }

    public boolean isManchas() {
        return manchas;
    }

    public void setManchas(boolean manchas) {
        this.manchas = manchas;
    }

    public boolean isUnha_quebradica() {
        return unha_quebradica;
    }

    public void setUnha_quebradica(boolean unha_quebradica) {
        this.unha_quebradica = unha_quebradica;
    }

    public boolean isDescolamento() {
        return descolamento;
    }

    public void setDescolamento(boolean descolamento) {
        this.descolamento = descolamento;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public boolean isAtividadeDesgaste() {
        return atividadeDesgaste;
    }

    public void setAtividadeDesgaste(boolean atividadeDesgaste) {
        this.atividadeDesgaste = atividadeDesgaste;
    }

    public Produto getProdutoEscolhido() {
        return produtoEscolhido;
    }

    public void setProdutoEscolhido(Produto produtoEscolhido) {
        this.produtoEscolhido = produtoEscolhido;
    }
}
