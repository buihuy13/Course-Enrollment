drop table user_roles;

alter table users add role_id int;

alter table users add constraint fk_role_id foreign key (role_id) references roles(id);