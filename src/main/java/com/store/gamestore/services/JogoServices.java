package com.store.gamestore.services;

import com.store.gamestore.exceptions.JogoNotFoundException;
import com.store.gamestore.model.Jogos;
import com.store.gamestore.repositories.JogosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoServices {
    @Autowired private JogosRepository repo;

    public List<Jogos> listAll() {
        List<Jogos> jogos = repo.findAll();
        return jogos;
    }

    public Jogos save(Jogos jogo) {
        repo.save(jogo);
        return jogo;
    }

    public Jogos get(Long id_jogo) throws JogoNotFoundException {
        Optional<Jogos> result = repo.findById(id_jogo);
        if (result.isPresent()) {
            return result.get();
        }
        throw new JogoNotFoundException("Não foi encontrado o jogo de id=".concat(String.valueOf(id_jogo)));
    }

    public void delete(Long id_jogo) throws JogoNotFoundException {
        repo.deleteById(id_jogo);
    }
}
