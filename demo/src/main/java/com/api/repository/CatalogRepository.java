package com.api.repository;

import com.api.entity.CatalogItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogItem, Integer> {
    @Query("UPDATE CatalogItem p SET p.name = ?1 , p.description =?2 WHERE p.id = ?3")
    @Modifying
    @Transactional
    void updateCatalog(String catalogName, String catalogDesc, Integer catalogId);
}
