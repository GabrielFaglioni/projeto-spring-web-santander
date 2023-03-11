package com.store.gamestore.repositories;//package com.store.gamestore.repositories;

import com.store.gamestore.model.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long> {
}
