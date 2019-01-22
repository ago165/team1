package dev.mvc.movielook;

import java.util.HashMap;
import java.util.List;

public interface MovielookProcInter {
  /**
   * ������ ���
   * @param movielookVO
   * @return
   */
  public int create(MovielookVO movielookVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list" resultType="MovielookVO">
   * </xmp> 
   * @return
   */
  public List<MovielookVO> list();

  /**
   * ���
   * return Join ���
   */
  public List<Movie_MovielookVO>list_by_movie();
  
  /**
   * ��ȸ
   * <xmp>
   *   <select id="read" resultType="CategrpVO" parameterType="int">
   * </xmp>  
   * @param movielookno
   * @return
   */
  public MovielookVO read (int movielookno);
   
  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="MovielookVO"> 
   * </xmp>
   * @param movielookVO
   * @return ó���� ���ڵ� ����
   */
  public int update(MovielookVO movielookVO);
  
  /**
   * ���� ó��
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param movielookno
   * @return ó���� ���ڵ� ����
   */
  public int delete (int movielookno);

/* public int search_count(HashMap hashMap);
  
  public List<MovielookVO> list_search(HashMap<String, Object> hashMap);
  
  public String paging(int search_count, int nowPage, String word);*/
}
