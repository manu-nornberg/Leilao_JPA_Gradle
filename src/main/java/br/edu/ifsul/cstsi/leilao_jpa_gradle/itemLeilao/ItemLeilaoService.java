package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemLeilaoService {

    @Autowired
    private ItemLeilaoRepository rep;

    public List<ItemLeilao> todosItens() {
        return rep.findAll();
    }

    public ItemLeilao getItemById(Long id) {
        Optional<ItemLeilao> it = rep.findById(id);
        if (it.isPresent()) {
            return it.get();
        }
        return null;
    }

    public List<ItemLeilao> getItemByIdLeilao(Leilao id) {
        return new ArrayList<>(rep.getItemByIdLeilao(id));
    }

    public ItemLeilao insertItem(ItemLeilao itemLeilao) {
        Assert.notNull(itemLeilao.getId(), "Erro no item!!!");
        return rep.save(itemLeilao);

    }

    public ItemLeilao update(ItemLeilao itemLeilao) {
        Assert.notNull(itemLeilao.getId(), "Erro!!!");
        Optional<ItemLeilao> opt = rep.findByID(itemLeilao.getId());
        if(opt.isPresent()){
            ItemLeilao it = opt.get();
            it.setTitulo(itemLeilao.getTitulo());
            it.setDescricao(itemLeilao.getDescricao());
            it.setCaminhoFoto(itemLeilao.getCaminhoFoto());
            it.setLanceMin(itemLeilao.getLanceMin());
            rep.save(it);
            return it;

        }else{
            return null;
        }
    }

}
