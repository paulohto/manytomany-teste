package testeconsultas.manytomany.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "tb_lazer")
public class Lazer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;

    @ManyToMany(mappedBy = "lazeres")
    private Set<Residencial> residenciais = new HashSet<>();

    public Lazer(){}

    public Lazer(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Set<Residencial> getResidenciais() {
        return residenciais;
    }
}
