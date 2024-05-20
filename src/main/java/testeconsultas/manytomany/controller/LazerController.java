package testeconsultas.manytomany.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import testeconsultas.manytomany.dto.LazerDTO;
import testeconsultas.manytomany.service.LazerService;

import java.net.URI;

@RestController
@RequestMapping("/lazer-teste")
public class LazerController {

    private final LazerService lazerService;

    public LazerController(LazerService lazerService) {
        this.lazerService = lazerService;
    }

    @PostMapping
    public ResponseEntity<LazerDTO> saveLazer(
            @Valid
            @RequestBody LazerDTO dto
    ){
        LazerDTO lazerDTO = lazerService.saveLazer(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((lazerDTO.getId())).toUri();
        return ResponseEntity.created(uri).body(lazerDTO);
    }

}
