package dw.trabalho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dw.trabalho.model.Pagamento;

@Repository
public interface PagamentoReposiory extends JpaRepository<Pagamento, Long>{    
    List<Pagamento> findByAno(int ano);

    List<Pagamento> findByMes(int mes);
}

