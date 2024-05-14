package testeconsultas.manytomany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.repository.ILazerRepository;

@Service
public class LazerService {

    private final ILazerRepository lazerRepository;

    public LazerService(ILazerRepository lazerRepository) {
        this.lazerRepository = lazerRepository;
    }

    @Transactional
    public LazerDTO saveLazer(LazerDTO lazerDTO){
        var entity = new Lazer();
        mapperDTOtoEntity(lazerDTO, entity);
        Lazer lazerSaved = lazerRepository.save(entity);
        return new LazerDTO(lazerSaved);
    }

    private void mapperDTOtoEntity(LazerDTO dto, Lazer entity){
        //entity.setId(dto.getId());
        entity.setTipo(dto.getTipo());
    }
}
