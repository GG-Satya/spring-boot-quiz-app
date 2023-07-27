package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product){
		return repository.save(product);
	}
	
	public List<Product> saveAllProduct(List<Product> productList){
		return repository.saveAll(productList);
	}
	public List<Product> getAllProducts(){
		return repository.findAll();
	}
	public Product getProductById(Long id){
		return repository.findById(id).orElse(null);
	}
	public List<Product> getProductByName(String name){
		return repository.findByName(name);
	}
	public String deleteProduct(Long id) {
		repository.deleteById(id);
		return "Product with id = "+id+" has been removed from db";
	}
	public Product updateProduct(Product product) {
		Product currentProduct = repository.findById(product.getId()).orElse(null);
		currentProduct.setName(product.getName());
		currentProduct.setDescription(product.getDescription());
		return repository.save(currentProduct);
	}
}
