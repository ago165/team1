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
   * @return ó���� ���ڵ� ����
   */
  public int create(SurveyVO surveyVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list" resultType="SurveyVO">
   * </xmp> 
   * @return
   */
  public List<SurveyVO> list();
  
  /**
   * ��ȸ
   * <xmp>
   *   <select id="read" resultType="SurveyVO" parameterType="int">
   * </xmp>  
   * @param surveyno
   * @return
   */
  public SurveyVO read (int surveyno);
  
  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="SurveyVO"> 
   * </xmp>
   * @param surveyVO
   * @return ó���� ���ڵ� ����
   */
  public int update(SurveyVO surveyVO);
  
  /**
   * ���� ó��
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param surveyno
   * @return ó���� ���ڵ� ����
   */
  public int delete (int surveyno);
  
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<SurveyVO> list_by_survey_search_paging(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word);
  
  public ArrayList<FileVO> getThumbs(SurveyVO surveyVO);
  
}
