package dev.mvc.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.event.EventProc")
public class EventProc implements EventProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.event.EventDAO")
  private EventDAOInter eventDAO = null;
   
  public EventProc() {
    System.out.println("--> EventProc created");
  }
  
  @Override
  public int create(EventVO eventVO) {
    
    int count = eventDAO.create(eventVO);    
    return count;
  }
  
  @Override
  public List<EventVO> list() {
    List<EventVO> list = eventDAO.list();
    return list;
  }
  
  /**
   * �̹��� ����߿� ù��° �̹��� ���ϸ��� �����Ͽ� ����
   * @param eventVO
   * @return
   */
  public String getThumb(EventVO eventVO) {
    String thumbs = eventVO.getThumb();
    String thumb = "";
    
    if (thumb != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    return thumb;
  }
  
  @Override
  public EventVO read(int eventno) {
    return eventDAO.read(eventno);
  }
  
  @Override
  public int update(EventVO eventVO) {
    return eventDAO.update(eventVO);
  }
  
  @Override
  public int delete(int eventno) {
    return eventDAO.delete(eventno);
  }
  
  @Override
  public int increaseCnt(int eventno) {
    return eventDAO.increaseCnt(eventno);
  }

  @Override
  public int decreaseCnt(int eventno) {
    return eventDAO.decreaseCnt(eventno);
  }
  
  @Override
  public List<EventVO> list_by_category_search(HashMap hashMap) {
    List<EventVO> list = eventDAO.list_by_category_search(hashMap);
    
    int count = list.size();
    for (int i=0; i < count; i++) {
      EventVO eventVO = list.get(i);
      eventVO.setThumb(getThumb(eventVO));
    }
    
    return list;
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return eventDAO.search_count(hashMap);
  }
  
  @Override
  public List<EventVO> list_by_category_search_paging(HashMap<String, Object> hashMap) {
    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Events.RECORD_PER_PAGE; 
    int startNum = beginOfPage + 1; 
    int endNum = beginOfPage + Events.RECORD_PER_PAGE;   

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<EventVO> list = eventDAO.list_by_category_search_paging(hashMap); 
    Iterator<EventVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      EventVO eventVO = iter.next();
      String title = Tool.textLength(eventVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      eventVO.setTitle(title);
      
      String thumb = eventVO.getThumb();
      if (thumb.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb1 = (thumb.split("/"))[0]; // ù��° ���ϸ� ����
        eventVO.setThumb(thumb1);
      }
    }
    
    return list;
  }
 
  @Override
  public String paging(int search_count, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)search_count/Events.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Events.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Events.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Events.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Events.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 

    int _nowPage = (nowGrp-1) * Events.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_category_search_paging.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_by_category_search_paging.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
    _nowPage = (nowGrp * Events.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_category_search_paging.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
}
 
