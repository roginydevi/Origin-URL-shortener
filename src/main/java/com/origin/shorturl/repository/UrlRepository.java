package com.origin.shorturl.repository;

import com.origin.shorturl.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, String> {
}
