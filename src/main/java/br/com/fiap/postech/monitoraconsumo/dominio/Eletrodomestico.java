package br.com.fiap.postech.monitoraconsumo.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "tb_eletrodomestico")
public class Eletrodomestico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String modelo;
    private BigDecimal potencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "usuario.id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "endereco.id")
    private Endereco endereco;

    public Eletrodomestico setId(UUID id) {
        this.id = id;
        return this;
    }

    public Eletrodomestico setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Eletrodomestico setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public Eletrodomestico setPotencia(BigDecimal potencia) {
        this.potencia = potencia;
        return this;
    }

    public Eletrodomestico setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Eletrodomestico setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
}
