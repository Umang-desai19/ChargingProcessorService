package com.example.chargingprocessor.service;

import com.example.chargingprocessor.entity.ChargingRequest;
import com.example.chargingprocessor.entity.ChargingStation;
import com.example.chargingprocessor.repository.ChargingRequestRepository;
import com.example.chargingprocessor.repository.ChargingStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChargingUpdateServiceTest {

    @Mock
    private ChargingStationRepository chargingStationRepository;

    @Mock
    private ChargingRequestRepository chargingRequestRepository;

    @InjectMocks
    private ChargingUpdateService chargingUpdateService;

    @Test
    void testUpdateChargingRequestDetails_nullInput_verifyNoSaveOperation() {
        chargingUpdateService.updateChargingRequestDetails(null);

        Mockito.verify(chargingRequestRepository, Mockito.times(0))
                .save(Mockito.any(ChargingRequest.class));
        Mockito.verify(chargingStationRepository, Mockito.times(0))
                .save(Mockito.any(ChargingStation.class));
    }

    @Test
    void testUpdateChargingRequestDetails_RequestNotPresent_verifyNoSaveOperation() {

        Mockito.doReturn(Optional.empty()).when(chargingRequestRepository)
                        .findById(0);
        chargingUpdateService.updateChargingRequestDetails(new ChargingRequest());

        Mockito.verify(chargingRequestRepository, Mockito.times(0))
                .save(Mockito.any(ChargingRequest.class));
        Mockito.verify(chargingStationRepository, Mockito.times(0))
                .save(Mockito.any(ChargingStation.class));
    }

    @Test
    void testUpdateChargingRequestDetails_RequestPresent_verifySaveOperationCalled() {

        ChargingRequest request = new ChargingRequest();
        request.setRequestId(1);
        request.setDuration(10);
        request.setStatus("REQUEST_ACCEPTED");

        Mockito.doReturn(Optional.of(request)).when(chargingRequestRepository)
                .findById(1);
        chargingUpdateService.updateChargingRequestDetails(request);

        Mockito.verify(chargingRequestRepository, Mockito.times(1))
                .save(Mockito.any(ChargingRequest.class));
        Mockito.verify(chargingStationRepository, Mockito.times(0))
                .save(Mockito.any(ChargingStation.class));
    }

    @ParameterizedTest
    @CsvSource({"CHARGING_STARTED", "CHARGING_COMPLETED"})
    void testUpdateChargingRequestDetails_RequestPresent_verifySaveOperationCalled(String status) {

        ChargingRequest request = new ChargingRequest();
        request.setRequestId(1);
        request.setChargingStationId(1);
        request.setDuration(10);
        request.setStatus(status);

        Mockito.doReturn(Optional.of(request)).when(chargingRequestRepository)
                .findById(1);
        Mockito.doReturn(Optional.of(new ChargingStation())).when(chargingStationRepository)
                .findById(1);
        chargingUpdateService.updateChargingRequestDetails(request);

        Mockito.verify(chargingRequestRepository, Mockito.times(1))
                .save(Mockito.any(ChargingRequest.class));
        Mockito.verify(chargingStationRepository, Mockito.times(1))
                .save(Mockito.any(ChargingStation.class));
    }

}