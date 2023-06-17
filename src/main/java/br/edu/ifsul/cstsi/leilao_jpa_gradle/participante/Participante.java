package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.Lance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "senha")
    private String senha;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "endereco")
    private String endereco;
    @Basic
    @Column(name = "telefone")
    private String telefone;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "participanteByIdPart")
    private Collection<Lance> LanceByCodParticipante;

    //toString sobescrito
    @Override
    public String toString() {
        return "\nParticipante= " +
                "id=" + id +
                " || nome='" + nome + '\'' +
                " || login='" + login + '\'' +
                " || senha='" + senha + '\'' +
                " || email='" + email + '\'' +
                " || endereco='" + endereco + '\'' +
                " || telefone='" + telefone + '\'' +
                " || status= " + (status ? "ativo" : "desativado") +
                "}";
    }
}
