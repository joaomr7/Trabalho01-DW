package dw.trabalho.repository;

//import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dw.trabalho.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByNomeContaining(String nome);

    List<Jogador> findByEmailContaining(String email);

    // List<Jogador> findByDatanasc(Date dataNasc);
}
