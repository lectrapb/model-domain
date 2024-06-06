package com.app.infra.adapter.thirdpartylimit.database.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "management_custom_limits", schema = "public")
public class ThirdPartyLimitData {


    @Column("custom_limit_id")
    private String id;
    @Builder.Default
    @Column("custom_limit_flag")
    private String flag="client";
    @Column("custom_limit_document_type")
    private String documentType;
    @Column("custom_limit_document_number")
    private String documentNumber;
    @Column( "custom_limit_delegate_document_type")
    private String documentTypeDelegate;
    @Column( "custom_limit_delegate_document_number")
    private String documentNumberDelegate;
    @Column("custom_limit_acronym_partner")
    private String acronymPartner;
    @Column("custom_limit_periodicity")
    private String periodicity;
    @Column("custom_limit_amount")
    private double amount;
    @Column("custom_limit_number_operations")
    private long numberOperations;
    @Column("custom_limit_number_state")
    private String state;
}
