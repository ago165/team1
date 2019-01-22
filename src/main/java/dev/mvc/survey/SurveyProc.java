package dev.mvc.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;


@Component("dev.mvc.survey.SurveyProc")
  public class SurveyProc implements SurveyProcInter {
    @Autowired
    @Qualifier("dev.mvc.survey.SurveyDAO")
    private SurveyDAOInter surveyDAO = null;

    public SurveyProc() {
      System.out.println("--> SurveyProc created.");
    }

    @Override
    public int create(SurveyVO surveyVO) {
      int count = surveyDAO.create(surveyVO);
      return count;
    }

    @Override
    public List<SurveyVO> list() {
      List<SurveyVO> list = surveyDAO.list();
      return list;
    }

    @Override
    public SurveyVO read(int surveyno) {
      return surveyDAO.read(surveyno);
    }

    @Override
    public int update(SurveyVO surveyVO) {
      return surveyDAO.update(surveyVO);
    }

    @Override
    public int delete(int surveyno) {
      return surveyDAO.delete(surveyno);
    }
    
    @Override
    public ArrayList<FileVO> getThumbs(SurveyVO surveyVO) {
      ArrayList<FileVO> file_list = new ArrayList<FileVO>();
      
      String thumbs = surveyVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
      String files = surveyVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
      String sizes = surveyVO.getSizes();        // 272558/404087... 
      
      String[] thumbs_array = thumbs.split("/");  // Thumbs
      String[] files_array = files.split("/");   // ���ϸ� ����
      String[] sizes_array = sizes.split("/"); // ���� ������
   
      int count = sizes_array.length;
      // System.out.println("sizes_array.length: " + sizes_array.length);
      // System.out.println("sizes: " + sizes);
      // System.out.println("files: " + files);
   
      if (files.length() > 0) {
        for (int index = 0; index < count; index++) {
          sizes_array[index] = Tool.unit(new Integer(sizes_array[index]));  // 1024 -> 1KB
        
          FileVO fileVO = new FileVO(thumbs_array[index], files_array[index], sizes_array[index]);
          file_list.add(fileVO);
        }
      } 

      return file_list;
    }
    
    @Override
    public int search_count(HashMap hashMap) {
      return surveyDAO.search_count(hashMap);
    }
    
    @Override
    public List<SurveyVO> list_by_survey_search_paging(HashMap<String, Object> hashMap) {
      /* 
       ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
       1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
       2 ������: nowPage = 2, (2 - 1) * 10 --> 10
       3 ������: nowPage = 3, (3 - 1) * 10 --> 20
       */
      int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Survey.RECORD_PER_PAGE;
      
       // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
      int startNum = beginOfPage + 1; 
      //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
      int endNum = beginOfPage + Survey.RECORD_PER_PAGE;   
      /*
       1 ������: WHERE r >= 1 AND r <= 10
       2 ������: WHERE r >= 11 AND r <= 20
       3 ������: WHERE r >= 21 AND r <= 30
       */
      hashMap.put("startNum", startNum); 
      hashMap.put("endNum", endNum);
      
      List<SurveyVO> list = surveyDAO.list_by_survey_search_paging(hashMap); 
      Iterator<SurveyVO> iter = list.iterator();
      
      while(iter.hasNext() == true) {
        SurveyVO surveyVO = iter.next();
        String survettitle = Tool.textLength(surveyVO.getSurveytitle(), 90);
        survettitle = Tool.convertChar(survettitle); // �±� ó��
        surveyVO.setSurveytitle(survettitle);
        
        String thumbs = surveyVO.getThumbs();
        if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
          String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
          surveyVO.setThumbs(thumb);
        }
      }
      
      return list;
    }

    
    /** 
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param categoryno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    @Override
    public String paging(int search_count, int nowPage, String word){ 
      int totalPage = (int)(Math.ceil((double)search_count/Survey.RECORD_PER_PAGE)); // ��ü ������  
      int totalGrp = (int)(Math.ceil((double)totalPage/Survey.PAGE_PER_BLOCK));// ��ü �׷� 
      int nowGrp = (int)(Math.ceil((double)nowPage/Survey.PAGE_PER_BLOCK));    // ���� �׷� 
      int startPage = ((nowGrp - 1) * Survey.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
      int endPage = (nowGrp * Survey.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
       
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
//      str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

      // ���� 10�� �������� �̵�
      // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
      // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� 10
      // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� 20
      int _nowPage = (nowGrp-1) * Survey.PAGE_PER_BLOCK;  
      if (nowGrp >= 2){ 
        str.append("<span class='span_box_1'><A href='./list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 

      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          str.append("<span class='span_box_1'><A href='./list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 

      // 10�� ���� �������� �̵�
      // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
      // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
      // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
      _nowPage = (nowGrp * Survey.PAGE_PER_BLOCK)+1;  
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
       
      return str.toString(); 
    }
}
