-- V1__init.sql
CREATE TABLE users (
  id          SERIAL PRIMARY KEY,
  username    TEXT NOT NULL,
  email       TEXT UNIQUE NOT NULL,
  password    TEXT NOT NULL,
  created_at  TIMESTAMP DEFAULT now()
);

CREATE TABLE articles (
  id          SERIAL PRIMARY KEY,
  slug        TEXT UNIQUE NOT NULL,
  title       TEXT NOT NULL,
  body        TEXT NOT NULL,
  author_id   INT REFERENCES users(id),
  created_at  TIMESTAMP DEFAULT now()
);