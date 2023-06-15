package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.Lance;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "item_leilao", schema = "leilao", catalog = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLeilao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "descricao")
    private String descricao;
    @Basic
    @Column(name = "lance_min")
    private Double lanceMin;
    @Basic
    @Column(name = "caminho_foto")
    private String caminhoFoto;
    @Basic
    @Column(name = "arrematado")
    private Boolean arrematado;
    @ManyToOne
    @JoinColumn(name = "cod_leilao", referencedColumnName = "id")
    private Leilao leilaoByCodLeilao;
    @OneToMany(mappedBy = "itemLeilaoByCodItem")
    private Collection<Lance> lancesByCodItem;

    @Override
    public String toString() {
        return "\nItemLeilao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", lanceMin=" + lanceMin +
                ", caminhoFoto='" + caminhoFoto + '\'' +
                ", arrematado=" + (arrematado ? "Não arrematado" : "Arrematado") +
                ", leilao=" + leilaoByCodLeilao.getId() +
                '}';
    }
}
