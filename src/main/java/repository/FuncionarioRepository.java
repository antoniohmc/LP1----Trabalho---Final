package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Funcionario;

/**
 * Classe responsável por operações de banco de dados relacionadas a funcionários.
 */
public class FuncionarioRepository {

    private  EntityManager entityManager;

    /**
     * Construtor que inicializa o EntityManager para operações com a base de dados.
     */
    public FuncionarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EscolaDB");
        entityManager = factory.createEntityManager();
    }

    /**
     * Insere um novo funcionário na base de dados.
     *
     * @param funcionario O funcionário a ser inserido.
     */
    public void inserirFuncionario(Funcionario funcionario) {
        entityManager.getTransaction().begin();
        entityManager.persist(funcionario);
        entityManager.getTransaction().commit();
    }

    /**
     * Retorna uma lista com todos os funcionários da base de dados.
     *
     * @return Uma lista de objetos Funcionario.
     */
    public List<Funcionario> lista() {
        TypedQuery<Funcionario> listarFuncionariosQuery = entityManager.createQuery(
            "SELECT e FROM Funcionario e", Funcionario.class
        );
        return listarFuncionariosQuery.getResultList();
    }

    /**
     * Busca um funcionário pelo seu ID.
     *
     * @param id O ID do funcionário.
     * @return O funcionário correspondente ao ID, ou null se não encontrado.
     */
    public Funcionario buscarFuncionarioPorId(Integer id) {
        return entityManager.find(Funcionario.class, id);
    }
}
