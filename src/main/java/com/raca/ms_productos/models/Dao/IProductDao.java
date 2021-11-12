package com.raca.ms_productos.models.Dao;

import com.raca.ms_productos.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductDao extends JpaRepository<Product,Long> {

    @Query("from Product  p where p.cost>=:min and p.cost <= :max")
    public List<Product> findByCost(Double min, Double max);

}
