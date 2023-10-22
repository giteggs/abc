package com.abc.ecommerce.service;

import com.abc.ecommerce.bo.CityLinkRequest;
import com.abc.ecommerce.bo.TrackingRequest;
import com.abc.ecommerce.dto.CityLinkData;
import com.abc.ecommerce.dto.CityLinkReq;
import com.abc.ecommerce.dto.CityLinkResponse;
import com.abc.ecommerce.dto.LogisticRequestDTO;
import com.abc.ecommerce.dto.LogisticResponseDTO;
import com.abc.ecommerce.dto.TrackingResponseDTO;
import com.abc.ecommerce.entity.CachedLogistic;
import com.abc.ecommerce.mapper.LogisticMapper;
import com.abc.ecommerce.repository.LogisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.abc.ecommerce.service.LogisticService.CITYLINK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogisticServiceTests {

    @Mock
    private LogisticMapper logisticMapper;

    @Mock
    private CityLinkService cityLinkService;

    @Mock
    private TrackingService trackingService;

    @Mock
    private LogisticRepository logisticRepository;

    @InjectMocks
    private LogisticService logisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testQueryLogisticInfo() {
        // Arrange
        LogisticRequestDTO requestDTO = new LogisticRequestDTO();
        CityLinkRequest cityLinkRequest = getCityLinkRequest();
        TrackingRequest request = new TrackingRequest();
        request.setWeight("10");
        request.setTo_postcode("50000");
        request.setFrom_postcode("51000");

        when(logisticMapper.toCityLinkRequest(requestDTO)).thenReturn(cityLinkRequest);
        when(logisticMapper.toTrackingRequest(requestDTO)).thenReturn(request);

        when(logisticRepository.findByHashKey(any())).thenReturn(null);

        CityLinkResponse cityLinkResponse = new CityLinkResponse();
        CityLinkReq req = new CityLinkReq();
        CityLinkData data = new CityLinkData();
        data.setRate(231.54);
        req.setData(data);
        cityLinkResponse.setReq(req);

        TrackingResponseDTO dto = new TrackingResponseDTO();
        dto.setPrice(100.00);

        CachedLogistic cachedLogistic = new CachedLogistic();
        cachedLogistic.setCourier(CITYLINK);
        cachedLogistic.setError("");
        cachedLogistic.setRate(231.54);


        when(logisticRepository.save(any())).thenReturn(cachedLogistic);
        when(cityLinkService.cityLinkLogisticRestCall(cityLinkRequest)).thenReturn(cityLinkResponse);
        when(trackingService.posLogisticRestCall(request)).thenReturn(dto);

        LogisticResponseDTO responseDTO = logisticService.queryLogisticInfo(requestDTO);

        assertEquals(231.54, responseDTO.getData().get(0).getRate());
        assertEquals("citylink", responseDTO.getData().get(0).getCourier());

    }

    @Test
    void testQueryLogisticInfofromCache() {
        // Arrange
        LogisticRequestDTO requestDTO = new LogisticRequestDTO();
        CityLinkRequest cityLinkRequest = getCityLinkRequest();
        TrackingRequest request = new TrackingRequest();
        request.setWeight("10");
        request.setTo_postcode("50000");
        request.setFrom_postcode("51000");

        when(logisticMapper.toCityLinkRequest(requestDTO)).thenReturn(cityLinkRequest);
        when(logisticMapper.toTrackingRequest(requestDTO)).thenReturn(request);

        List<CachedLogistic> list = new ArrayList<>();
        CachedLogistic cachedLogistic = new CachedLogistic();
        cachedLogistic.setCourier(CITYLINK);
        cachedLogistic.setError("");
        cachedLogistic.setRate(231.54);
        list.add(cachedLogistic);
        when(logisticRepository.findByHashKey(any())).thenReturn(list);

        LogisticResponseDTO responseDTO = logisticService.queryLogisticInfo(requestDTO);

        assertEquals(231.54, responseDTO.getData().get(0).getRate());
        assertEquals("citylink", responseDTO.getData().get(0).getCourier());

    }

    private static CityLinkRequest getCityLinkRequest() {
        CityLinkRequest cityLinkRequest = new CityLinkRequest();
        cityLinkRequest.setHeight(1);
        cityLinkRequest.setLength(1);
        cityLinkRequest.setDestination_country("IN");
        cityLinkRequest.setOrigin_country("MY");
        cityLinkRequest.setWidth(1);
        cityLinkRequest.setDestination_postcode("50000");
        cityLinkRequest.setOrigin_postcode("508213");
        cityLinkRequest.setOrigin_state("KL");
        cityLinkRequest.setDestination_state("Mayak");
        cityLinkRequest.setSelected_type(1);
        return cityLinkRequest;
    }
}
