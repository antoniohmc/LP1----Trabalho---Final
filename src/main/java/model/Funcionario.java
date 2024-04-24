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
 * Classe que representa um funcionário.
 */
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    /**
     * Gereção de valores no banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "funcionario_generator")
    @TableGenerator(
        name = "funcionario_generator",
        table = "chave",
        pkColumnName = "id",
        valueColumnName = "valor",
        allocationSize = 1
    )
    @Column(name = "Id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    /**
     * Construtor padrão.
     */
    public Funcionario() {
    }

    /**
     * Construtor que aceita ID e nome do funcionário.
     *
     * @param nome O nome do funcionário.
     */
    public Funcionario(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID do funcionário.
     *
     * @return O ID do funcionário.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return O nome do funcionário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do funcionário.
     *
     * @param nome O nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma string representando o funcionário.
     *
     * @return Uma string com o ID e o nome do funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario{" +
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
        Funcionario that = (Funcionario) o;
        return id.equals(that.id) && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
