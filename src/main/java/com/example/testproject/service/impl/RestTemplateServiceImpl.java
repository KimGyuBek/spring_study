package com.example.testproject.service.impl;

import com.example.testproject.dto.MemberDTO;
import com.example.testproject.service.RestTemplateService;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER = Logger.getGlobal();

    @Override
    public String getAroundHub() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/around-hub")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : " + responseEntity.getStatusCode());
//        LOGGER.info("status code : " + HttpStatus.ACCEPTED);
        LOGGER.info("body : " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/name")
                .queryParam("name", "DDANGDDANG") //key값, value
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : " + responseEntity.getStatusCode());
        LOGGER.info("body : " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("Falutre") //{name}에 값을 넣기 위해. 복수의 값을 넣어야 할 경우 ","를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : " + responseEntity.getStatusCode());
        LOGGER.info("body : " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/member")
                .queryParam("name", "ddangddang")
                .queryParam("email", "ddangddang@naver.com")
                .queryParam("organization", "zzaltoon")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("syumdang");
        memberDTO.setEmail("syumdang@naver.com");
        memberDTO.setOrganization("zzaltoon");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        LOGGER.info("status code : " + responseEntity.getStatusCode());
        LOGGER.info("body : " + responseEntity.getBody());

//        LOGGER.info(String.valueOf(responseEntity));

        return responseEntity;

    }


    @Override
    public ResponseEntity<MemberDTO> addHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("syumdang");
        memberDTO.setEmail("syumdang@naver.com");
        memberDTO.setOrganization("zzaltoon");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("around-header", "Aroundhub-Studio")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        LOGGER.info("status code : " + responseEntity.getStatusCode());
        LOGGER.info("body : " + responseEntity.getBody());

        return responseEntity;

    }
}

