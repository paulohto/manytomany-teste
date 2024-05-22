package testeconsultas.manytomany.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.dto.ResidencialLazerDTO;
import testeconsultas.manytomany.entity.Residencial;
import testeconsultas.manytomany.service.ResidencialLazerService;
import testeconsultas.manytomany.service.ResidencialService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/residencial-teste")
public class ResidencialController {

    private final ResidencialService residencialService;
    private final ResidencialLazerService residencialLazerService;

    public ResidencialController(ResidencialService residencialService, ResidencialLazerService residencialLazerService) {
        this.residencialService = residencialService;
        this.residencialLazerService = residencialLazerService;
    }

    @GetMapping
    public ResponseEntity<Page<ResidencialDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var residenciais = residencialService.findAll(pageRequest);
        return ResponseEntity.ok(residenciais);
    }

    //TESTANDO COONSULTAR GERAL NA ALTER TABLE TB_RESINDENCIAL_LAZER
    @GetMapping("/alt-table-geral")
    public List<Object[]> getAllResidencialLazer() {
        return residencialService.getAllResidencialLazer();
    }

    //TESTANDO COONSULTAR POR ID "RESIDENCIAL" NA ALTER TABLE TB_RESINDENCIAL_LAZER
    @GetMapping("/alt-table-residencial/{id}")
    public List<ResidencialLazerDTO> getLazerByResidencialId(@PathVariable Long id) {
        return residencialService.findLazerByResidencialId(id);
    }

    @GetMapping("/teste-byid/{id}")
    public ResponseEntity<ResidencialDTO> getResidencialWithLazer(@PathVariable Long id) {
        ResidencialDTO residencialDTO = residencialLazerService.findResidencialWithLazerById(id);
        if (residencialDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(residencialDTO);
    }

    @PostMapping
    public ResponseEntity<ResidencialDTO> saveResidencial(
            @Valid
            @RequestBody ResidencialDTO dto
    ){
        ResidencialDTO residencialDTO = residencialService.saveResidencial(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((residencialDTO.getId())).toUri();
        return ResponseEntity.created(uri).body(residencialDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResidencialDTO> updateResidencial(
            @PathVariable Long id,
            @Valid @RequestBody ResidencialDTO residencialDTO) {
        ResidencialDTO updatedResidencial = residencialService.updateResidencial(id, residencialDTO);
        return ResponseEntity.ok(updatedResidencial);
    }

}
