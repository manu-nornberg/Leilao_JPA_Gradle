package br.edu.ifsul.cstsi.leilao_jpa_gradle.lance;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.Participante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Basic
    @Column(name = "valor_lance")
    private Double valorLance;
    @Basic
    @Column(name = "hr_lance")
    private Time hrLance;
    @ManyToOne
    @JoinColumn(name = "id_part", referencedColumnName = "id")
    private Participante participanteByIdPart;
    @ManyToOne
    @JoinColumn(name = "cod_item", referencedColumnName = "id")
    private ItemLeilao itemLeilaoByCodItem;

    @Override
    public String toString() {
        return "\nLance{" +
                "id=" + id +
                " || valorLance=" + valorLance +
                " || hrLance=" + hrLance +
                " || participanteByIdPart=" + participanteByIdPart +
                " || itemLeilaoByCodItem=" + itemLeilaoByCodItem +
                '}';
    }
}
