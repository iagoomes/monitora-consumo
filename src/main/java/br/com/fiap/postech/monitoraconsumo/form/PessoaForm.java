package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Parentesco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.dominio.Sexo;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PessoaForm {

    @JsonProperty
    private UUID id;
    @JsonProperty
    @NotBlank(message = "Nome é um campo obrigatório e não pode estar em branco.")
    private String nome;
    @JsonProperty
    @NotNull(message = "Data de nascimento é um campo obrigatório e não pode estar em branco.")
    @Past(message = "Data de nascimento deve ser menor que a data atual.")
    private LocalDate dataDeNascimento;
    @JsonProperty
    @NotNull(message = "Sexo é um campo obrigatório e não pode estar em branco.")
    private Sexo sexo;
    @JsonProperty
    @NotNull(message = "Parentesco é um campo obrigatório e não pode estar em branco.")
    private Parentesco parentesco;

    @JsonProperty
    @NotNull(message = "Id do usuário é um campo obrigatório e não pode estar em branco.")
    private UUID idUsuario;

    public PessoaForm(Pessoa pessoa) {
        this.setId(pessoa.getId());
        this.setNome(pessoa.getNome());
        this.setDataDeNascimento(pessoa.getDataDeNascimento());
        this.setSexo(pessoa.getSexo());
        this.setParentesco(pessoa.getParentesco());
        this.setIdUsuario(pessoa.getUsuario().getId());
    }

    public PessoaForm () {}

    public Pessoa toPessoa() {
        return new Pessoa()
                .setNome(nome)
                .setDataDeNascimento(dataDeNascimento)
                .setSexo(sexo)
                .setParentesco(parentesco)
                .setUsuario(new Usuario().setId(idUsuario));
    }

}
