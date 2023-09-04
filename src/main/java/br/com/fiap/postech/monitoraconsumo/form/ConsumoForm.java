package br.com.fiap.postech.monitoraconsumo.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConsumoForm {

    @JsonProperty
    private UUID id;

    @JsonProperty
    @NotNull(message = "Id da pessoa é um campo obrigatório e não pode estar em branco.")
    private UUID idPessoa;

    @JsonProperty
    @NotNull(message = "Id do eletrodoméstico é um campo obrigatório e não pode estar em branco.")
    private UUID idEletrodomestico;

    @JsonProperty
    @NotNull(message = "Data de início do consumo é um campo obrigatório e não pode estar em branco.")
    @Past
    LocalDateTime inicioConsumo;

    @JsonProperty
    @NotNull(message = "Data de término do consumo é um campo obrigatório e não pode estar em branco.")
    @Past
    LocalDateTime terminoConsumo;

}
