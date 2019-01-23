package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

public interface EventDAOInter {

  /**
   * ���
   * @param eventVO
   * @return
   */
  public abstract int create(EventVO eventVO);
  
  /**
   * ���
   * @return
   */
  public List<EventVO> list();
  
  /**
   * ��ȸ
   * @param evevtno
   * @return
   */
  public EventVO read(int evevtno);
  
  /**
   * ����
   * @param eventVO
   * @return
   */
  public int update(EventVO eventVO);
  
  /**
   * ����
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  
  /**
   * �ۼ� ����
   * @param eventno
   * @return
   */
  public int increaseCnt(int eventno);

  /**
   * �ۼ� ����
   * @param eventno
   * @return
   */
  public int decreaseCnt(int eventno);
  
  /**
   * �˻� ���
   * @param hashMap
   * @return
   */
  public List<EventVO> list_by_category_search(HashMap hashMap);
  
  /**
   * event �� �˻��� ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
 
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<EventVO> list_by_category_search_paging(HashMap<String, Object> hashMap);
}
