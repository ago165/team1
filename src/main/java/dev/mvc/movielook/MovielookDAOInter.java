package dev.mvc.movielook;

import java.util.HashMap;
import java.util.List;

public interface MovielookDAOInter {

  /**
   * 컨텐츠 등록
   * @param movielookVO
   * @return
   */
  public int create(MovielookVO movielookVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="MovielookVO">
   * </xmp> 
   * @return
   */
  public List<MovielookVO> list();
  
  /**
   * 목록
   * return Join 목록
   */
  public List<Movie_MovielookVO>list_by_movie();
  
  /**
   * 조회
   * <xmp>
   *   <select id="read" resultType="MovielookVO" parameterType="int">
   * </xmp>  
   * @param movielookno
   * @return
   */
  public MovielookVO read (int movielookno);
  
  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="MovielookVO"> 
   * </xmp>
   * @param movielookVO
   * @return 처리된 레코드 갯수
   */
  public int update(MovielookVO movielookVO);
  
  /**
   * 삭제 처리
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param movielookno
   * @return 처리된 레코드 갯수
   */
  public int delete (int movielookno);

}
