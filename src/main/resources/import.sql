INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username) VALUES (now(), FALSE, now(), 'COMPRO', 'DEPT', 'admin', 'admin');
INSERT INTO admins (id) VALUES ((SELECT id FROM users WHERE first_name='COMPRO' and last_name='DEPT'));
