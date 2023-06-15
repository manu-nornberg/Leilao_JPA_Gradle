package br.edu.ifsul.cstsi.leilao_jpa_gradle.lance;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoRepository;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanceService {

    @Autowired
    private LanceRepository rep;


    public List<Lance> getLanceAll() {
        return rep.findAll();
    }

    public List<Lance> getLanceAllByItem(ItemLeilao id) {
        return new ArrayList<>(rep.getLanceAllByItem(id));
    }


    public Lance insertLance(Lance lance) {
        Assert.isNull(lance.getId(), "Erro !!!");
        return rep.save(lance);
    }

}
