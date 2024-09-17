package dw.trabalho.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import dw.trabalho.model.Jogador;
import dw.trabalho.repository.JogadorRepository;
import dw.trabalho.view.View;

@RequestMapping(value = "jogador/")
@RestController
public class JogadorController {
    
    @Autowired
    JogadorRepository rep;

    // GET - listar todos jogadores + jogador containing nome
    @JsonView(View.Base.class)
    @GetMapping("/")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome, @RequestParam(required = false) String email) {
        try {
            List<Jogador> lj = new ArrayList<Jogador>();

            if (nome == null && email == null) 
                rep.findAll().forEach(lj::add);
            else if (nome != null) 
                rep.findByNomeContaining(nome).forEach(lj::add);
            else
                rep.findByEmailContaining(email).forEach(lj::add);  

            if(lj.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<List<Jogador>>(lj, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET - buscar jogador com base em id + pagamentos associados a ele
    @JsonView(View.AdvancedJogador.class)
    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") long id) {
        Optional<Jogador> data = rep.findById(id);

        if (data.isPresent())
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST - criar jogador
    @JsonView(View.AdvancedJogador.class)
    @PostMapping("/")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador j) {
        try {
            Jogador jogador = rep.save(new Jogador(j.getNome(), j.getEmail(), j.getDatanasc()));
            return new ResponseEntity<Jogador>(jogador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT - atualizar um jogador
    @JsonView(View.AdvancedJogador.class)
    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") long id, @RequestBody Jogador j) {
        Optional<Jogador> data = rep.findById(id);

        if (data.isPresent()) {
            Jogador jogador = data.get();
            jogador.setNome(j.getNome());
            jogador.setEmail(j.getEmail());
            jogador.setDatanasc(j.getDatanasc());

            return new ResponseEntity<>(rep.save(jogador), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DEL - deleta um jogador e todos os seus pagamentos
    @JsonView(View.AdvancedJogador.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Jogador> deleteJogador(@PathVariable("id") long id) {
        Optional<Jogador> data = rep.findById(id);

        if (data.isPresent()) {
            Jogador jogador = data.get();
            rep.deleteById(id);
            return new ResponseEntity<>(jogador, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DEL - remover todos os jogadores e todos os seus pagamentos
    @JsonView(View.Base.class)
    @DeleteMapping("/")
    public ResponseEntity<List<Jogador>> deleteAllJogadores() {
        List<Jogador> lj = new ArrayList<Jogador>();

        try {
            rep.findAll().forEach(lj::add);
            rep.deleteAll();
            return new ResponseEntity<>(lj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
