package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.Lance;
import br.edu.ifsul.cstsi.leilao_jpa_gradle.lance.LanceService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class ParticipanteController {
    private static final Scanner input = new Scanner(System.in); //inserção de texto
    public static ParticipanteService participanteService;
    public static LanceService lanceService; //service

    //injeção de dependencia
    public ParticipanteController(ParticipanteService participanteService, LanceService lanceService) {
        ParticipanteController.participanteService = participanteService;
        ParticipanteController.lanceService = lanceService;
    }

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n====== Area do participante: ======");
            System.out.println("""
                    1. Cadastro de participante
                    2. Atualizar participantes
                    3. Listar participantes
                    4. Buscar por nome um participante
                    5. Buscar por ID
                    6. Desativar/ativar participante
                    7. Ver lances
                    0. Voltar para página anterior\s""");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> selectAll();
                case 4 -> selectByNome();
                case 5 -> selectById();
                case 6 -> desativar();
                case 7 -> lances();
                case 0 -> HomeController.main(null);
            }

        } while (opcao < 0 || opcao > 7);
    }

    //opcao 1 - inserir participante
    private static void inserir() {
        Participante participante = new Participante();
        System.out.println("\n======= Cadastrar novo participante =========");
        System.out.println("\n Digite o nome: ");
        participante.setNome(input.nextLine());
        System.out.println("\n Digite o login: ");
        participante.setLogin(input.nextLine());
        System.out.println("\n Digite o email: ");
        participante.setEmail(input.nextLine());
        System.out.println("\n Digite uma senha: ");
        participante.setSenha(input.nextLine());
        System.out.println("\n Digite o telefone: ");
        participante.setTelefone(input.nextLine());
        System.out.println("\n Digite o endereço: ");
        participante.setEndereco(input.nextLine());
        participante.setStatus(true);

        System.out.println("\nTem certeza que deseja adicionar esse participante? ");
        int opcao = 0;
        do{
            System.out.println("\n  0- não e 1- sim");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1 -> {
                    System.out.println("\nParticipante salvo: " + participanteService.insert(participante));
                    HomeController.main(null);

                }
                case 0 -> {
                    System.out.println("\nCadastro não realizado!");
                    HomeController.main(null);

                }
            }

        }while (opcao < 0 || opcao > 1);

    }

    //opção 2 - atualiza participante
    private static void atualizar() {
        System.out.println("\n ====== Alterar dados do participante ======");
        int opcao = 0;
        Participante participante = null;
        do {
            System.out.println("\nDigite o id do participante para alterar (0 pra sair) ");
            long id = input.nextLong();
            input.nextLine();
            if (id == 0) {
                opcao = 0;
            } else {
                participante = participanteService.getParticipanteById(id);
                if (participante == null) {
                    System.out.println(" ID invalido! Tente novamente ");
                } else {
                    int opcao2 = 0;
                    do {
                        System.out.println("Qual opcao queres alterar?\n ");
                        System.out.println("1. Nome: " + participante.getNome());
                        System.out.println("2. Login: " + participante.getLogin());
                        System.out.println("3. Senha: " + participante.getSenha());
                        System.out.println("4. Email: " + participante.getEmail());
                        System.out.println("5. Endereco: " + participante.getEndereco());
                        System.out.println("6. Telefone: " + participante.getTelefone());
                        System.out.println("0. Nenhuma, desejo voltar ");
                        opcao2 = input.nextInt();
                        input.nextLine();

                        switch (opcao2) {
                            case 1 -> {
                                System.out.println("Digite o novo nome: ");
                                participante.setNome(input.nextLine());
                            }
                            case 2 -> {
                                System.out.println("Digite o novo login: ");
                                participante.setLogin(input.nextLine());
                            }
                            case 3 -> {
                                System.out.println("Digite a nova senha: ");
                                participante.setSenha(input.nextLine());
                            }
                            case 4 -> {
                                System.out.println("Digite o novo email: ");
                                participante.setEmail(input.nextLine());
                            }
                            case 5 -> {
                                System.out.println("Digite o novo endereço: ");
                                participante.setEndereco(input.nextLine());
                            }
                            case 6 -> {
                                System.out.println("Digite o novo telefone: ");
                                participante.setTelefone(input.nextLine());
                            }

                            case 0 ->{
                                return;
                            }

                        }

                    } while (opcao < 0 || opcao > 6);

                    participante.setStatus(true);
                    System.out.println("Atualizado com sucesso\n" + participanteService.update(participante));
                    ParticipanteController.main(null);
                }
            }

        } while (opcao != 0);
    }

    //opçao 3 - listar todos
    private static void selectAll(){
        List<Participante> participantes = participanteService.getParticipanteAll();
        if(participantes == null)
            System.out.println("\nNão há participantes cadastrados!");

        System.out.println("\nLista de participante do leilao: " + participantes);
        ParticipanteController.main(null);
    }

    //opção 4 - listar pelo nome
    private static void selectByNome() {
        System.out.println("\nDigite o nome do participante: ");
        String nome = input.nextLine();
        List<Participante> participantes = participanteService.getParticipanteByNome(nome + "%");
        if (participantes == null) {
            System.out.println("\nNão existe participante com esse nome: " + nome);
            ParticipanteController.main(null);
        } else {
            System.out.println(participantes);
            ParticipanteController.main(null);
        }
    }

    //opcao 5 - selecionar participante pelo id
    private static void selectById() {
        System.out.println("Digite o id do participante: ");
        Long id = Long.valueOf(input.nextLine());
        Participante participante = participanteService.getParticipanteById(id);
        if(participante == null){
            System.out.println("\nNão há registro de participante com esse id: " + id);
            ParticipanteController.main(null);
        }else {
            System.out.println(participante);
            ParticipanteController.main(null);
        }
    }

    //opcao 6 - desativar participante
    private static void desativar() {
        System.out.println("\n ====== Digite o ID do participante para desativar ou ativar ======");
        Long id = input.nextLong();
        input.nextLine();
        Participante participante = null;
//        Boolean status = null;
        participante = participanteService.getParticipanteById(id);
        if (participante == null) {
            System.out.println("ID invalido");
        } else {
            if (participante.getStatus() == true) {
                participante.setStatus(false);
                participanteService.delete(participante);
            } else {
                participante.setStatus(true);
                participanteService.delete(participante);
            }
            System.out.println("\nOperação realizada com sucesso");
            List<Participante> participantes = participanteService.getParticipanteAll();
            System.out.println("\nLista de participante do leilao atualizada: " + participantes);
            ParticipanteController.main(null);
        }
    }

    //lances de cada participante
    private static void lances(){
        System.out.println("Insira o ID do participante: ");
        Long id = input.nextLong();
        input.nextLine();
        Participante participante = participanteService.getParticipanteById(id);
        List<Lance> lances = lanceService.getLanceAllByPart(participante);
        System.out.println(lances);
        ParticipanteController.main(null);
    }
}
