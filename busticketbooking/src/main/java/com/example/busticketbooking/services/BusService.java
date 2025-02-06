package com.example.busticketbooking.services;

import com.example.busticketbooking.models.Bus;
import com.example.busticketbooking.repositories.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // Create a new Bus
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Get all buses sorted by bus number
    public List<Bus> getAllBusesSorted() {
        return busRepository.findAllByOrderByBusNumberAsc();
    }

    // Get a Bus by ID
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    // Update a Bus
    public Bus updateBus(Long id, Bus updatedBus) {
        return busRepository.findById(id).map(bus -> {
            bus.setBusNumber(updatedBus.getBusNumber());
            bus.setRoutes(updatedBus.getRoutes());
            bus.setCapacity(updatedBus.getCapacity());
            return busRepository.save(bus);
        }).orElse(null);
    }

    // Delete a Bus
    public boolean deleteBus(Long id) {
        if (busRepository.existsById(id)) {
            busRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
