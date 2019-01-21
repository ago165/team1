package dev.mvc.actor;

import java.util.HashMap;
import java.util.List;







public interface ActorProcInter {
  
  /**
   * 등록
   * @param actorVO
   * @return
   */
  public int create(ActorVO actorVO);
  
  /**
   * 목록
   * @return
   */
/*  public List<ActorVO> list(HashMap hashMap);*/
  
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
  public List<ActorVO> list(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param  
   * @param  
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String s_word);
  

  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="ActorVO" parameterType="int">
   * @param actorno 글 번호
   * @return
   */
  public ActorVO read(int actorno);

  /**
   * 수정폼
   * @param actorno 블로그 번호
   * @return
   */
  public int update(ActorVO actorVO);
  


  /**
   * 삭제 처리
   * <delete id="delete" parameterType="int">
   * @param actorno
   * @return
   */
  public int delete(int actorno);
  
  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
/*  public List<ActorVO> list_by_actor_search(HashMap hashMap);*/

}
