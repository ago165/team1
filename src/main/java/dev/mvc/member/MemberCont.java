package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

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

import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
   
  public MemberCont(){
    System.out.println("--> MemberCont created.");
    
  }  
  
  /**
   * �ߺ� ID �˻�
   * http://localhost:9090/ojt/member/checkId.do?id=member1
   * ���: {"count":1}
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
    System.out.println("--> checkId() GET executed");    
    JSONObject obj = new JSONObject();   
    
    int count = memberProc.checkId(id);
    
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  
  
  // http://localhost:9090/member/create.do
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET excuted");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // webapp/member/create.jsp

    return mav;
  }  

  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create (HttpServletRequest request, MemberVO memberVO){
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    /*mav.setViewName("/member/message"); // webapp/member/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");
    MultipartFile filesMF = memberVO.getFilesMF();
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
    memberVO.setFiles(files);
    memberVO.setSizes(sizes);
    memberVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    int count = memberProc.create(memberVO);
    
    mav.setViewName("redirect:/member/create_message.jsp?count=" + count);

   /* String root = request.getContextPath();

    // �ߺ� ���̵� �˻�
    int count = memberProc.checkId(memberVO.getId());
    if (count > 0) {
      msgs.add("ID�� �ߺ��˴ϴ�.");
      msgs.add("������ ȸ�����������ּ���. �� 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    } else {
      // ȸ�� ���� ó��
      if (memberProc.create(memberVO) == 1) {
        msgs.add("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
        msgs.add("������ �ּż� �����մϴ�.");
        links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
        
      } else {
        msgs.add("ȸ�� ���Կ� �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
        
      }
      
    }
    
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/
    
    return mav;
  }
   
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="s_word", defaultValue="") String s_word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ){
    // System.out.println("--> create() GET called.");
       System.out.println("--> nowPage: " + nowPage);
    
       System.out.println("--> list() GET called.");
       ModelAndView mav = new ModelAndView();
    
       mav.setViewName("/member/list"); // webapp/member/list.jsp
       
/*    if (Tool.isMember(session) == false) {
       mav.setViewName("redirect:/member/login.jsp"); // d 

      
      } else {*/
            
     // List<MemberVO> list = memberProc.list();
     // mav.addObject("list", list);
    
      
   // }

      // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      // hashMap.put("memberno", memberno); // #{categoryno}
      hashMap.put("s_word", s_word); // #{word}
      hashMap.put("nowPage", nowPage);
      
      
   // �˻� ���
      List<MemberVO> list = memberProc.list(hashMap);
      mav.addObject("list", list);

      int search_count = memberProc.search_count(hashMap);
      mav.addObject("search_count", search_count);
      
      String paging = memberProc.paging(search_count, nowPage, s_word);
      mav.addObject("paging", paging);
    
      mav.addObject("nowPage", nowPage);
      
    return mav;
  }  
  
// ��ȸ http://localhost:9090/movie/member/read.do
  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
  public ModelAndView read(int memberno){
    System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read"); // webapp/member/read.jsp
    
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    System.out.println("mav : " + mav);
     
    return mav;       
  }  
 
// ���� �߰� get ���!
@RequestMapping(value = "/member/update.do", method = RequestMethod.GET)
  public ModelAndView update(int memberno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/update");
    

    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);


    return mav;
  }

// ���� http://localhost:9090/movie/member/update.do
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, MemberVO memberVO){
    System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // webapp/member/message.jsp
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    
 // ---------------------------------------------------------------------------
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ��
     name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
    ��
    BlogVO.java: private MultipartFile file1MF;
    ��
     ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile filesMF = memberVO.getFilesMF();
    String files = "";                    // DBMS file1 �÷��� ��
    long sizes = filesMF.getSize(); // ���� ũ��
    String thumbs = "";                 // DBMS thumb �÷��� ��
    
    // ������ ��ϵ� �� ���� �ε�
    MemberVO memberVO_bf = memberProc.read(memberVO.getMemberno());
    
    if (sizes > 0) { // ��ϵ� ������ �ִٸ�
      Tool.deleteFile(upDir, memberVO_bf.getFiles());    // ���� ���� ����
      Tool.deleteFile(upDir, memberVO_bf.getThumbs()); // ���� Thumb ���� ����
      
      files = Upload.saveFileSpring(filesMF, upDir); // �ű� ���� ���ε�
      
      if (Tool.isImage(files)) { // ���ο� ���� �̹������� �˻�
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb �̹��� ����
      } 
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      files = memberVO_bf.getFiles();
      sizes = memberVO_bf.getSizes();
      thumbs = memberVO_bf.getThumbs();
    }
    memberVO.setFiles(files);
    memberVO.setSizes(sizes);
    memberVO.setThumbs(thumbs);
    // ---------------------------------------------------------------------------
    
    String root = request.getContextPath();    
    int count = memberProc.update(memberVO);
    
/*    redirectAttributes.addAttribute("count", count);
    redirectAttributes.addAttribute("memberno", memberVO.getMemberno());*/
    
    mav.setViewName("redirect:/member/update_message.jsp?count="+ count);
    
/*    if (memberProc.update(memberVO) == 1) {
      msgs.add("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./read.do?memberno="+memberVO.getMemberno()+"'\">���� ���� Ȯ��</button>");
      
    } else {
      msgs.add("ȸ�� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      
    }
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/
    
    return mav;
  }
  
/*  // http://localhost:9090/member/member/passwd_update.do?memberno=1
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(int memberno){
    System.out.println("--> passwd_update(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd_update"); // passwd_update.jsp

    mav.addObject("memberno", memberno);
    
    return mav;
  }  */
  
  
  
  
  
  
  
  @RequestMapping(value = "/member/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int memberno) {
    System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/delete"); // /webapp/member/delete.jsp
     
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }

  @RequestMapping(value = "/member/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, int memberno){
   
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
    
    MemberVO memberVO = memberProc.read(memberno);
    
    
 // ---------------------------------------------------------------------------
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");

    Tool.deleteFile(upDir, memberVO.getFiles());    // ���� ���� ����
    Tool.deleteFile(upDir, memberVO.getThumbs()); // ���� Thumb ���� ����
      
    // ---------------------------------------------------------------------------

    

    String root = request.getContextPath();
    int count = memberProc.delete(memberno);
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    System.out.println("--> create() POST called.");
    System.out.println("count" + count);
    mav.setViewName("redirect:/member/delete_message.jsp?count=" + count);

 /*   if (memberProc.delete(memberVO.getMemberno()) == 1) {
      msgs.add("ȸ���� �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
    }

    links.add("<button type='button' onclick=\"location.href='"+root+"/member/list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/

    return mav;
  }
  
  
  
  
}

