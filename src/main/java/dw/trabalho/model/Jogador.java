package dw.trabalho.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import dw.trabalho.validation.IdadeLimite;
import dw.trabalho.view.View;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Valid

    @JsonView(View.Base.class)
    @Id
    @GeneratedValue()
    private long id;

    @JsonView(View.Base.class)
    @Column(nullable = false, length = 60)
    @NotBlank(message = "nome não pode ser vazio")
    @Size(min = 2, max = 60, message = "nome deve ter entre 2 a 60 caracteres")
    private String nome;

    @JsonView(View.Base.class)
    @Column(nullable = false, length = 60)
    @NotBlank(message = "e-mail não pode ser vazio")
    @Email(message = "e-mail deve ser válido")
    private String email;

    @JsonView(View.Base.class)
    @Column(nullable = false)
    @IdadeLimite(idadeMinima = 16, message = "o jogador deve ter pelo menos 16 anos")
    private LocalDate datanasc;

    @JsonView(View.AdvancedJogador.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    public Jogador() {
    }

    public Jogador(long id) {
        this.id = id;
    }

    public Jogador(String nome, String email, LocalDate datanasc) {
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

    public LocalDate getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(LocalDate datanasc) {
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
