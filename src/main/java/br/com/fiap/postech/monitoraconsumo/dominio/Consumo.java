package br.com.fiap.postech.monitoraconsumo.dominio;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name="tb_consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eletrodomestico_id")
    private Eletrodomestico eletrodomestico;

    private LocalDateTime inicioConsumo;

    private LocalDateTime terminoConsumo;

    public Consumo setId(UUID id) {
        this.id = id;
        return this;
    }

    public Consumo setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public Consumo setEletrodomestico(Eletrodomestico eletrodomestico) {
        this.eletrodomestico = eletrodomestico;
        return this;
    }

    public Consumo setInicioConsumo(LocalDateTime inicioConsumo) {
        this.inicioConsumo = inicioConsumo;
        return this;
    }

    public Consumo setTerminoConsumo(LocalDateTime terminoConsumo) {
        this.terminoConsumo = terminoConsumo;
        return this;
    }
}
