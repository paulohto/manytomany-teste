package testeconsultas.manytomany.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testeconsultas.manytomany.dto.ResidencialDTO;
import testeconsultas.manytomany.service.ResidencialService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/residencial-teste")
public class ResidencialController {

    private final ResidencialService residencialService;

    public ResidencialController(ResidencialService residencialService) {
        this.residencialService = residencialService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResidencialDTO> findById(@PathVariable Long id) {
        var residencial = residencialService.findById(id);
        return ResponseEntity.ok(residencial);
    }

    @PostMapping
    public ResponseEntity<ResidencialDTO> saveResidencial(
            @Valid
            @RequestBody ResidencialDTO dto
    ){
        ResidencialDTO residencialDTO =residencialService.saveResidencial(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((residencialDTO.getId())).toUri();
        return ResponseEntity.created(uri).body(residencialDTO);
    }

}
