create table author(
                       id serial primary key not null,
                       first_name varchar(45) default null,
                       email varchar(60) not null,
                       password varchar(15) not null
);

create table task(
                     id serial primary key,
                     task_title varchar(255) not null,
                     task_description text default null,
                     comment_count integer default 0 not null,
                     author_id integer not null references author(id)
);

create table comment(
                        id serial not null primary key,
                        task_id int references task(id),
                        author_id int references author(id),
                        comment_text text not null
);

insert into comment (task_id, author_id, comment_text)
values (11, 1, 'First Comment');

insert into author(first_name, email, password) values('Imant', 'imant@gmail.com', '111'),
                                                      ('Vitalij', 'vitalij@gmail.com', '111'),
                                                      ('Zarina', 'zarina@gmail.com', '111'),
                                                      ('Igorj', 'igorj@gmail.com', '111'),
                                                      ('Valentin', 'valentin@gmail.com', '111'),
                                                      ('Sergej', 'sergej@gmail.com', '111'),
                                                      ('Dmitrij', 'dmitrij@gmail.com', '111'),
                                                      ('Edgar', 'edgar@gmail.com', '111')
;

insert into task (task, author_id) values('Some example text 111', 1);

insert into task (task, author_id) values('Some example text 222', 2);
insert into task (task, author_id) values('Some example text 323221', 2);
insert into task (task, author_id) values('Some example asdasd 333333221', 2);
insert into task (task, author_id) values('Some example gh 323245441', 2);
insert into task (task, author_id) values('Some example text 99991', 2);

select * from task;
select * from author;

drop table author;
drop table task;

insert into author(first_name, email, password) values('Imants', 'imants.spiridovskis@gmail.com', '6404');

/**/
/*-----------------------*/
/**/
create table "author"(
                       id serial not null primary key,
                       first_name varchar(45) default null,
                       email varchar(60) not null,
                       password varchar(15) not null
);

create table task(
                     id serial primary key,
                     task_title varchar(255) not null,
                     task_description text default null,
                     comment_count integer default 0 not null,
                     author_id int references author(user_id)
);

create table task_user(
                          task_id int not null references task(id),
                          member_id int not null references "author"(id),
                          authorities varchar(50) not null
);

create table author(
                       id serial primary key not null,
                       task_id int references task(id),
                       user_id int references "author"(id)
);

/**/
/*--------------------------------------------------------*/
/**/
drop table "author";
drop table task;
/**/
/*--------------------------------------------------------*/
/**/

insert into author(first_name, email, password) values('Imant', 'imant@gmail.com', '{noop}111'),
                                                      ('Vitalij', 'vitalij@gmail.com', '{noop}111'),
                                                      ('Zarina', 'zarina@gmail.com', '{noop}111'),
                                                      ('Igorj', 'igorj@gmail.com', '{noop}111'),
                                                      ('Valentin', 'valentin@gmail.com', '{noop}111'),
                                                      ('Sergej', 'sergej@gmail.com', '{noop}111'),
                                                      ('Dmitrij', 'dmitrij@gmail.com', '{noop}111'),
                                                      ('Edgar', 'edgar@gmail.com', '{noop}111');

