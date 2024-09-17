package dw.trabalho.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import dw.trabalho.view.View;

@Entity
@Table(name = "jogador")
public class Jogador {
    @JsonView(View.Base.class)
    @Id
    @GeneratedValue()
    private long id;

    @JsonView(View.Base.class)
    @Column(nullable = false, length = 60)
    private String nome;

    @JsonView(View.Base.class)
    @Column(nullable = false, length = 60)
    private String email;

    @JsonView(View.Base.class)
    @Column(nullable = false)
    private Date datanasc;

    @JsonView(View.AdvancedJogador.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    public Jogador() {
    }

    public Jogador(long id) {
        this.id = id;
    }

    public Jogador(String nome, String email, Date datanasc) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public String toString() {
        return "Jogador [id=" + id + ", nome=" + nome + ",email=" + email + ",datanasc=" + datanasc + "]";
    }
}
