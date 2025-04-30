package com.origin.shorturl.controller;

import com.origin.shorturl.domain.dto.url.UrlRequestDTO;
import com.origin.shorturl.domain.dto.url.UrlResponseDTO;
import com.origin.shorturl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponseDTO> shortenUrl(@RequestBody UrlRequestDTO data, HttpServletRequest request){
        String longUrl = data.url();
        if (!urlService.isValidUrl(longUrl)){

        }
        return ResponseEntity.ok(urlService.shortenUrl(longUrl,request));
    }


    @GetMapping("/{var1}/{var2}/{var3}/{var4}")
    public String longUrl(HttpServletRequest request) {
        return "Redirected Successfully to OriginalUrl " + request.getRequestURL();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        HttpHeaders headers = urlService.redirect(id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
