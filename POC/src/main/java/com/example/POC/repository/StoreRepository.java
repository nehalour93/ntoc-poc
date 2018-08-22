package com.example.POC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.POC.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Store findBystoreId(int storeId);

	List<Store> findAll();
	
	List<Store> findAllBystoreId(List<Store> list);

	Store save(Store persisted);

	Store getByStoreId(int id);
	

}
