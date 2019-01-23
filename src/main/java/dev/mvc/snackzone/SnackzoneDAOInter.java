package dev.mvc.snackzone;

import java.util.HashMap;
import java.util.List;





public interface SnackzoneDAOInter {
  /**
   * <Xmp>
   * ������ �׷� ���
   * <insert id="create" parameterType="SnackzoneVO">
   * </Xmp>
   * @param snackzoneVO
   * @return 
   */
	public int create(SnackzoneVO snackzoneVO);
	
  /**
   * ���
   * <Xmp>
   * <select id="list" resultType="SnackzoneVO">
   * </Xmp>
   * 
   * @return
   */
  public List<SnackzoneVO> list(HashMap<String, Object> hashMap);
  
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * ��ȸ
   * <xmp>
   *   <select id="read" resultType="SnackzoneVO" parameterType="int">
   * </xmp>  
   * @param categrpno
   * @return
   */
  public SnackzoneVO read (int snackno);
  
  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="SnackzoneVO"> 
   * </xmp>
   * @param categrpVO
   * @return ó���� ���ڵ� ����
   */
  public int update(SnackzoneVO snackzoneVO);
  
  /**
   * ����
   * @param snackno
   * @return
   */
  public int delete(int snackno);
  
 /* *//**
   * �˻� ���
   * @param snackno
   * @return
   *//*
  public List<SnackzoneVO> list_search(HashMap hashMap);

  *//**
   *  �˻��� ���ڵ� ����
   * @return
   *//*
  public int search_count(HashMap hashMap);*/
  
}
