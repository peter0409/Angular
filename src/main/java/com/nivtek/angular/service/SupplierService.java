package com.nivtek.angular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivtek.angular.dao.SupplierRepository;
import com.nivtek.angular.entity.Supplier;


@Service
public class SupplierService {
	
	
	@Autowired
    private SupplierRepository supplierRepository;

    
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    
    public Optional<Supplier> findSupplier(int supplierid) {
        return supplierRepository.findById(supplierid);
    }

   
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

}
