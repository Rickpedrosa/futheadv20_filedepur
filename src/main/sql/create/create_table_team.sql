create table if not exists teams
(
  name           varchar(100) charset utf8 not null
    primary key,
  id             int                       null,
  logox2         varchar(255) charset utf8 null,
  logox4         varchar(255) charset utf8 null,
  logox6         varchar(255) charset utf8 null,
  average        float                     null,
  eleven_average float                     null,
  quality        float                     null
)


