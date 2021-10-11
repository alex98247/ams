INSERT INTO t_right (id, name)
values (1, 'all');

INSERT INTO t_role (id, name)
values (1, 'admin');

INSERT INTO t_user (id, username, employee_id, password, is_locked, expiration_date, pwd_expiration_date, is_system)
values (1, 'admin', NULL, '$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2', FALSE, FALSE, FALSE, FALSE);

INSERT INTO role_user (role_id, user_id)
values (1, 1);

INSERT INTO role_right (role_id, right_id)
values (1, 1);