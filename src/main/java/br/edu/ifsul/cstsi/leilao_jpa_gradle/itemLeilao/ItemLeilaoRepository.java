package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemLeilaoRepository extends JpaRepository<ItemLeilao,Long> {

    @Query(value = "SELECT i FROM ItemLeilao i WHERE i.id= ?1")
    Optional findByID(Long id);

    @Query(value = "SELECT i FROM ItemLeilao i WHERE i.leilaoByCodLeilao= ?1")
    List<ItemLeilao> getItemByIdLeilao(Leilao id);

}
