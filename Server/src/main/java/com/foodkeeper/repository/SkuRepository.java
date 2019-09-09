package com.foodkeeper.repository;

import com.foodkeeper.domain.Sku;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends CrudRepository<Sku, Long> {
    Sku findByBarcode(String barcode);
}
