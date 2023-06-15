package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.Lance;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.LanceService;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class FinalizarLeilaoController {

    private static ItemLeilaoService itemLeilaoService;
    private static LeilaoService leilaoService;
    private static LanceService lanceService;

    public FinalizarLeilaoController(LanceService lanceService, ItemLeilaoService itemLeilaoService, LeilaoService leilaoService){
        FinalizarLeilaoController.itemLeilaoService = itemLeilaoService;
        FinalizarLeilaoController.leilaoService = leilaoService;
        FinalizarLeilaoController.lanceService = lanceService;
    }

    public static void main(String[] args) {
        System.out.println("Ganhadores: ");

        LeilaoController.atualizarStatus();
        leilaofalse();
//        leilaotrue();

    }

    public static void leilaotrue(){
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        leilaos.forEach(l -> {
            if(l.getStatus() == true) {
                List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(l);
                itens.forEach(i -> {
                    List<Lance> lances = lanceService.getLanceAllByItem(i);
                    Lance maiorLance = Collections.max(lances, Comparator.comparing(Lance::getValorLance));
                    System.out.println(maiorLance);
                });
            }
        });
    }

    public static void leilaofalse(){
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        leilaos.forEach(l ->{
            if(l.getStatus()==false) {
                List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(l);
                itens.forEach(i -> {
                    List<Lance> lances = lanceService.getLanceAllByItem(i);
                    Lance maiorLance = Collections.max(lances, Comparator.comparing(Lance::getValorLance));
                    System.out.println(maiorLance);
                });

            }

        });
    }

}


