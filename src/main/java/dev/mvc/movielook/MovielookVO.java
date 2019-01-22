package dev.mvc.movielook;

/*
 * CREATE TABLE movielook(
    movielookno NUMBER(10) PRIMARY KEY,
    starttime      VARCHAR(100)  NOT NULL,
    endtime       VARCHAR(100)  NOT NULL,
    pay             VARCHAR(100) NOT NULL,
    point           VARCHAR(100) NOT NULL,
    FOREIGN KEY (movieno) REFERENCES movie (movieno),
    FOREIGN KEY (memberno) REFERENCES member (memberno)
);
*/

public class MovielookVO {
 private int movielookno; // ��û��ȣ
 private String starttime;  // ���۽ð�
 private String endtime;   // ����ð�
 private String pay;         // �������
 private String point;       // ����Ʈ
 private int movieno;       // ��ȭ��ȣ
 private int memberno;     // ȸ����ȣ
 private String word;
 public MovielookVO(){
   
 }

public String getWord() {
  return word;
}

public void setWord(String word) {
  this.word = word;
}

public int getMovielookno() {
  return movielookno;
}

public void setMovielookno(int movielookno) {
  this.movielookno = movielookno;
}

public String getStarttime() {
  return starttime;
}

public void setStarttime(String starttime) {
  this.starttime = starttime;
}

public String getEndtime() {
  return endtime;
}

public void setEndtime(String endtime) {
  this.endtime = endtime;
}

public String getPay() {
  return pay;
}

public void setPay(String pay) {
  this.pay = pay;
}

public String getPoint() {
  return point;
}

public void setPoint(String point) {
  this.point = point;
}

public int getMovieno() {
  return movieno;
}

public void setMovieno(int movieno) {
  this.movieno = movieno;
}

public int getMemberno() {
  return memberno;
}

public void setMemberno(int memberno) {
  this.memberno = memberno;
}


 
 
}
