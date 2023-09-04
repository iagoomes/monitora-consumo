package br.com.fiap.postech.monitoraconsumo.service;

import br.com.fiap.postech.monitoraconsumo.dominio.Consumo;
import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.form.ConsumoForm;
import br.com.fiap.postech.monitoraconsumo.repository.IConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumoService {

    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private IConsumoRepository consumoRepository;

    public ConsumoForm registrar(ConsumoForm consumoForm) {

        Pessoa pessoa = pessoaService.findById(consumoForm.getIdPessoa());
        Eletrodomestico eletrodomestico = eletrodomesticoService.findById(consumoForm.getIdEletrodomestico());
        Consumo consumo =
                new Consumo()
                        .setPessoa(pessoa)
                        .setEletrodomestico(eletrodomestico)
                        .setInicioConsumo(consumoForm.getInicioConsumo())
                        .setTerminoConsumo(consumoForm.getTerminoConsumo());
        consumoRepository.save(consumo);
        consumoForm.setId(consumo.getId());
        return consumoForm;
    }
}
