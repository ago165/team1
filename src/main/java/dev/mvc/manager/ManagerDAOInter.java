package dev.mvc.manager;

import java.util.HashMap;
import java.util.List;


public interface ManagerDAOInter {
  
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
  /*public List<ManagerVO> list();*/
  
  public int search_count(HashMap hashMap);
  
  // 검색목록, 페이징
  public List<ManagerVO> list(HashMap<String, Object> hashMap);
  

  /**
   * 조회
   * @param managerno
   * @return
   */
  public ManagerVO read(int managerno);

  /**
   * 조회
   * @param id
   * @return
   */
  public ManagerVO readById(String id);
  
  
  /**
   * 변경 <update id="update" parameterType="ManagerVO">
   * 
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



