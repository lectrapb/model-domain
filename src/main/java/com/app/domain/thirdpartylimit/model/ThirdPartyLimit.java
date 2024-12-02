package com.app.domain.thirdpartylimit.model;

import com.app.domain.share.common.model.AggregateRoot;
import com.app.domain.thirdpartylimit.model.event.ThirdLimitCreateEvent;
import com.app.domain.thirdpartylimit.model.value.UUID;
import com.app.domain.thirdpartylimit.value.Customer;
import com.app.domain.thirdpartylimit.value.Delegate;
import com.app.domain.thirdpartylimit.value.StatusMonetaryLimit;
import lombok.Data;



@Data
public class ThirdPartyLimit extends AggregateRoot {
    private UUID id;
    private Channel  channel;
    private Customer customer;
    private Delegate delegate;
    private StatusMonetaryLimit status;

    public static ThirdPartyLimit create(Customer customer, StatusMonetaryLimit status, Channel channel) {
        var thirdPartyLimit = new ThirdPartyLimit(customer, status,channel);
        thirdPartyLimit.record(new ThirdLimitCreateEvent(thirdPartyLimit.id.value(),
                               channel.value(),
                               customer.number(),
                               status.getValue()));
        return thirdPartyLimit;
    }

    public ThirdPartyLimit(Customer customer, StatusMonetaryLimit status, Channel channel) {

        this.id = new UUID();
        this.customer = customer;
        this.status = status;
        this.channel = channel;
    }
}
