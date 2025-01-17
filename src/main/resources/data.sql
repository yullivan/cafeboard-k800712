-- 회원 데이터 삽입
INSERT INTO member (id, username, email, password) VALUES
(1, 'user1', 'user1@example.com', 'password1'),
(2, 'user2', 'user2@example.com', 'password2');

-- 게시판 데이터 삽입
INSERT INTO board (id, name, description) VALUES
(1, '자유게시판', '자유롭게 이야기를 나누는 공간입니다.'),
(2, '공지사항', '중요한 공지사항을 확인하세요.');

-- 게시글 데이터 삽입 (created_at 및 updated_at 포함)
INSERT INTO post (id, title, content, created_at, updated_at, board_id, author_id) VALUES
(1, '첫 번째 게시글', '안녕하세요, 첫 번째 게시글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
(2, '두 번째 게시글', '반갑습니다, 두 번째 게시글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
(3, '세 번째 게시글', '게시판 테스트입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
(4, '공지사항', '중요한 공지입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2);

-- 댓글 데이터 삽입
INSERT INTO comment (id, content, created_at, updated_at, post_id, author_id) VALUES
(1, '첫 번째 댓글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
(2, '두 번째 댓글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
(3, '세 번째 댓글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1);
T_TIMESTAMP, 2, 2);