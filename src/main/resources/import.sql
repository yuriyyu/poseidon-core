INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'COMPRO', 'DEPT', 'password', 'admin', 'admin');
INSERT INTO admins (id) VALUES ((SELECT id FROM users WHERE first_name='COMPRO' and last_name='DEPT'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Stu', 'Dent', 'password', 'student', 'student');
INSERT INTO students (id) VALUES ((SELECT id FROM users WHERE first_name='Stu' and last_name='Dent'));

INSERT INTO users (dcreated, is_deleted, dupdated, first_name, last_name, password, username, type) VALUES (now(), FALSE, now(), 'Facul', 'Ty', 'password', 'faculty', 'faculty');
INSERT INTO faculties (id) VALUES ((SELECT id FROM users WHERE first_name='Facul' and last_name='Ty'));

INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 390,'Fundamental Programming Practices: Modern Programming Methods and Systems ');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 401,'Modern Programming Practices: Current Concepts and Best Practices in Software Development ');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 422,'Database Systems: Capturing the Organizing Power of Information ');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 423,'Systems Analysis and Design');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 425,'Software Engineering: Knowledge Is the Basis of Action ');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 430,' Business Intelligence and Data Mining');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 435,'Algorithms: Discovering the Hidden Dynamics of Natural Law');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 440,'Compiler Construction: Connecting Name and Form ');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 450,'Computer Networks: Connecting the Parts and Whole');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 465,'Operating Systems');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 467,'Secure Coding Practices');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 470,'Artificial Intelligence');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 471,'Parallel Programming');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 472,'Web Application Programming');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 473,'Mobile Device Programming');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 475,'Computer Graphics');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 476,'Image Processing');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 482,'Software Development with Fundamental Design Patterns');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 488,'Big Data Analytics');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 501,'Advanced Computer Architecture');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 505,'Advanced Programming Languages');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 522,'Big Data');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 523,'Big Data Technologies');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 544,'Enterprise Architecture');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 545,'Web Application Architecture and Frameworks');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 547,'Distributed Architecture');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 572,'Modern Web Applications');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 575,'Practicum in Software Development');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 582,'Machine Learning');
INSERT INTO courses (dcreated, is_deleted, dupdated, number, name) VALUES (now(), FALSE, now(), 500,'Science of creative intelligence');

INSERT INTO entries (dcreated, is_deleted, dupdated, n_fpp_opt, n_fpp_students, n_mpp_opt, n_mpp_students, name, start_date, us_res) VALUES (now(), FALSE, now(), 100, 100, 100, 100, '2017-Aug', '2017-08-01', 35);

INSERT INTO blocks (dcreated, is_deleted, dupdated, end_date, start_date, name, entry_id) VALUES (now(), FALSE, now(), '2017-12-01', '2017-11-01', '2017-Nov', 1);

INSERT INTO sections (dcreated, is_deleted, dupdated, block_id, end_date, location, max_seats, start_date, faculty_id, course_id) VALUES (now(), FALSE, now(), 1, '2017-12-01', 'mclaugh room 102', 25, '2017-11-01', 3, 1);
