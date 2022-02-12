--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2022-02-12 23:11:53 MSK

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

--
-- TOC entry 6 (class 2615 OID 24947)
-- Name: main; Type: SCHEMA; Schema: -; Owner: qr_game
--

CREATE SCHEMA main;


ALTER SCHEMA main OWNER TO qr_game;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3317 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 208 (class 1259 OID 25017)
-- Name: event_seq; Type: SEQUENCE; Schema: main; Owner: qr_game
--

CREATE SEQUENCE main.event_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.event_seq OWNER TO qr_game;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 24948)
-- Name: event_table; Type: TABLE; Schema: main; Owner: qr_game
--

CREATE TABLE main.event_table (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    city character varying(40) NOT NULL,
    date date NOT NULL,
    area character varying NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    qr_path character varying(300) NOT NULL
);


ALTER TABLE main.event_table OWNER TO qr_game;

--
-- TOC entry 209 (class 1259 OID 25084)
-- Name: personal_password_seq; Type: SEQUENCE; Schema: main; Owner: qr_game
--

CREATE SEQUENCE main.personal_password_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.personal_password_seq OWNER TO qr_game;

--
-- TOC entry 210 (class 1259 OID 25201)
-- Name: personal_password_table; Type: TABLE; Schema: main; Owner: qr_game
--

CREATE TABLE main.personal_password_table (
    id bigint NOT NULL,
    qr_id bigint NOT NULL,
    password character varying(30) NOT NULL,
    quantity integer DEFAULT 1
);


ALTER TABLE main.personal_password_table OWNER TO qr_game;

--
-- TOC entry 207 (class 1259 OID 25015)
-- Name: qr_seq; Type: SEQUENCE; Schema: main; Owner: qr_game
--

CREATE SEQUENCE main.qr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.qr_seq OWNER TO qr_game;

--
-- TOC entry 202 (class 1259 OID 24957)
-- Name: qr_table; Type: TABLE; Schema: main; Owner: qr_game
--

CREATE TABLE main.qr_table (
    id bigint NOT NULL,
    qr_suffix character varying(100) NOT NULL,
    event_id bigint NOT NULL,
    qr_url character varying(300) NOT NULL,
    team boolean,
    team_for_front boolean,
    deleted boolean DEFAULT false,
    general_default_resource_people_count bigint,
    default_resource character varying,
    default_resource_people_count bigint,
    group_access boolean DEFAULT false,
    group_password character varying(30),
    personal_access boolean DEFAULT false
);


ALTER TABLE main.qr_table OWNER TO qr_game;

--
-- TOC entry 203 (class 1259 OID 24971)
-- Name: resouces_table; Type: TABLE; Schema: main; Owner: qr_game
--

CREATE TABLE main.resouces_table (
    id bigint NOT NULL,
    qr_id bigint NOT NULL,
    qr_suffix character varying NOT NULL,
    infinity boolean,
    url character varying,
    people_count bigint,
    came_people_count bigint,
    deleted boolean
);


ALTER TABLE main.resouces_table OWNER TO qr_game;

--
-- TOC entry 206 (class 1259 OID 25013)
-- Name: resources_seq; Type: SEQUENCE; Schema: main; Owner: qr_game
--

CREATE SEQUENCE main.resources_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.resources_seq OWNER TO qr_game;

--
-- TOC entry 205 (class 1259 OID 25011)
-- Name: users_seq; Type: SEQUENCE; Schema: main; Owner: qr_game
--

CREATE SEQUENCE main.users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE main.users_seq OWNER TO qr_game;

--
-- TOC entry 204 (class 1259 OID 24984)
-- Name: users_table; Type: TABLE; Schema: main; Owner: qr_game
--

CREATE TABLE main.users_table (
    id bigint NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(100) NOT NULL,
    role character varying(30) NOT NULL
);


ALTER TABLE main.users_table OWNER TO qr_game;

--
-- TOC entry 3302 (class 0 OID 24948)
-- Dependencies: 201
-- Data for Name: event_table; Type: TABLE DATA; Schema: main; Owner: qr_game
--

COPY main.event_table (id, name, city, date, area, deleted, qr_path) FROM stdin;
\.


--
-- TOC entry 3311 (class 0 OID 25201)
-- Dependencies: 210
-- Data for Name: personal_password_table; Type: TABLE DATA; Schema: main; Owner: qr_game
--

COPY main.personal_password_table (id, qr_id, password, quantity) FROM stdin;
\.


--
-- TOC entry 3303 (class 0 OID 24957)
-- Dependencies: 202
-- Data for Name: qr_table; Type: TABLE DATA; Schema: main; Owner: qr_game
--

COPY main.qr_table (id, qr_suffix, event_id, qr_url, team, team_for_front, deleted, general_default_resource_people_count, default_resource, default_resource_people_count, group_access, group_password, personal_access) FROM stdin;
\.


--
-- TOC entry 3304 (class 0 OID 24971)
-- Dependencies: 203
-- Data for Name: resouces_table; Type: TABLE DATA; Schema: main; Owner: qr_game
--

COPY main.resouces_table (id, qr_id, qr_suffix, infinity, url, people_count, came_people_count, deleted) FROM stdin;
\.


--
-- TOC entry 3305 (class 0 OID 24984)
-- Dependencies: 204
-- Data for Name: users_table; Type: TABLE DATA; Schema: main; Owner: qr_game
--

COPY main.users_table (id, login, password, role) FROM stdin;
1	admin	admin	ADMIN
2	westy	$2a$10$RiFdDsXoW/t9wHfGcPh91OARTX.FQflyU6l1U0nqoEp3P.i/xLsT6	USER
\.


--
-- TOC entry 3318 (class 0 OID 0)
-- Dependencies: 208
-- Name: event_seq; Type: SEQUENCE SET; Schema: main; Owner: qr_game
--

SELECT pg_catalog.setval('main.event_seq', 28, true);


--
-- TOC entry 3319 (class 0 OID 0)
-- Dependencies: 209
-- Name: personal_password_seq; Type: SEQUENCE SET; Schema: main; Owner: qr_game
--

SELECT pg_catalog.setval('main.personal_password_seq', 162, true);


--
-- TOC entry 3320 (class 0 OID 0)
-- Dependencies: 207
-- Name: qr_seq; Type: SEQUENCE SET; Schema: main; Owner: qr_game
--

SELECT pg_catalog.setval('main.qr_seq', 28, true);


--
-- TOC entry 3321 (class 0 OID 0)
-- Dependencies: 206
-- Name: resources_seq; Type: SEQUENCE SET; Schema: main; Owner: qr_game
--

SELECT pg_catalog.setval('main.resources_seq', 26, true);


--
-- TOC entry 3322 (class 0 OID 0)
-- Dependencies: 205
-- Name: users_seq; Type: SEQUENCE SET; Schema: main; Owner: qr_game
--

SELECT pg_catalog.setval('main.users_seq', 2, true);


--
-- TOC entry 3150 (class 2606 OID 24956)
-- Name: event_table event_table_pk; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.event_table
    ADD CONSTRAINT event_table_pk PRIMARY KEY (id);


--
-- TOC entry 3167 (class 2606 OID 25206)
-- Name: personal_password_table personal_pass_pk; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.personal_password_table
    ADD CONSTRAINT personal_pass_pk PRIMARY KEY (id);


--
-- TOC entry 3152 (class 2606 OID 24965)
-- Name: qr_table qr_table_pk; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.qr_table
    ADD CONSTRAINT qr_table_pk PRIMARY KEY (id);


--
-- TOC entry 3155 (class 2606 OID 24996)
-- Name: qr_table qr_table_un; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.qr_table
    ADD CONSTRAINT qr_table_un UNIQUE (qr_suffix);


--
-- TOC entry 3157 (class 2606 OID 24978)
-- Name: resouces_table resouces_table_pk; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.resouces_table
    ADD CONSTRAINT resouces_table_pk PRIMARY KEY (id);


--
-- TOC entry 3160 (class 2606 OID 24992)
-- Name: resouces_table resouces_table_un; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.resouces_table
    ADD CONSTRAINT resouces_table_un UNIQUE (qr_suffix);


--
-- TOC entry 3163 (class 2606 OID 24988)
-- Name: users_table users_table_pk; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.users_table
    ADD CONSTRAINT users_table_pk PRIMARY KEY (id);


--
-- TOC entry 3165 (class 2606 OID 24990)
-- Name: users_table users_table_un; Type: CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.users_table
    ADD CONSTRAINT users_table_un UNIQUE (login);


--
-- TOC entry 3148 (class 1259 OID 24998)
-- Name: event_table_name_idx; Type: INDEX; Schema: main; Owner: qr_game
--

CREATE INDEX event_table_name_idx ON main.event_table USING btree (name);


--
-- TOC entry 3168 (class 1259 OID 25212)
-- Name: personal_pass_qr_idx; Type: INDEX; Schema: main; Owner: qr_game
--

CREATE INDEX personal_pass_qr_idx ON main.personal_password_table USING btree (qr_id);


--
-- TOC entry 3153 (class 1259 OID 24997)
-- Name: qr_table_qr_suffix_idx; Type: INDEX; Schema: main; Owner: qr_game
--

CREATE UNIQUE INDEX qr_table_qr_suffix_idx ON main.qr_table USING btree (qr_suffix);


--
-- TOC entry 3158 (class 1259 OID 24993)
-- Name: resouces_table_qr_suffix_idx; Type: INDEX; Schema: main; Owner: qr_game
--

CREATE UNIQUE INDEX resouces_table_qr_suffix_idx ON main.resouces_table USING btree (qr_suffix);


--
-- TOC entry 3161 (class 1259 OID 24994)
-- Name: resouces_table_url_idx; Type: INDEX; Schema: main; Owner: qr_game
--

CREATE INDEX resouces_table_url_idx ON main.resouces_table USING btree (url);


--
-- TOC entry 3169 (class 2606 OID 24966)
-- Name: qr_table qr_table_fk; Type: FK CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.qr_table
    ADD CONSTRAINT qr_table_fk FOREIGN KEY (event_id) REFERENCES main.event_table(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3170 (class 2606 OID 25087)
-- Name: resouces_table resouces_table_fk; Type: FK CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.resouces_table
    ADD CONSTRAINT resouces_table_fk FOREIGN KEY (qr_id) REFERENCES main.qr_table(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3171 (class 2606 OID 25207)
-- Name: personal_password_table resouces_table_fk; Type: FK CONSTRAINT; Schema: main; Owner: qr_game
--

ALTER TABLE ONLY main.personal_password_table
    ADD CONSTRAINT resouces_table_fk FOREIGN KEY (qr_id) REFERENCES main.qr_table(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2022-02-12 23:11:54 MSK

--
-- PostgreSQL database dump complete
--

