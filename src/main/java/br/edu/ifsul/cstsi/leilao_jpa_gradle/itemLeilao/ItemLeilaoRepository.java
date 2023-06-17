package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemLeilaoRepository extends JpaRepository<ItemLeilao,Long> {

    //achar pelo id
    @Query(value = "SELECT i FROM ItemLeilao i WHERE i.id= ?1")
    Optional findByID(Long id);

    //achaaar id dos nao arrematados
    @Query(value = "SELECT i FROM ItemLeilao i WHERE i.id= ?1 and i.arrematado=true")
    Optional findByIDTrue(Long id);

    //achar pelo leilao
    @Query(value = "SELECT i FROM ItemLeilao i WHERE i.leilaoByCodLeilao= ?1")
    List<ItemLeilao> getItemByIdLeilao(Leilao id);

}
