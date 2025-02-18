package com.tech.ps.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tech.ps.model.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
