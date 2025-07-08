package com.rizwan.prdocut.Product;

import com.rizwan.prdocut.Product.handler.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
//    need to inject dependency of product Repoistory
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public Integer createProduct(@Valid ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productRepository.save(product);
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(@Valid  List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(productPurchaseRequest -> productPurchaseRequest.productId()).toList();
        var storeProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storeProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var storesRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();//empty now a time
        for(int i=0;i<storesRequest.size();i++) {
            var product = storeProducts.get(i);//this belongs to our database request
            var productRequest = storesRequest.get(i);//this belongs to our request object
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID::"+ productRequest.productId());
            }
            var newAvailabeQunantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailabeQunantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return null;

    }

    public ProductResponse findById(Integer prdouctId) {
        return productRepository.findById(prdouctId).map(productMapper::toProductResponse).orElseThrow(()->new EntityNotFoundException("product not found with the ID :: "+ prdouctId));

    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }
}
