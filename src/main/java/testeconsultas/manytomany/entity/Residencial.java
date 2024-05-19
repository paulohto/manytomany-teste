package testeconsultas.manytomany.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_residencial")
public class Residencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
            name = "tb_residencial_lazer",
            joinColumns = @JoinColumn(name = "residencial_id"),
            inverseJoinColumns = @JoinColumn(name = "lazer_id")
    )
    private Set<Lazer> lazeres = new HashSet<>();

    public Residencial(){}

    public Residencial(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Set<Lazer> getLazeres() {
        return lazeres;
    }

    public void setLazeres(Set<Lazer> lazerer) { this.lazeres = lazeres; }

}
