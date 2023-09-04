package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EletrodomesticoForm {

    @JsonProperty
    private UUID id;
    @JsonProperty
    @NotBlank(message = "Nome é um campo obrigatório e não pode estar em branco.")
    private String nome;
    @JsonProperty
    @NotBlank(message = "Modelo é um campo obrigatório e não pode estar em branco.")
    private String modelo;
    @JsonProperty
    @NotNull(message = "Potência é um campo obrigatório e não pode estar em branco.")
    @Positive(message = "Potência não pode ser negativa ou zero.")
    private BigDecimal potencia;

    public EletrodomesticoForm(Eletrodomestico eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
    }

    public Eletrodomestico toEletrodomestico() {
        return new
                Eletrodomestico()
                .setNome(nome)
                .setModelo(modelo)
                .setPotencia(potencia);
    }

}
