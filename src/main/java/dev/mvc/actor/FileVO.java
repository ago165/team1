package dev.mvc.actor;

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
   * @return the file
   */
  public String getFiles() {
    return files;
  }

  /**
   * @param file the file to set
   */
  public void setFile(String files) {
    this.files = files;
  }

  /**
   * @return the size
   */
  public String getSizes() {
    return sizes;
  }

  /**
   * @param size the size to set
   */
  public void setSize(String sizes) {
    this.sizes = sizes;
  }
  
  
  
}
 