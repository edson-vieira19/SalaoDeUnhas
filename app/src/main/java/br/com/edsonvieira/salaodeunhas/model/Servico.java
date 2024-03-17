package br.com.edsonvieira.salaodeunhas.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity
public class Servico {

    public static Comparator<Servico> comparator = new Comparator<Servico>() {
        @Override
        public int compare(Servico s1, Servico s2) {
            return s1.getDescricao().compareTo(s2.getDescricao());
        }
    };

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String descricao;
    private double preco;
    private int duracao;

    public Servico(String descricao, double preco, int duracao) {
        this.descricao = descricao;
        this.preco = preco;
        this.duracao = duracao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
