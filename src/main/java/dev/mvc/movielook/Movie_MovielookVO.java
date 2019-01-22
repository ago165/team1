package dev.mvc.movielook;

/*
 * SELECT DISTINCT m.movieno, m.title, m.mp4, 
 * l.movielookno, l.starttime, l.endtime, l.pay, l.point, l.movieno
FROM movie m, movielook l
WHERE m.movieno = l.movieno;
*/

public class Movie_MovielookVO {
  //movie table
  private int movieno;
  private String title;
  private String mp4;
  
  //movielook table
  private int movielookno; // 시청번호
  private String starttime;  // 시작시간
  private String endtime;   // 종료시간
  private String pay;         // 결재수단
  private String point;       // 포인트
  private int memberno;     // 회원번호
  
  public int getMovieno() {
    return movieno;
  }
  public void setMovieno(int movieno) {
    this.movieno = movieno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getMp4() {
    return mp4;
  }
  public void setMp4(String mp4) {
    this.mp4 = mp4;
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
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

}
