package dev.mvc.snackzone;

import java.util.HashMap;
import java.util.List;



public interface SnackzoneProcInter {
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
   * 검색목록 + 페이징
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word);
  
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
   * 수정처리
   * @param vo
   * @return
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
