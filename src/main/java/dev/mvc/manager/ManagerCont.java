package dev.mvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.manager.ManagerVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ManagerCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc = null;
   
  public ManagerCont(){
    System.out.println("--> ManagerCont created.");
    
  }  
  
  /**
   * �ߺ� ID �˻�
   * http://localhost:9090/ojt/manager/checkId.do?id=manager1
   * ���: {"count":1}
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/manager/checkId.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
    System.out.println("--> checkId() GET executed");    
    JSONObject obj = new JSONObject();   
    
    int count = managerProc.checkId(id);
    
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  
  
  // http://localhost:9090/manager/create.do
  @RequestMapping(value = "/manager/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET excuted");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/create"); // webapp/manager/create.jsp

    return mav;
  }  

  @RequestMapping(value="/manager/create.do", method=RequestMethod.POST)
  public ModelAndView create (HttpServletRequest request, ManagerVO managerVO){
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    /*mav.setViewName("/manager/message"); // webapp/manager/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    MultipartFile filesMF = managerVO.getFilesMF();
    String files = ""; // �÷��� ������ ���ϸ�
    long sizes = filesMF.getSize();
    String thumbs = ""; // �÷��� ������ ���ϸ�
    
    //long count = filesMF.getSizes();  
    
    if (sizes > 0) {
      files = Upload.saveFileSpring(filesMF, upDir);

      if (Tool.isImage(files)) {
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb �̹��� ����
      }
    }
    managerVO.setFiles(files);
    managerVO.setSizes(sizes);
    managerVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    int count = managerProc.create(managerVO);
    
    mav.setViewName("redirect:/manager/create_message.jsp?count=" + count);

   /* String root = request.getContextPath();

    // �ߺ� ���̵� �˻�
    int count = managerProc.checkId(managerVO.getId());
    if (count > 0) {
      msgs.add("ID�� �ߺ��˴ϴ�.");
      msgs.add("������ �����ڰ��������ּ���. �� 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    } else {
      // ������ ���� ó��
      if (managerProc.create(managerVO) == 1) {
        msgs.add("������ ������ �Ϸ�Ǿ����ϴ�.");
        msgs.add("������ �ּż� �����մϴ�.");
        links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
        
      } else {
        msgs.add("������ ���Կ� �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
        
      }
      
    }
    
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/
    
    return mav;
  }
   
  @RequestMapping(value="/manager/list.do", method=RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="s_word", defaultValue="") String s_word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ){
    // System.out.println("--> create() GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/manager/list"); // webapp/manager/list.jsp

    /*    if (Tool.isManager(session) == false) {
    mav.setViewName("redirect:/manager/login.jsp"); // d 

   
   } else {*/
         
  // List<ManagerVO> list = managerProc.list();
  // mav.addObject("list", list);
 
   
// }

   // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   // hashMap.put("managerno", managerno); // #{categoryno}
   hashMap.put("s_word", s_word); // #{word}
   hashMap.put("nowPage", nowPage);
   
   
// �˻� ���
   List<ManagerVO> list = managerProc.list(hashMap);
   mav.addObject("list", list);

   int search_count = managerProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   String paging = managerProc.paging(search_count, nowPage, s_word);
   mav.addObject("paging", paging);
 
   mav.addObject("nowPage", nowPage);
   
 return mav;
  }  
  
// ��ȸ http://localhost:9090/movie/manager/read.do
  @RequestMapping(value="/manager/read.do", method=RequestMethod.GET)
  public ModelAndView read(int managerno){
    System.out.println("--> read(int managerno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/read"); // webapp/manager/read.jsp
    
    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);
    System.out.println("mav : " + mav);
    return mav;       
  }  
 
// ���� �߰� get ���!
@RequestMapping(value = "/manager/update.do", method = RequestMethod.GET)
  public ModelAndView update(int managerno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/update");
    

    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);


    return mav;
  }

// ���� http://localhost:9090/movie/manager/update.do
  @RequestMapping(value="/manager/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, ManagerVO managerVO){
    System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/message"); // webapp/manager/message.jsp
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    
 // ---------------------------------------------------------------------------
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ��
     name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
    ��
    BlogVO.java: private MultipartFile file1MF;
    ��
     ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile filesMF = managerVO.getFilesMF();
    String files = "";                    // DBMS file1 �÷��� ��
    long sizes = filesMF.getSize(); // ���� ũ��
    String thumbs = "";                 // DBMS thumb �÷��� ��
    
    // ������ ��ϵ� �� ���� �ε�
    ManagerVO managerVO_bf = managerProc.read(managerVO.getManagerno());
    
    if (sizes > 0) { // ��ϵ� ������ �ִٸ�
      Tool.deleteFile(upDir, managerVO_bf.getFiles());    // ���� ���� ����
      Tool.deleteFile(upDir, managerVO_bf.getThumbs()); // ���� Thumb ���� ����
      
      files = Upload.saveFileSpring(filesMF, upDir); // �ű� ���� ���ε�
      
      if (Tool.isImage(files)) { // ���ο� ���� �̹������� �˻�
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb �̹��� ����
      } 
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      files = managerVO_bf.getFiles();
      sizes = managerVO_bf.getSizes();
      thumbs = managerVO_bf.getThumbs();
    }
    managerVO.setFiles(files);
    managerVO.setSizes(sizes);
    managerVO.setThumbs(thumbs);
    // ---------------------------------------------------------------------------
    
    String root = request.getContextPath();    
    int count = managerProc.update(managerVO);
    
/*    redirectAttributes.addAttribute("count", count);
    redirectAttributes.addAttribute("managerno", managerVO.getManagerno());*/
    
    mav.setViewName("redirect:/manager/update_message.jsp?count="+ count);
    
/*    if (managerProc.update(managerVO) == 1) {
      msgs.add("������ ������ �Ϸ�Ǿ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./read.do?managerno="+managerVO.getManagerno()+"'\">���� ���� Ȯ��</button>");
      
    } else {
      msgs.add("������ ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      
    }
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/
    
    return mav;
  }
  
/*  // http://localhost:9090/manager/manager/passwd_update.do?managerno=1
  @RequestMapping(value="/manager/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(int managerno){
    System.out.println("--> passwd_update(int managerno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/passwd_update"); // passwd_update.jsp

    mav.addObject("managerno", managerno);
    
    return mav;
  }  */
  
  
  
  
  
  
  
  @RequestMapping(value = "/manager/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int managerno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/delete"); // /webapp/manager/delete.jsp
     
    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);
    
    return mav;
  }

  @RequestMapping(value = "/manager/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, int managerno){
   
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/message");
    
    ManagerVO managerVO = managerProc.read(managerno);
    
    
 // ---------------------------------------------------------------------------
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");

    Tool.deleteFile(upDir, managerVO.getFiles());    // ���� ���� ����
    Tool.deleteFile(upDir, managerVO.getThumbs()); // ���� Thumb ���� ����
      
    // ---------------------------------------------------------------------------

    

    String root = request.getContextPath();
    int count = managerProc.delete(managerno);
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    System.out.println("--> create() POST called.");
    System.out.println("count" + count);
    mav.setViewName("redirect:/manager/delete_message.jsp?count=" + count);

 /*   if (managerProc.delete(managerVO.getManagerno()) == 1) {
      msgs.add("�����ڸ� �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    } else {
      msgs.add("������ ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    }

    links.add("<button type='button' onclick=\"location.href='"+root+"/manager/list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/

    return mav;
  }
  
  
  
  
}
