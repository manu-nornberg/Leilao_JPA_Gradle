package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Controller
public class ItemLeilaoController {
    private static final Scanner input = new Scanner(System.in); //inserir texto teclado

    public static ItemLeilaoService ItemLeilaoService;
    public static LeilaoService leilaoService;

    public ItemLeilaoController(ItemLeilaoService itemLeilaoService, LeilaoService leilaoService) {
        ItemLeilaoController.ItemLeilaoService = itemLeilaoService;
        LeilaoController.LeilaoService = leilaoService;
    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area dos itens =======");
            System.out.println("""
                    1. Adicionar Item 
                    2. Atualizar item
                    3. Listar item
                    4. Voltar para página anterior
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
//                case 1 -> inserir();
//                case 2 ->
                case 3 -> selectAll();
//                case 3 -> selectById();
                case 4 -> HomeController.main(null);
            }
        } while (opcao != 0);

    }

//    private static void inserir() {
//        long l = 0;
//        ItemLeilao itemLeilao = new ItemLeilao();
//        System.out.println("Insira o titulo: ");
//        itemLeilao.setTitulo(input.nextLine());
//        System.out.println("Insira a descrição: ");
//        itemLeilao.setDescricao(input.nextLine());
//        System.out.println("Lance minimo: ");
//        itemLeilao.setLanceMin(input.nextBigDecimal());
//        itemLeilao.setArrematado(true);
//        System.out.println("Qual leilao deseja por esse item?");
//        List<Leilao> leilaos = leilaoService.getAllLeiloes();
//        leilaos.forEach(aa -> {
//            System.out.println(leilaos);
//        });
//
//        l = input.nextLong();
//        itemLeilao.setLeilaoByCodLeilao(leilaoService.getLeilaoById(l));
//
//
//        System.out.println("\nTem certeza que deseja adicionar esse item ");
//        int opcao = 0;
//        do {
//            System.out.println("\n  0- não e 1- sim");
//            opcao = input.nextInt();
//            input.nextLine();
//
//            switch (opcao) {
//                case 1 -> {
//                    System.out.println("\nItem salvo: " + ItemLeilaoService.insertItem(itemLeilao));
//                    return;
//                }
//                case 0 -> {
//                    System.out.println("\nItem não adicionado! :( ");
//                }
//            }
//
//        } while (opcao != 0);
//
//    }


    private static void selectAll() {
        List<ItemLeilao> itens = ItemLeilaoService.todosItens();
        System.out.println("\n Lista de participante do leilao: " + itens);

    }

}




