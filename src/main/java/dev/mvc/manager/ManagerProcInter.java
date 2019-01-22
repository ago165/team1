package dev.mvc.manager;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpSession;

import dev.mvc.manager.ManagerVO;






public interface ManagerProcInter {

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
/*    public List<ManagerVO> list();*/
  
  // �˻��� ���ڵ� ����
  public int search_count(HashMap hashMap);
  
  // �˻� ��� + ����¡
  public List<ManagerVO> list(HashMap<String, Object> hashMap);
  
  public String paging(int search_count, int nowPage, String s_word);

  
  /**
   * �α��ε� ������ �������� �˻��մϴ�.
   * @param request
   * @return true: ������
   */
  public boolean isManager(HttpSession session);
  
  /**
   * 1���� ���ڵ� ��ȸ
   * @param managerno
   * @return
   */
  public ManagerVO read(int managerno);

  /**
   * ���̵� ��ȸ
   * @param id
   * @return
   */
  public ManagerVO readById(String id);

/**
* ����
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




