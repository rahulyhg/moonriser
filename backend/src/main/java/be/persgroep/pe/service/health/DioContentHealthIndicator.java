package be.persgroep.pe.service.health;

import be.persgroep.red.diocontent.api.client.DioContentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class DioContentHealthIndicator implements HealthIndicator {
    @Autowired
    private DioContentClient dioContentClient;

    @Override
    public Health health() {
        if (this.dioContentClient.isDioContentAvailable()) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}
