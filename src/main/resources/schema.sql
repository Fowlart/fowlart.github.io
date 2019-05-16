create table if not exists WordTranslate (
  id identity,
  engword varchar(50) not null,
  ukrword varchar(50) not null,
  points bigint
);

create table if not exists User (
	id identity,
	name varchar(50) not null,
	password varchar(50) not null,
	);

create table if not exists User_WordTranslate (
  user bigint not null,
  wordtranslate  bigint not null,
);

alter table User_WordTranslate add foreign key (wordtranslate) references WordTranslate(id);
alter table User_WordTranslate add foreign key (user) references User(id);
