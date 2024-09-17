package dw.trabalho.model;

import com.fasterxml.jackson.annotation.JsonView;

import dw.trabalho.view.View;
import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @JsonView(View.Base.class)
    @Id
    @GeneratedValue()
    private long id;

    @JsonView(View.Base.class)
    @Column(nullable = false)
    private int ano;

    @JsonView(View.Base.class)
    @Column(nullable = false)
    private int mes;

    @JsonView(View.Base.class)
    @Column(nullable = false)
    private float valor;

    @JsonView(View.AdvancedPagamento.class)
    @ManyToOne()
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    public Pagamento(int ano, int mes, float valor, Jogador jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public Pagamento() {
    }

    public long getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", ano=" + ano + ",mes=" + mes + ",valor=" + valor + "]";
    }
}
