package com.example.empresa.services;

import com.example.empresa.controller.SaleRequest;
import com.example.empresa.model.Customer;
import com.example.empresa.model.Product;
import com.example.empresa.model.Sale;
import com.example.empresa.model.SaleDetail;
import com.example.empresa.repository.CustomerRepository;
import com.example.empresa.repository.ProductRepository;
import com.example.empresa.repository.SaleRepository;
import com.example.empresa.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Sale createSale(Date saleDate, Long customerId, List<SaleRequest.SaleDetailRequest> saleDetailsRequest) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        Customer customer = customerOpt.get();

        Sale sale = new Sale();
        sale.setSaleDate(saleDate);
        sale.setCustomer(customer);

        for (SaleRequest.SaleDetailRequest detailRequest : saleDetailsRequest) {
            Optional<Product> productOpt = productRepository.findById(detailRequest.getProductId());
            if (productOpt.isEmpty()) {
                throw new RuntimeException("Product not found");
            }
            Product product = productOpt.get();

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(product);
            saleDetail.setQuantity(detailRequest.getQuantity());
            saleDetail.setSale(sale);

            sale.getSaleDetails().add(saleDetail);
        }

        return saleRepository.save(sale);
    }
}
