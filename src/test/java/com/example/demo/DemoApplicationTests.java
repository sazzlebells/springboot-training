package com.example.demo;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	ProductService productService;

	@Test
	void contextLoads() {

	}

	@Test
	@DisplayName("Add new product")
	void testAddNewProduct(){
		ProductDto request = new ProductDto();
		request.setProductName("Voucher");
		request.setPrice(100L);
		request.setStock(100L);

		productService.add(request);
		//validasi data masuk db
		//
	}

}
