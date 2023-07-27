package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	@PostMapping("/addProduct")
//	public Product addProduct(@RequestBody Product product){
//		System.err.println(product);
//		return productService.saveProduct(product);
//		
//	}
	@PostMapping("/addAllProduct")
	public List<Product> addAllProduct(@RequestBody List<Product> productList){
		return productService.saveAllProduct(productList);
		
	}
	@GetMapping("/products")
	public List<Product> findAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	@GetMapping("/productsByName/{name}")
	public List<Product> findProductByName(@PathVariable String name){
		return productService.getProductByName(name);
	}
	
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable long id) {
		return productService.deleteProduct(id);
	}
}
