package br.edu.ifsul.cstsi.leilao_jpa_gradle.lance;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoService;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.Leilao;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoService;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.Participante;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.ParticipanteController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.ParticipanteService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class LanceController {

    private static final Scanner input = new Scanner(System.in);

    private static ItemLeilaoService itemLeilaoService;
    private static ParticipanteService participanteService;
    private static LeilaoService leilaoService;
    private static LanceService lanceService;

    public LanceController(LanceService lanceService, ItemLeilaoService itemLeilaoService, ParticipanteService participanteService, LeilaoService leilaoService){
        LanceController.itemLeilaoService = itemLeilaoService;
        LanceController.participanteService = participanteService;
        LanceController.leilaoService = leilaoService;
        LanceController.lanceService = lanceService;
    }

    public static void main(String[] args) {
        //atualizando os leiloes
        LeilaoController.atualizarStatus();

        //listando os leiloes
        System.out.println("\nPara qual leilao deseja fazer lances? ");
        List<Leilao> leilaos = leilaoService.getAllLeiloesTrue();
        System.out.println(leilaos);
        Long id = input.nextLong();
        input.nextLine();
        Leilao leilao = leilaoService.getLeilaoById(id);
        List<ItemLeilao> itens = itemLeilaoService.getItemByIdLeilao(leilao);
        List<ItemLeilao> itensliberados = new ArrayList<>();

        //mostrando o item do leilao
        itens.forEach(i ->{
            if(i.getArrematado() == true){
                itensliberados.add(i);
            }
        });
        System.out.println("Itens do leilao: " + itens);

        //selecionar participante
        System.out.println("Quais participantes vao coisas o coisado? ");
        List<Participante> participantes = new ArrayList<>();
        int op = 0;
        do{
            System.out.println("Qual o seu login? ");
            String login = input.nextLine();
            System.out.println("Qual sua senha? ");
            String senha = input.nextLine();

            Participante participante = participanteService.getParticipanteByLogin(login, senha);

            if(participante != null){
                participantes.add(participante);
                System.out.println("\nParticipante adicionado! :) ");
            }else {
                System.out.println("\nN te achei meno");
            }

            System.out.println("\nDeseja adicionar outro? 1-sim 2-nao");
            op = input.nextInt();
            input.nextLine();
        }while(op!=2);

        System.out.println("\nParticipantes q tao dentro " + participantes);

        //lance
        do{
            System.out.println("\nQuem deseja dar? ");
            Long idp = input.nextLong();
            input.nextLine();
            for (Participante p : participantes) {
                if(idp == p.getId()){
                    Participante pachado = participanteService.getParticipanteById(idp);
                    System.out.println("\nQual o item que deseja arrematar? ");
                    System.out.println(itensliberados);
                    Long item = input.nextLong();
                    input.nextLine();
                    for (ItemLeilao i : itensliberados) {
                        if (item == i.getId()) {

                            ItemLeilao itemachado = itemLeilaoService.getItemById(item);
                            System.out.println("\nQuanto vai dar de lance? ");
                            double lance = 0;
                            lance = input.nextDouble();
                            input.nextLine();
                            if (lance < i.getLanceMin()) {
                                System.out.println("\nLance não aceito ? ");
                            }else{
                                Lance lance1 = new Lance();
                                lance1.setHrLance(LocalDateTime.now());
                                lance1.setValorLance(lance);
                                lance1.setItemLeilaoByCodItem(itemachado);
                                lance1.setParticipanteByIdPart(pachado);
                                System.out.println("Lance efetuado! " + lanceService.insertLance(lance1));
                            }
                        }
                    }
                }
                System.out.println("Não achei participante blablabla");
            }

            System.out.println("\nDeseja fazer outro? 1-sim 2-nao");
            op = input.nextInt();
            input.nextLine();

        }while(op!=2);

    }

}
