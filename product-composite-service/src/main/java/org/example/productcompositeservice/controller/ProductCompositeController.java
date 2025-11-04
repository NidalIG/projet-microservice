package org.example.productcompositeservice.controller;

import org.example.productcompositeservice.dto.ProductCompositeDTO;
import org.example.productcompositeservice.metrics.MetricsService;
import org.example.productcompositeservice.service.ProductCompositeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products-composite")
public class ProductCompositeController {

    private final ProductCompositeService compositeService;
    private final MetricsService metricsService;

    public ProductCompositeController(ProductCompositeService compositeService, MetricsService metricsService) {
        this.compositeService = compositeService;
        this.metricsService = metricsService;
    }

    @GetMapping("/{productId}")
    public ProductCompositeDTO getProductComposite(@PathVariable Long productId) {
        metricsService.incrementGet();
        return compositeService.getProductAggregate(productId);
    }

    @PostMapping
    public void createProductComposite(@RequestBody ProductCompositeDTO dto) {
        metricsService.incrementPostPut();

    }

    @PutMapping("/{productId}")
    public void updateProductComposite(@PathVariable Long productId, @RequestBody ProductCompositeDTO dto) {
        metricsService.incrementPostPut();
       
    }
}
