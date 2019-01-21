CREATE TABLE admin(
		adminno NUMBER(10) NOT NULL, -- 관리자 번호
		id VARCHAR(60) NOT NULL,				 -- 아이디
		passwd VARCHAR(10) NOT NULL,	-- 비밀 번호
		auth VARCHAR(30) NOT NULL,			-- 가입키
		act CHAR(1) DEFAULT 'N' NOT NULL,		-- M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기, G: 손님, C: 탈퇴
		mdate DATE NOT NULL,						-- 가입일
		tel VARCHAR(20) NOT NULL,			-- 전화 번호
		name VARCHAR(30) NOT NULL,	-- 성명
	PRIMARY KEY(adminno)
);

CREATE TABLE member(
		memberno NUMBER(10) NOT NULL, -- 관리자 번호
	PRIMARY KEY(memberno)
);



DROP TABLE movie;

/**********************************/
/* Table Name: 영화 */
/**********************************/
CREATE TABLE movie(
		movieno NUMBER(30) NOT NULL,
		title VARCHAR(100) NOT NULL,
		choice VARCHAR(300) NOT NULL,
		contents VARCHAR(500) NOT NULL,
		actor    VARCHAR(100) NOT NULL,
		thumbs VARCHAR(500) NOT NULL,
		image VARCHAR(10) NOT NULL,
		image_size NUMBER(20)  DEFAULT 0 NOT NULL,
		star VARCHAR(10) NOT NULL,
		rdate DATE NOT NULL,

	PRIMARY KEY(movieno)

);

COMMENT ON TABLE movie is '영화';
COMMENT ON COLUMN movie.movieno is '영화 번호';
COMMENT ON COLUMN movie.title is '영화명';
COMMENT ON COLUMN movie.choice is '기본정보';
COMMENT ON COLUMN movie.contents is '줄거리';
COMMENT ON COLUMN movie.actor is '배우';
COMMENT ON COLUMN movie.thumbs is '썸네일';
COMMENT ON COLUMN movie.image is '이미지';
COMMENT ON COLUMN movie.image_size is '이미지 크기';
COMMENT ON COLUMN movie.star is '평점';
COMMENT ON COLUMN movie.rdate is '등록일';


2. 등록
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'보헤미안 랩소디', '예매율 31.4% 장르: 범죄', '재밌어요', '프레디 머큐리', 10, 10, 10, 10, sysdate);
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'미스트', '예매율 31.4% 장르: 범죄', '재밌어요', '마미손', 10, 10, 10, 10, sysdate);
INSERT INTO movie(movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate)
VALUES((SELECT NVL(MAX(movieno), 0)+1 as movieno FROM movie),'해리포터', '예매율 31.4% 장르: 범죄', '재밌어요', '마미손', 10, 10, 10, 10, sysdate);

3. 목록
SELECT movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate
FROM movie
ORDER BY title ASC;

 MOVIENO TITLE    CHOICE                  CONTENTS             ACTOR   THUMBS IMAGE IMAGE_SIZE STAR RDATE
 ------- -------- ---------------- -------- ------- ------ ----- ---------- ---- ---------------------
       2 미스트      예매율 31.4% 장르: 범죄    재밌어요              마미손     10     10            10 10   2019-01-08 19:54:12.0
       1 보헤미안  예매율 31.4% 장르: 범죄     재밌어요     프레디 머큐리      10     10            10 10   2019-01-08 19:53:25.0
       3 해리포터     예매율 31.4% 장르: 범죄   재밌어요             마미손     10     10            10 10   2019-01-08 19:54:13.0

4. 삭제
DELETE FROM movie
WHERE movieno = 1;

5. 조회
SELECT movieno, title, choice, contents, actor, thumbs, image, image_size, star, rdate
FROM movie
WHERE movieno = 2;

6. 수정
UPDATE movie
SET title = '슛돌이'
WHERE movieno = 3;


/**********************************/
/* Table Name: 영화배우 */
/**********************************/
DROP TABLE m_actor;

CREATE TABLE m_actor(
		m_actorno NUMBER(30) NOT NULL,
		m_actorname VARCHAR(30) NOT NULL,
		m_actorbrd   VARCHAR(20) NOT NULL,
		m_actorwork	VARCHAR(100) NOT NULL,
		m_actorimage VARCHAR(500) NOT NULL,
		movieno		NUMBER(30) NOT NULL,
		PRIMARY KEY(m_actorno),
		FOREIGN KEY(movieno) REFERENCES movie(movieno)
);

COMMENT ON TABLE m_actor is '영화 배우';
COMMENT ON COLUMN m_actor.m_actorno is '영화배우 번호';
COMMENT ON COLUMN m_actor.m_actorname is '영화배우 이름';
COMMENT ON COLUMN m_actor.m_actorbrd is '영화배우 생년월일';
COMMENT ON COLUMN m_actor.m_actorwork is '영화배우 작품';
COMMENT ON COLUMN m_actor.m_actorimage is '영화배우 사진';
COMMENT ON COLUMN m_actor.movieno is '영화 번호';

2. 등록
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '송강호', '1900-10-19', '작품', '이미지', 1);
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '임창정', '1900-10-19', '작품', '이미지', 1);
INSERT INTO m_actor(m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno)
VALUES((SELECT NVL(MAX(m_actorno), 0)+1 as m_actorno FROM m_actor), '마동석', '1900-10-19', '작품', '이미지', 1);

3. 목록
SELECT m_actorno, m_actorname, m_actorbrd, m_actorwork, m_actorimage, movieno
FROM m_actor
ORDER BY m_actorname ASC;
M_ACTORNO M_ACTORNAME M_ACTORBRD M_ACTORWORK M_ACTORIMAGE MOVIENO
 --------- ----------- ---------- ----------- ------------ -------
         3         마동석         1900-10-19          작품                이미지                1
         1         송강호         1900-10-19          작품                이미지                1
         2         임창정         1900-10-19          작품                이미지                1

4. 삭제
DELETE FROM m_actor
WHERE m_actorno = 1;













