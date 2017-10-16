INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'COMPRO', 'DEPT', 'password', 'admin', 'admin');
INSERT INTO admins (id) VALUES ((SELECT id FROM users WHERE first_name='COMPRO' and last_name='DEPT'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Stu', 'Dent', 'password', 'student', 'student');
INSERT INTO students (id) VALUES ((SELECT id FROM users WHERE first_name='Stu' and last_name='Dent'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Facul', 'Ty', 'password', 'faculty', 'faculty');
INSERT INTO faculties (id) VALUES ((SELECT id FROM users WHERE first_name='Facul' and last_name='Ty'));
