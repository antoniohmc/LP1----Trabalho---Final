package model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Classe que representa um restaurante.
 */
@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    /**
     * Gereção de valores no banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "restaurante_generator")
    @TableGenerator(
        name = "restaurante_generator",
        table = "chave",
        pkColumnName = "id",
        valueColumnName = "valor",
        allocationSize = 1
    )

    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    /**
     * Construtor padrão.
     */
    public Restaurante() {
    }

    /**
     * Construtor que aceita ID e nome do restaurante.
     *
     * @param nome O nome do restaurante.
     */
    public Restaurante(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID do restaurante.
     *
     * @return O ID do restaurante.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtém o nome do restaurante.
     *
     * @return O nome do restaurante.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do restaurante.
     *
     * @param nome O nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma string representando o restaurante.
     *
     * @return Uma string com o ID e nome do restaurante.
     */
    @Override
    public String toString() {
        return "Restaurante{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Restaurante that = (Restaurante) o;
        return id.equals(that.id) && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
