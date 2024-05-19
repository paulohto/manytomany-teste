package testeconsultas.manytomany.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.dto.ResidencialLazerDTO;
import testeconsultas.manytomany.entity.Residencial;
import testeconsultas.manytomany.repository.ILazerRepository;
import testeconsultas.manytomany.repository.IResidencialRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidencialLazerService {

    @Autowired
    private IResidencialRepository residencialRepository;

    @Autowired
    private ILazerRepository lazerRepository;

    @Autowired
    private ResidencialService residencialService;

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

    //TESTE FUNCIONANDO ESSE - OK
    @Transactional(readOnly = true)
    public ResidencialDTO findResidencialWithLazerById(Long id) {
        // Busque o residencial pelo ID
        Optional<Residencial> residencialOpt = residencialRepository.findById(id);
        if (!residencialOpt.isPresent()) {
            return null;
        }

        Residencial residencial = residencialOpt.get();
        // Busque os lazeres associados ao residencial
        List<LazerDTO> lazerDTOs = residencialRepository.findByResidencialId(id).stream()
                .map(result -> new LazerDTO((Long) result[1], (String) result[2]))
                .collect(Collectors.toList());

        // Retorne o DTO do residencial com os lazeres
        return new ResidencialDTO(residencial.getId(), residencial.getNome(), lazerDTOs);
    }

//    @Transactional(readOnly = true)
//    public List<ResidencialLazerDTO> findLazerByResidencialId(Long id) {
//        List<Object[]> results = residencialRepository.findByResidencialId(id);
//        return results.stream()
//                .map(result -> new ResidencialLazerDTO((Long) result[0], (Long) result[1]))
//                .collect(Collectors.toList());
//    }

}
