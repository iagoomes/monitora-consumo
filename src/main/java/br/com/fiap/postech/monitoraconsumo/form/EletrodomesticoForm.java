package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EletrodomesticoForm  {

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

    @JsonProperty
    @NotNull(message = "Id do usuário é um campo obrigatório e não pode estar em branco.")
    private UUID idUsuario;

    public EletrodomesticoForm(Eletrodomestico eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.idUsuario = eletrodomestico.getUsuario().getId();
    }

    public Eletrodomestico toEletrodomestico() {
        return new
                Eletrodomestico()
                    .setNome(nome)
                    .setModelo(modelo)
                    .setPotencia(potencia)
                    .setUsuario(new Usuario().setId(idUsuario));
    }

}
