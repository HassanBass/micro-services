insert into user_details(id, birth_date, name)
values (1001, current_date(), 'Hassan');

insert into user_details(id, birth_date, name)
values (1003, current_date(), 'Mark');

insert into user_details(id, birth_date, name)
values (1002, current_date(), 'John');

insert into post(id, description, user_id)
values (2001, 'Just a new learner', 1003);

insert into post(id, description, user_id)
values (2002, 'Udemy Learner', 1002);

insert into post(id, description, user_id)
values (2003, 'Certificate seeker', 1001);