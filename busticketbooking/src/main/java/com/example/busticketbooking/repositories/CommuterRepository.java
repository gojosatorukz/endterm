package com.example.busticketbooking.repositories;

import com.example.busticketbooking.models.Commuter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommuterRepository extends JpaRepository<Commuter, Long> {
    List<Commuter> findByNameContainingIgnoreCase(String name);
}
