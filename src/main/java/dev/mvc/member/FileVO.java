package dev.mvc.member;

public class FileVO {
  private String thumbs;
  private String files;
  private String sizes;

  public FileVO() {

  }

  public FileVO(String thumbs, String files, String sizes) {
    super();
    this.thumbs = thumbs;
    this.files = files;
    this.sizes = sizes;
  }

  /**
   * @return the thumb
   */
  public String getThumbs() {
    return thumbs;
  }

  /**
   * @param thumb the thumb to set
   */
  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }
  /**
   * @return the files
   */
  public String getFiles() {
    return files;
  }

  /**
   * @param files the files to set
   */
  public void setFiles(String files) {
    this.files = files;
  }

  /**
   * @return the sizes
   */
  public String getSizes() {
    return sizes;
  }

  /**
   * @param sizes the sizes to set
   */
  public void setSizes(String sizes) {
    this.sizes = sizes;
  }
  
  
  
}
 