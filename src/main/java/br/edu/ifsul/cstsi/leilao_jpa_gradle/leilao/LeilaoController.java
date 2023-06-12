package br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Controller
public class LeilaoController {

    private static final Scanner input = new Scanner(System.in); //inserir texto teclado
    public static LeilaoService LeilaoService; //service

    public LeilaoController(LeilaoService leilaoService) {

        LeilaoController.LeilaoService = leilaoService;
    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area do leilao =======");
            System.out.println("""
                    1. Criar novo leilao
                    2. Listar leiloes
                    3. Buscar por id
                    4. Voltar para página anterior
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
//                case 1 -> inserir();
                case 2 -> listar();
                case 3 -> selectById();
                case 4 -> HomeController.main(null);
            }
        } while (opcao != 0);
    }

    //opcao 1 - criar um leilao
//    private static void inserir() {
//        Leilao leilao = new Leilao();
//        System.out.println("\n==== Criar um novo leilao: =====");
//
//        LocalDate datain = LocalDate.now();
////        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        leilao.setDtInicio(Date.valueOf(datain));
//        leilao.setDtFinal(Date.valueOf(datain));
//
//        LocalTime horain = LocalTime.now();
//        LocalTime horafim = horain.plusHours(1);
//        leilao.setHrInicio(Time.valueOf(horain));
//        leilao.setHrFinal(Time.valueOf(horafim));
//        lei lao.setStatus(true);
//
//
//        System.out.println("Leilao criado: " + LeilaoService.insert(leilao));
//
//    }

    private static void listar() {
        Leilao leilao = new Leilao();
        List<Leilao> leilaos = LeilaoService.getAllLeiloes();
        leilaos.forEach(l -> {
            LocalTime aaa = l.getHrFinal().toLocalTime();
            LocalDate bbb = l.getDtFinal().toLocalDate();
            if (LocalDate.now().isAfter(bbb) || LocalTime.now().isAfter(aaa)){
                l.setStatus(false);
                LeilaoService.getLeilaoById(l.getId());
                LeilaoService.update(l);
            }
        });
        System.out.println("\n Lista de leiloes: " + leilaos);
    }

    private static void selectById() {
        System.out.println("Digite o id do leilao: ");
        Long id = Long.valueOf(input.nextLine());
        Leilao leilao = LeilaoService.getLeilaoById(id);
        if (leilao == null) {
            System.out.println("\nNão há registro de leiloes com esse id: " + id);
        } else {
            System.out.println(leilao);
        }
    }


}
