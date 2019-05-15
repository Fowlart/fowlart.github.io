create table if not exists WordTranslate (
  id identity,
  engword varchar(50) not null,
  ukrword varchar(50) not null,
  points bigint
);
