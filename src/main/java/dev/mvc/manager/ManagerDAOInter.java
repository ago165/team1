package dev.mvc.manager;

import java.util.HashMap;
import java.util.List;


public interface ManagerDAOInter {
  
  /**
   * �ߺ� ���̵� �˻�
   * @param id
   * @return �ߺ� ���̵� ����
   */
  public int checkId(String id);
  
  /**
  ������ ���
  @param managerVO
  @return ��ϵ� �����ڼ� 1 or 0
  */
  public int create(ManagerVO managerVO);
  
  /**
   * ������ ��ü ���
   * @return
   */
  /*public List<ManagerVO> list();*/
  
  public int search_count(HashMap hashMap);
  
  // �˻����, ����¡
  public List<ManagerVO> list(HashMap<String, Object> hashMap);
  

  /**
   * ��ȸ
   * @param managerno
   * @return
   */
  public ManagerVO read(int managerno);

  /**
   * ��ȸ
   * @param id
   * @return
   */
  public ManagerVO readById(String id);
  
  
  /**
   * ���� <update id="update" parameterType="ManagerVO">
   * 
   * @param managerVO
   * @return
   */
  public int update(ManagerVO managerVO);
  
  
  
  
  
  
  
  /**
   * ���ڵ� 1�� ����
   * <delete id="delete" parameterType="int">
   * @param managerno ������ ������ ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int managerno);
  
  
  
  
  
  
  
  
}



