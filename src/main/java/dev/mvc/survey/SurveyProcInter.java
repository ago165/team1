package dev.mvc.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SurveyProcInter {
  
  /**
   * <Xmp>
   * <insert id="create" parameterType="SurveyVO">
   * </Xmp>
   * @param surveyVO
   * @return 처리된 레코드 갯수
   */
  public int create(SurveyVO surveyVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="SurveyVO">
   * </xmp> 
   * @return
   */
  public List<SurveyVO> list();
  
  /**
   * 조회
   * <xmp>
   *   <select id="read" resultType="SurveyVO" parameterType="int">
   * </xmp>  
   * @param surveyno
   * @return
   */
  public SurveyVO read (int surveyno);
  
  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="SurveyVO"> 
   * </xmp>
   * @param surveyVO
   * @return 처리된 레코드 갯수
   */
  public int update(SurveyVO surveyVO);
  
  /**
   * 삭제 처리
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param surveyno
   * @return 처리된 레코드 갯수
   */
  public int delete (int surveyno);
  
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<SurveyVO> list_by_survey_search_paging(HashMap<String, Object> hashMap);
  
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
  
  public ArrayList<FileVO> getThumbs(SurveyVO surveyVO);
  
}
