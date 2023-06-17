package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository rep;

    //selecionar o participante pelo id
    public Participante getParticipanteById(Long id) {
        Optional<Participante> opt = rep.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    //selecionar o participante pelo nome dele
    public List<Participante> getParticipanteByNome(String nome) {
        ArrayList<Participante> participantes = new ArrayList<>(rep.findByNome(nome + "%"));
        if(participantes.isEmpty())
            return null;
        return participantes;
    }

    //selecionar o participante pelo login e senha dele
    public Participante getParticipanteByLogin(String login, String senha) {
        Optional<Participante> opt = Optional.ofNullable(rep.findByLogin(login, senha));
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    //selecionar todos os participantes
    public List<Participante>  getParticipanteAll() {
        List<Participante> all = rep.findAll();
        if(all.isEmpty())
            return null;
        return all;
    }

    //inserir um participante
    public Participante insert(Participante participante) {
        Assert.isNull(participante.getId(), "Erro!!!");
        return rep.save(participante);

    }

    //update em um participante
    public Participante update(Participante participante) {
        Assert.notNull(participante.getId(), "Erro!!!");
        Optional<Participante> opt = rep.findByID(participante.getId());
        if(opt.isPresent()){
            Participante pt = opt.get();
            pt.setNome(participante.getNome());
            pt.setLogin(participante.getLogin());
            pt.setSenha(participante.getSenha());
            pt.setEmail(participante.getEmail());
            pt.setEndereco(participante.getEndereco());
            pt.setTelefone(participante.getTelefone());
            pt.setStatus(participante.getStatus());
            rep.save(pt);
            return pt;

        }else{
            return null;
        }
    }

    //desativar ou ativar um participante
    public Participante delete(Participante participante) {
        Assert.notNull(participante.getId(), "Erro!!!");
        Optional<Participante> opt = rep.findByID(participante.getId());
        if(opt.isPresent()){
            Participante pt = opt.get();
            pt.setStatus(participante.getStatus());
            rep.save(pt);
            return pt;

        }else{
            return null;
        }
    }
}
