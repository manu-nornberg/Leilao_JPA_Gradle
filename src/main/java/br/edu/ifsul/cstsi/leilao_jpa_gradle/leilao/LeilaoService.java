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

    //selecionar pelo id do leilao
    public Leilao getLeilaoById(Long id) {
        Optional<Leilao> opt = rep.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    //selecionar pelo id do leilao q tao ativos
    public Leilao getLeilaoByIdTrue(Long id, Boolean status) {
        Optional<Leilao> opt = rep.findByIDTrue(id,status);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }


    //selecionar TODOS os leiloes
    public List<Leilao> getAllLeiloes() {
        return rep.findAll();
    }

    //selecionar os leiloes q tao ativos
    public List<Leilao> getAllLeiloesTrue() {
        if(rep.findAllTrue().isEmpty()){
            return null;
        }
        return rep.findAllTrue();
    }

    //inserir leilao
    public Leilao insert(Leilao leilao) {
        Assert.isNull(leilao.getId(), "Erro!!!");
        return rep.save(leilao);
    }

    //update leilao
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

    public Leilao updateHora(Leilao leilao) {
        Assert.notNull(leilao.getId(), "Erro!!!");
        Optional<Leilao> opt = rep.findByID(leilao.getId());
        if(opt.isPresent()){
            Leilao lt = opt.get();
            lt.setStatus(leilao.getStatus());
            lt.setHrFinal(leilao.getHrFinal());
            rep.save(lt);
            return lt;

        }else{
            return null;
        }
    }
}
