package com.app.application.thirdpartylimit;

import com.app.domain.customlimit.model.MonetaryLimitCreate;
import com.app.domain.share.bus.command.CommandData;
import com.app.domain.share.common.model.cqrs.ContextData;


public record ThirdLimitCreateCommand(MonetaryLimitCreate limitCreate, ContextData contextData) implements CommandData {
}
