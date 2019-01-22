package dev.mvc.member;

import java.util.HashMap;
import java.util.List;


public interface MemberDAOInter {
  
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkId(String id);
  
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(MemberVO memberVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  /*public List<MemberVO> list();*/
  
  public int search_count(HashMap hashMap);
  
  // 검색목록, 페이징
  public List<MemberVO> list(HashMap<String, Object> hashMap);
  

  /**
   * 조회
   * @param memberno
   * @return
   */
  public MemberVO read(int memberno);

  /**
   * 조회
   * @param id
   * @return
   */
  public MemberVO readById(String id);
  
  
  /**
   * 변경 <update id="update" parameterType="MemberVO">
   * 
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  
  
  
  
  
  
  /**
   * 레코드 1건 삭제
   * <delete id="delete" parameterType="int">
   * @param memberno 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int memberno);
  
  
  
  
  
  
  
  
}







