--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-03 11:23:07

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
-- TOC entry 221 (class 1259 OID 16718)
-- Name: cinema_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cinema_user (
    id bigint NOT NULL,
    fname character varying(30) NOT NULL,
    lname character varying(30) NOT NULL,
    email character varying(50) NOT NULL,
    phone_number character varying(15) NOT NULL
);


ALTER TABLE public.cinema_user OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16693)
-- Name: hall; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hall (
    id integer NOT NULL,
    number_of_seats integer NOT NULL
);


ALTER TABLE public.hall OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16778)
-- Name: hall_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hall ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hall_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 16686)
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(320) NOT NULL,
    genre character varying(20) NOT NULL,
    duration integer NOT NULL,
    actors character varying(300) NOT NULL,
    director character varying(100) NOT NULL,
    image character varying(300) NOT NULL
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16779)
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.movie ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16698)
-- Name: screening; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.screening (
    id bigint NOT NULL,
    start_time character varying(10) NOT NULL,
    date character varying(20) NOT NULL,
    movie_id bigint NOT NULL,
    hall_id integer NOT NULL,
    ongoing boolean NOT NULL
);


ALTER TABLE public.screening OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16780)
-- Name: screening_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.screening ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.screening_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 16713)
-- Name: seat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seat (
    id bigint NOT NULL,
    status boolean,
    "row" integer NOT NULL,
    col integer NOT NULL,
    hall_id integer NOT NULL
);


ALTER TABLE public.seat OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16781)
-- Name: seat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.seat ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.seat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16703)
-- Name: seat_reserved; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seat_reserved (
    id bigint NOT NULL,
    seat_id bigint NOT NULL,
    hall_id integer NOT NULL,
    screening_id bigint NOT NULL
);


ALTER TABLE public.seat_reserved OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16782)
-- Name: seat_reserved_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.seat_reserved ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.seat_reserved_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 16708)
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    price integer NOT NULL,
    seat_id integer NOT NULL,
    screening_id bigint NOT NULL,
    movie_id bigint NOT NULL,
    hall_id integer NOT NULL
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16783)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ticket ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 16784)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.cinema_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4892 (class 0 OID 16718)
-- Dependencies: 221
-- Data for Name: cinema_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cinema_user (id, fname, lname, email, phone_number) FROM stdin;
1	Kristijan	Danilovic	kdanilovic@etfos.hr	test
\.


--
-- TOC entry 4887 (class 0 OID 16693)
-- Dependencies: 216
-- Data for Name: hall; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hall (id, number_of_seats) FROM stdin;
\.


--
-- TOC entry 4886 (class 0 OID 16686)
-- Dependencies: 215
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movie (id, name, description, genre, duration, actors, director, image) FROM stdin;
3	Inception	A mind-bending thriller about dreams within dreams.	Sci-Fi	148	Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page	Christopher Nolan	inception.jpg
\.


--
-- TOC entry 4888 (class 0 OID 16698)
-- Dependencies: 217
-- Data for Name: screening; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.screening (id, start_time, date, movie_id, hall_id, ongoing) FROM stdin;
\.


--
-- TOC entry 4891 (class 0 OID 16713)
-- Dependencies: 220
-- Data for Name: seat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seat (id, status, "row", col, hall_id) FROM stdin;
\.


--
-- TOC entry 4889 (class 0 OID 16703)
-- Dependencies: 218
-- Data for Name: seat_reserved; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seat_reserved (id, seat_id, hall_id, screening_id) FROM stdin;
\.


--
-- TOC entry 4890 (class 0 OID 16708)
-- Dependencies: 219
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket (id, user_id, price, seat_id, screening_id, movie_id, hall_id) FROM stdin;
\.


--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 222
-- Name: hall_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hall_id_seq', 1, false);


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 223
-- Name: movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_id_seq', 3, true);


--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 224
-- Name: screening_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.screening_id_seq', 1, false);


--
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 225
-- Name: seat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seat_id_seq', 1, false);


--
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 226
-- Name: seat_reserved_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seat_reserved_id_seq', 1, false);


--
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 227
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ticket_id_seq', 1, false);


--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 228
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, true);


--
-- TOC entry 4721 (class 2606 OID 16697)
-- Name: hall hall_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hall
    ADD CONSTRAINT hall_pkey PRIMARY KEY (id);


--
-- TOC entry 4719 (class 2606 OID 16692)
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- TOC entry 4723 (class 2606 OID 16702)
-- Name: screening screening_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.screening
    ADD CONSTRAINT screening_pkey PRIMARY KEY (id);


--
-- TOC entry 4729 (class 2606 OID 16717)
-- Name: seat seat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat
    ADD CONSTRAINT seat_pkey PRIMARY KEY (id);


--
-- TOC entry 4725 (class 2606 OID 16707)
-- Name: seat_reserved seat_reserved_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat_reserved
    ADD CONSTRAINT seat_reserved_pkey PRIMARY KEY (id);


--
-- TOC entry 4727 (class 2606 OID 16712)
-- Name: ticket ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id);


--
-- TOC entry 4731 (class 2606 OID 16722)
-- Name: cinema_user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cinema_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 4732 (class 2606 OID 16773)
-- Name: screening screening_hall_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.screening
    ADD CONSTRAINT screening_hall_id_foreign FOREIGN KEY (hall_id) REFERENCES public.hall(id);


--
-- TOC entry 4733 (class 2606 OID 16768)
-- Name: screening screening_movie_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.screening
    ADD CONSTRAINT screening_movie_id_foreign FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- TOC entry 4742 (class 2606 OID 16733)
-- Name: seat seat_hall_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat
    ADD CONSTRAINT seat_hall_id_foreign FOREIGN KEY (hall_id) REFERENCES public.hall(id);


--
-- TOC entry 4734 (class 2606 OID 16743)
-- Name: seat_reserved seat_reserved_hall_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat_reserved
    ADD CONSTRAINT seat_reserved_hall_id_foreign FOREIGN KEY (hall_id) REFERENCES public.hall(id);


--
-- TOC entry 4735 (class 2606 OID 16728)
-- Name: seat_reserved seat_reserved_screening_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat_reserved
    ADD CONSTRAINT seat_reserved_screening_id_foreign FOREIGN KEY (screening_id) REFERENCES public.screening(id);


--
-- TOC entry 4736 (class 2606 OID 16763)
-- Name: seat_reserved seat_reserved_seat_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seat_reserved
    ADD CONSTRAINT seat_reserved_seat_id_foreign FOREIGN KEY (seat_id) REFERENCES public.seat(id);


--
-- TOC entry 4737 (class 2606 OID 16723)
-- Name: ticket ticket_hall_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_hall_id_foreign FOREIGN KEY (hall_id) REFERENCES public.hall(id);


--
-- TOC entry 4738 (class 2606 OID 16748)
-- Name: ticket ticket_movie_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_movie_id_foreign FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- TOC entry 4739 (class 2606 OID 16758)
-- Name: ticket ticket_screening_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_screening_id_foreign FOREIGN KEY (screening_id) REFERENCES public.screening(id);


--
-- TOC entry 4740 (class 2606 OID 16753)
-- Name: ticket ticket_seat_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_seat_id_foreign FOREIGN KEY (seat_id) REFERENCES public.seat(id);


--
-- TOC entry 4741 (class 2606 OID 16738)
-- Name: ticket ticket_user_id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_user_id_foreign FOREIGN KEY (user_id) REFERENCES public.cinema_user(id);


-- Completed on 2024-07-03 11:23:07

--
-- PostgreSQL database dump complete
--

