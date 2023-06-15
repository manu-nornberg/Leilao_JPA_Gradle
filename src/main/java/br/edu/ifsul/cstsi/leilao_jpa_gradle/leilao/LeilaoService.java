package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
public class LeilaoService {

    @Autowired
    private LeilaoRepository rep;

    public Leilao getLeilaoById(Long id) {
        Optional<Leilao> opt = rep.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
    public List<Leilao> getAllLeiloes() {
        return rep.findAll();
    }

    public List<Leilao> getAllLeiloesTrue() {
        return rep.findAllTrue();
    }


    public Leilao insert(Leilao leilao) {
        Assert.isNull(leilao.getId(), "Erro!!!");
        return rep.save(leilao);

    }

    public Leilao update(Leilao leilao) {
        Assert.notNull(leilao.getId(), "Erro!!!");
        Optional<Leilao> opt = rep.findByID(leilao.getId());
        if(opt.isPresent()){
            Leilao lt = opt.get();
            lt.setStatus(leilao.getStatus());
            rep.save(lt);
            return lt;

        }else{
            return null;
        }
    }
}
