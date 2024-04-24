package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Voto;

/**
 * Repositório para gerenciar operações relacionadas à entidade Voto no banco de dados.
 */
public class VotoRepository {

    private  EntityManager entityManager;

    /**
     * Construtor que inicializa o EntityManager para acesso ao banco de dados.
     */
    public VotoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EscolaDB");
        entityManager = factory.createEntityManager();
    }

    /**
     * Insere um novo voto no banco de dados.
     *
     * @param voto O objeto Voto a ser inserido.
     */
    public void inserir(Voto voto) {
        entityManager.getTransaction().begin();
        entityManager.merge(voto);
        entityManager.getTransaction().commit();
    }

    /**
     * Retorna uma lista com todos os votos no banco de dados.
     *
     * @return Uma lista de objetos Voto.
     */
    public List<Voto> lista() {
        TypedQuery<Voto> buscarTodosQuery = entityManager.createQuery(
            "SELECT e FROM Voto e", Voto.class
        );
        return buscarTodosQuery.getResultList();
    }


}
