package br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Controller
public class ItemLeilaoController {
    private static final Scanner input = new Scanner(System.in); //inserir texto teclado

    public static ItemLeilaoService itemLeilaoService;
    public static LeilaoService leilaoService;

    public ItemLeilaoController(ItemLeilaoService itemLeilaoService, LeilaoService leilaoService ) {
        ItemLeilaoController.itemLeilaoService = itemLeilaoService;
        ItemLeilaoController.leilaoService = leilaoService;

    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area dos itens =======");
            System.out.println("""
                    1. Atualizar item
                    2. Listar item
                    3. Selecionar por ID do leilao
                    4. Voltar para página anterior
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            LeilaoController.atualizarStatus();

            switch (opcao) {
                case 1 -> updateItem();
                case 2 -> selectAll();
                case 3 -> selectByIdLeilao();
                case 4 -> HomeController.main(null);
            }
        } while (opcao != 0);

    }

    //opcao 1 - update
    private static void updateItem() {
        System.out.println("\n ====== Alterar dados do item ======");
        int opcao = 0;
        ItemLeilao itemLeilao = null;
        do {
            //pegar o leilao para alterar o item
            System.out.println("Qual o id do leilao que deseja alterar o item? ");
            Long l = input.nextLong();
            input.nextLine();
            Leilao leilao = leilaoService.getLeilaoById(l);
            List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(leilao);
            System.out.println(itens);
            //pegar o item
            System.out.println("\n Digite o id do item para alterar(0 pra sair) ");
            long id = input.nextLong();
            input.nextLine();
            if (id == 0) {
                opcao = 0;
            } else {
                itemLeilao = itemLeilaoService.getItemById(id);
                if (itemLeilao == null) {
                    System.out.println("ID invalido");
                } else {
                    int opcao2 = 0;
                    do {
                        System.out.println("Qual opcao queres alterar?\n ");
                        System.out.println("1. Titulo: " + itemLeilao.getTitulo());
                        System.out.println("2. Descriçao: " + itemLeilao.getDescricao());
                        System.out.println("3. Foto: " + itemLeilao.getCaminhoFoto());
                        System.out.println("4. Lance minimo: " + itemLeilao.getLanceMin());
                        opcao2 = input.nextInt();
                        input.nextLine();

                        switch (opcao2) {
                            case 1 -> {
                                System.out.println("Digite o novo titulo: ");
                                itemLeilao.setTitulo(input.nextLine());
                            }
                            case 2 -> {
                                System.out.println("Digite a nova descricao: ");
                                itemLeilao.setDescricao(input.nextLine());
                            }
                            case 3 -> {
                                System.out.println("Digite o novo caminho: ");
                                itemLeilao.setCaminhoFoto(input.nextLine());
                            }
                            case 4 -> {
                                System.out.println("Digite o lance minimo: ");
                                itemLeilao.setLanceMin(input.nextDouble());
                                input.nextLine();
                            }

                        }

                    } while (opcao != 0);

                    itemLeilao.setArrematado(false);
                    System.out.println("Atualizado com sucesso\n" + itemLeilaoService.update(itemLeilao));
                }
            }

        } while (opcao != 0);
    }

    //opcao 2 - listar tudo
    private static void selectAll() {
        List<ItemLeilao> itens = itemLeilaoService.todosItens();
        System.out.println("\n Lista de participante do leilao: " + itens);
        ItemLeilaoController.main(null);

    }

    //opcao 3 - selecionar pelo id do leilao
    private static void selectByIdLeilao(){
        System.out.println("Qual o id do leilao? ");
        Long id = input.nextLong();
        input.nextLine();
        Leilao leilao = leilaoService.getLeilaoById(id);
        List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(leilao);
        System.out.println(itens);
        ItemLeilaoController.main(null);
    }


}










