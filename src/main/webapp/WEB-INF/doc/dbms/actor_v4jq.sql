DROP TABLE actor;

DROP TABLE actor CASCADE CONSTRAINTS;
/**********************************/
/* Table Name: 배우 */
/**********************************/
CREATE TABLE actor(
		actorno NUMBER(30) NOT NULL,
		actorname VARCHAR2(10) NOT NULL,
		actorbrd VARCHAR2(30) NOT NULL,
		thumbs  VARCHAR2(1000)   NULL ,
		files   VARCHAR2(1000)    NULL,
		sizes  VARCHAR2(1000)    NULL ,
		actor_sang VARCHAR2(500) NOT NULL,
		physical VARCHAR2(100) NOT NULL,
		actorwork VARCHAR2(500) NOT NULL,
		s_word			VARCHAR2(1000) NOT NULL,
 	PRIMARY KEY(actorno)
);


COMMENT ON TABLE actor is '배우';
COMMENT ON COLUMN actor.actorno is '배우 번호';
COMMENT ON COLUMN actor.actorname is '배우 이름';
COMMENT ON COLUMN actor.actorbrd is '배우 출생';
COMMENT ON COLUMN actor.thumbs is '썸네일';
COMMENT ON COLUMN actor.files is '파일명';
COMMENT ON COLUMN actor.sizes is '파일크기';
COMMENT ON COLUMN actor.actor_sang is '배우 수상';
COMMENT ON COLUMN actor.physical is '배우 신체';
COMMENT ON COLUMN actor.actorwork is '배우 작품';
COMMENT ON COLUMN actor.word is '검색어';

2. 등록
INSERT INTO actor(actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), '유해진', '1970년 1월 4일', 'm_you.jpg', 'yyou.jpg', 0,
						'2017년 37회 한국영화평론가협회상 남우조연상 수상', '174cm,65kg', '완벽한 타인 등등', '유해진');
INSERT INTO actor(actorno, actorname, actorbrd, actorimage, actor_sang, physical, actor_photo, actorwork)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), '조인성', '1988년 1월 4일', 'jo.jpg', 
						'2017년 38회 청룡영화상 인기스타상 수상', '186cm,76kg', 'photo', '안시성 등등');
INSERT INTO actor(actorno, actorname, actorbrd, actorimage, actor_sang, physical, actor_photo, actorwork)
VALUES((SELECT NVL(MAX(actorno), 0) +1 as actorno FROM actor), '황정민', '1979년 1월 4일', 'hwang.jpg', 
						'2018년 55회 대종상 영화제 남우주연상 수상', '180cm,75kg', 'photo', '신세계 등등');


3. 목록
SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork
FROM actor
ORDER BY actorno ASC;

ACTORNO ACTORNAME ACTORBRD    ACTORIMAGE ACTOR_SANG                    PHYSICAL   ACTORWORK
 ------- --------- ----------- ---------- ----------------------------- ---------- ---------
       1 유해진       1970년 1월 4일 you.jpg    2017년 37회 한국영화평론가협회상 남우조연상 수상 174cm,65kg 완벽한 타인 등등
       2 조인성       1988년 1월 4일 jo.jpg     2017년 38회 청룡영화상 인기스타상 수상      186cm,76kg 안시성 등등
       3 황정민       1979년 1월 4일 hwang.jpg  2018년 55회 대종상 영화제 남우주연상 수상    180cm,75kg 신세계 등등

4. 삭제
DELETE FROM actor
WHERE actorno = 6;

5. 조회
SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, word
FROM actor
WHERE actorno = 1;

ACTORNO ACTORNAME ACTORBRD    ACTORIMAGE ACTOR_SANG                 PHYSICAL   ACTORWORK
 ------- --------- ----------- ---------- -------------------------- ---------- ---------
       3 황정민       1979년 1월 4일 hwang.jpg  2018년 55회 대종상 영화제 남우주연상 수상 180cm,75kg 신세계 등등

6. 수정
UPDATE actor
SET actorname='강영모', actorbrd = '1982년 1월 4일', thumbs='하이.jpg', files='할루.jpg', sizes=0, actor_sang='수도없이 많음'
			,physical='크다', actorwork='수많은 작품들'
WHERE actorno = 1;

DROP TABLE PG;
CREATE TABLE PG(
  num NUMBER(5) NOT NULL,
  actorname  VARCHAR2(20) NOT NULL,
  PRIMARY KEY(num)
);

SELECT * FROM actor;
-- 검색
 SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word, r
    FROM (
              SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word, rownum as r
              FROM (
                        SELECT actorno, actorname, actorbrd, thumbs, files, sizes, actor_sang, physical, actorwork, s_word
                        FROM actor
                  WHERE actorname AND s_word LIKE '%유해진%'
                  ORDER BY actorno DESC
         )
)
WHERE r >=1 AND r <= 3;


