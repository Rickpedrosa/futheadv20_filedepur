create or replace table if not exists positions
(
  position varchar(20) charset utf8 not null
    primary key
)
  collate = utf8_spanish_ci;

