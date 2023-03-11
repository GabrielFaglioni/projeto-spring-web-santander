package com.store.gamestore.controllers;

import com.store.gamestore.exceptions.JogoNotFoundException;
import com.store.gamestore.model.Jogos;
import com.store.gamestore.services.JogoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogosRESTController {
    @Autowired
    JogoServices jogoServices;

    @PostMapping("/novo")
    public Jogos save(@RequestBody Jogos jogo){
        return jogoServices.save(jogo);
    }

    @GetMapping("/{id_jogo}")
    public Jogos findById(@PathVariable Long id_jogo) throws JogoNotFoundException {
        return jogoServices.get(id_jogo);
    }

    @DeleteMapping("deletar/{id_jogo}")
    public void deleteById(@PathVariable Long id_jogo) throws JogoNotFoundException {
        jogoServices.delete(id_jogo);
    }

    @GetMapping("/listarTodos")
    public List<Jogos> listAll(){
        return jogoServices.listAll();
    }

    @PostMapping("/editar/{id_jogo}")
    public ResponseEntity<?> update(@RequestParam Long id_jogo, @RequestBody Jogos jogo) {
        if (jogo.getNome() == null) {
            return ResponseEntity.badRequest().body("O nome não pode ser nulo!");
        }
        if (jogo.getGenero() == null) {
            return ResponseEntity.badRequest().body("O genero não pode ser nulo");
        }
        if (jogo.getPreco() == null) {
            return ResponseEntity.badRequest().body("O preço não pode ser nulo");
        }
        jogo.setId_jogo(id_jogo);
        try {
            jogoServices.save(jogo);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(jogo);

    }
}
