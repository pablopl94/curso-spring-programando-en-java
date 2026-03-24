package com.programandoenjava.bootcamp_1_2026.product.infraestructure.api;

import com.programandoenjava.bootcamp_1_2026.product.application.dto.in.ProductInput;
import com.programandoenjava.bootcamp_1_2026.product.application.dto.out.ProductOutput;
import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
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
        List<ProductOutput> output = productService.getAllProducts();
        List<ProductResponse> response = output.stream()
                .map(productApiMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable Long id) {
        ProductOutput output = productService.getProductById(id);
        ProductResponse response = productApiMapper.toResponse(output);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ProductInput input) {
        ProductOutput response = productService.createProduct(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody ProductInput product) {
        ProductOutput updateProduct = productService.updateProduct(id, product);
        ProductResponse response = productApiMapper.toResponse(updateProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

