package br.com.fiap.postech.monitoraconsumo.dominio;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Pessoa> pessoas = new ArrayList<>();

    public Usuario setId(UUID id) {
        this.id = id;
        return this;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Usuario setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
        return this;
    }

    public Usuario setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }
}
