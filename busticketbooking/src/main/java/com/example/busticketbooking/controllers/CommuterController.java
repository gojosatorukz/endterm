package com.example.busticketbooking.controllers;

import com.example.busticketbooking.models.Commuter;
import com.example.busticketbooking.services.CommuterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commuters")
public class CommuterController {
    private final CommuterService commuterService;

    public CommuterController(CommuterService commuterService) {
        this.commuterService = commuterService;
    }

    // Create a new Commuter
    @PostMapping
    public ResponseEntity<Commuter> addCommuter(@RequestBody Commuter commuter) {
        return ResponseEntity.ok(commuterService.addCommuter(commuter));
    }

    // Get all Commuters
    @GetMapping
    public ResponseEntity<List<Commuter>> getAllCommuters() {
        return ResponseEntity.ok(commuterService.getAllCommuters());
    }

    // Get a Commuter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commuter> getCommuterById(@PathVariable Long id) {
        Optional<Commuter> commuter = commuterService.getCommuterById(id);
        return commuter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Search Commuters by Name
    @GetMapping("/search")
    public ResponseEntity<List<Commuter>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(commuterService.searchByName(name));
    }

    // Update a Commuter
    @PutMapping("/{id}")
    public ResponseEntity<Commuter> updateCommuter(@PathVariable Long id, @RequestBody Commuter updatedCommuter) {
        Commuter commuter = commuterService.updateCommuter(id, updatedCommuter);
        return commuter != null ? ResponseEntity.ok(commuter) : ResponseEntity.notFound().build();
    }

    // Delete a Commuter
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommuter(@PathVariable Long id) {
        return commuterService.deleteCommuter(id)
                ? ResponseEntity.ok("Commuter deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
