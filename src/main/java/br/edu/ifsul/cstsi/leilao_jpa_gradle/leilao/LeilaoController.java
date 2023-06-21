package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;
import org.springframework.stereotype.Controller;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class LeilaoController {

    private static final Scanner input = new Scanner(System.in); //inserir texto teclado
    public static LeilaoService leilaoService;
    public static ItemLeilaoService itemLeilaoService;//service

    public LeilaoController(LeilaoService leilaoService, ItemLeilaoService itemLeilaoService) {
        LeilaoController.leilaoService = leilaoService;
        LeilaoController.itemLeilaoService = itemLeilaoService;
    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area do leilao =======");
            System.out.println("""
                    1. Listar leiloes
                    2. Buscar por id
                    3. Adicionar mais itens
                    4. Terminar leilao antes da hora
                    0. Voltar para página anterior\s""");
            opcao = input.nextInt();
            input.nextLine();

            atualizarStatus(); //atualiza os status dos leiloes

            switch (opcao) {
                case 1 -> listar();
                case 2 -> selectById();
                case 3 -> addItem();
                case 4 -> alterarhora();
                case 0 -> HomeController.main(null);
            }
        } while (opcao < 0 || opcao > 3);
    }

    //opcao 1 - listar todos os leiloes
    private static void listar() {
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        System.out.println("\n Lista de leiloes: " + leilaos);
        LeilaoController.main(null);
    }

    //opcao 2 - selecionar por id do leilao
    private static void selectById() {
        System.out.println("Digite o id do leilao: ");
        Long id = Long.valueOf(input.nextLine());
        Leilao leilao = leilaoService.getLeilaoById(id);
        if (leilao == null) {
            System.out.println("\nNão há registro de leiloes com esse id: " + id);
        } else {
            System.out.println(leilao);
            LeilaoController.main(null);
        }
    }

    //opcao 3 - adicionar mais itens
    private static void addItem(){
        System.out.println("Digite o id do leilao: ");
        Long id = Long.valueOf(input.nextLine());
        Boolean status = (true);
        Leilao leilao = leilaoService.getLeilaoByIdTrue(id, status);
        if (leilao == null) {
            System.out.println("\nNão há registro de leiloes ativos com esse id: " + id);
            LeilaoController.main(null);
        } else {
            System.out.println(leilao);
            List<ItemLeilao> itens = new ArrayList<>();
            int add = 0;
            do {
                System.out.println("Formulário para adicição de item: ");
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
                itens.forEach(i ->{
                    i.setLeilaoByCodLeilao(leilao);
                    System.out.println("\nItem salvo: "
                            + itemLeilaoService.insertItem(i)
                    );
                });
            }

            LeilaoController.main(null);
        }
    }

    //opcao 4 - atualiza hora final do leilao
    private static void alterarhora(){
        System.out.println("Digite o id do leilao: ");
        Long id = Long.valueOf(input.nextLine());
        Boolean status = (true);
        Leilao leilao = leilaoService.getLeilaoByIdTrue(id, status);
            if (leilao == null) {
            System.out.println("\nNão há registro de leiloes ativos com esse id: " + id);
            LeilaoController.main(null);
        } else {
                System.out.println("\nDeseja terminar? 1-sim 2- nao");
                int op = input.nextInt();
                input.nextLine();
                if(op == 1){
                    leilao.setStatus(false);
                    leilao.setHrFinal(Time.valueOf(LocalTime.now()));
                    System.out.println("Horario alterado! " + leilaoService.updateHora(leilao));
                    atualizarStatus();
                    System.out.println(leilao);
                    LeilaoController.main(null);
                }
                if(op == 2){
                    LeilaoController.main(null);
                }
            }
    }

    //atualiza o status dos leiloes
    public static void atualizarStatus() {
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        leilaos.forEach(l -> {
            LocalTime aaa = l.getHrFinal().toLocalTime();
            if (LocalDate.now().isAfter(l.getDtFinal()) || LocalTime.now().isAfter(aaa)) {
                l.setStatus(false);
                leilaoService.getLeilaoById(l.getId());
                leilaoService.update(l);
            }
        });
    }


}
