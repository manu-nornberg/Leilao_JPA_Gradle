package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;

import org.springframework.stereotype.Controller;

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

    private static LeilaoService leilaoService;
    private static ItemLeilaoService itemLeilaoService;

    public CriarLeilaoController(ItemLeilaoService itemLeilaoService, LeilaoService leilaoService) {
        CriarLeilaoController.itemLeilaoService = itemLeilaoService;
        CriarLeilaoController.leilaoService = leilaoService;
    }

    public static void main(String[] args) {
        Leilao leilao = new Leilao();
        List<ItemLeilao> itens = new ArrayList<>();
        int add = 0;
        LocalDate datain = LocalDate.now();
        leilao.setDtInicio(Date.valueOf(datain));
        leilao.setDtFinal(Date.valueOf(datain));
        LocalTime horain = LocalTime.now();
        LocalTime horafim = horain.plusHours(1);
        leilao.setHrInicio(Time.valueOf(horain));
        leilao.setHrFinal(Time.valueOf(horafim));
        leilao.setStatus(true);


        do {
            ItemLeilao itemLeilao = new ItemLeilao();
            System.out.println("Insira o titulo: ");
            itemLeilao.setTitulo(input.nextLine());
            System.out.println("Insira o Caminho da foto: ");
            itemLeilao.setCaminhoFoto(input.nextLine());
            System.out.println("Insira a descrição: ");
            itemLeilao.setDescricao(input.nextLine());
            System.out.println("Lance minimo: ");
            itemLeilao.setLanceMin(input.nextDouble());
            itemLeilao.setArrematado(true);

            itens.add(itemLeilao);

            System.out.println("Deseja adicionar mais algum?(1.Sim 2.Não)");

            add = input.nextInt();
            input.nextLine();

        } while (add == 1);

        System.out.println("Confirma o cadastro?(1.Sim 2.Não)");
        add = input.nextInt();
        input.nextLine();

        if(add == 1){
            System.out.println("Leilao criado: "
                + leilaoService.insert(leilao)
            );
            itens.forEach(i ->{
                i.setLeilaoByCodLeilao(leilao);
                System.out.println("\nItem salvo: "
                    + itemLeilaoService.insertItem(i)
                );
            });
        }




    }

}

