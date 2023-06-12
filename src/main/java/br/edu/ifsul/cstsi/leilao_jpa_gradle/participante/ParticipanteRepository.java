package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
    @Query(value = "SELECT p FROM Participante p WHERE p.id= ?1")
    Optional findByID(Long id);

    @Query(value = "SELECT p FROM Participante p WHERE p.nome ilike ?1")
    List<Participante> findByNome(String nome);


}
