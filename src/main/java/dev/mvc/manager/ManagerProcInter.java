package dev.mvc.manager;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpSession;

import dev.mvc.manager.ManagerVO;






public interface ManagerProcInter {

  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkId(String id);
  
  /**
  관리자 등록
  @param managerVO
  @return 등록된 관리자수 1 or 0
  */
  public int create(ManagerVO managerVO);
  
  /**
   * 관리자 전체 목록
   * @return
   */
/*    public List<ManagerVO> list();*/
  
  // 검색된 레코드 갯수
  public int search_count(HashMap hashMap);
  
  // 검색 목록 + 페이징
  public List<ManagerVO> list(HashMap<String, Object> hashMap);
  
  public String paging(int search_count, int nowPage, String s_word);

  
  /**
   * 로그인된 관리자 계정인지 검사합니다.
   * @param request
   * @return true: 관리자
   */
  public boolean isManager(HttpSession session);
  
  /**
   * 1개의 레코드 조회
   * @param managerno
   * @return
   */
  public ManagerVO read(int managerno);

  /**
   * 아이디 조회
   * @param id
   * @return
   */
  public ManagerVO readById(String id);

/**
* 수정
* @param managerVO
* @return
*/
  public int update(ManagerVO managerVO);
  
  
  
  
  
  
  
  
  /**
   * 레코드 1건 삭제
   * <delete id="delete" parameterType="int">
   * @param managerno 삭제할 관리자 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int managerno);
  
  
  
  
  
  
  
  
  

}




