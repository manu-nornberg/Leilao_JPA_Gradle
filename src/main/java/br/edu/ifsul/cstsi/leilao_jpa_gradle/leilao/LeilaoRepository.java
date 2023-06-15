package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LeilaoRepository extends JpaRepository<Leilao,Long> {

    @Query(value = "SELECT l FROM Leilao l WHERE l.id= ?1 and l.status = true")
    Optional findByID(Long id);

    @Query(value = "SELECT l FROM Leilao l WHERE l.status = true")
    List<Leilao> findAllTrue();

}
