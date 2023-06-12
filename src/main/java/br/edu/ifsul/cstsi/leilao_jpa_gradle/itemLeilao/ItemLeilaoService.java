package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ItemLeilaoService {

    @Autowired
    private ItemLeilaoRepository rep;

    public List<ItemLeilao> todosItens() {
        return rep.findAll();
    }

    public ItemLeilao insertItem(ItemLeilao itemLeilao) {
        Assert.notNull(itemLeilao.getId(), "Erro no item!!!");
        return rep.save(itemLeilao);

    }

}
