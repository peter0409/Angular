package com.nivtek.angular.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nivtek.angular.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	List<Product> findBySupplierId(int supplierId);

    Optional<Product> findByIdAndSupplierId(int id, int supplierId);

    @Query("select supplier.id from Product where id= :id")
    List<Object[]> findSupplierByProductId(@Param("id") int productId);

}
