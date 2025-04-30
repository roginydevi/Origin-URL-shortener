package com.origin.shorturl.service;

import com.origin.shorturl.domain.dto.url.UrlResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public interface UrlService {

    UrlResponseDTO shortenUrl(String longUrl, HttpServletRequest request);

    HttpHeaders redirect(String id);

    boolean isValidUrl(String url);

}
