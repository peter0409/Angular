package com.nivtek.angular.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivtek.angular.dao.ProductRepository;
import com.nivtek.angular.dao.SupplierRepository;
import com.nivtek.angular.entity.Product;
import com.nivtek.angular.entity.Supplier;
import com.nivtek.angular.exception.ResourceNotFoundException;


@Service
public class ProductService {
	
	 @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private SupplierRepository supplierRepository;

	    
	    public List<Product> findAll() {
	        return productRepository.findAll();
	    }

	  
	    public Optional<Product> findById(int productid) {
	        return productRepository.findById(productid);
	    }

	    public Product saveProduct(Product product) {
	        return productRepository.save(product);
	    }

	   
	    public Product createProduct(int supplierId, Product product) throws ResourceNotFoundException {
	        Set<Product> products = new HashSet<>();
	        Supplier supplier = new Supplier();

	        Optional<Supplier> supplierById =  supplierRepository.findById(supplierId);
	        if(!supplierById.isPresent()) {
	            throw new ResourceNotFoundException("Supplier with Id " + supplierId + " Doesn't Exist! ");
	        }

	        Supplier supplierinfo = supplierById.get();
	        // tie this supplierinfo to the product
	        product.setSupplier(supplierinfo);

	        Product savedProduct = productRepository.save(product);

	        //tie the product to supplier
	        products.add(savedProduct);
	        supplier.setProducts(products);

	        return savedProduct;
	    }

}
