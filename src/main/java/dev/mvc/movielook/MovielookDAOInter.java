package dev.mvc.movielook;

import java.util.HashMap;
import java.util.List;

public interface MovielookDAOInter {

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
   *   <select id="read" resultType="MovielookVO" parameterType="int">
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

}
