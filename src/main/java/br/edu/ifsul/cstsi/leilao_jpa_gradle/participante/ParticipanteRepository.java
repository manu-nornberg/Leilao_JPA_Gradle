package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
    //achar o id
    @Query(value = "SELECT p FROM Participante p WHERE p.id= ?1")
    Optional findByID(Long id);

    //achar o nome
    @Query(value = "SELECT p FROM Participante p WHERE p.nome ilike ?1")
    List<Participante> findByNome(String nome);

    //achar o login e senha
    @Query(value = "SELECT p FROM Participante p WHERE p.nome like ?1 and p.senha like ?2 and p.status = true")
    Participante findByLogin(String login, String senha);


}
