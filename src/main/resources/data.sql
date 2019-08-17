delete from WordTranslate;
insert into WordTranslate (id, engword, ukrword, points) values (1, 'test1', 'тест1',22);
insert into WordTranslate (id, engword, ukrword, points) values (2, 'test2', 'тест2',21);

delete from User;
INSERT into User(id,name,password) VALUES (0,'admin','admin');