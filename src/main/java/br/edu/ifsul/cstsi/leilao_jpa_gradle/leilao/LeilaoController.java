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
    public static LeilaoService leilaoService; //service

    public LeilaoController(LeilaoService leilaoService) {

        LeilaoController.leilaoService = leilaoService;
    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area do leilao =======");
            System.out.println("""
                    1. Listar leiloes
                    2. Buscar por id
                    3. Voltar para página anterior
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            atualizarStatus();

            switch (opcao) {
                case 1 -> listar();
                case 2 -> selectById();
                case 3 -> HomeController.main(null);
            }
        } while (opcao != 0);
    }

    private static void listar() {
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        System.out.println("\n Lista de leiloes: " + leilaos);
    }

    private static void selectById() {
        System.out.println("Digite o id do leilao: ");
        Long id = Long.valueOf(input.nextLine());
        Leilao leilao = leilaoService.getLeilaoById(id);
        if (leilao == null) {
            System.out.println("\nNão há registro de leiloes com esse id: " + id);
        } else {
            System.out.println(leilao);
        }
    }

    public static void atualizarStatus() {
        List<Leilao> leilaos = leilaoService.getAllLeiloes();
        leilaos.forEach(l -> {
            LocalTime aaa = l.getHrFinal().toLocalTime();
            LocalDate bbb = l.getDtFinal().toLocalDate();
            if (LocalDate.now().isAfter(bbb) || LocalTime.now().isAfter(aaa)) {
                l.setStatus(false);
                leilaoService.getLeilaoById(l.getId());
                leilaoService.update(l);
            }
        });
    }


}
