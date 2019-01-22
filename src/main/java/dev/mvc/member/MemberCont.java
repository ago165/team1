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
   * 중복 ID 검사
   * http://localhost:9090/ojt/member/checkId.do?id=member1
   * 결과: {"count":1}
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");
    MultipartFile filesMF = memberVO.getFilesMF();
    String files = ""; // 컬럼에 저장할 파일명
    long sizes = filesMF.getSize();
    String thumbs = ""; // 컬럼에 저장할 파일명
    
    //long count = filesMF.getSizes();  
    
    if (sizes > 0) {
      files = Upload.saveFileSpring(filesMF, upDir);

      if (Tool.isImage(files)) {
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb 이미지 생성
      }
    }
    memberVO.setFiles(files);
    memberVO.setSizes(sizes);
    memberVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    int count = memberProc.create(memberVO);
    
    mav.setViewName("redirect:/member/create_message.jsp?count=" + count);

   /* String root = request.getContextPath();

    // 중복 아이디 검사
    int count = memberProc.checkId(memberVO.getId());
    if (count > 0) {
      msgs.add("ID가 중복됩니다.");
      msgs.add("폼에서 회원가입을해주세요. ☏ 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
    } else {
      // 회원 가입 처리
      if (memberProc.create(memberVO) == 1) {
        msgs.add("회원 가입이 완료되었습니다.");
        msgs.add("가입해 주셔서 감사합니다.");
        links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
        
      } else {
        msgs.add("회원 가입에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
        
      }
      
    }
    
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">홈페이지</button>");
    
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

      // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      // hashMap.put("memberno", memberno); // #{categoryno}
      hashMap.put("s_word", s_word); // #{word}
      hashMap.put("nowPage", nowPage);
      
      
   // 검색 목록
      List<MemberVO> list = memberProc.list(hashMap);
      mav.addObject("list", list);

      int search_count = memberProc.search_count(hashMap);
      mav.addObject("search_count", search_count);
      
      String paging = memberProc.paging(search_count, nowPage, s_word);
      mav.addObject("paging", paging);
    
      mav.addObject("nowPage", nowPage);
      
    return mav;
  }  
  
// 조회 http://localhost:9090/movie/member/read.do
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
 
// 수정 추가 get 방식!
@RequestMapping(value = "/member/update.do", method = RequestMethod.GET)
  public ModelAndView update(int memberno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/update");
    

    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);


    return mav;
  }

// 수정 http://localhost:9090/movie/member/update.do
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, MemberVO memberVO){
    System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // webapp/member/message.jsp
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    
 // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ↓
     name='file1MF'에 해당하는 필드를 찾아서 File 객체를 자동으로 할당
    ↓
    BlogVO.java: private MultipartFile file1MF;
    ↓
     파일 객체 사용: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile filesMF = memberVO.getFilesMF();
    String files = "";                    // DBMS file1 컬럼의 값
    long sizes = filesMF.getSize(); // 파일 크기
    String thumbs = "";                 // DBMS thumb 컬럼의 값
    
    // 기존에 등록된 글 정보 로딩
    MemberVO memberVO_bf = memberProc.read(memberVO.getMemberno());
    
    if (sizes > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, memberVO_bf.getFiles());    // 기존 파일 삭제
      Tool.deleteFile(upDir, memberVO_bf.getThumbs()); // 기존 Thumb 파일 삭제
      
      files = Upload.saveFileSpring(filesMF, upDir); // 신규 파일 업로드
      
      if (Tool.isImage(files)) { // 새로운 파일 이미지인지 검사
        thumbs = Tool.preview(upDir, files, 120, 80); // Thumb 이미지 생성
      } 
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
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
      msgs.add("회원 수정이 완료되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./read.do?memberno="+memberVO.getMemberno()+"'\">변경 사항 확인</button>");
      
    } else {
      msgs.add("회원 수정에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      
    }
    links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">홈페이지</button>");
    
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
    // 파일 삭제
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/member/storage");

    Tool.deleteFile(upDir, memberVO.getFiles());    // 기존 파일 삭제
    Tool.deleteFile(upDir, memberVO.getThumbs()); // 기존 Thumb 파일 삭제
      
    // ---------------------------------------------------------------------------

    

    String root = request.getContextPath();
    int count = memberProc.delete(memberno);
    
/*    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();*/
    System.out.println("--> create() POST called.");
    System.out.println("count" + count);
    mav.setViewName("redirect:/member/delete_message.jsp?count=" + count);

 /*   if (memberProc.delete(memberVO.getMemberno()) == 1) {
      msgs.add("회원을 삭제했습니다.");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">홈페이지</button>");
    } else {
      msgs.add("회원 삭제에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">홈페이지</button>");
    }

    links.add("<button type='button' onclick=\"location.href='"+root+"/member/list.do'\">목록</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/

    return mav;
  }
  
  
  
  
}

