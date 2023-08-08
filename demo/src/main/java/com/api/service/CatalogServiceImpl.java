package com.api.service;

import com.api.Exception.CatalogException;
import com.api.dto.CatalogDTO;
import com.api.entity.CatalogItem;
import com.api.repository.CatalogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public long addCatalog(CatalogDTO catalogDTO) throws CatalogException {
        CatalogItem catalog = new CatalogItem();
        catalog.setDescription(catalogDTO.getDescription());
        catalog.setName(catalogDTO.getName());
        CatalogItem createdCatalog = catalogRepository.save(catalog);
        return createdCatalog.getId();
    }
    @Override
    public CatalogDTO getCatalog(Integer catalogId) throws CatalogException {
        Optional<CatalogItem> optional = catalogRepository.findById(catalogId);
        CatalogItem catalog = optional.orElseThrow(() -> new CatalogException("Service.PRODUCT_NOT_FOUND"));
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setId(catalog.getId());
        catalogDTO.setName(catalog.getName());
        catalogDTO.setDescription(catalog.getDescription());
        return catalogDTO;
    }
    @Override
    public List<CatalogDTO> findAll() throws CatalogException
    {
        Iterable<CatalogItem> catalogs = catalogRepository.findAll();
        List<CatalogDTO>  catalogDTOs = new ArrayList<>();
        catalogs.forEach(catalog -> {
            CatalogDTO catalogDTO = new CatalogDTO();
            catalogDTO.setId(catalog.getId());
            catalogDTO.setName(catalog.getName());
            catalogDTO.setDescription(catalog.getDescription());
            catalogDTOs.add(catalogDTO);
        });
        if(catalogDTOs.isEmpty()){
            throw new CatalogException("Service.PRODUCTS_NOT_FOUND");
        }
        return catalogDTOs;
    }
    @Override
    public void updateCatalog(Integer catalogId, CatalogDTO catalogDTO) throws CatalogException {
        Optional<CatalogItem> optional = catalogRepository.findById(catalogId);
        optional.orElseThrow(() -> new CatalogException("Service.PRODUCT_NOT_FOUND"));
        catalogRepository.updateCatalog(catalogDTO.getName(), catalogDTO.getDescription(), catalogId);
    }
    @Override
    public void deleteCatalog(Integer catalogId)throws CatalogException {
        Optional<CatalogItem> optional = catalogRepository.findById(catalogId);
        optional.orElseThrow(() -> new CatalogException("Service.PRODUCT_NOT_FOUND"));
        catalogRepository.deleteById(catalogId);
    }
}
