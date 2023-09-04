package br.com.fiap.postech.monitoraconsumo.repository;

import br.com.fiap.postech.monitoraconsumo.dominio.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IConsumoRepository extends JpaRepository<Consumo, UUID> {
}
