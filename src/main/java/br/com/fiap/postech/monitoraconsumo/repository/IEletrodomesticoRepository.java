package br.com.fiap.postech.monitoraconsumo.repository;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface IEletrodomesticoRepository extends JpaRepository<Eletrodomestico, UUID> {
    @Query("""
            SELECT e
            FROM Eletrodomestico e
            WHERE e.nome = :nome OR e.modelo = :modelo OR e.potencia = :potencia
            """)
    Eletrodomestico getEletrodomestico(String nome, String modelo, BigDecimal potencia);
}
