package com.raca.ms_productos.models.Service.Impl;

import com.raca.ms_productos.models.Dao.IProductDao;
import com.raca.ms_productos.models.Service.IProductService;
import com.raca.ms_productos.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;


    @Override
    @Transactional
    public Product Save(Product product) {
        return productDao.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        if(productDao.existsById(id)){
            return productDao.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCost(Double min, Double max) {
        return productDao.findByCost(min,max);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if(productDao.existsById(id)){
            productDao.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
