package com.nivtek.angular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nivtek.angular.dao.ProductRepository;
import com.nivtek.angular.entity.Product;
import com.nivtek.angular.entity.Supplier;
import com.nivtek.angular.exception.ResourceNotFoundException;
import com.nivtek.angular.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class ProductController {
	
	
	@Autowired
    private ProductService productService;

    @Autowired
    private SupplierController supplierController;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable(value = "id") int productId) throws ResourceNotFoundException{
		
	
		
		return this.productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Supplier Not found with id : " + productId));
       
    }

	@PostMapping("products")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int productId,
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));

		product.setSupplier(productDetails.getSupplier());
		product.setDescription(productDetails.getDescription());
		product.setId(productDetails.getId());
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setQuantity(productDetails.getQuantity());

		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") int productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@GetMapping("/suppliers/{id}/products")
    public List<Product> getAllProductsBySupplierId(@PathVariable(value = "id") int id) {
        return productRepository.findBySupplierId(id);
    }

    @PostMapping("/suppliers/{id}/products")
    public Product createProduct(@PathVariable(value = "id") int supplierId, @RequestBody Product product) throws ResourceNotFoundException {
        return productService.createProduct(supplierId, product);
    }

}
