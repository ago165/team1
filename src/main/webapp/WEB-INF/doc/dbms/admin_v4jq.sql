DROP TABLE admin;
/**********************************/
/* Table Name: 관리자 */
/**********************************/
1. 테이블 생성
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

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.id is '아이디';
COMMENT ON COLUMN admin.passwd is '비밀 번호';
COMMENT ON COLUMN admin.auth is '가입키';
COMMENT ON COLUMN admin.act is ' 권한';
COMMENT ON COLUMN admin.mdate is '가입일';
COMMENT ON COLUMN admin.adminno is '전화 번호';
COMMENT ON COLUMN admin.adminno is '성명';


2. 등록
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin1@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '관리자1');
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin2@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '관리자2');
INSERT INTO admin(adminno, id, passwd, auth, act, mdate, tel, name)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin3@gmail.com', '1234', 'AXD0123456789012', 'M', sysdate, '010-0000-0000', '관리자3');

3. 목록
SELECT adminno, id, passwd, auth, act, mdate, tel, name
FROM admin
ORDER BY id ASC;

ADMINNO ID               PASSWD AUTH             ACT MDATE                 TEL           NAME
 ------- ---------------- ------ ---------------- --- --------------------- ------------- ----
       1 admin1@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:40:30.0 010-0000-0000 관리자1
       2 admin2@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:41:11.0 010-0000-0000 관리자2
       3 admin3@gmail.com 1234   AXD0123456789012 M   2018-12-18 10:41:12.0 010-0000-0000 관리자3

4. 삭제
DELETE FROM admin
WHERE adminno = 3;

5. 조회
SELECT adminno, id, passwd, auth, act, mdate, tel, name
FROM admin
WHERE adminno = 1;

6. 수정
UPDATE admin
SET id = 'admin5@gmail.com'
WHERE adminno = 1;

1) 중복 이메일 검사 관련 SQL 
    -- 0: 중복 아님, 1: 중복  
    SELECT COUNT(*) as cnt
    FROM admin
    WHERE id = 'admin5@gmail.com';
    
2) MASTER 계정의 조회
    SELECT COUNT(*) as cnt
    FROM admin
    WHERE act = 'M';
    
2) 권한의 변경(admin4DAO: int updateAct(int admin4no, String act))
UPDATE admin
SET  act='Y'
WHERE adminno=1;

7. 패스워드 변경
1) 기존 패스워드 검사
- DAO: public int countPasswd(int admin4no, String passwd){ ... }
SELECT count(*) as cnt
FROM admin
WHERE adminno = 1 AND passwd='123';

2) 패스워드 변경
- DAO: public int updatePasswd(int admin4no, String passwd){ ... }
UPDATE admin
SET passwd=''
WHERE adminno=1;
-----------------------------------------------------------------------------------------------------
/**********************************/
/* Table Name: 설문 조사 */
/**********************************/
DROP TABLE serch;
1. 테이블 등록
CREATE TABLE serch(
		serchno NUMBER(30) NOT NULL,
		adminno NUMBER(10) NOT NULL,
		memberno NUMBER(10) NOT NULL,
		moviename VARCHAR(30) NOT NULL,
		good VARCHAR(10) NOT NULL,
		repair VARCHAR(100) NOT NULL,
		theater VARCHAR(30) NOT NULL,
		moviedate VARCHAR(30) NOT NULL,
		showtime VARCHAR(30) NOT NULL,
		
		PRIMARY KEY(serchno),
		FOREIGN KEY (adminno) REFERENCES admin(adminno)
);

COMMENT ON TABLE serch is '설문조사';
COMMENT ON COLUMN serch.serchno is '설문조사 번호';
COMMENT ON COLUMN serch.adminno is '관리자 번호';
COMMENT ON COLUMN serch.memberno is '회원 번호';
COMMENT ON COLUMN serch.moviename is '영화명';
COMMENT ON COLUMN serch.good is '만족도';
COMMENT ON COLUMN serch.repair is '개선점';
COMMENT ON COLUMN serch.theater is '상영 극장';
COMMENT ON COLUMN serch.moviedate is '상영 날짜';
COMMENT ON COLUMN serch.showtime is '상영 시간';

2. 등록
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, '완벽한 타인', '3.5', '너무 더워요', '인천 cgv', '2018-12-18', '16:10~18:00');
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, '두치와 뿌꾸', '3.5', '너무 더워요', '인천 cgv', '2018-12-18', '15:10~16:00');
INSERT INTO serch(serchno, adminno, memberno, moviename, good, repair, theater, moviedate, showtime) 
VALUES ((SELECT NVL(MAX(serchno), 0)+1 as serchno FROM serch), 2, 3, '친구', '3.5', '너무 더워요', '인천 cgv', '2018-12-18', '13:10~14:00');


3. 목록
SELECT serchno, adminno, memberno,moviename, good, repair, theater, moviedate, showtime
FROM serch
ORDER BY moviename ASC;

SERCHNO ADMINNO MEMBERNO MOVIENAME GOOD REPAIR THEATER MOVIEDATE  SHOWTIME
 ------- ------- -------- --------- ---- ------ ------- ---------- -----------
       2       2        3 두치와 뿌꾸    3.5  너무 더워요 인천 cgv  2018-12-18 15:10~16:00
       1       2        3 완벽한 타인    3.5  너무 더워요 인천 cgv  2018-12-18 16:10~18:00
       3       2        3 친구        3.5  너무 더워요 인천 cgv  2018-12-18 13:10~14:00
       
       
4. 삭제
DELETE FROM serch
WHERE serchno = 1;

5. 조회
SELECT serchno, adminno, memberno,moviename, good, repair, theater, moviedate, showtime
FROM serch
WHERE serchno = 1;

6. 수정
UPDATE serch
SET moviename = '해리포터'
WHERE serchno = 1;

-------------------------------------------------------------------------------------------
DROP TABLE serchlist;

/**********************************/
/* Table Name: 설문 조사 항목 */
/**********************************/
1. 테이블 등록
CREATE TABLE serchlist(
		serchlistno NUMBER(30) NOT NULL,
		serchno NUMBER(30) NOT NULL,
		serchtitle VARCHAR(100) NOT NULL,
		listprintorder NUMBER(30) NOT NULL,
		orderlist VARCHAR(30) NOT NULL,
		orderfile VARCHAR(100) NOT NULL,
		listchoiceman NUMBER(30) NOT NULL,
		PRIMARY KEY(serchlistno),
		FOREIGN KEY (serchno) REFERENCES serch(serchno)
);

COMMENT ON TABLE serchlist is '설문 조사 항목';
COMMENT ON COLUMN serchlist.serchno is '설문 조사 번호';
COMMENT ON COLUMN serchlist.serchlistno is '설문 조사 항목 번호';
COMMENT ON COLUMN serchlist.serchtitle is '설문 조사 제목';
COMMENT ON COLUMN serchlist.listprintorder is '항목 출력 순서';
COMMENT ON COLUMN serchlist.orderlist is '항목';
COMMENT ON COLUMN serchlist.orderfile is '항목 파일';
COMMENT ON COLUMN serchlist.listchoiceman is '항목 선택 인원';

2. 등록
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (1, 1 ,'인천 CGV 평가', 1, '영화관', '111.jpg', 5);
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (2, 2 ,'서울 CGV 평가', 1, '영화관', '111.jpg', 5);
INSERT INTO serchlist (serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman)
VALUES (3, 3 ,'경기 CGV 평가', 1, '영화관', '111.jpg', 5);

3. 목록
SELECT serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman
FROM serchlist
ORDER BY serchtitle ASC;

SERCHNO SERCHLISTNO SERCHTITLE LISTPRINTORDER ORDERLIST ORDERFILE LISTCHOICEMAN
 ------- ----------- ---------- -------------- --------- --------- -------------
       3           3        경기 CGV 평가           1           영화관       111.jpg               5
       2           2        서울 CGV 평가           1           영화관       111.jpg               5
       1           1        경기 CGV 평가           1           영화관       111.jpg               5

4. 삭제
DELETE FROM serchlist
WHERE serchlistno = 1;

SERCHNO SERCHLISTNO SERCHTITLE LISTPRINTORDER ORDERLIST ORDERFILE LISTCHOICEMAN
 ------- ----------- ---------- -------------- --------- --------- -------------
       3           3          경기 CGV 평가         1           영화관       111.jpg               5
       2           2          서울 CGV 평가         1           영화관       111.jpg               5
       
5. 조회
SELECT serchno, serchlistno, serchtitle, listprintorder, orderlist, orderfile, listchoiceman
FROM serchlist
WHERE serchlistno = 2;

6. 수정
UPDATE serchlist
SET serchtitle = '대전 CGV 평가'
WHERE serchlistno = 3;
----------------------------------------------------------------------------------------------
/**********************************/
/* Table Name: 설문 참여 회원 */
/**********************************/
1. 테이블 등록
CREATE TABLE serchmember(
		serchmemberno NUMBER(30) NOT NULL,
    /*memberno NUMBER(30) NOT NULL,*/
		serchdate DATE NOT NULL,
		serchlistno NUMBER(30) NOT NULL,
		PRIMARY KEY (serchmemberno),
		FOREIGN KEY (serchlistno) REFERENCES serchlist(serchlistno)
		/*FOREIGN KEY (memberno) REFERENCES member(memberno)*/
);

DROP TABLE serchmember;


/* 회원 테이블 받아오면 수정 끝*/



















