INSERT INTO t_right (id, name)
values (1, 'ADMIN'), (2, 'ORDER'), (3, 'SELL');

INSERT INTO t_role (id, name)
values (1, 'admin'), (2, 'sell_manager'), (3, 'order_manager');

INSERT INTO t_user (id, username, employee_id, password, is_locked, expiration_date, pwd_expiration_date, is_system)
values (1, 'admin', NULL, '$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2', FALSE,
        NOW() + interval '1' year, NOW() + interval '1' month, TRUE),
       (2, 'sell_manager', NULL, '$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2', FALSE,
        NOW() + interval '1' year, NOW() + interval '1' month, TRUE),
       (3, 'order_manager', NULL, '$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2', FALSE,
        NOW() + interval '1' year, NOW() + interval '1' month, TRUE);

INSERT INTO role_user (role_id, user_id)
values (1, 1), (2, 2), (3, 3);

INSERT INTO role_right (role_id, right_id)
values (1, 1), (2, 2), (3, 3);