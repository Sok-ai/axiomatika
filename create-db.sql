CREATE TABLE IF NOT EXISTS public.client
(
    id                   SERIAL PRIMARY KEY,
    full_name            varchar(255) NOT NULL,
    passport             varchar(100) NOT NULL UNIQUE,
    gender               varchar(10),
    marital_status       varchar(50),
    residence_address    text,
    registration_address text,
    phone                varchar(20)  NOT NULL,
    employment_period    int4,
    "position"           varchar(100),
    organization_name    varchar(255),
    CONSTRAINT client_passport_key UNIQUE (passport)
);

-- Индексы для ускорения поиска клиентов
CREATE INDEX IF NOT EXISTS idx_client_phone ON public.client (phone);
CREATE INDEX IF NOT EXISTS idx_client_full_name ON public.client (full_name);
CREATE INDEX IF NOT EXISTS idx_client_passport ON public.client (passport);

CREATE TABLE IF NOT EXISTS public.loan_application
(
    id             SERIAL PRIMARY KEY,
    desired_amount numeric   NOT NULL,
    credit_purpose varchar(255),
    created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    client_id      INTEGER   NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES public.client (id)
);

CREATE TABLE IF NOT EXISTS public.loan_decision
(
    id              SERIAL PRIMARY KEY,
    approved        bool    NULL,
    term_in_months  int4    NULL,
    approved_amount numeric NULL,
    application_id  INTEGER NOT NULL UNIQUE,
    CONSTRAINT fk_application_decision FOREIGN KEY (application_id) REFERENCES public.loan_application (id)
);

CREATE TABLE IF NOT EXISTS public.loan_contract
(
    id             SERIAL PRIMARY KEY,
    signed_date    TIMESTAMP,
    signed         bool,
    application_id INTEGER NOT NULL UNIQUE,
    CONSTRAINT fk_application_contract FOREIGN KEY (application_id) REFERENCES public.loan_application (id)
);
