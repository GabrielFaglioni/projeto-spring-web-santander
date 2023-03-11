package com.store.gamestore;

import com.store.gamestore.model.Jogos;
import com.store.gamestore.repositories.JogosRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class JogosRepositoryTests {
    @Autowired private JogosRepository repo;

    @Test
    public void testAddNew() {
        Jogos jogo = new Jogos();
        jogo.setNome("Elden Ring");
        jogo.setGenero("Souls-like");
        jogo.setPreco(249.99D);

        Jogos jogoSalvo = repo.save(jogo);

        Assertions.assertThat(jogoSalvo).isNotNull();
        Assertions.assertThat(jogoSalvo.getId_jogo()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Jogos> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (Jogos user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Long id_jogo = 1L;
        Optional<Jogos> optionalUser = repo.findById(id_jogo);
        Jogos user = optionalUser.get();
        user.setNome("hello2000");
        repo.save(user);

        Jogos updatedUser = repo.findById(id_jogo).get();
        Assertions.assertThat(updatedUser.getNome()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Long id_jogo = 2L;
        Optional<Jogos> optionalUser = repo.findById(id_jogo);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Long id_jogo = 2L;
        repo.deleteById(id_jogo);

        Optional<Jogos> optionalUser = repo.findById(id_jogo);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
