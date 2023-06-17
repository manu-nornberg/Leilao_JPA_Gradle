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

@Service
public class LanceService {

    @Autowired
    private LanceRepository rep;

    //busca todos os lances
    public List<Lance> getLanceAll() {
        return rep.findAll();
    }

    //busca os lances pelo id do item
    public List<Lance> getLanceAllByItem(ItemLeilao id) {
        ArrayList<Lance> lances = new ArrayList<>(rep.getLanceAllByItem(id));
        return lances;
    }

    //busca os lances pelo id do participante
    public List<Lance> getLanceAllByPart(Participante id) {
        return new ArrayList<>(rep.getLanceAllByPart(id));
    }

    //inserir lance
    public Lance insertLance(Lance lance) {
        Assert.isNull(lance.getId(), "Erro !!!");
        return rep.save(lance);
    }

}
