package org.example.productcompositeservice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter postPutCounter;
    private final Counter getCounter;

    public MetricsService(MeterRegistry registry) {
        this.postPutCounter = Counter.builder("composite.requests.postput.count")
                .description("Nombre total de requêtes POST et PUT reçues par Product Composite Service")
                .register(registry);

        this.getCounter = Counter.builder("composite.requests.get.count")
                .description("Nombre total de requêtes GET reçues par Product Composite Service")
                .register(registry);
    }

    public void incrementPostPut() {
        postPutCounter.increment();
    }

    public void incrementGet() {
        getCounter.increment();
    }
}
