create table if not exists players
(
    id          int unsigned              not null
        primary key,
    name        varchar(100) charset utf8 not null,
    image       varchar(255) charset utf8 not null,
    nationality varchar(100) charset utf8 not null,
    potential   int unsigned              not null,
    age         int unsigned              not null,
    club        varchar(100) charset utf8 not null,
    value       bigint                    null,
    wage        bigint                    not null,
    INDEX player_club (club),
    foreign key (club) references teams (name)
        on update cascade on delete CASCADE
)