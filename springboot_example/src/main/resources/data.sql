INSERT INTO article(id, title, content) values (1, '가가가가', '1111');
INSERT INTO article(id, title, content) values (2, '나나나나', '2222');
INSERT INTO article(id, title, content) values (3, '다다다다', '3333');


 -- article 더미 데이터 추가
INSERT INTO article(id, title, content) values (4, '당신의 인생 영화는?', '댓글 ㄱ');
INSERT INTO article(id, title, content) values (5, '당신의 소울 푸드는?', '댓글 ㄱㄱ');
INSERT INTO article(id, title, content) values (6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

--comment 더미 데이터
INSERT INTO comment(id, article_id, nickname, body) values (1, 4, 'Park', '굳 윌 헌팅');
INSERT INTO comment(id, article_id, nickname, body) values (2, 4, 'Kim', '아이 엠 샘');
INSERT INTO comment(id, article_id, nickname, body) values (3, 4, 'Choi', '쇼생크의 탈출');

INSERT INTO comment(id, article_id, nickname, body) values (4, 5, 'Park', '치킨');
INSERT INTO comment(id, article_id, nickname, body) values (5, 5, 'Kim', '샤브샤브');
INSERT INTO comment(id, article_id, nickname, body) values (6, 5, 'Choi', '초밥');

INSERT INTO comment(id, article_id, nickname, body) values (7, 6, 'Park', '조깅');
INSERT INTO comment(id, article_id, nickname, body) values (8, 6, 'Kim', '유튜브');
INSERT INTO comment(id, article_id, nickname, body) values (9, 6, 'Choi', '독서');