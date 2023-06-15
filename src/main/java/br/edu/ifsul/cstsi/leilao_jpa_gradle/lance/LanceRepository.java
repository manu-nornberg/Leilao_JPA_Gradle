package br.edu.ifsul.cstsi.leilao_jpa_gradle.lance;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanceRepository extends JpaRepository<Lance,Long> {
    @Query(value = "SELECT lan FROM Lance lan WHERE lan.itemLeilaoByCodItem = ?1")
    List<Lance> getLanceAllByItem(ItemLeilao id);

}
