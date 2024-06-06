package com.app.infra.adapter.thirdpartylimit.database.infra;

import com.app.domain.thirdpartylimit.model.value.UUID;
import com.app.infra.adapter.thirdpartylimit.database.domain.ThirdPartyLimitData;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonetaryLimitRepository extends R2dbcRepository<ThirdPartyLimitData, String> {
}
