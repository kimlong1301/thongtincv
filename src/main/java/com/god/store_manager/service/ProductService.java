package com.god.store_manager.service;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.product.Product;
import com.god.store_manager.repository.product.ProductCategoryRepository;
import com.god.store_manager.repository.product.ProductRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Page<Product> getAllProductWithPagination(@Nullable PaginationRequest paginationRequest){
        return productRepository.findAll(PaginationRequest.getPageRequest(paginationRequest,""));
    }
}
