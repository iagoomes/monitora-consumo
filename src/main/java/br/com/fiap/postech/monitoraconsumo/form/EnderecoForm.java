package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EnderecoForm {

    @JsonProperty
    private UUID id;
    @JsonProperty
    @NotBlank(message = "Rua é um campo obrigatório e não pode estar em branco.")
    private String rua;
    @JsonProperty
    @NotNull(message = "Número não pode ser nulo.")
    private Integer numero;
    @JsonProperty
    @NotBlank(message = "Bairro é um campo obrigatório e não pode estar em branco.")
    private String bairro;
    @JsonProperty
    @NotBlank(message = "Cidade é um campo obrigatório e não pode estar em branco.")
    private String cidade;
    @JsonProperty
    @NotBlank(message = "Estado é um campo obrigatório e não pode estar em branco.")
    private String estado;

    @JsonProperty
    @NotNull(message = "Id do usuário é um campo obrigatório e não pode estar em branco.")
    private UUID idUsuario;

    private List<Pessoa> pessoas;

    public EnderecoForm(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.setIdUsuario(endereco.getUsuario().getId());
        this.pessoas = endereco.getPessoas();
    }

    public EnderecoForm() {}

    public Endereco toEndereco() {
        return new Endereco()
                    .setRua(rua)
                    .setBairro(bairro)
                    .setCidade(cidade)
                    .setEstado(estado)
                    .setNumero(numero)
                    .setUsuario(new Usuario().setId(idUsuario));
    }

}
