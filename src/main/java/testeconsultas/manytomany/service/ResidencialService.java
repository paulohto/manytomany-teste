package testeconsultas.manytomany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.entity.Residencial;
import testeconsultas.manytomany.exception.service.ControllerNotFoundException;
import testeconsultas.manytomany.repository.ILazerRepository;
import testeconsultas.manytomany.repository.IResidencialRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResidencialService {

    private final IResidencialRepository residencialRepository;
    private final ILazerRepository lazerRepository;

    public ResidencialService(IResidencialRepository residencialRepository, ILazerRepository lazerRepository) {
        this.residencialRepository = residencialRepository;
        this.lazerRepository = lazerRepository;
    }

    @Transactional(readOnly = true)
    public Page<ResidencialDTO> findAll(PageRequest pagina){
        var residenciais = residencialRepository.findAll(pagina);
        return residenciais.map(x ->  new ResidencialDTO(x, x.getLazeres()));
    }

    @Transactional(readOnly = true)
    public ResidencialDTO findById(Long id) {
        var residencial = residencialRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return new ResidencialDTO(residencial, residencial.getLazeres());
    }

    @Transactional
    public ResidencialDTO saveResidencial(ResidencialDTO residencialDTO){
        var entity = new Residencial();
        mapperDTOtoEntity(residencialDTO, entity);
        var residencialSaved = residencialRepository.save(entity);

//        //TESTE
//        // Associa os Lazeres ao Residencial
//        for (LazerDTO lazerDTO : residencialDTO.getLazeres()) {
//            Lazer lazer = lazerRepository.getOne(lazerDTO.getId());
//            residencialSaved.getLazeres().add(lazer);
//        }
//
//        // Salva o Residencial novamente para atualizar os Lazeres associados
//        residencialSaved = residencialRepository.save(residencialSaved);
//        // FIM TESTE
        return new ResidencialDTO(residencialSaved, residencialSaved.getLazeres());
    }

    private void mapperDTOtoEntity(ResidencialDTO dto, Residencial entity){
        entity.setNome(dto.getNome());

        for(LazerDTO lazerDTO: dto.getLazeres()){
            Lazer lazer = lazerRepository.getOne(lazerDTO.getId());
            entity.getLazeres().add(lazer);
        }

    }

}
