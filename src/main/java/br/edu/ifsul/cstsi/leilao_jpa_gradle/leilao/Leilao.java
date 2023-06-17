package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leilao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Basic
    @Column(name = "dt_inicio")
    private LocalDate dtInicio;
    @Basic
    @Column(name = "hr_inicio")
    private Time hrInicio;
    @Basic
    @Column(name = "dt_final")
    private LocalDate dtFinal;
    @Basic
    @Column(name = "hr_final")
    private Time hrFinal;
    @Basic
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "leilaoByCodLeilao")
    private Collection<ItemLeilao> itemLeilaosByCodLeilao;

    public static Leilao valueof(Long id) {
        Collection<Leilao> LeilaoByCodLeilao;
        return null;
    }

    //toString
    @Override
    public String toString() {
        return "\nLeilao {" +
                "id= " + id +
                " || dtInicio= " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dtInicio) +
                " || dtFinal= " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dtFinal) +
                " || hrInicio= " + hrInicio +
                " || hrFinal= " + hrFinal +
                " || status= " + (status ? "em andamento" : "terminado") +
                '}';
    }
}