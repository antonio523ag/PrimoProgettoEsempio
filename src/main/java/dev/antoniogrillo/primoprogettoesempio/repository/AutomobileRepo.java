package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileRepo extends JpaRepository<Automobile,Long> {
}
