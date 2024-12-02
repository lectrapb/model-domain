package com.app.infra.adapter.share.bus.event.local;

import com.app.domain.share.bus.event.EventBus;
import com.app.domain.share.common.model.DomainEvent;
import com.app.domain.thirdpartylimit.model.event.ThirdLimitCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
public class LocalEventBus implements EventBus {

    HashMap<String,  Class<? extends DomainEvent>> indexedEventBus;

    public LocalEventBus() {
        this.indexedEventBus = indexedEventBus = new HashMap<>();
        indexedEventBus.put("createThirdLimit", ThirdLimitCreateEvent.class);
    }

    @Override
    public Mono<Void> publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
        return Mono.empty();
    }

    private void publish(DomainEvent<?> domainEvent) {
        var data = domainEvent.getData();
        System.out.print("DATA EVENT");
        System.out.println(data);
    }


}
