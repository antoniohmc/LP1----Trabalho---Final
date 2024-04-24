package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.*;

/**
 * Classe que representa um voto em um restaurante por um funcionário.
 */
@Entity
@Table(name = "VOTO")
public class Voto {

    /**
     * Gereção de valores no banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "voto_generator")
    @TableGenerator(
        name = "voto_generator",
        table = "chave",
        pkColumnName = "id",
        valueColumnName = "valor",
        allocationSize = 1
    )

    @Column(name = "Id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Calendar data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Restaurante restaurante;

    /**
     * Construtor padrão.
     */
    public Voto() {
    }

    /**
     * Construtor que aceita ID e data do voto.
     *
     * @param id   O ID do voto.
     * @param data A data do voto.
     */
    public Voto(Integer id, Calendar data) {
        this.id = id;
        this.data = data;
    }

    /**
     * Obtém o ID do voto.
     *
     * @return O ID do voto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Obtém a data do voto.
     *
     * @return A data do voto.
     */
    public Calendar getData() {
        return data;
    }

    /**
     * Define a data do voto.
     *
     * @param data A data a ser definida.
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * Obtém o funcionário que votou.
     *
     * @return O funcionário associado ao voto.
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário associado ao voto.
     *
     * @param funcionario O funcionário a ser definido.
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Obtém o restaurante votado.
     *
     * @return O restaurante associado ao voto.
     */
    public Restaurante getRestaurante() {
        return restaurante;
    }

    /**
     * Define o restaurante associado ao voto.
     *
     * @param restaurante O restaurante a ser definido.
     */
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * Retorna uma string representando o voto.
     *
     * @return Uma string com o ID, data, e detalhes do voto.
     */
    @Override
    public String toString() {
        return "Voto{" +
            "id=" + id +
            ", data=" + data +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Voto voto = (Voto) o;
        return id.equals(voto.id) && data.equals(voto.data) && funcionario.equals(voto.funcionario)
            && restaurante.equals(
            voto.restaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, funcionario, restaurante);
    }
}
