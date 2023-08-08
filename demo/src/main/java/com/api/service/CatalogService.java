package com.api.service
        ;
import com.api.Exception.CatalogException;
import com.api.dto.CatalogDTO;

import java.util.List;
public interface CatalogService {
    long addCatalog(CatalogDTO productDTO) throws CatalogException;
    CatalogDTO getCatalog(Integer productId) throws CatalogException;
    List<CatalogDTO> findAll() throws CatalogException;
    void updateCatalog(Integer productId, CatalogDTO productDTO) throws CatalogException;
    void deleteCatalog(Integer productId)throws CatalogException;
}
