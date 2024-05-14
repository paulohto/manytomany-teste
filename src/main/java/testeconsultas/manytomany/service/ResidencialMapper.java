package testeconsultas.manytomany.service;

import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.entity.Residencial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResidencialMapper {

    public static ResidencialDTO toDTO(Residencial residencial, Set<Lazer> lazeres) {
        ResidencialDTO dto = new ResidencialDTO();
        dto.setId(residencial.getId());
        dto.setNome(residencial.getNome());

        // Converter o Set para List
        List<LazerDTO> lazeresDTO = new ArrayList<>();
        for (Lazer lazer : lazeres) {
            LazerDTO lazerDTO = new LazerDTO();
            lazerDTO.setId(lazer.getId());
            lazerDTO.setTipo(lazer.getTipo());
            lazeresDTO.add(lazerDTO);
        }
        dto.setLazeres(lazeresDTO);

        return dto;
    }

}
