package dw.trabalho.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import dw.trabalho.model.Jogador;
import dw.trabalho.model.Pagamento;

import dw.trabalho.repository.JogadorRepository;
import dw.trabalho.repository.PagamentoReposiory;
import dw.trabalho.view.View;

@RequestMapping(value = "pagamento/")
@RestController
public class PagamentoController {
    private PagamentoReposiory rep;
    private JogadorRepository jRep;

    public PagamentoController(PagamentoReposiory rep, JogadorRepository jRep) {
        this.rep = rep;
        this.jRep = jRep;
    }

    // GET - listar todos os pagamentos
    @JsonView(View.AdvancedPagamento.class)
    @GetMapping("/")
    public ResponseEntity< List<Pagamento> > getAllPagamentos(@RequestParam(required = false) Integer ano, @RequestParam(required = false) Integer mes) {
        try {
            List<Pagamento> lp = new ArrayList<Pagamento>();

            if (ano == null && mes == null)
                rep.findAll().forEach(lp::add); //cada artigo retornado pelo findAll -> chame o metodo add de la
            else if (ano != null)
                rep.findByAno(ano.intValue()).forEach(lp::add);
            else
                rep.findByMes(mes.intValue()).forEach(lp::add);

            if (lp.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(lp, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET - buscar um pagamento com base em um id
    @JsonView(View.AdvancedPagamento.class)
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable("id") long id) {
        Optional<Pagamento> data = rep.findById(id);

        if (data.isPresent()) 
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST - criar um artigo
    @JsonView(View.AdvancedPagamento.class)
    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento ar) {
        try {
            long jogador_id = ar.getJogador().getId();
            Optional<Jogador> data = jRep.findById(jogador_id);

            if (data.isPresent()) {
                Pagamento pagamento = rep.save(new Pagamento(ar.getAno(), ar.getMes(), ar.getValor(), data.get()));
                return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT - atualizar um pagamento
    @JsonView(View.AdvancedPagamento.class)
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") long id, @RequestBody Pagamento p) {
        Optional<Pagamento> data = rep.findById(id);

        if (data.isPresent()) {
            Pagamento pagamento = data.get();
            pagamento.setAno(p.getAno());            
            pagamento.setMes(p.getMes());
            pagamento.setValor(p.getValor());

            try {
                long jogador_id = p.getJogador().getId();
                Optional<Jogador> dataJ = jRep.findById(jogador_id);

                if (dataJ.isPresent()) {
                    pagamento.setJogador(p.getJogador());
                    return new ResponseEntity<>(rep.save(pagamento), HttpStatus.OK);
                } else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DEL - deleta um pagamento
    @JsonView(View.AdvancedPagamento.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Pagamento> deletePagamento(@PathVariable("id") long id) {
        Optional<Pagamento> data = rep.findById(id);

        if (data.isPresent()) {
            Pagamento pagamento = data.get();
            rep.deleteById(id);
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DEL - remover todos os pagamentos
    @JsonView(View.Base.class)
    @DeleteMapping("/")
    public ResponseEntity<List<Pagamento>> deleteAllJogadores() {
        List<Pagamento> lp = new ArrayList<Pagamento>();

        try {
            rep.findAll().forEach(lp::add);
            rep.deleteAll();
            return new ResponseEntity<>(lp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
