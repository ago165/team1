package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

public interface EventProcInter {

  /**
   * 등록
   * @param eventVO
   * @return
   */
  public abstract int create(EventVO eventVO);
  
  /**
   * 목록
   * @return
   */
  public List<EventVO> list();
  
  /**
   * 조회
   * @param evevtno
   * @return
   */
  public EventVO read(int evevtno);
  
  /**
   * 수정
   * @param eventVO
   * @return
   */
  public int update(EventVO eventVO);
  
  /**
   * 삭제
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  
  /**
   * 글수 증가
   * @param eventno
   * @return
   */
  public int increaseCnt(int eventno);

  /**
   * 글수 감소
   * @param eventno
   * @return
   */
  public int decreaseCnt(int eventno);

  
  /**
   * 검색 목록
   * @param hashMap
   * @return
   */
  public List<EventVO> list_by_category_search(HashMap hashMap);
  
  /**
   * event 별 검색된 레코드 개수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<EventVO> list_by_category_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * span 태그를 이용한 박스 모델의 지원
   * @param categoryno
   * @param search_count
   * @param nowPage
   * @param word
   * @return
   */
  public String paging(int search_count, int nowPage, String word);
}
