package com.app.domain.thirdpartylimit.model;

import com.app.domain.thirdpartylimit.model.value.UUID;
import com.app.domain.thirdpartylimit.value.Customer;
import com.app.domain.thirdpartylimit.value.Delegate;
import com.app.domain.thirdpartylimit.value.StatusMonetaryLimit;
import lombok.Data;



@Data
public class ThirdPartyLimit {
    private UUID id;
    private Channel  channel;
    private Customer customer;
    private Delegate delegate;
    private StatusMonetaryLimit status;

    public ThirdPartyLimit(Customer customer, StatusMonetaryLimit status, Channel channel) {
        this.id = new UUID();
        this.customer = customer;
        this.status = status;
        this.channel = channel;
    }
}
