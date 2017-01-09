INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (1, 'javajigi', 'test', '재성', 'javajigi@slipp.net');
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (2, 'sanjigi', 'test', '산지기', 'sanjigi@slipp.net');

INSERT INTO QUESTION (id, writer_id, title, contents, count_of_answer, deleted, create_date) VALUES(1, 1,'국내에서 Ruby on Rails와 Play가 ','Ruby on Rails(이하 RoR)는', 0, true, CURRENT_TIMESTAMP());
INSERT INTO QUESTION (id, writer_id, title, contents, count_of_answer, deleted, create_date) VALUES(2, 2,'산지기가 쓴 글','산지기는 군생활 때 나의 별명. 자바지기의 유래는 산지기', 2, false, CURRENT_TIMESTAMP());
INSERT INTO QUESTION (id, writer_id, title, contents, count_of_answer, deleted, create_date) VALUES(3, 1,'자바지기가 쓴 글','자바지기는 군생활 때 나의 별명. 자바지기의 유래는 산지기', 3, false, CURRENT_TIMESTAMP());

INSERT INTO ANSWER (id, writer_id, question_id, contents, deleted, create_date) VALUES(1, 2, 2, '댓글 2-1', false, CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, question_id, contents, deleted, create_date) VALUES(2, 2, 2, '댓글 2-2', false, CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, question_id, contents, deleted, create_date) VALUES(3, 1, 3, '댓글 3-1', false, CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, question_id, contents, deleted, create_date) VALUES(4, 2, 3, '댓글 3-2', true, CURRENT_TIMESTAMP());
INSERT INTO ANSWER (id, writer_id, question_id, contents, deleted, create_date) VALUES(5, 2, 3, '댓글 3-3', false, CURRENT_TIMESTAMP());