package dev.mvc.movielook;

import java.util.HashMap;
import java.util.List;

public interface MovielookProcInter {
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
   *   <select id="read" resultType="CategrpVO" parameterType="int">
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

/* public int search_count(HashMap hashMap);
  
  public List<MovielookVO> list_search(HashMap<String, Object> hashMap);
  
  public String paging(int search_count, int nowPage, String word);*/
}
