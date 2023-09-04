package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.form.ConsumoForm;
import br.com.fiap.postech.monitoraconsumo.form.EnderecoForm;
import br.com.fiap.postech.monitoraconsumo.service.ConsumoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity registrarConsumo(@RequestBody ConsumoForm consumoForm) {
        var violacoesToMap = validar(consumoForm);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        consumoForm = consumoService.registrar(consumoForm);
        var uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((consumoForm.getId())).toUri();
        return ResponseEntity.created(uri).body(consumoForm);
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return violacoesToMap;
    }

}
