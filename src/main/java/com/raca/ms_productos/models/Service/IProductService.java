package com.raca.ms_productos.models.Service;

import com.raca.ms_productos.models.entity.Product;

import java.util.List;

public interface IProductService {

    public Product Save(Product product);

    public List<Product> findAll();

    public Product findById(Long id);

    public List<Product> findByCost(Double min,Double max);

    public boolean delete(Long id);


}
