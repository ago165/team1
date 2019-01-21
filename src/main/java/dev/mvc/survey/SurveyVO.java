package dev.mvc.survey;
/*  surveyno            NUMBER(10) NOT NULL PRIMARY KEY,
surveytitle          VARCHAR(200) NOT NULL,
startdate           VARCHAR(30) NOT NULL,
enddate            VARCHAR(30) NOT NULL,
partycnt            NUMBER(10) NOT NULL,
surveystatus       CHAR(1) DEFAULT 'Y' NOT NULL

COMMENT ON COLUMN survey.surveyno is '���� ���� ��ȣ';
COMMENT ON COLUMN survey.surveytitle is '���� ���� ����';
COMMENT ON COLUMN survey.startdate is '���� ��¥';
COMMENT ON COLUMN survey.enddate is '���� ��¥';
COMMENT ON COLUMN survey.partycnt is '���� �ο�';
COMMENT ON COLUMN survey.surveystatus is '���� ���� ����';*/

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SurveyVO {

  private int surveyno;
  private String surveytitle;
  private String startdate;
  private String enddate;
  private int partycnt;
  private String surveystatus;
  private String thumbs;
  private String files;
  private String sizes;
  private String word;
  
  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public SurveyVO() {
    
  }

  private List<MultipartFile> filesMF;

  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String sizesLabel;
  
  
  public String getThumbs() {
    return thumbs;
  }

  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }

  public String getSizes() {
    return sizes;
  }

  public void setSizes(String sizes) {
    this.sizes = sizes;
  }
    
  public int getSurveyno() {
    return surveyno;
  }

  public void setSurveyno(int surveyno) {
    this.surveyno = surveyno;
  }

  public String getSurveytitle() {
    return surveytitle;
  }

  public void setSurveytitle(String surveytitle) {
    this.surveytitle = surveytitle;
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

  public int getPartycnt() {
    return partycnt;
  }

  public void setPartycnt(int partycnt) {
    this.partycnt = partycnt;
  }

  public String getSurveystatus() {
    return surveystatus;
  }

  public void setSurveystatus(String surveystatus) {
    this.surveystatus = surveystatus;
  }

  
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }

  public String getSizesLabel() {
    return sizesLabel;
  }

  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }
  
  
  
} 


