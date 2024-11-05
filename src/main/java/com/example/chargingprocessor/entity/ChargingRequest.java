package com.example.chargingprocessor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CHARGING_REQUEST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChargingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    private int carId;
    private int chargingStationId;
    private int duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long remainingTime;
    private String BatteryStatus;
    private int percentComplete;
    private String status;

    public int getCarId() {
        return carId;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getChargingStationId() {
        return chargingStationId;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public String getBatteryStatus() {
        return BatteryStatus;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public String getStatus() {
        return status;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setChargingStationId(int chargingStationId) {
        this.chargingStationId = chargingStationId;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setBatteryStatus(String batteryStatus) {
        BatteryStatus = batteryStatus;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
