package br.edu.ifsul.cstsi.leilao_jpa_gradle;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.itemLeilao.ItemLeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.LanceController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.CriarLeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.FinalizarLeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.leilao.LeilaoController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.participante.ParticipanteController;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class HomeController {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Bem vindo =======");
            System.out.println("""
                    1. Dar lance
                    2. Manter leilões
                    3. Manter itens 
                    4. Manter participantes
                    5. Criar leilao
                    6. Finalizar leilao
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> LanceController.main(null);
                case 2 -> LeilaoController.main(null);
                case 3 -> ItemLeilaoController.main(null);
                case 4 -> ParticipanteController.main(null);
                case 5 -> CriarLeilaoController.main(null);
                case 6 -> FinalizarLeilaoController.main(null);
            }
        } while (opcao != 0);
        System.out.println("====== FIM ======");
        input.close();
    }

}
