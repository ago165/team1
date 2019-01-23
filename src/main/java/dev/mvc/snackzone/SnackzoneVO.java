package dev.mvc.snackzone;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*
 snackno                       		NUMBER(10)		 NOT NULL, 
 kind                          		NUMBER(10)		 NOT NULL,  
 snack_name                    		VARCHAR(50)     NOT NULL,  
 seqno                         		NUMBER(10)		 NOT NULL, 
 */

public class SnackzoneVO {
	/** 스낵존 번호 */
	private int snackno;
	/** 스낵종류 번호 */
	private int kind;
	/** 스낵 종류 이름 */
	private String name;
	/** 가격 */
	private int price;
	/** 구성 */
	private String composition;
	/** 유효 기간 */
	private String term;
	/** 원산지 */
	private String orgin;
	/** 내용 */
	private String contents;
	/** 썸네일 */
	private String thumb;
	/** 파일명 */
	private String file1;
	/** 파일 크기 */
	private String size1;
	/** 출력 순서 */
	private int seqno;
	
	/** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  */  
  private List<MultipartFile> file1MF;
  
  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size1Label;
	
	public SnackzoneVO() {
	  
	}

  public int getSnackno() {
    return snackno;
  }

  public void setSnackno(int snackno) {
    this.snackno = snackno;
  }

  public int getKind() {
    return kind;
  }

  public void setKind(int kind) {
    this.kind = kind;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getOrgin() {
    return orgin;
  }

  public void setOrgin(String orgin) {
    this.orgin = orgin;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
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

  public String getSize1() {
    return size1;
  }

  public void setSize1(String size1) {
    this.size1 = size1;
  }

  public int getSeqno() {
    return seqno;
  }

  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }

  public List<MultipartFile> getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(List<MultipartFile> file1mf) {
    file1MF = file1mf;
  }

  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  
	
}
