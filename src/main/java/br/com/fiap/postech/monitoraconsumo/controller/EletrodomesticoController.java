package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.form.EletrodomesticoForm;
import br.com.fiap.postech.monitoraconsumo.service.EletrodomesticoService;
import br.com.fiap.postech.monitoraconsumo.service.UsuarioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService eletrodomesticoService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Collection<Eletrodomestico>> findAll() {
        var eletrodomesticos = eletrodomesticoService.findAll();
        return ResponseEntity.ok(eletrodomesticos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Eletrodomestico> findById(@PathVariable UUID id) {
        var eletrodomestico = eletrodomesticoService.findById(id);
        return ResponseEntity.ok(eletrodomestico);
    }

    @GetMapping("/buscar")
    public ResponseEntity<EletrodomesticoForm> findEletrodomestico(@RequestParam(name = "nome") String nome,
                                                                   @RequestParam(name = "modelo") String modelo,
                                                                   @RequestParam(name = "potencia") BigDecimal potencia) {

        return eletrodomesticoService.getEletrodomestico(nome, modelo, potencia);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EletrodomesticoForm eletrodomesticoForm) {
        var violacoesToMap = validar(eletrodomesticoForm);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var eletrodomestico = eletrodomesticoForm.toEletrodomestico();
        var usuario = usuarioService.findById(eletrodomestico.getUsuario().getId());
        eletrodomestico.setUsuario(usuario);
        var eletrodomesticoSaved = eletrodomesticoService.save(eletrodomestico);
        eletrodomesticoForm.setId(eletrodomesticoSaved.getId());
        var uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((eletrodomesticoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(eletrodomesticoForm);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody EletrodomesticoForm eletrodomesticoForm) {
        var violacoesToMap = validar(eletrodomesticoForm);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var eletrodomestico = eletrodomesticoForm.toEletrodomestico();
        var eletrodomesticoAdicionado = eletrodomesticoService.update(id, eletrodomestico);
        eletrodomesticoForm.setId(eletrodomesticoAdicionado.getId());
        return ResponseEntity.ok(eletrodomesticoForm);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        eletrodomesticoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return violacoesToMap;
    }

}
