package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.Lance;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.LanceService;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class FinalizarLeilaoController {

    private static final Scanner input = new Scanner(System.in); //inserir texto teclado
    private static ItemLeilaoService itemLeilaoService;
    private static LeilaoService leilaoService;
    private static LanceService lanceService;

    public FinalizarLeilaoController(LanceService lanceService, ItemLeilaoService itemLeilaoService, LeilaoService leilaoService){
        FinalizarLeilaoController.itemLeilaoService = itemLeilaoService;
        FinalizarLeilaoController.leilaoService = leilaoService;
        FinalizarLeilaoController.lanceService = lanceService;
    }

    public static void main(String[] args) {
        LeilaoController.atualizarStatus();

        System.out.println("====== Pagina de ganhadores ====== ");

        ganhadores();


    }

    public static void ganhadores(){
        System.out.println("Selecione o id do leilao: ");
        Long id = input.nextLong();
        input.nextLine();
        Leilao leilao = leilaoService.getLeilaoById(id);
        if(leilao.getStatus()==false){
//            System.out.println(leilao);
            List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(leilao);
//            System.out.println(itens);
            itens.forEach(i->{
                List<Lance> lances = lanceService.getLanceAllByItem(i);
                lances.forEach(l->{
                    Lance maiorLance = Collections.max(lances, Comparator.comparing(Lance::getValorLance));
                    System.out.println("\nO maior lance do item foi: " + maiorLance);
                    arremarado();
                });
            });
        }else{
            System.out.println("Leilao em andamento");
            System.out.println("Por favor espere o leilao acabar");
            HomeController.main(null);
        }

        HomeController.main(null);
    }

    private static void arremarado(){
        List<Lance> lances = lanceService.getLanceAll();
        lances.forEach(l->{
            ItemLeilao itemLeilao = itemLeilaoService.updateArre(l.getItemLeilaoByCodItem());
//            System.out.println(itemLeilao);
        });
    }

}


