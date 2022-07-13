package com.example.testproject.controller;

import com.example.testproject.data.dto.ShortUrlResponseDTO;
import com.example.testproject.service.ShortUrlService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/short-url")
public class ShortUrlController {

    private final Logger LOGGER = Logger.getGlobal();

    @Value("${test.project.short.url.id}")
    private String CLIENT_ID;

    @Value("${test.project.short.url.secret}")
    private String CLIENT_SECRET;

    ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping()
    public ShortUrlResponseDTO generateShortUrl(String orignalUrl) {

        LOGGER.info("[generateShortUrl] perform API. CLIENT_ID : " + CLIENT_ID + ", CLIENT_SECRET : " + CLIENT_SECRET);

        return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, orignalUrl);
    }

    @GetMapping()
    public ShortUrlResponseDTO getShortUrl(String originalUrl) {
        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO("ss", "ss");

        return shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    @PutMapping("/")
    public ShortUrlResponseDTO updateShortUrl(String originalUrl) {
        return null;
    }

    @DeleteMapping("/")
    public ShortUrlResponseDTO deleteShortUrl(String url) {
        return null;
    }



}
