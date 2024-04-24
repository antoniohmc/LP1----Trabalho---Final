package controller;

import exception.AplicacaoException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.PersistenceException;
import model.Funcionario;
import model.Restaurante;
import model.Voto;
import repository.FuncionarioRepository;
import repository.RestauranteRepository;
import repository.VotoRepository;

/**
 * Controlador pra gerenciar as operações relacionadas a funcionários, restaurantes e votos.
 * Fornece métodos para listar, inserir e gerenciar essas entidades.
 */
public class RestauranteController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;
    private VotoRepository votoRepository;

    /**
     * Construtor que inicializa os repositorios para funcionários, restaurantes e votos.
     */
    public RestauranteController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
        this.votoRepository = new VotoRepository();
    }

    /**
     * Retorna a lista de todos os funcionários.
     *
     * @return Uma lista de objetos Funcionario.
     */
    public List<Funcionario> listaFuncionario() {
        return funcionarioRepository.lista();
    }

    /**
     * Retorna a lista de todos os restaurantes.
     *
     * @return Uma lista de objetos Restaurante.
     */
    public List<Restaurante> listaRestaurantes() {
        return restauranteRepository.lista();
    }

    public List<Voto> listaVotos() {
        return votoRepository.lista();
    }

    /**
     * Insere um novo funcionário no banco de dados.
     *
     * @param nome O nome do funcionário a ser inserido.
     * @throws AplicacaoException Se ocorrer um erro durante a inserção do funcionário.
     */
    public void inserirFuncionario( String nome) throws AplicacaoException {
        listaFuncionario().add(new Funcionario(nome));

        try {
            funcionarioRepository.inserirFuncionario(new Funcionario(nome));
        } catch (PersistenceException e) {
            throw new AplicacaoException("Falha ao inserir um funcionário.");
        }
    }

    /**
     * Insere um novo restaurante no banco de dados.
     *
     * @param nome O nome do restaurante a ser inserido.
     * @throws AplicacaoException Se ocorrer um erro durante a inserção do restaurante.
     */
    public void inserirRestaurante(String nome) throws AplicacaoException {
        listaRestaurantes().add(new Restaurante(nome));

        try {
            restauranteRepository.inserir(new Restaurante(nome));
        } catch (PersistenceException e) {
            throw new AplicacaoException("Falha ao inserir um restaurante.");
        }
    }

    /**
     * Insere um novo voto, verificando se o funcionário já votou no mesmo dia.
     *
     * @param idFuncionario O ID do funcionário que está votando.
     * @param idRestaurante O ID do restaurante que está recebendo o voto.
     * @param data A data em que o voto está sendo realizado.
     * @throws AplicacaoException Se o funcionário já votou no mesmo dia ou se um ID não for encontrado.
     */
    public void inserirVoto(Integer idFuncionario, Integer idRestaurante, LocalDate data) throws AplicacaoException {
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(idFuncionario);
        if (funcionario == null) {
            throw new AplicacaoException("Funcionário não encontrado.");
        }

        Restaurante restaurante = restauranteRepository.buscarPorId(idRestaurante);
        if (restaurante == null) {
            throw new AplicacaoException("Restaurante não encontrado.");
        }

        List<Voto> votos = votoRepository.lista();
        for (Voto voto : votos) {
            if (voto.getFuncionario().equals(funcionario) && voto.getData().equals(data)) {
                throw new AplicacaoException("Funcionário já votou neste dia.");
            }
        }

        Voto novoVoto = new Voto();
        novoVoto.setFuncionario(funcionario);
        novoVoto.setRestaurante(restaurante);
        novoVoto.setData(data);

        votoRepository.inserir(novoVoto);
    }
}
