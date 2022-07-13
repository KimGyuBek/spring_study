package com.example.testproject.service;

import com.example.testproject.data.dto.ShortUrlResponseDTO;

public interface ShortUrlService {

    ShortUrlResponseDTO getShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDTO generateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDTO updateShortUrl(String clientId, String clientSecret, String originalUrl);

    void deleteShorUrl(String url);
}
