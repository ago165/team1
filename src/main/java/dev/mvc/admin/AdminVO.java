package dev.mvc.admin;


/*1. 테이블 생성
CREATE TABLE admin(
    adminno NUMBER(10) NOT NULL, -- 관리자 번호
    id VARCHAR(60) NOT NULL,         -- 아이디
    passwd VARCHAR(10) NOT NULL,  -- 비밀 번호
    auth VARCHAR(30) NOT NULL,      -- 가입키
    act CHAR(1) DEFAULT 'N' NOT NULL,   -- M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기, G: 손님, C: 탈퇴
    mdate DATE NOT NULL,            -- 가입일
    tel VARCHAR(20) NOT NULL,     -- 전화 번호
    name VARCHAR(30) NOT NULL,  -- 성명
  PRIMARY KEY(adminno)
);*/
public class AdminVO {
  private int adminno;
  private String id;
  private String passwd;
  private String auth;
  private String act;
  private String mdate;
  private String tel;
  private String name;
  
  public AdminVO(){
    
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getAct() {
    return act;
  }

  public void setAct(String act) {
    this.act = act;
  }

  public String getMdate() {
    return mdate;
  }

  public void setMdate(String mdate) {
    this.mdate = mdate;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}













