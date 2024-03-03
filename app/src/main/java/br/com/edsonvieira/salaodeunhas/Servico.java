package br.com.edsonvieira.salaodeunhas;

import java.util.Collections;
import java.util.Comparator;

public class Servico {

    public static Comparator comparator = new Comparator<Servico>() {
        @Override
        public int compare(Servico s1, Servico s2) {
            return s1.getDescricao().compareTo(s2.getDescricao());
        }
    };

    private String descricao;
    private double preco;
    private int duracao;

    public Servico(String descricao, double valor, int duracao) {
        this.descricao = descricao;
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
