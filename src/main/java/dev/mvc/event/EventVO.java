package dev.mvc.event;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class EventVO {
  
/*  eventno     NUMBER(35)      NOT NULL, -- 이벤트 번호
  title       VARCHAR(100)     NOT NULL, -- 제목
  content     VARCHAR(200)    NOT NULL, -- 내용
  thumb       VARCHAR(500)     NOT NULL, -- 사진
  file1       VARCHAR(500)     NOT NULL, -- 파일
  startdate   DATE            NOT NULL, -- 기간 시작일         
  enddate     DATE            NOT NULL, -- 기간 종료일 
  cnt         NUMBER(10)      NOT NULL, -- 조회 수
  rdate       DATE            NOT NULL, -- 등록일
  seqno       NUMBER(10)      NOT NULL, -- 출력 순서
  grpno       NUMBER(10)      NOT NULL, -- 그룹 번호
  size1       NUMBER(30)    NOT NULL, -- 파일 크기                  
  entry       NUMBER(10)      NOT NULL, -- 응모*/
  
  private int eventno;
  private String title;
  private String content;
  private String gift;
  private String thumb = "";
  private String file1 = "";
  private String word;
  private String startdate;
  private String enddate;
  private int cnt;
  private String rdate;
  private int seqno;
  private int grpno;
  private String size1;
  private int entry;
  
  /*private MultipartFile file1MF; */
  
  private List<MultipartFile> file1MF;
  
  public int getEventno() {
    return eventno;
  }
  public void setEventno(int eventno) {
    this.eventno = eventno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getGift() {
    return gift;
  }
  public void setGift(String gift) {
    this.gift = gift;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public int getGrpno() {
    return grpno;
  }
  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }
  public String getSize1() {
    return size1;
  }
  public void setSize1(String size1) {
    this.size1 = size1;
  }
  public int getEntry() {
    return entry;
  }
  public void setEntry(int entry) {
    this.entry = entry;
  }
  /*public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }*/
  
  public List<MultipartFile> getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(List<MultipartFile> file1MF) {
    this.file1MF = file1MF;
  }
}
 
