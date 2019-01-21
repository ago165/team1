package dev.mvc.actor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*CREATE TABLE actor(
    actorno NUMBER(30) NOT NULL,
    actorname VARCHAR(10) NOT NULL,
    actorbrd VARCHAR(30) NOT NULL,
    actorimage VARCHAR(1000) NOT NULL,
    actor_sang VARCHAR(500) NOT NULL,
    physical VARCHAR(100) NOT NULL,
    actorwork VARCHAR(500) NOT NULL,
  PRIMARY KEY(actorno)
);*/

public class ActorVO {
  private int actorno;
  private String actorname;
  private String actorbrd;
  private String thumbs;
  private String files;
  private long sizes;
  private String actor_sang;
  private String physical;
  private String actorwork;
  private MultipartFile filesMF;
  private String thumb = "";
  private String s_word;
  public ActorVO() {
    
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

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

  public MultipartFile getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(MultipartFile filesMF) {
    this.filesMF = filesMF;
  }

  public int getActorno() {
    return actorno;
  }

  public void setActorno(int actorno) {
    this.actorno = actorno;
  }

  public String getActorname() {
    return actorname;
  }

  public void setActorname(String actorname) {
    this.actorname = actorname;
  }

  public String getActorbrd() {
    return actorbrd;
  }

  public void setActorbrd(String actorbrd) {
    this.actorbrd = actorbrd;
  }

  public String getActor_sang() {
    return actor_sang;
  }

  public void setActor_sang(String actor_sang) {
    this.actor_sang = actor_sang;
  }

  public String getPhysical() {
    return physical;
  }

  public void setPhysical(String physical) {
    this.physical = physical;
  }

  public String getActorwork() {
    return actorwork;
  }

  public void setActorwork(String actorwork) {
    this.actorwork = actorwork;
  }


  
  
}
