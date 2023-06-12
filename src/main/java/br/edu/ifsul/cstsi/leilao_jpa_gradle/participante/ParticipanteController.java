package br.edu.ifsul.cstsi.leilao_jpa_gradle.participante;

import br.edu.ifsul.cstsi.leilao_jpa_gradle.HomeController;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class ParticipanteController {
    private static final Scanner input = new Scanner(System.in); //inserção de texto
    private static ParticipanteService ParticipanteService; //service

    //injeção de dependencia
    public ParticipanteController(ParticipanteService participanteService) {
        ParticipanteController.ParticipanteService = participanteService;
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
                    7. Voltar para página anterior
                    0. Sair\s""");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> selectAll();
                case 4 -> selectByNome();
                case 5 -> selectById();
                case 6 -> desativar();
                case 7 -> HomeController.main(null);
            }

        } while (opcao != 0);
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
                    System.out.println("\nParticipante salvo: " + ParticipanteService.insert(participante));
                    return;
                }
                case 0 -> {
                    System.out.println("\nCadastro não realizado!");
                }
            }

        }while (opcao != 0);

    }

    //opção 2 - atualiza participante
    private static void atualizar() {
        System.out.println("\n ====== Alterar dados do participante ======");
        int opcao = 0;
        Participante participante = null;
        do {
            System.out.println("\n Digite o id do participante para alterar(0 pra sair) ");
            long id = input.nextLong();
            input.nextLine();
            if (id == 0) {
                opcao = 0;
            } else {
                participante = ParticipanteService.getParticipanteById(id);
                if (participante == null) {
                    System.out.println("ID invalido");
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

                        }

                    } while (opcao != 0);

                    participante.setStatus(true);
                    System.out.println("Atualizado com sucesso\n" + ParticipanteService.update(participante));
                }
            }

        } while (opcao != 0);
    }

    //opçao 3 - listar todos
    private static void selectAll(){
        List<Participante> participantes = ParticipanteService.getParticipanteAll();
        System.out.println("\n Lista de participante do leilao: " + participantes);
    }

    //opção 4 - listar pelo nome
    private static void selectByNome() {
        System.out.println("\nDigite o nome do participante: ");
        String nome = input.nextLine();
        List<Participante> participantes = ParticipanteService.getParticipanteByNome(nome + "%");
        if (participantes.isEmpty()) {
            System.out.println("\nNão existe participante com esse nome: " + nome);
        } else {
            System.out.println(participantes);
        }
    }

    //opcao 5 - selecionar participante pelo id
    private static void selectById() {
        System.out.println("Digite o id do participante: ");
        Long id = Long.valueOf(input.nextLine());
        Participante participante = ParticipanteService.getParticipanteById(id);
        if(participante == null){
            System.out.println("\nNão há registro de participante com esse id: " + id);
        }else {
            System.out.println(participante);
        }
    }

    //opcao 6 - desativar participante
    private static void desativar() {
        System.out.println("\n ====== Digite o ID do participante para desativar ou ativar ======");
        Long id = input.nextLong();
        input.nextLine();
        Participante participante = null;
//        Boolean status = null;
        participante = ParticipanteService.getParticipanteById(id);
        if (participante == null) {
            System.out.println("ID invalido");
        } else {
            if (participante.getStatus() == true) {
                participante.setStatus(false);
                ParticipanteService.delete(participante);
            } else {
                participante.setStatus(true);
                ParticipanteService.delete(participante);
            }
            System.out.println("SUCESSO");
        }
    }
}
