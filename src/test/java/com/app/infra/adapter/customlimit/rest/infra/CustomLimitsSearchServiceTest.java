package com.app.infra.adapter.customlimit.rest.infra;

import com.app.infra.adapter.customlimit.rest.domain.ConstantHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomLimitsSearchServiceTest {

    @Autowired
    private WebClient webClient;
    private CustomLimitsSearchService service;

    @BeforeEach
    void setUp() {
        service = new CustomLimitsSearchService(webClient);
    }

    @Test
    void findCustomLimits() {
        //Given
        Map<String, String> meta = new HashMap<>();
        meta.put(ConstantHeader.HEADER_CONSUMER_ACRONYM , "testChannel");
        meta.put(ConstantHeader.HEADER_MESSAGE_ID_SUID, "testUid");
        var url = "https://41173142-7d45-46ce-99f0-c725b5a8cec5.mock.pstmn.io/list&data=2";
        //var url = "https://af96e035-9a03-4ef7-91b1-4a0461eb238d.mock.pstmn.io/list&data=0";
        //When
        service.findCustomLimits(meta, url)
                .doOnNext(response -> {
                    // Asserts to verify the response
                    System.out.println(response);
                }).doOnError(throwable -> {
                    System.out.println(throwable);
                })
                .block();
        //Then
    }
}