--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: additional_information; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.additional_information (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    additional_field character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.additional_information OWNER TO postgres;

--
-- Name: aditional_information; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aditional_information (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    field_aditional character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.aditional_information OWNER TO postgres;

--
-- Name: branch_office; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.branch_office (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    address character varying(255) NOT NULL,
    business_name character varying(255) NOT NULL,
    establishment_code character varying(3) NOT NULL,
    movil_phone character varying(255),
    phone character varying(255)
);


ALTER TABLE public.branch_office OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    code character varying(20) NOT NULL,
    description character varying(200),
    name character varying(50) NOT NULL,
    predetermined boolean NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: cellar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cellar (
    id bigint NOT NULL,
    address character varying(200),
    code character varying(20) NOT NULL,
    description character varying(200),
    name character varying(50) NOT NULL,
    predetermined boolean NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    active boolean NOT NULL
);


ALTER TABLE public.cellar OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    business_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    identification character varying(255) NOT NULL,
    identification_type character varying(255) NOT NULL,
    is_client boolean NOT NULL,
    is_provider boolean NOT NULL,
    movil_phone character varying(255),
    phone character varying(255),
    tradename character varying(255)
);


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: detail_invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detail_invoice (
    id bigint NOT NULL,
    auxiliary_code character varying(255),
    description character varying(255) NOT NULL,
    discount numeric(21,2) NOT NULL,
    main_code character varying(255) NOT NULL,
    quantity numeric(21,2) NOT NULL,
    total numeric(21,2) NOT NULL,
    unit_price numeric(21,2) NOT NULL,
    invoice_client_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.detail_invoice OWNER TO postgres;

--
-- Name: electronic_document_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.electronic_document_info (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    access_key character varying(255) NOT NULL,
    business_name character varying(255) NOT NULL,
    emission_type character varying(255) NOT NULL,
    establishment_address character varying(255) NOT NULL,
    identification character varying(255) NOT NULL,
    is_keep_accounting boolean NOT NULL,
    address character varying(255) NOT NULL,
    special_taxpayer_number character varying(255),
    sri_environment character varying(255) NOT NULL,
    tradename character varying(255) NOT NULL
);


ALTER TABLE public.electronic_document_info OWNER TO postgres;

--
-- Name: emission_point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emission_point (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    emission_point_code character varying(3) NOT NULL,
    name character varying(20) NOT NULL,
    sequence character varying(9) NOT NULL,
    branch_office_id bigint NOT NULL
);


ALTER TABLE public.emission_point OWNER TO postgres;

--
-- Name: emission_point_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emission_point_user (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    is_main boolean NOT NULL,
    emission_point_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.emission_point_user OWNER TO postgres;

--
-- Name: invoice_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_client (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    access_key character varying(255) NOT NULL,
    business_name character varying(255) NOT NULL,
    emission_type character varying(255) NOT NULL,
    establishment_address character varying(255) NOT NULL,
    identification character varying(255) NOT NULL,
    is_keep_accounting boolean NOT NULL,
    address character varying(255) NOT NULL,
    special_taxpayer_number character varying(255),
    sri_enviroment character varying(255) NOT NULL,
    tradename character varying(255) NOT NULL,
    currency character varying(255) NOT NULL,
    date_issue timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    emission_point_code character varying(255) NOT NULL,
    establishment_code character varying(255) NOT NULL,
    identification_type character varying(255),
    phone character varying(255),
    receipt_type character varying(255) NOT NULL,
    sequence character varying(255) NOT NULL,
    tip numeric(21,2) NOT NULL,
    total numeric(21,2) NOT NULL,
    total_base_tax_ice numeric(21,2) NOT NULL,
    total_base_tax_iva numeric(21,2) NOT NULL,
    total_discount numeric(21,2) NOT NULL,
    total_tax_free numeric(21,2) NOT NULL,
    total_tax_ice numeric(21,2) NOT NULL,
    total_tax_iva numeric(21,2) NOT NULL,
    company_id bigint NOT NULL,
    electronic_document_id bigint,
    emission_point_id bigint NOT NULL,
    organization_id bigint NOT NULL
);


ALTER TABLE public.invoice_client OWNER TO postgres;

--
-- Name: invoice_client_additional_information; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_client_additional_information (
    additionals_information_id bigint NOT NULL,
    invoices_client_id bigint NOT NULL
);


ALTER TABLE public.invoice_client_additional_information OWNER TO postgres;

--
-- Name: invoice_client_aditional_information; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_client_aditional_information (
    aditionals_informations_id bigint NOT NULL,
    invoices_client_id bigint NOT NULL
);


ALTER TABLE public.invoice_client_aditional_information OWNER TO postgres;

--
-- Name: invoice_client_pruebas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_client_pruebas (
    aditionals_informations_id bigint NOT NULL,
    pruebas_id bigint NOT NULL
);


ALTER TABLE public.invoice_client_pruebas OWNER TO postgres;

--
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO postgres;

--
-- Name: jhi_date_time_wrapper; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_date_time_wrapper (
    id bigint NOT NULL,
    instant timestamp without time zone,
    local_date date,
    local_date_time timestamp without time zone,
    local_time time without time zone,
    offset_date_time timestamp without time zone,
    offset_time time without time zone,
    zoned_date_time timestamp without time zone
);


ALTER TABLE public.jhi_date_time_wrapper OWNER TO postgres;

--
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO postgres;

--
-- Name: jhi_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_evt_data OWNER TO postgres;

--
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(191),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(10),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    organization_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.jhi_user OWNER TO postgres;

--
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO postgres;

--
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id bigint NOT NULL,
    address character varying(200) NOT NULL,
    business_name character varying(200) NOT NULL,
    identification character varying(13) NOT NULL,
    keep_accounting boolean NOT NULL,
    logo character varying(255),
    mail character varying(100) NOT NULL,
    movil_phone character varying(13),
    phone character varying(13),
    special_taxpayer_number integer,
    token character varying(20) NOT NULL,
    tradename character varying(200) NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    emission_type character varying(10) NOT NULL,
    sri_environment character varying(20) NOT NULL,
    identification_type character varying(50) NOT NULL,
    CONSTRAINT organization_special_taxpayer_number_check CHECK ((special_taxpayer_number <= 99999))
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- Name: pay_way; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pay_way (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.pay_way OWNER TO postgres;

--
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    id bigint NOT NULL,
    amount numeric(21,2) NOT NULL,
    date timestamp without time zone NOT NULL,
    decription character varying(255),
    time_limit integer,
    time_unit character varying(255),
    invoice_id bigint,
    way_pay_id bigint NOT NULL,
    CONSTRAINT payment_time_limit_check CHECK ((time_limit >= 0))
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    attribute_1 character varying(100),
    attribute_2 character varying(100),
    attribute_3 character varying(100),
    auxiliary_code character varying(50),
    barcode character varying(50),
    main_code character varying(255) NOT NULL,
    name character varying(50) NOT NULL,
    price numeric(9,4) NOT NULL,
    product_type character varying(10),
    category_id bigint NOT NULL,
    tax_ice_id bigint,
    tax_iva_id bigint NOT NULL,
    active boolean NOT NULL,
    cellar_id bigint NOT NULL,
    unit_id bigint NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO postgres;

--
-- Name: sub_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sub_category (
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL,
    predetermined boolean NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.sub_category OWNER TO postgres;

--
-- Name: tax; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tax (
    id bigint NOT NULL,
    code character varying(20) NOT NULL,
    description character varying(200) NOT NULL,
    percentage real NOT NULL,
    tax_type character varying(10) NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    name character varying(200) NOT NULL,
    active boolean NOT NULL
);


ALTER TABLE public.tax OWNER TO postgres;

--
-- Name: tax_detail_invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tax_detail_invoice (
    id bigint NOT NULL,
    amount numeric(21,2) NOT NULL,
    code integer NOT NULL,
    percentage_code integer NOT NULL,
    rate integer NOT NULL,
    tax_base numeric(21,2) NOT NULL,
    details_invoice_id bigint
);


ALTER TABLE public.tax_detail_invoice OWNER TO postgres;

--
-- Name: tax_invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tax_invoice (
    id bigint NOT NULL,
    amount numeric(21,2) NOT NULL,
    code integer NOT NULL,
    percentage_code integer NOT NULL,
    tax_base numeric(21,2) NOT NULL,
    invoice_id bigint
);


ALTER TABLE public.tax_invoice OWNER TO postgres;

--
-- Name: unit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unit (
    id bigint NOT NULL,
    created_by character varying(50),
    created_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    active boolean NOT NULL,
    code character varying(20) NOT NULL,
    description character varying(200),
    name character varying(50) NOT NULL,
    predetermined boolean NOT NULL,
    unit_type character varying(255) NOT NULL
);


ALTER TABLE public.unit OWNER TO postgres;

--
-- Data for Name: additional_information; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.additional_information (id, created_by, created_date, last_modified_by, last_modified_date, additional_field, value) FROM stdin;
\.


--
-- Data for Name: aditional_information; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aditional_information (id, created_by, created_date, last_modified_by, last_modified_date, field_aditional, value) FROM stdin;
\.


--
-- Data for Name: branch_office; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.branch_office (id, created_by, created_date, last_modified_by, last_modified_date, address, business_name, establishment_code, movil_phone, phone) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, code, description, name, predetermined, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
2907	6wwwww	aa	aa	f	admin	2020-10-01 03:34:15.324	admin	2020-10-03 04:17:28.655
1	1as	CATEGORIA 1	Amigo's Market aa	t	\N	\N	admin	2020-10-03 15:41:28.323
\.


--
-- Data for Name: cellar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cellar (id, address, code, description, name, predetermined, created_by, created_date, last_modified_by, last_modified_date, active) FROM stdin;
2701	asdasd	1as	asdas	sasd	t	\N	\N	\N	\N	t
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company (id, address, business_name, email, identification, identification_type, is_client, is_provider, movil_phone, phone, tradename) FROM stdin;
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-08-23 14:49:04.18655	1	EXECUTED	8:b8c27d9dc8db18b5de87cdb8c38a416b	createSequence sequenceName=sequence_generator		\N	3.6.3	\N	\N	8212144146
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-08-23 14:49:04.291363	2	EXECUTED	8:477f075f156771f4b4a08c934b4457d8	createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...		\N	3.6.3	\N	\N	8212144146
20200426223500-1	jhipster	config/liquibase/changelog/20200426223500_added_entity_Tax.xml	2020-08-23 14:49:04.297574	3	EXECUTED	8:d41d8cd98f00b204e9800998ecf8427e	empty		\N	3.6.3	\N	\N	8212144146
20200426223500-1-relations	jhipster	config/liquibase/changelog/20200426223500_added_entity_Tax.xml	2020-08-23 14:49:04.299831	4	EXECUTED	8:d41d8cd98f00b204e9800998ecf8427e	empty		\N	3.6.3	\N	\N	8212144146
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	t	2020-10-03 11:56:06.47	DESKTOP-A37S0D0 (192.168.56.1)
\.


--
-- Data for Name: detail_invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detail_invoice (id, auxiliary_code, description, discount, main_code, quantity, total, unit_price, invoice_client_id, product_id) FROM stdin;
\.


--
-- Data for Name: electronic_document_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.electronic_document_info (id, created_by, created_date, last_modified_by, last_modified_date, access_key, business_name, emission_type, establishment_address, identification, is_keep_accounting, address, special_taxpayer_number, sri_environment, tradename) FROM stdin;
\.


--
-- Data for Name: emission_point; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emission_point (id, created_by, created_date, last_modified_by, last_modified_date, emission_point_code, name, sequence, branch_office_id) FROM stdin;
\.


--
-- Data for Name: emission_point_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emission_point_user (id, created_by, created_date, last_modified_by, last_modified_date, is_main, emission_point_id, user_id) FROM stdin;
\.


--
-- Data for Name: invoice_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice_client (id, created_by, created_date, last_modified_by, last_modified_date, access_key, business_name, emission_type, establishment_address, identification, is_keep_accounting, address, special_taxpayer_number, sri_enviroment, tradename, currency, date_issue, email, emission_point_code, establishment_code, identification_type, phone, receipt_type, sequence, tip, total, total_base_tax_ice, total_base_tax_iva, total_discount, total_tax_free, total_tax_ice, total_tax_iva, company_id, electronic_document_id, emission_point_id, organization_id) FROM stdin;
\.


--
-- Data for Name: invoice_client_additional_information; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice_client_additional_information (additionals_information_id, invoices_client_id) FROM stdin;
\.


--
-- Data for Name: invoice_client_aditional_information; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice_client_aditional_information (aditionals_informations_id, invoices_client_id) FROM stdin;
\.


--
-- Data for Name: invoice_client_pruebas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoice_client_pruebas (aditionals_informations_id, pruebas_id) FROM stdin;
\.


--
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: jhi_date_time_wrapper; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_date_time_wrapper (id, instant, local_date, local_date_time, local_time, offset_date_time, offset_time, zoned_date_time) FROM stdin;
\.


--
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
1451	admin	2020-09-11 01:20:13.543	AUTHENTICATION_SUCCESS
1501	admin	2020-09-11 01:24:01.356	AUTHENTICATION_SUCCESS
1551	admin	2020-09-11 01:29:39.41	AUTHENTICATION_SUCCESS
1601	admin	2020-09-11 01:32:50.578	AUTHENTICATION_SUCCESS
1651	admin	2020-09-11 01:39:29.95	AUTHENTICATION_SUCCESS
1652	admin	2020-09-11 01:40:05.443	AUTHENTICATION_FAILURE
1653	admin	2020-09-11 01:40:09.336	AUTHENTICATION_SUCCESS
1654	admin	2020-09-11 01:43:28.036	AUTHENTICATION_SUCCESS
1701	admin	2020-09-11 01:47:16.557	AUTHENTICATION_SUCCESS
1751	admin	2020-09-11 01:56:25.434	AUTHENTICATION_FAILURE
1752	admin	2020-09-11 01:56:28.533	AUTHENTICATION_SUCCESS
1801	admin	2020-09-15 23:38:07.977	AUTHENTICATION_FAILURE
1802	admin	2020-09-15 23:38:11.875	AUTHENTICATION_SUCCESS
1851	admin@localhost	2020-09-21 23:08:40.5	AUTHENTICATION_FAILURE
1852	admin	2020-09-21 23:08:45.504	AUTHENTICATION_FAILURE
1853	admin	2020-09-21 23:08:45.906	AUTHENTICATION_FAILURE
1854	admin	2020-09-21 23:08:49.167	AUTHENTICATION_SUCCESS
1901	admin@localhost	2020-09-23 02:04:34.046	AUTHENTICATION_FAILURE
1902	admin	2020-09-23 02:04:47.195	AUTHENTICATION_FAILURE
1903	admin	2020-09-23 02:04:49.851	AUTHENTICATION_SUCCESS
1951	admin	2020-09-23 23:21:18.787	AUTHENTICATION_FAILURE
1952	admin	2020-09-23 23:21:22.125	AUTHENTICATION_SUCCESS
2051	admin@localhost	2020-09-25 00:53:50.01	AUTHENTICATION_FAILURE
2052	admin	2020-09-25 00:54:00.1	AUTHENTICATION_SUCCESS
2101	admin	2020-09-27 14:25:10.084	AUTHENTICATION_FAILURE
2102	admin	2020-09-27 14:25:17.998	AUTHENTICATION_SUCCESS
2151	admin	2020-09-27 21:13:28.277	AUTHENTICATION_SUCCESS
2201	admin	2020-09-27 21:45:40.902	AUTHENTICATION_FAILURE
2202	admin@localhost	2020-09-27 21:45:53.641	AUTHENTICATION_FAILURE
2203	admin	2020-09-27 21:48:19.627	AUTHENTICATION_SUCCESS
2301	admin	2020-09-27 23:03:25.75	AUTHENTICATION_SUCCESS
2351	admin	2020-09-28 22:36:08.847	AUTHENTICATION_SUCCESS
2451	admin	2020-09-29 22:09:30.747	AUTHENTICATION_FAILURE
2452	admin	2020-09-29 22:09:34.458	AUTHENTICATION_SUCCESS
2551	andres.peralta@formos.com	2020-09-29 23:39:19.732	AUTHENTICATION_FAILURE
2552	admin	2020-09-29 23:39:22.78	AUTHENTICATION_FAILURE
2553	admin	2020-09-29 23:39:26.167	AUTHENTICATION_SUCCESS
2601	admin	2020-09-30 22:19:19.515	AUTHENTICATION_SUCCESS
2602	admin	2020-09-30 22:20:28.914	AUTHENTICATION_SUCCESS
2603	admin	2020-09-30 22:20:28.926	AUTHENTICATION_SUCCESS
2604	admin	2020-09-30 22:20:33.504	AUTHENTICATION_SUCCESS
2605	admin	2020-09-30 22:20:41.703	AUTHENTICATION_SUCCESS
2606	admin	2020-09-30 22:21:38.737	AUTHENTICATION_SUCCESS
2607	admin	2020-09-30 22:24:47.485	AUTHENTICATION_SUCCESS
2608	admin	2020-09-30 22:25:27.376	AUTHENTICATION_SUCCESS
2651	admin	2020-09-30 23:53:49.701	AUTHENTICATION_SUCCESS
2751	admin	2020-10-01 00:50:33.224	AUTHENTICATION_SUCCESS
2951	admin	2020-10-02 02:52:28.587	AUTHENTICATION_SUCCESS
3001	admin	2020-10-03 01:11:56.35	AUTHENTICATION_SUCCESS
3002	admin	2020-10-03 01:37:34.069	AUTHENTICATION_SUCCESS
3003	admin	2020-10-03 03:18:42.736	AUTHENTICATION_SUCCESS
3101	admin	2020-10-03 17:29:41.877	AUTHENTICATION_FAILURE
3102	admin	2020-10-03 17:30:01.175	AUTHENTICATION_SUCCESS
3151	admin	2020-10-05 18:38:23.542	AUTHENTICATION_SUCCESS
3152	admin	2020-10-05 18:38:29.461	AUTHENTICATION_SUCCESS
3153	admin	2020-10-05 18:38:38.988	AUTHENTICATION_SUCCESS
3154	admin	2020-10-05 18:39:12.217	AUTHENTICATION_SUCCESS
3155	admin	2020-10-05 18:39:56.08	AUTHENTICATION_SUCCESS
3156	admin	2020-10-05 18:40:27.809	AUTHENTICATION_SUCCESS
3157	admin	2020-10-05 18:40:38.311	AUTHENTICATION_SUCCESS
3158	admin	2020-10-05 18:40:48.072	AUTHENTICATION_SUCCESS
3159	admin	2020-10-05 18:41:11.894	AUTHENTICATION_SUCCESS
3160	admin	2020-10-05 18:41:11.91	AUTHENTICATION_SUCCESS
3161	admin	2020-10-05 18:41:36.136	AUTHENTICATION_SUCCESS
3162	admin	2020-10-05 19:03:09.21	AUTHENTICATION_SUCCESS
3201	admin	2020-10-05 22:07:22.792	AUTHENTICATION_SUCCESS
3301	admin	2020-10-06 02:10:56.684	AUTHENTICATION_SUCCESS
3401	admin	2020-10-06 03:26:42.096	AUTHENTICATION_SUCCESS
3501	admin	2020-10-07 03:31:19.424	AUTHENTICATION_SUCCESS
3502	admin	2020-10-07 03:31:19.424	AUTHENTICATION_SUCCESS
3551	admin	2020-10-08 14:41:24.883	AUTHENTICATION_SUCCESS
3552	admin	2020-10-08 16:10:13.075	AUTHENTICATION_SUCCESS
3553	admin	2020-10-09 13:14:20.521	AUTHENTICATION_SUCCESS
3554	admin	2020-10-09 17:06:50.841	AUTHENTICATION_SUCCESS
3701	admin	2020-10-09 17:47:57.157	AUTHENTICATION_SUCCESS
3751	admin	2020-10-09 20:36:07.995	AUTHENTICATION_SUCCESS
3801	admin	2020-10-09 20:42:28.513	AUTHENTICATION_SUCCESS
3802	admin	2020-10-09 20:43:02.356	AUTHENTICATION_SUCCESS
3851	admin	2020-10-09 20:55:33.056	AUTHENTICATION_SUCCESS
3901	admin	2020-10-09 20:56:57.071	AUTHENTICATION_SUCCESS
3951	admin	2020-10-09 21:01:23.501	AUTHENTICATION_SUCCESS
3952	admin	2020-10-09 21:01:56.674	AUTHENTICATION_SUCCESS
3953	admin	2020-10-09 21:06:04.996	AUTHENTICATION_SUCCESS
\.


--
-- Data for Name: jhi_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
1652	type	org.springframework.security.authentication.BadCredentialsException
1652	message	Bad credentials
1751	type	org.springframework.security.authentication.BadCredentialsException
1751	message	Bad credentials
1801	type	org.springframework.security.authentication.BadCredentialsException
1801	message	Bad credentials
1851	type	org.springframework.security.authentication.BadCredentialsException
1851	message	Bad credentials
1852	type	org.springframework.security.authentication.BadCredentialsException
1852	message	Bad credentials
1853	type	org.springframework.security.authentication.BadCredentialsException
1853	message	Bad credentials
1901	type	org.springframework.security.authentication.BadCredentialsException
1901	message	Bad credentials
1902	type	org.springframework.security.authentication.BadCredentialsException
1902	message	Bad credentials
1951	type	org.springframework.security.authentication.BadCredentialsException
1951	message	Bad credentials
2051	type	org.springframework.security.authentication.BadCredentialsException
2051	message	Bad credentials
2101	type	org.springframework.security.authentication.BadCredentialsException
2101	message	Bad credentials
2201	type	org.springframework.security.authentication.BadCredentialsException
2201	message	Bad credentials
2202	type	org.springframework.security.authentication.BadCredentialsException
2202	message	Bad credentials
2451	type	org.springframework.security.authentication.BadCredentialsException
2451	message	Bad credentials
2551	type	org.springframework.security.authentication.BadCredentialsException
2551	message	Bad credentials
2552	type	org.springframework.security.authentication.BadCredentialsException
2552	message	Bad credentials
3101	type	org.springframework.security.authentication.BadCredentialsException
3101	message	Bad credentials
\.


--
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date, organization_id) FROM stdin;
2	anonymoususer	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost		t	es	\N	\N	system	\N	\N	system	\N	1301
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	es	\N	\N	system	\N	\N	system	\N	1301
1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost		t	es	\N	\N	system	\N	\N	system	\N	1301
3	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost		t	es	\N	\N	system	\N	\N	system	\N	1301
\.


--
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jhi_user_authority (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
\.


--
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organization (id, address, business_name, identification, keep_accounting, logo, mail, movil_phone, phone, special_taxpayer_number, token, tradename, created_by, created_date, last_modified_by, last_modified_date, emission_type, sri_environment, identification_type) FROM stdin;
2501	asd	Andrés Peralta	31	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:29.927	admin	2020-09-29 22:14:29.927	NORMAL	TEST	RUC
2502	asd	Andrés Peralta	32	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:32.794	admin	2020-09-29 22:14:32.794	NORMAL	TEST	RUC
2503	asd	Andrés Peralta	33	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:35.86	admin	2020-09-29 22:14:35.86	NORMAL	TEST	RUC
2504	asd	Andrés Peralta	34	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:39.04	admin	2020-09-29 22:14:39.04	NORMAL	TEST	RUC
2256	asd	Andrés Peralta	3	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:11.3	admin	2020-09-27 22:45:11.3	NORMAL	TEST	RUC
2258	asd	Andrés Peralta	5	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:18.556	admin	2020-09-27 22:45:18.556	NORMAL	TEST	RUC
2259	asd	Andrés Peralta	6	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:21.431	admin	2020-09-27 22:45:21.431	NORMAL	TEST	RUC
2260	asd	Andrés Peralta	7	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:24.552	admin	2020-09-27 22:45:24.552	NORMAL	TEST	RUC
2261	asd	Andrés Peralta	8	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:27.103	admin	2020-09-27 22:45:27.103	NORMAL	TEST	RUC
2266	asd	Andrés Peralta	13	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:41.965	admin	2020-09-27 22:45:41.965	NORMAL	TEST	RUC
2268	asd	Andrés Peralta	15	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:48.211	admin	2020-09-27 22:45:48.211	NORMAL	TEST	RUC
2269	asd	Andrés Peralta	16	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:50.837	admin	2020-09-27 22:45:50.837	NORMAL	TEST	RUC
2270	asd	Andrés Peralta	17	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:53.317	admin	2020-09-27 22:45:53.317	NORMAL	TEST	RUC
2271	asd	Andrés Peralta	18	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:57.005	admin	2020-09-27 22:45:57.005	NORMAL	TEST	RUC
2272	asd	Andrés Peralta	19	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:59.524	admin	2020-09-27 22:45:59.524	NORMAL	TEST	RUC
2274	asd	Andrés Peralta	21	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 23:56:32.205	admin	2020-09-27 23:56:32.205	NORMAL	TEST	RUC
2505	asd	Andrés Peralta	35	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:41.869	admin	2020-09-29 22:14:41.869	NORMAL	TEST	RUC
2409	asd	Andrés Peralta	30	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 03:14:06.147	admin	2020-09-29 03:14:06.147	NORMAL	TEST	RUC
2507	asd	Andrés Peralta	37	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:51.96	admin	2020-09-29 22:14:51.96	NORMAL	TEST	RUC
2508	asd	Andrés Peralta	38	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:54.472	admin	2020-09-29 22:14:54.472	NORMAL	TEST	RUC
2509	asd	Andrés Peralta	39	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:14:57.028	admin	2020-09-29 22:14:57.028	NORMAL	TEST	RUC
2510	asd	Andrés Peralta	40	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 22:15:00.346	admin	2020-09-29 22:15:00.346	NORMAL	TEST	RUC
2408	asd	Andrés Peralta	1720238706003	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-29 03:14:02.429	admin	2020-09-29 23:14:39.985	NORMAL	TEST	RUC
2255	asd	Andrés Peralta	2	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:45:02.104	admin	2020-09-30 02:44:26.587	NORMAL	TEST	RUC
2252	asd	Andrés Peralta	1709903775001	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-27 22:44:33.338	admin	2020-10-02 03:14:32.026	NORMAL	TEST	RUC
1301	asd	Andrés Peralta	1720238706001	t	\N	gapatmej@gmail.com	234234	232423	5564	EXPORTED_CERTIFICATE	Quick Sales	admin	2020-09-09 02:04:01.363	admin	2020-10-06 02:12:18.974	NORMAL	TEST	IDENTIFICATION_CARD
\.


--
-- Data for Name: pay_way; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pay_way (id, code, description) FROM stdin;
3651	22	asdasd
3652	23	asdasd
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, amount, date, decription, time_limit, time_unit, invoice_id, way_pay_id) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, created_by, created_date, last_modified_by, last_modified_date, attribute_1, attribute_2, attribute_3, auxiliary_code, barcode, main_code, name, price, product_type, category_id, tax_ice_id, tax_iva_id, active, cellar_id, unit_id) FROM stdin;
\.


--
-- Data for Name: sub_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sub_category (id, code, description, name, predetermined, category_id) FROM stdin;
1	SUBCAT1	\N	SUBCAT1	t	1
\.


--
-- Data for Name: tax; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tax (id, code, description, percentage, tax_type, created_by, created_date, last_modified_by, last_modified_date, name, active) FROM stdin;
1	0	iMPUESTO 0%	0	IVA	\N	\N	admin	2020-10-06 03:42:58.946	0 %	t
3451	2	IMPUESTO CON EL 12	12	IVA	admin	2020-10-06 03:43:28.674	admin	2020-10-06 03:43:28.674	12 %	t
3452	3	iVA AL 14%	14	IVA	admin	2020-10-06 03:44:06.722	admin	2020-10-06 03:44:06.722	14%	t
2	3011	ICE Cigarrillos Rubios	150	ICE	\N	\N	admin	2020-10-06 03:44:59.62	Cigarrillos Rubios	t
\.


--
-- Data for Name: tax_detail_invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tax_detail_invoice (id, amount, code, percentage_code, rate, tax_base, details_invoice_id) FROM stdin;
\.


--
-- Data for Name: tax_invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tax_invoice (id, amount, code, percentage_code, tax_base, invoice_id) FROM stdin;
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.unit (id, created_by, created_date, last_modified_by, last_modified_date, active, code, description, name, predetermined, unit_type) FROM stdin;
1	admin	\N	admin	2020-10-06 02:38:29.947	t	UNI	adasda sdasdasdasdasd	Unidades	f	QUANTITIES
\.


--
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_generator', 4000, true);


--
-- Name: additional_information additional_information_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.additional_information
    ADD CONSTRAINT additional_information_pkey PRIMARY KEY (id);


--
-- Name: aditional_information aditional_information_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aditional_information
    ADD CONSTRAINT aditional_information_pkey PRIMARY KEY (id);


--
-- Name: branch_office branch_office_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch_office
    ADD CONSTRAINT branch_office_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: cellar cellar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cellar
    ADD CONSTRAINT cellar_pkey PRIMARY KEY (id);


--
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: detail_invoice detail_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detail_invoice
    ADD CONSTRAINT detail_invoice_pkey PRIMARY KEY (id);


--
-- Name: electronic_document_info electronic_document_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.electronic_document_info
    ADD CONSTRAINT electronic_document_info_pkey PRIMARY KEY (id);


--
-- Name: emission_point emission_point_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emission_point
    ADD CONSTRAINT emission_point_pkey PRIMARY KEY (id);


--
-- Name: emission_point_user emission_point_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emission_point_user
    ADD CONSTRAINT emission_point_user_pkey PRIMARY KEY (id);


--
-- Name: invoice_client_additional_information invoice_client_additional_information_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_additional_information
    ADD CONSTRAINT invoice_client_additional_information_pkey PRIMARY KEY (additionals_information_id, invoices_client_id);


--
-- Name: invoice_client_aditional_information invoice_client_aditional_information_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_aditional_information
    ADD CONSTRAINT invoice_client_aditional_information_pkey PRIMARY KEY (aditionals_informations_id, invoices_client_id);


--
-- Name: invoice_client invoice_client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT invoice_client_pkey PRIMARY KEY (id);


--
-- Name: invoice_client_pruebas invoice_client_pruebas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_pruebas
    ADD CONSTRAINT invoice_client_pruebas_pkey PRIMARY KEY (aditionals_informations_id, pruebas_id);


--
-- Name: jhi_authority jhi_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_authority
    ADD CONSTRAINT jhi_authority_pkey PRIMARY KEY (name);


--
-- Name: jhi_date_time_wrapper jhi_date_time_wrapper_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_date_time_wrapper
    ADD CONSTRAINT jhi_date_time_wrapper_pkey PRIMARY KEY (id);


--
-- Name: jhi_persistent_audit_event jhi_persistent_audit_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);


--
-- Name: jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: jhi_user_authority jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: jhi_user jhi_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT jhi_user_pkey PRIMARY KEY (id);


--
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- Name: pay_way pay_way_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pay_way
    ADD CONSTRAINT pay_way_pkey PRIMARY KEY (id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: sub_category sub_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT sub_category_pkey PRIMARY KEY (id);


--
-- Name: tax_detail_invoice tax_detail_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_detail_invoice
    ADD CONSTRAINT tax_detail_invoice_pkey PRIMARY KEY (id);


--
-- Name: tax_invoice tax_invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_invoice
    ADD CONSTRAINT tax_invoice_pkey PRIMARY KEY (id);


--
-- Name: tax tax_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax
    ADD CONSTRAINT tax_pkey PRIMARY KEY (id);


--
-- Name: product uk_25frqyyqffan7e2s94bvm7lsq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_25frqyyqffan7e2s94bvm7lsq UNIQUE (main_code);


--
-- Name: invoice_client uk_2gqqh05ymi93vjpyw9a1xsoks; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT uk_2gqqh05ymi93vjpyw9a1xsoks UNIQUE (access_key);


--
-- Name: pay_way uk_622i28d6f3lup5hrhrf837lpn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pay_way
    ADD CONSTRAINT uk_622i28d6f3lup5hrhrf837lpn UNIQUE (code);


--
-- Name: tax uk_7r0gk25l9lt9ki4i80c4lby0g; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax
    ADD CONSTRAINT uk_7r0gk25l9lt9ki4i80c4lby0g UNIQUE (code);


--
-- Name: invoice_client uk_935purnea81orsvm6ebk00w48; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT uk_935purnea81orsvm6ebk00w48 UNIQUE (electronic_document_id);


--
-- Name: unit uk_csaj16dw8fhmuj2q7n9qagq35; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT uk_csaj16dw8fhmuj2q7n9qagq35 UNIQUE (code);


--
-- Name: electronic_document_info uk_gox0oyjgygmhm59rqvq2oas9x; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.electronic_document_info
    ADD CONSTRAINT uk_gox0oyjgygmhm59rqvq2oas9x UNIQUE (access_key);


--
-- Name: unit unit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (id);


--
-- Name: jhi_user ux_user_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: jhi_user ux_user_login; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: category_code_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX category_code_uindex ON public.category USING btree (code);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);


--
-- Name: organization_identification_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX organization_identification_uindex ON public.organization USING btree (identification);


--
-- Name: tax_detail_invoice fk1jbp7nj195ndor52ds4kv3fdw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_detail_invoice
    ADD CONSTRAINT fk1jbp7nj195ndor52ds4kv3fdw FOREIGN KEY (details_invoice_id) REFERENCES public.detail_invoice(id);


--
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: invoice_client fk3tcad54c1dmlykspi50hbo823; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT fk3tcad54c1dmlykspi50hbo823 FOREIGN KEY (electronic_document_id) REFERENCES public.electronic_document_info(id);


--
-- Name: tax_invoice fk3ueg9wtawabqndfqwh6cmxf6y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_invoice
    ADD CONSTRAINT fk3ueg9wtawabqndfqwh6cmxf6y FOREIGN KEY (invoice_id) REFERENCES public.invoice_client(id);


--
-- Name: invoice_client_pruebas fk6epa5le0ievcns66dfyt15cgo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_pruebas
    ADD CONSTRAINT fk6epa5le0ievcns66dfyt15cgo FOREIGN KEY (aditionals_informations_id) REFERENCES public.aditional_information(id);


--
-- Name: invoice_client_aditional_information fk6gchijgourr52cxwgdqdpkw51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_aditional_information
    ADD CONSTRAINT fk6gchijgourr52cxwgdqdpkw51 FOREIGN KEY (aditionals_informations_id) REFERENCES public.aditional_information(id);


--
-- Name: invoice_client fk7gb9e6pby10a61y9qukben0ns; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT fk7gb9e6pby10a61y9qukben0ns FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: invoice_client_additional_information fk8p7k2ekt481406wgwenvkdkrk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_additional_information
    ADD CONSTRAINT fk8p7k2ekt481406wgwenvkdkrk FOREIGN KEY (additionals_information_id) REFERENCES public.additional_information(id);


--
-- Name: jhi_user_authority fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.jhi_authority(name);


--
-- Name: jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--
-- Name: product fk_product_cellar_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_cellar_id FOREIGN KEY (cellar_id) REFERENCES public.cellar(id);


--
-- Name: product fk_product_unit_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_unit_id FOREIGN KEY (unit_id) REFERENCES public.unit(id);


--
-- Name: jhi_user_authority fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- Name: invoice_client fkbqka5m5r325b630mga6k60nlm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT fkbqka5m5r325b630mga6k60nlm FOREIGN KEY (electronic_document_id) REFERENCES public.invoice_client(id);


--
-- Name: jhi_user fkc5mupsid5m6h7i4ak5snp6tsf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jhi_user
    ADD CONSTRAINT fkc5mupsid5m6h7i4ak5snp6tsf FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: invoice_client_aditional_information fkceohwi0xd5n9ehqoilu3x8lpj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_aditional_information
    ADD CONSTRAINT fkceohwi0xd5n9ehqoilu3x8lpj FOREIGN KEY (invoices_client_id) REFERENCES public.invoice_client(id);


--
-- Name: invoice_client fkd13petbb5dpfpcvwmfquirmoa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT fkd13petbb5dpfpcvwmfquirmoa FOREIGN KEY (emission_point_id) REFERENCES public.emission_point(id);


--
-- Name: product fkfpl9csxw81p228ddbl3ksf3sd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkfpl9csxw81p228ddbl3ksf3sd FOREIGN KEY (tax_ice_id) REFERENCES public.tax(id);


--
-- Name: invoice_client_additional_information fkhjv9a7lxfrdw1w7m4tq5yet5p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client_additional_information
    ADD CONSTRAINT fkhjv9a7lxfrdw1w7m4tq5yet5p FOREIGN KEY (invoices_client_id) REFERENCES public.invoice_client(id);


--
-- Name: payment fkhqoo90sv4sh78hhlsm2pbf57w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT fkhqoo90sv4sh78hhlsm2pbf57w FOREIGN KEY (invoice_id) REFERENCES public.invoice_client(id);


--
-- Name: payment fkiqfqomohrlqc4no63n9idpbsj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT fkiqfqomohrlqc4no63n9idpbsj FOREIGN KEY (way_pay_id) REFERENCES public.pay_way(id);


--
-- Name: emission_point fkju6fqrv3ue8wtjh10ku2j0xq5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emission_point
    ADD CONSTRAINT fkju6fqrv3ue8wtjh10ku2j0xq5 FOREIGN KEY (branch_office_id) REFERENCES public.branch_office(id);


--
-- Name: detail_invoice fkkbo9acylcqjtmct3xwdhhvwws; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detail_invoice
    ADD CONSTRAINT fkkbo9acylcqjtmct3xwdhhvwws FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: emission_point_user fkkf2lne62289eoinrtey0alblh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emission_point_user
    ADD CONSTRAINT fkkf2lne62289eoinrtey0alblh FOREIGN KEY (user_id) REFERENCES public.jhi_user(id);


--
-- Name: invoice_client fkkmgbaww0ms113dvp94naiaovt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_client
    ADD CONSTRAINT fkkmgbaww0ms113dvp94naiaovt FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: sub_category fkl65dyy5me2ypoyj8ou1hnt64e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT fkl65dyy5me2ypoyj8ou1hnt64e FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: emission_point_user fknh1luqseu0jc77jimjqm6be9o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emission_point_user
    ADD CONSTRAINT fknh1luqseu0jc77jimjqm6be9o FOREIGN KEY (emission_point_id) REFERENCES public.emission_point(id);


--
-- Name: detail_invoice fkptirejukjvlg7f434li6r74dg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detail_invoice
    ADD CONSTRAINT fkptirejukjvlg7f434li6r74dg FOREIGN KEY (invoice_client_id) REFERENCES public.invoice_client(id);


--
-- Name: product fksuo2dndaq9yls63qy8yj6m14t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fksuo2dndaq9yls63qy8yj6m14t FOREIGN KEY (tax_iva_id) REFERENCES public.tax(id);


--
-- PostgreSQL database dump complete
--

