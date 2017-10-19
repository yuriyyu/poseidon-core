INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'COMPRO', 'DEPT', 'password', 'admin', 'admin');
INSERT INTO admins (id) VALUES ((SELECT id FROM users WHERE first_name='COMPRO' and last_name='DEPT'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Stu', 'Dent', 'password', 'student', 'student');
INSERT INTO students (id) VALUES ((SELECT id FROM users WHERE first_name='Stu' and last_name='Dent'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Facul', 'Ty', 'password', 'faculty', 'faculty');
INSERT INTO faculties (id) VALUES ((SELECT id FROM users WHERE first_name='Facul' and last_name='Ty'));

INSERT INTO entries (dcreated, is_deleted, dupdated, n_fpp_opt, n_fpp_students, n_mpp_opt, n_mpp_students, name, start_date, us_res) VALUES (now(), FALSE, now(), 10, 10, 10, 10, '2017-Aug', '2017-08-01', 35);

INSERT INTO blocks (dcreated, is_deleted, dupdated, end_date, start_date, name, entry_id) VALUES (now(), FALSE, now(), '2017-12-01', '2017-11-01', '2017-Nov', 1);

INSERT INTO sections (dcreated, is_deleted, dupdated, block_id, end_date, location, max_seats, start_date, faculty_id) VALUES (now(), FALSE, now(), 1, '2017-12-01', 'mclaugh room 102', 25, '2017-11-01', 3);
