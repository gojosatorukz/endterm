package com.example.busticketbooking.controllers;

import com.example.busticketbooking.models.Bus;
import com.example.busticketbooking.services.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buses")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    // Create a new Bus
    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.addBus(bus));
    }

    // Get all buses sorted by bus number
    @GetMapping
    public ResponseEntity<List<Bus>> getAllSorted() {
        return ResponseEntity.ok(busService.getAllBusesSorted());
    }

    // Get a Bus by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        Optional<Bus> bus = busService.getBusById(id);
        return bus.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Bus
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus updatedBus) {
        Bus bus = busService.updateBus(id, updatedBus);
        return bus != null ? ResponseEntity.ok(bus) : ResponseEntity.notFound().build();
    }

    // Delete a Bus
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        return busService.deleteBus(id)
                ? ResponseEntity.ok("Bus deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
