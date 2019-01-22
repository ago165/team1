package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

public interface SurveyDAOInter {
  
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
}
