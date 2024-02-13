package br.com.edsonvieira.salaodeunhas;

public class Servico {

    private String descricao;
    private double preco;
    private int duracao;

    public Servico(String nome, double valor, int duracao) {
        this.descricao = nome;
        this.preco = valor;
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
