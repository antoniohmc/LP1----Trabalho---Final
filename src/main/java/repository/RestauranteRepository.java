package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Restaurante;

/**
 * Classe que gerencia operações com o banco de dados para a entidade Restaurante.
 */
public class RestauranteRepository {

    private  EntityManager entityManager;

    /**
     * Construtor que inicializa o EntityManager para interações com o banco de dados.
     */
    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EscolaDB");
        entityManager = factory.createEntityManager();
    }

    /**
     * Insere um novo restaurante no banco de dados.
     *
     * @param restaurante O objeto Restaurante a ser inserido.
     */
    public void inserir(Restaurante restaurante) {
        entityManager.getTransaction().begin();
        entityManager.persist(restaurante);
        entityManager.getTransaction().commit();
    }

    /**
     * Retorna uma lista com todos os restaurantes do banco de dados.
     *
     * @return Uma lista de objetos Restaurante.
     */
    public List<Restaurante> lista() {
        TypedQuery<Restaurante> buscarTodosQuery = entityManager.createQuery(
            "SELECT e FROM Restaurante e", Restaurante.class
        );
        return buscarTodosQuery.getResultList();
    }

    /**
     * Busca um restaurante pelo seu ID.
     *
     * @param id O ID do restaurante a ser buscado.
     * @return O objeto Restaurante correspondente ao ID, ou null se não encontrado.
     */
    public Restaurante buscarPorId(Integer id) {
        return entityManager.find(Restaurante.class, id);
    }
}
