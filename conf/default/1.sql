# --- !Ups

create table "item" (
  "id" BIGSERIAL PRIMARY KEY,
  "name" varchar not null,
);

# --- !Downs

drop table "item" if exists;
