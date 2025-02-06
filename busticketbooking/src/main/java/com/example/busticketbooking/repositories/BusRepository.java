package com.example.busticketbooking.repositories;

import com.example.busticketbooking.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findAllByOrderByBusNumberAsc();
}
