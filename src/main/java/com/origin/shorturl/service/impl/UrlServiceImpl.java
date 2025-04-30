package com.origin.shorturl.service.impl;

import com.origin.shorturl.domain.dto.url.UrlResponseDTO;
import com.origin.shorturl.entities.UrlEntity;
import com.origin.shorturl.exceptions.InvalidUrlException;
import com.origin.shorturl.exceptions.UrlNotFoundException;
import com.origin.shorturl.repository.UrlRepository;
import com.origin.shorturl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    private static final String base_url = "http://localhost:8080";
    @Override
    public UrlResponseDTO shortenUrl(String longUrl, HttpServletRequest request) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, longUrl));

        String redirectUrl = base_url + "/" + id;

        return new UrlResponseDTO(longUrl, redirectUrl);
    }

    @Override
    public HttpHeaders redirect(String id) {
        UrlEntity url = urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return headers;
    }

    @Override
    public boolean isValidUrl(String url) {
            try {
                new java.net.URL(url);
                return true;
            } catch (Exception e) {
                throw new InvalidUrlException(" Invalid URL format: " + url);
            }
    }
}
