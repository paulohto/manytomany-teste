package testeconsultas.manytomany.dto;

import jakarta.persistence.*;
import lombok.Data;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.entity.Residencial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ResidencialDTO {

    private Long id;
    private String nome;
    private List<LazerDTO> lazeres = new ArrayList<>();
    //private List<LazerDTO> lazeres;
    public ResidencialDTO() {}

    public ResidencialDTO(Long id, String nome, List<LazerDTO> lazeres) {
        this.id = id;
        this.nome = nome;
        this.lazeres = lazeres;
    }

    public ResidencialDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public ResidencialDTO(Residencial entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();

    }

    public ResidencialDTO(Residencial residencial, Set<Lazer> lazeres) {
        this(residencial);
        lazeres.forEach(lazer -> this.lazeres.add(new LazerDTO(lazer)));
    }

    public List<LazerDTO> getLazeres() {
        return lazeres;
    }

    public void setLazeres(List<LazerDTO> lazeres) {
        this.lazeres = lazeres;
    }


}
