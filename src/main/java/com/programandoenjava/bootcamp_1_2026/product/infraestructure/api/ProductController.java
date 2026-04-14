package com.programandoenjava.bootcamp_1_2026.product.infraestructure.api;

import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductRequest;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductResponse;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.mapper.ProductApiMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductApiMapper productApiMapper;

    public ProductController(ProductService productService, ProductApiMapper productApiMapper) {
        this.productService = productService;
        this.productApiMapper = productApiMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> response = products.stream()
                .map(productApiMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable Long id) {
        Product product = productService.getOneProduct(id);
        ProductResponse response = productApiMapper.toResponse(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ProductRequest request) {
        Product product = productApiMapper.toDomain(request);
        Product newProduct = productService.createProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {
        Product product = productApiMapper.toDomain(request);
        Product savedProduct = productService.updateProduct(product, id);
        return ResponseEntity.ok(productApiMapper.toResponse(savedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

