package dev.mvc.snackzone;

import java.util.HashMap;
import java.util.List;





public interface SnackzoneDAOInter {
  /**
   * <Xmp>
   * 스낵존 그룹 등록
   * <insert id="create" parameterType="SnackzoneVO">
   * </Xmp>
   * @param snackzoneVO
   * @return 
   */
	public int create(SnackzoneVO snackzoneVO);
	
  /**
   * 목록
   * <Xmp>
   * <select id="list" resultType="SnackzoneVO">
   * </Xmp>
   * 
   * @return
   */
  public List<SnackzoneVO> list(HashMap<String, Object> hashMap);
  
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 조회
   * <xmp>
   *   <select id="read" resultType="SnackzoneVO" parameterType="int">
   * </xmp>  
   * @param categrpno
   * @return
   */
  public SnackzoneVO read (int snackno);
  
  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="SnackzoneVO"> 
   * </xmp>
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(SnackzoneVO snackzoneVO);
  
  /**
   * 삭제
   * @param snackno
   * @return
   */
  public int delete(int snackno);
  
 /* *//**
   * 검색 목록
   * @param snackno
   * @return
   *//*
  public List<SnackzoneVO> list_search(HashMap hashMap);

  *//**
   *  검색된 레코드 갯수
   * @return
   *//*
  public int search_count(HashMap hashMap);*/
  
}
