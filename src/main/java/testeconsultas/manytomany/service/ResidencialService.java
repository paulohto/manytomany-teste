package testeconsultas.manytomany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.dto.ResidencialLazerDTO;
import testeconsultas.manytomany.entity.Lazer;
import testeconsultas.manytomany.entity.Residencial;
import testeconsultas.manytomany.exception.service.ControllerNotFoundException;
import testeconsultas.manytomany.repository.ILazerRepository;
import testeconsultas.manytomany.repository.IResidencialRepository;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<Object[]> getAllResidencialLazer() {
        return residencialRepository.findAllResidencialLazer();
    }

    //Funcionando como lista sem DTO
//    @Transactional(readOnly = true)
//    public List<Object[]> findLazerByResidencialId(Long id) {
//        return residencialRepository.findByResidencialId(id);
//    }

    //Funcionando como JSON
    @Transactional(readOnly = true)
    public List<ResidencialLazerDTO> findLazerByResidencialId(Long id) {
        List<Object[]> results = residencialRepository.findByResidencialId(id);
        return results.stream()
                .map(result -> new ResidencialLazerDTO((Long) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }

    ////

//    @Transactional(readOnly = true)
//    public List<Object[]> findByIdResidencialLazer() {
//        return residencialRepository.findByIdResidencialLazer();
//    }

//    @Transactional(readOnly = true)
//    public ResidencialDTO findById(Long id) {
//        var residencial = residencialRepository.findById(id)
//                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
//        return new ResidencialDTO(residencial, residencial.getLazeres());
//    }

//    @Transactional(readOnly = true)
//    public Residencial findById(Long id) {
//        Optional<Residencial> residencialOpt = residencialRepository.findByIdWithLazeres(id);
//        return residencialOpt.orElseThrow(() -> new RuntimeException("Residencial não encontrado com o ID: " + id));
//    }

    @Transactional(readOnly = true)
    public Residencial findById(Long id) {
        Optional<Residencial> residencialOpt = residencialRepository.findByIdWithLazeres(id);
        if (residencialOpt.isPresent()) {
            return residencialOpt.get();
        } else {
            throw new RuntimeException("Residencial não encontrado com o ID: " + id);
        }
    }

    // TESTE CONSULTA RESINDECIAL COM LAZER - OK!!!!!!!!!!1
//    @Transactional(readOnly = true)
//    public ResidencialDTO findResidencialWithLazerById(Long id) {
//        Optional<Residencial> residencialOpt = residencialRepository.findById(id);
//        if (!residencialOpt.isPresent()) {
//            return null;
//        }
//
//        Residencial residencial = residencialOpt.get();
//        List<LazerDTO> lazerDTOs = residencial.getLazeres().stream()
//                .map(lazer -> new LazerDTO(lazer.getId(), lazer.getTipo()))
//                .collect(Collectors.toList());
//
//        return new ResidencialDTO(residencial.getId(), residencial.getNome(), lazerDTOs);
//    }

    @Transactional
    public ResidencialDTO saveResidencial(ResidencialDTO residencialDTO){
        var entity = new Residencial();
        mapperDTOtoEntity(residencialDTO, entity);
        var residencialSaved = residencialRepository.save(entity);

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
