package com.nivtek.angular.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivtek.angular.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

}
