package dev.mvc.member;

import java.util.HashMap;
import java.util.List;


public interface MemberDAOInter {
  
  /**
   * �ߺ� ���̵� �˻�
   * @param id
   * @return �ߺ� ���̵� ����
   */
  public int checkId(String id);
  
  /**
  ȸ�� ���
  @param memberVO
  @return ��ϵ� ȸ���� 1 or 0
  */
  public int create(MemberVO memberVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  /*public List<MemberVO> list();*/
  
  public int search_count(HashMap hashMap);
  
  // �˻����, ����¡
  public List<MemberVO> list(HashMap<String, Object> hashMap);
  

  /**
   * ��ȸ
   * @param memberno
   * @return
   */
  public MemberVO read(int memberno);

  /**
   * ��ȸ
   * @param id
   * @return
   */
  public MemberVO readById(String id);
  
  
  /**
   * ���� <update id="update" parameterType="MemberVO">
   * 
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  
  
  
  
  
  
  /**
   * ���ڵ� 1�� ����
   * <delete id="delete" parameterType="int">
   * @param memberno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int memberno);
  
  
  
  
  
  
  
  
}







