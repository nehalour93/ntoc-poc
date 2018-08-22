package com.example.POC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.POC.model.Payment;
import com.example.POC.model.Product;
import com.example.POC.repository.PaymentRepository;
import com.example.POC.repository.ProductRepository;
import com.example.POC.repository.StoreRepository;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping(value = "/payment/{noOfProducts}", method = RequestMethod.PUT)
	public ResponseEntity<String> createUser(@PathVariable("noOfProducts") String noOfProducts,
			@RequestBody Payment payment) {
		String msg = null;
		int price = 0;
		int sum = 0;
		Product product = null;
		List<Product> products = productRepository.findAllByprodStoreId(payment.getStoreId());
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else if (noOfProducts.equalsIgnoreCase("single")) {
			product = productRepository.getByproductId(payment.getProductId());
			if (product != null) {
				price = product.getPrice();
				String pType = payment.getPaymentType();
				payment.setPrice(price);
				msg = "payment done of price " + price + " in " + pType;
				paymentRepository.save(payment);
			} else {
				msg = "product with productId " + payment.getProductId() + " does ot exist in store "
						+ payment.getStoreId();
			}

		} else if (noOfProducts.equalsIgnoreCase("multiple")) {
			for (Product p : products) {
				price = p.getPrice();
				sum = sum + price;
			}
			payment.setPrice(sum);
			String pType = payment.getPaymentType();
			msg = "payment done of price " + sum + " in " + pType;
			paymentRepository.save(payment);

		} else {
			msg = "Please specify no. of products";
		}

		return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	}
}
