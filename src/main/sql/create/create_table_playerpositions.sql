create table if not exists playerpositions
(
  player_id int unsigned             not null,
  pos       varchar(20) charset utf8 not null,
  primary key (player_id, pos),
  foreign key (pos) references positions (position) ON UPDATE CASCADE ON DELETE CASCADE,
  foreign key (player_id) references players (id) ON UPDATE CASCADE ON DELETE CASCADE,
  INDEX pos_player (pos)
)