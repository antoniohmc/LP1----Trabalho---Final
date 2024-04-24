package view;

import controller.RestauranteController;
import exception.AplicacaoException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Classe principal do sistema para interação com o usuário. Fornece opções para cadastrar funcionários, restaurantes e
 * votos.
 */
public class Sistema {

    private static RestauranteController controller = new RestauranteController();

    /**
     * O método principal que inicia o loop para interação com o usuário.
     *
     * @param args Argumentos de linha de comando (não usados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                int escolha = imprimirOpcoes(scanner);

                switch (escolha) {
                    case 1:
                        cadastrarFuncionario(scanner);
                        break;

                    case 2:
                        cadastrarRestaurante(scanner);
                        break;

                    case 3:
                        cadastrarVoto(scanner);
                        break;

                    case 4:
                        controller.listaVotos();
                        break;

                    case 0:
                        System.out.println("Saindo do sistema.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (AplicacaoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Cadastra um novo funcionário com entrada do usuário.
     *
     * @param scanner O scanner para entrada do usuário.
     * @throws AplicacaoException Se ocorrer um erro durante o cadastro do funcionário.
     */
    public static void cadastrarFuncionario(Scanner scanner) throws AplicacaoException {

        System.out.println("Informe o nome do Funcionário:");
        String nomeFuncionario = scanner.next();

        controller.inserirFuncionario(nomeFuncionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    /**
     * Cadastra um novo restaurante com entrada do usuário.
     *
     * @param scanner O scanner para entrada do usuário.
     * @throws AplicacaoException Se ocorrer um erro durante o cadastro do restaurante.
     */
    public static void cadastrarRestaurante(Scanner scanner) throws AplicacaoException {

        System.out.println("Informe o nome do Restaurante:");
        String nomeRestaurante = scanner.next();

        controller.inserirRestaurante(nomeRestaurante);
        System.out.println("Restaurante cadastrado com sucesso!");
    }

    /**
     * Cadastra um novo voto, verificando se o funcionário já votou no mesmo dia.
     *
     * @param scanner O scanner para entrada do usuário.
     * @throws AplicacaoException Se o funcionário já votou no mesmo dia ou se ocorrer erro ao cadastrar o voto.
     */
    public static void cadastrarVoto(Scanner scanner) throws AplicacaoException {
        System.out.println("Informe o ID do Funcionário:");
        Integer idFuncionario = scanner.nextInt();

        System.out.println("Informe o ID do Restaurante:");
        Integer idRestaurante = scanner.nextInt();



        Calendar data = Calendar.getInstance();

        controller.inserirVoto(idFuncionario, idRestaurante, data);
        System.out.println("Voto cadastrado com sucesso!");
    }

    /**
     * Exibe as opções do menu para o usuário e retorna a escolha.
     *
     * @param scanner O scanner para entrada do usuário.
     * @return O número correspondente à opção escolhida pelo usuário.
     */
    public static int imprimirOpcoes(Scanner scanner) {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Cadastrar restaurante");
        System.out.println("3 - Cadastrar voto");
        System.out.println("4 - Apuração de votos ");
        System.out.println("0 - Sair");

        return scanner.nextInt();
    }
}
