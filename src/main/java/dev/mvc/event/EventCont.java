package dev.mvc.event;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class EventCont {
  @Autowired
  @Qualifier("dev.mvc.event.EventProc")
  private EventProcInter eventProc = null;

  public EventCont() {
    System.out.println("--> EventCont created");
  }

  // http://localhost:9090/movie/event/create.do
  @RequestMapping(value = "/event/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/create");

    return mav;
  }

  // http://localhost:9090/movie/event/create.do
  @RequestMapping(value = "/event/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, EventVO eventVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/event/message");

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> file1MF = eventVO.getFile1MF(); // Spring이 File 객체를

    String file1 = ""; // 컬럼에 저장할 파일명
    String file1_item = ""; // 하나의 파일명
    String size1 = "";
    long size1_item = 0; // 하나의 파일 사이즈
    String thumb = ""; // Thumb 파일들
    String thumb_item = ""; // 하나의 Thumb 파일명
    int count = file1MF.size(); // 업로드된 파일 갯수

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        if (multipartFile.getSize() > 0) {

          file1_item = Upload.saveFileSpring(multipartFile, upDir);
          size1_item = multipartFile.getSize();

          if (Tool.isImage(file1_item)) {
            thumb_item = Tool.preview(upDir, file1_item, 120, 80); // Thumb 이미지
                                                                   // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            file1 = file1 + "/" + file1_item;
            size1 = size1 + "/" + size1_item;
            thumb = thumb + "/" + thumb_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            file1 = file1_item; // file1.jpg
            size1 = "" + size1_item; // 123456
            thumb = thumb_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) { END

      }
    }
    eventVO.setFile1(file1);
    eventVO.setSize1(size1);
    eventVO.setThumb(thumb);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    int count1 = eventProc.create(eventVO);
    mav.setViewName("redirect:/event/create_message.jsp?count=" + count1); // /webapp/category/create_message.jsp

    return mav;
  }
  
  //http://localhost:9090/movie/event/create_json.do?categrpno=1&title=등산&seqno=1&visible=Y&ids=admin
  @ResponseBody
  @RequestMapping(value = "/event/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(EventVO eventVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (eventProc.create(eventVO) == 1) {
     msgs.put("글을 등록했습니다.");
     msgs.put("등록된 글 " + eventVO.getTitle());
    } else {
     msgs.put("글 등록에 실패했습니다.");
     msgs.put("다시한번 시도해주세요.");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  // http://localhost:9090/movie/event/list.do
  @RequestMapping(value = "/event/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    List<EventVO> list = eventProc.list();

    mav.addObject("list", list);
    mav.setViewName("/event/list");

    return mav;
  }

  /**
   * 조회
   * 
   * @param eventno
   * @return
   */
  // http://localhost:9090/movie/event/read.do
  @RequestMapping(value = "/event/read.do", method = RequestMethod.GET)
  public ModelAndView read(int eventno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/read");

    EventVO eventVO = eventProc.read(eventno);
    mav.addObject("eventVO", eventVO);

    return mav;
  }

  /**
   * 다중 파일 수정 폼 http://localhost:9090/movie/event/update.do
   * 
   * @param eventno
   * @return
   */
  @RequestMapping(value = "/event/update.do", method = RequestMethod.GET)
  public ModelAndView update(int eventno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/update"); 
    EventVO eventVO = eventProc.read(eventno);
    mav.addObject("eventVO", eventVO);

    return mav;
  }

  /**
   * - 글만 수정하는 경우의 구현 - 파일만 수정하는 경우의 구현 - 글과 파일을 동시에 수정하는 경우의 구현
   * 
   * @param request
   * @param eventVO
   * @return
   */
  @RequestMapping(value = "/event/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, EventVO eventVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> file1MF = eventVO.getFile1MF(); // Spring이 File 객체를

    String file1 = ""; // 컬럼에 저장할 파일명
    String file1_item = ""; // 하나의 파일명
    String size1 = "";
    long size1_item = 0; // 하나의 파일 사이즈
    String thumb = ""; // Thumb 파일들
    String thumb_item = ""; // 하나의 Thumb 파일명

    int count = file1MF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    EventVO eventVO_old = eventProc.read(eventVO.getEventno());
    if (file1MF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumb_old = eventVO_old.getThumb();
      StringTokenizer thumbs_st = new StringTokenizer(thumb_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String file1_old = eventVO_old.getFile1();
      StringTokenizer files_st = new StringTokenizer(file1_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // 새로운 파일의 등록 시작
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          file1_item = Upload.saveFileSpring(multipartFile, upDir);
          size1_item = multipartFile.getSize();

          if (Tool.isImage(file1_item)) {
            thumb_item = Tool.preview(upDir, file1_item, 120, 80); // Thumb 이미지
                                                                   // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            file1 = file1 + "/" + file1_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            size1 = size1 + "/" + size1_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumb = thumb + "/" + thumb_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            file1 = file1_item; // file1.jpg
            size1 = "" + size1_item; // 123456
            thumb = thumb_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      file1 = eventVO_old.getFile1();
      size1 = eventVO_old.getSize1();
      thumb = eventVO_old.getThumb();
    }

    eventVO.setFile1(file1);
    eventVO.setSize1(size1);
    eventVO.setThumb(thumb);

    count = eventProc.update(eventVO);
    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("eventno", eventVO.getEventno());

    mav.setViewName("redirect:/event/update_message.jsp");

    return mav;

  }

  /**
   * 삭제 폼 http://localhost:9090/movie/event/delete.do
   * 
   * @param eventno
   * @return
   */
  @RequestMapping(value = "/event/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int eventno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/delete"); 

    EventVO eventVO = eventProc.read(eventno);
    mav.addObject("eventVO", eventVO);

    return mav;
  }

  @RequestMapping(value = "/event/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, HttpServletRequest request, int eventno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/delete_message"); 

    String upDir = Tool.getRealPath(request, "/event/storage"); // 저장 폴더 절대

    EventVO eventVO = eventProc.read(eventno);

    String thumbs_old = eventVO.getThumb();
    String files_old = eventVO.getFile1();

    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + files_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    int count = eventProc.delete(eventno); // 레코드 삭제

    if (count == 1) {
      eventProc.decreaseCnt(eventno); // 등록된 글수의 감소

    }

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("eventno", eventVO.getEventno());

    mav.setViewName("redirect:/event/delete_message.jsp");

    return mav;
  }

  /**
   * 검색 목록
   * 
   * @param categoryno
   * @param word
   * @return
   */
  @RequestMapping(value = "/event/list_by_category_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_category_search(String word) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/list_by_category_search");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);

    List<EventVO> list = eventProc.list_by_category_search(hashMap);
    mav.addObject("list", list);

    int search_count = eventProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    return mav;
  }

  /**
   * 목록 + 검색 + 페이징 지원
   * 
   * @param eventno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/event/list_by_category_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_category_search_paging(
      @RequestParam(value = "word", defaultValue = "") String word,
      @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
    System.out.println("--> nowPage: " + nowPage);

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/list_by_category_search_paging");
    
    // 숫자와 문자열 타입을 저장
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);
    hashMap.put("nowPage", nowPage);
    
    // 검색목록
    List<EventVO> list = eventProc.list_by_category_search_paging(hashMap);
    mav.addObject("list", list);

    int search_count = eventProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param categoryno 카테고리번호 
     * @param search_count 검색(전체) 레코드수 
     * @param nowPage     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
     */ 
    String paging = eventProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);

    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
