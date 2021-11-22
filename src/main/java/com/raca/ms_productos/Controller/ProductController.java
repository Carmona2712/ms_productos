package com.raca.ms_productos.Controller;

import com.raca.ms_productos.models.Service.Impl.ProductService;
import com.raca.ms_productos.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private Environment enviroment;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        try{
            return new ResponseEntity<>(this.productService.Save(product), HttpStatus.CREATED);
        }catch(DataAccessException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> listProducts = new ArrayList<>();
        try{
            listProducts = this.productService.findAll().stream().map(p -> {
                //p.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
                p.setPort(port);
                return p;
            }).collect(Collectors.toList());
        }catch(DataAccessException ex){
            System.out.println("");
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listProducts,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Product findById(@PathVariable("id") Long id){
        Product product = null;
        try{
            product =  productService.findById(id);
            //product.setPort( Integer.parseInt(enviroment.getProperty("local.server.port")));
            product.setPort(port);
            //Thread.sleep(2000L);
        }catch(Exception ex){
            System.err.println("");
        }

       /* boolean ok = false;
        if(!ok){
            throw new RuntimeException("No se pudo mostrar el producto");
        }*/

        return product;
    }



}
