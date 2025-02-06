package com.example.busticketbooking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="buses")
public class Bus extends BaseEntity {
    private String busNumber;
    private String routes;
    private int capacity;

    public Bus() {
    }
    // Private constructor for Builder pattern
    private Bus(Builder builder) {
        this.busNumber = builder.busNumber;
        this.routes = builder.routes;
        this.capacity = builder.capacity;
    }

    // Getters and Setters
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getRoutes() { return routes; }
    public void setRoutes(String routes) { this.routes = routes; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    // Builder Pattern
    public static class Builder {
        private String busNumber;
        private String routes;
        private int capacity;

        public Builder setBusNumber(String busNumber) {
            this.busNumber = busNumber;
            return this;
        }

        public Builder setRoutes(String routes) {
            this.routes = routes;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return capacity == bus.capacity &&
                Objects.equals(busNumber, bus.busNumber) &&
                Objects.equals(routes, bus.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busNumber, routes, capacity);
    }
    @Override
    public String toString() {
        return "Bus{id=" + id + ", busNumber='" + busNumber + "', routes='" + routes + "', capacity=" + capacity + "}";
    }
}
