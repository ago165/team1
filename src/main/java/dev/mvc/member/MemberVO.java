package dev.mvc.member;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*
memberno     NUMBER(30)    NOT NULL, -- ȸ�� ��ȣ
id           VARCHAR(20)          UNIQUE NOT NULL, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 ���� 
passwd    VARCHAR(50)         NOT NULL,  -- ��й�ȣ
mname    VARCHAR(20)         NOT NULL, -- �̸�, �ѱ� 10�� ���� ����
thumbs    VARCHAR(1000)          NULL, -- �����
files      VARCHAR(1000)             NULL, -- ����
sizes     VARCHAR(1000)             NULL, -- ������
tel           VARCHAR(14)         NOT NULL, -- ��ȭ��ȣ
zipcode   VARCHAR(5)           NULL, -- �����ȣ, 12345
address1  VARCHAR(80)         NULL, -- �ּ� 1
address2  VARCHAR(50)         NULL, -- �ּ� 2
mdate      DATE                     NOT NULL, -- ������
*/

public class MemberVO {
  private int memberno;
  private String id;
  private String passwd;
  private String mname;
  private String thumbs;
  private String files;
  private long sizes;
  private String tel;
  private String zipcode;
  private String address1;
  private String address2;
  private String mdate;
  private MultipartFile filesMF;
  private String thumb = "";
  private String s_word;
  
  public MemberVO() {
    
  }
  
  /** ��ϵ� �н����� */
  private String old_passwd = "";
  /** id ���� ���� */
  private String id_save = "";
  /** passwd ���� ���� */
  private String passwd_save = "";
  /** �̵��� �ּ� ���� */
  private String url_address = "";
  

  public String getS_word() {
    return s_word;
  }

  public void setS_word(String s_word) {
    this.s_word = s_word;
  }
  
  public String getThumbs() {
    return thumbs;
  }

  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }
  
  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  
  
  public int getMemberno() {
    return memberno;
  }
  
  public void setMemberno(int memberno) {
    this.memberno = memberno;
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
  
  public String getMname() {
    return mname;
  }
  
  public void setMname(String mname) {
    this.mname = mname;
  }
  
  public String getFiles() {
    return files;
  }
  
  public void setFiles(String files) {
    this.files = files;
  }
  
  public long getSizes() {
    return sizes;
  }
  
  public void setSizes(long sizes) {
    this.sizes = sizes;
  }
  
  public String getTel() {
    return tel;
  }
  
  public void setTel(String tel) {
    this.tel = tel;
  }
  
  public String getZipcode() {
    return zipcode;
  }
  
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  
  public String getAddress1() {
    return address1;
  }
  
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  
  public String getAddress2() {
    return address2;
  }
  
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  
  public String getMdate() {
    return mdate;
  }
  
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  
  public String getOld_passwd() {
    return old_passwd;
  }
  
  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }
  
  public String getId_save() {
    return id_save;
  }
  
  public void setId_save(String id_save) {
    this.id_save = id_save;
  }
  
  public String getPasswd_save() {
    return passwd_save;
  }
  
  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }
  
  public String getUrl_address() {
    return url_address;
  }
  
  public void setUrl_address(String url_address) {
    this.url_address = url_address;
  }
  
  public MultipartFile getFilesMF() {
    return filesMF;
  }
  
  public void setFilesMF(MultipartFile filesMF) {
    this.filesMF = filesMF;
  }
  
  
 
 
}