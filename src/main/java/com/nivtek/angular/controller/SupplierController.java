package com.nivtek.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nivtek.angular.dao.SupplierRepository;
import com.nivtek.angular.entity.Supplier;
import com.nivtek.angular.exception.ResourceNotFoundException;
import com.nivtek.angular.service.SupplierService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
	
	@Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * @return List of Product Objects. If no Products are present then it returns empty list.
     */
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    /**
     * @param supplierId
     * @return Supplier Object. If no supplier is found; throw Supplier Not Found Exception!
     * @throws ResourceNotFoundException 
     */
    @GetMapping("{id}")
    public Supplier getSupplier(@PathVariable(value = "id") int supplierId) throws ResourceNotFoundException {
        return this.supplierService.findSupplier(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Supplier Not found with id : " + supplierId));
    }

    /**
     * @param supplier
     * @return Supplier Object we want to save
     */
    @PostMapping
    public Supplier saveSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping("{id}")
    public Supplier updateSupplier(@PathVariable(value = "id") int id, @RequestBody Supplier supplierPost) throws ResourceNotFoundException {
        return supplierRepository.findById(id).map(supplier -> {
            supplier.setName(supplierPost.getName());
            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new ResourceNotFoundException("id " + id + " Not Found!!! Sorry!!!"));
    }

}
