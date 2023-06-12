package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class CriarLeilaoController {
    private static final Scanner input = new Scanner(System.in); //inserir texto teclado

    private static LeilaoService LeilaoService;
    private static ItemLeilaoService ItemLeilaoService;

    public CriarLeilaoController(ItemLeilaoService itemLeilaoService, LeilaoService leilaoService) {
        CriarLeilaoController.ItemLeilaoService = itemLeilaoService;
        CriarLeilaoController.LeilaoService = leilaoService;
    }

    public static void main(String[] args) {
        Leilao leilao = new Leilao();
        System.out.println("\n==== Criar um novo leilao: =====");


        LocalDate datain = LocalDate.now();
//          DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        leilao.setDtInicio(Date.valueOf(datain));
        leilao.setDtFinal(Date.valueOf(datain));

        LocalTime horain = LocalTime.now();
        LocalTime horafim = horain.plusHours(1);
        leilao.setHrInicio(Time.valueOf(horain));
        leilao.setHrFinal(Time.valueOf(horafim));
        leilao.setStatus(true);

        //se a pessoa desistir vai criar leilao vazio

        ItemLeilao itemLeilao = new ItemLeilao();
        System.out.println("Insira o titulo: ");
        itemLeilao.setTitulo(input.nextLine());
        System.out.println("Insira o Caminho da foto: ");
        itemLeilao.setCaminhoFoto(input.nextLine());
        System.out.println("Insira a descrição: ");
        itemLeilao.setDescricao(input.nextLine());
        System.out.println("Lance minimo: ");
        itemLeilao.setLanceMin(input.nextBigDecimal());
        itemLeilao.setArrematado(true);


        System.out.println("\nTem certeza que deseja adicionar esse item? ");
        int opcao = 0;
        do {
            System.out.println("\n  0- nãooo e 1- sim");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Leilao criado: "
                            + LeilaoService.insert(leilao)
                    );
                    itemLeilao.setLeilaoByCodLeilao(LeilaoService.getLeilaoById(leilao.getId()));
                    System.out.println("\nItem salvo: "
                            + ItemLeilaoService.insertItem(itemLeilao)
                    );
                    return;
                }
                case 0 -> {
                    System.out.println("\nItem não adicionado! :( ");
                }
            }

        } while (opcao != 0);

        ///adicionar mais itens
    }
}
