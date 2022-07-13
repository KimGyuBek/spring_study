package com.example.testproject.service.impl;

import com.example.testproject.data.dao.ShortUrlDAO;
import com.example.testproject.data.dto.NaverUriDTO;
import com.example.testproject.data.dto.ShortUrlResponseDTO;
import com.example.testproject.data.entity.ShortUrl;
import com.example.testproject.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = Logger.getGlobal();
    private final ShortUrlDAO shortUrlDAO;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO) {
        this.shortUrlDAO = shortUrlDAO;
    }

    @Override
    public ShortUrlResponseDTO getShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[getShortUrl] request data : " + originalUrl);
        ShortUrl getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if(getShortUrlEntity == null) {
          LOGGER.warning("[getShortUrl] No Entity in Database.");
          ResponseEntity<NaverUriDTO> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

          orgUrl = responseEntity.getBody().getResult().getOrgUrl();
          shortUrl = responseEntity.getBody().getResult().getUrl();
        } else {
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO " + shortUrlResponseDTO.toString());

        return shortUrlResponseDTO;
    }

    @Override
    public ShortUrlResponseDTO generateShortUrl(String clientId, String clientSecret,
        String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : " + originalUrl);

        ResponseEntity<NaverUriDTO> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO(orgUrl, shortUrl);

        LOGGER.info("[generateShortUrl] ResponseDTO : " + shortUrlResponseDTO.toString());

        return shortUrlResponseDTO;
    }

    @Override
    public ShortUrlResponseDTO updateShortUrl(String clientId, String clientSecret,
        String originalUrl) {
        return null;
    }

    @Override
    public ShortUrlResponseDTO deleteByShortUrl(String shortUrl) {
        return null;
    }

    @Override
    public ShortUrlResponseDTO deleteByOriginalShortUrl(String originalUrl) {
        return null;
    }

    private ResponseEntity<NaverUriDTO> requestShortUrl(String clientId, String clientSecret, String originalUrl) {

        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : " + originalUrl);

        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/util/shorturl")
            .queryParam("url", originalUrl)
            .encode()
            .build()
            .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        LOGGER.info("url : " + uri.toString());

//        body 와 header를 조합해주는 객체
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        LOGGER.info("[requestShortUrl] HttpEntity : " + entity.toString() );
        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");

        ResponseEntity<NaverUriDTO> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, NaverUriDTO.class);//NaverUriDTO class로 리턴됨
        LOGGER.info("[requestShortUrl] reponseEntity : " + responseEntity.toString() );

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;


    }

}
