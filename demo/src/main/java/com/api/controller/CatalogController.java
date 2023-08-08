package com.api.controller;

import com.api.Exception.CatalogException;
import com.api.service.CatalogService;
import com.api.dto.CatalogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private Environment environment;

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogDTO>> getAllCatalogs() throws CatalogException {
        List<CatalogDTO> catalogs = catalogService.findAll();
        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @GetMapping("/catalog")
    public ResponseEntity<CatalogDTO> getCatalogById(@RequestParam Integer catalogId) throws CatalogException {
        CatalogDTO catalog = catalogService.getCatalog(catalogId);
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    @PostMapping("/catalog")
    public ResponseEntity<String> addCatalog(@RequestBody CatalogDTO catalogDTO) throws CatalogException {
        long catalogId = catalogService.addCatalog(catalogDTO);
        String message = environment.getProperty("API.INSERT_SUCCESS") + catalogId;
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/catalog")
    public ResponseEntity<String> updateCatalog(@RequestParam Integer catalogId, @RequestBody CatalogDTO catalogDTO) throws CatalogException {
        catalogService.updateCatalog(catalogId, catalogDTO);
        String message = environment.getProperty("API.UPDATE_SUCCESS");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/catalog")
    public ResponseEntity<String> deleteCatalog(@RequestParam Integer catalogId) throws CatalogException {
        catalogService.deleteCatalog(catalogId);
        String message = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
