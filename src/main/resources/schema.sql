-- DROP TABLE IF EXISTS management_custom_limits;
-- CREATE TABLE IF NOT EXISTS management_custom_limits (custom_limit_id UUID PRIMARY KEY,custom_limit_flag VARCHAR(254),custom_limit_document_type VARCHAR(254),custom_limit_document_number VARCHAR(254),custom_limit_delegate_document_type VARCHAR(254),custom_limit_delegate_document_number VARCHAR(254),custom_limit_acronym_partner VARCHAR(254),custom_limit_periodicity VARCHAR(254),custom_limit_amount NUMERIC,custom_limit_number_operations VARCHAR(254),custom_limit_number_state VARCHAR(254));


DROP TABLE IF EXISTS management_custom_limits;

CREATE TABLE IF NOT EXISTS management_custom_limits (
    custom_limit_id VARCHAR(254) PRIMARY KEY,
    custom_limit_flag VARCHAR(254),
    custom_limit_document_type VARCHAR(254),
    custom_limit_document_number VARCHAR(254),
    custom_limit_delegate_document_type VARCHAR(254),
    custom_limit_delegate_document_number VARCHAR(254),
    custom_limit_acronym_partner VARCHAR(254),
    custom_limit_periodicity VARCHAR(254),
    custom_limit_amount NUMERIC,
    custom_limit_number_operations VARCHAR(254),
    custom_limit_number_state VARCHAR(254)
);
