package com.example.testproject.data.dao.impl;

import com.example.testproject.data.dao.ShortUrlDAO;
import com.example.testproject.data.entity.ShortUrl;
import com.example.testproject.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDAOImpl implements ShortUrlDAO {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrl saveShortUrl(ShortUrl shortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrl getShortUrl(String originalUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrl getOriginalUrl(String shortUrl) {
        ShortUrl fondShortUrl = shortUrlRepository.findByUrl(shortUrl);
        return fondShortUrl;
    }

    @Override
    public ShortUrl updateShortUrl(ShortUrl newShortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(newShortUrl.getOrgUrl());

        foundShortUrl.setUrl(newShortUrl.getUrl());

        ShortUrl saveShorUrl = shortUrlRepository.save(foundShortUrl);

        return saveShorUrl;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrl);

    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}
