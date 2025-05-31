-- Adminer 5.2.1 PostgreSQL 17.4 dump

DROP TABLE IF EXISTS "task";
DROP SEQUENCE IF EXISTS task_id_seq;
CREATE SEQUENCE task_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."task" (
    "id" bigint DEFAULT nextval('task_id_seq') NOT NULL,
    "description" character varying(255),
    "status" character varying(255),
    "title" character varying(255),
    CONSTRAINT "task_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "task_status_check" CHECK ((status)::text = ANY ((ARRAY['TO_DO'::character varying, 'IN_PROGRESS'::character varying, 'DONE'::character varying])::text[]))
) WITH (oids = false);

INSERT INTO "task" ("id", "description", "status", "title") VALUES
(2,	'ne pas oublier le lait',	'TO_DO',	'Acheter du pain et du lait'),
(3,	'Je ne veux pas être perturbée aujourd''hui',	'IN_PROGRESS',	'Faire la grasse matinée'),
(1,	'finir avant 10h',	'DONE',	'faire le ménage'),
(5,	'Avant 14h',	'DONE',	'Soumettre ma todo list app');

-- 2025-05-31 12:46:43 UTC
