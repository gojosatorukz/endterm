package com.example.busticketbooking.services;

import com.example.busticketbooking.models.Commuter;
import com.example.busticketbooking.repositories.CommuterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommuterService {
    private final CommuterRepository commuterRepository;

    public CommuterService(CommuterRepository commuterRepository) {
        this.commuterRepository = commuterRepository;
    }

    // Create a new Commuter
    public Commuter addCommuter(Commuter commuter) {
        return commuterRepository.save(commuter);
    }

    // Get all commuters
    public List<Commuter> getAllCommuters() {
        return commuterRepository.findAll();
    }

    // Get a Commuter by ID
    public Optional<Commuter> getCommuterById(Long id) {
        return commuterRepository.findById(id);
    }

    // Search Commuters by Name
    public List<Commuter> searchByName(String name) {
        return commuterRepository.findByNameContainingIgnoreCase(name);
    }

    // Update a Commuter
    public Commuter updateCommuter(Long id, Commuter updatedCommuter) {
        return commuterRepository.findById(id).map(commuter -> {
            commuter.setName(updatedCommuter.getName());
            commuter.setTicketNumber(updatedCommuter.getTicketNumber());
            return commuterRepository.save(commuter);
        }).orElse(null);
    }

    // Delete a Commuter
    public boolean deleteCommuter(Long id) {
        if (commuterRepository.existsById(id)) {
            commuterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
