package testeconsultas.manytomany.dto;

import lombok.Data;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.entity.Residencial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class LazerDTO {

    private Long id;
    private String tipo;

    public LazerDTO(){}

    public LazerDTO(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public LazerDTO(Lazer lazer) {
        this.id = lazer.getId();
        this.tipo = lazer.getTipo();
    }

}
