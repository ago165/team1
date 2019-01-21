package dev.mvc.actor;

import java.util.HashMap;
import java.util.List;


public interface ActorDAOInter {
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
   * actor별 검색된 레코드 갯수
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
   * 한건의 레코드 조회
   * <select id="read" resultType="ActorVO" parameterType="int">
   * @param actorno 글 번호
   * @return
   */
  public ActorVO read(int actorno);

  /**
   * 수정처리
   * @param vo
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
   * @param actorno
   * @return
   */
/*  public List<ActorVO> list_by_actor_search(HashMap hashMap);*/
  
}
  

