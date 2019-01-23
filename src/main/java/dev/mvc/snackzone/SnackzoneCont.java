package dev.mvc.snackzone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SnackzoneCont {
  @Autowired
  @Qualifier("dev.mvc.snackzone.SnackzoneProc")
  private SnackzoneProcInter SnackzoneProc = null;

  public SnackzoneCont() {
    System.out.println("--> Snackzone created.");
  }

  // http://localhost:9090/movie/snackzone/create.do
  @RequestMapping(value = "/snackzone/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/snackzone/create"); // /webapp/categrp/create.jsp

    return mav;
  }

  // request.getParameter() 자동 실행
  // 형변환 자동 실행
  // CategrpVO 객체 자동 생성
  // http://localhost:9090/ojt/snackzone/create.do
  @RequestMapping(value = "/snackzone/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, SnackzoneVO snackzoneVO) {
    
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    /*System.out.println("-->mav create");*/
    
    /*// -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
 
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    System.out.println(upDir);
    MultipartFile thumbMF = snackzoneVO.getFile1MF();
    System.out.println("thumbMF: " + thumbMF);
    String file1 = ""; // 컬럼에 저장할 파일명
    String size1 = "";
    long size_1 = thumbMF.getSize();
    String thumb = ""; // 컬럼에 저장할 파일명
    if (size_1 > 0) {
      file1 = Upload.saveFileSpring(thumbMF, upDir);
      
      System.out.println("file1: " + file1);

      if (Tool.isImage(file1)) {
        thumb = Tool.preview(upDir, file1, 270, 270); // Thumb 이미지 생성
        System.out.println("thumb: " + thumb);
      }
    }
    
    snackzoneVO.setThumb(thumb);
    snackzoneVO.setSize1(size1);
    snackzoneVO.setFile1(file1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    */
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    List<MultipartFile> file1MF = snackzoneVO.getFile1MF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String file1 = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String size1 = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumb = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = file1MF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 200, 200); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            file1 = file1 + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            size1 = size1 + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumb = thumb + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            file1 = files_item; // file1.jpg
            size1 = "" + sizes_item; // 123456
            thumb = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    snackzoneVO.setFile1(file1);
    snackzoneVO.setSize1(size1);
    snackzoneVO.setThumb(thumb);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    
    
    count = SnackzoneProc.create(snackzoneVO); 
    
    mav.setViewName("redirect:/snackzone/create_message.jsp?count=" + count); // /webapp/category/create_message.jsp
    mav.addObject("count", count);
    mav.addObject("name", snackzoneVO.getName());
    
    
      //mav.setViewName("/snackzone/create_message"); //categrp/create_message2.jsp
     
    return mav;
  }

  /**
   * 목록
   * 
   * @param snackno
   * @return
   */
  
  
  // http://localhost:9090/ojt/snackzone/list.do
  @RequestMapping(value = "/snackzone/list.do", method = RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) {
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();

    //--------------------------------------------------------------------
    // 검색 구현
    //--------------------------------------------------------------------
    
      // 검색 기능 추가, webapp/contents/list_by_category_search.jsp
      mav.setViewName("/snackzone/list");

      // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", word); // #{word}
      hashMap.put("nowPage", nowPage);   
      // System.out.println("categoryno: " + categoryno);
      // System.out.println("word_find: " + word_find);


      // mav.addObject("word", word);

    
    //--------------------------------------------------------------------
    // 검색 구현 종료
    //--------------------------------------------------------------------
    
    // 검색 목록
    List<SnackzoneVO> list = SnackzoneProc.list(hashMap);
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = SnackzoneProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    System.out.println("search_count:" + search_count);
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
    String paging = SnackzoneProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    

    return mav;
  }
  
  /**
   * 조회
   * 
   * @param snackno
   * @return
   */
  @RequestMapping(value = "/snackzone/read.do", method = RequestMethod.GET)
  public ModelAndView read(int snackno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/snackzone/read"); // /webapp/snackzone/read.jsp

    SnackzoneVO snackzoneVO = SnackzoneProc.read(snackno);
    mav.addObject("snackzoneVO", snackzoneVO);

    /*Categrp_CategoryVO categoryVO = categoryProc.read(snackzoneVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
*/    
    mav.addObject("snackzoneVO", snackzoneVO);


    return mav;
  }
  
  /**
   * 수정 폼 http://localhost:9090/snackzone/snackzone/update.do
   * 
   * @param snackzoneno
   * @return
   */
  @RequestMapping(value = "/snackzone/update.do", method = RequestMethod.GET)
  public ModelAndView update(int snackno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/snackzone/update"); // /webapp/snackzone/update.jsp

    SnackzoneVO snackzoneVO = SnackzoneProc.read(snackno);
    mav.addObject("snackzoneVO", snackzoneVO);

    return mav;
  }
  
  
   /**
   * - 글만 수정하는 경우의 구현 - 파일만 수정하는 경우의 구현 - 글과 파일을 동시에 수정하는 경우의 구현
   * 
   * @param request
   * @param snackzoneVO
   * @return
   */
  @RequestMapping(value = "/snackzone/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, SnackzoneVO snackzoneVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    List<MultipartFile> file1MF = snackzoneVO.getFile1MF(); // Spring이 File 객체를

    String file1 = ""; // 컬럼에 저장할 파일명
    String file1_item = ""; // 하나의 파일명
    String size1 = "";
    long size1_item = 0; // 하나의 파일 사이즈
    String thumb = ""; // Thumb 파일들
    String thumb_item = ""; // 하나의 Thumb 파일명

    int count = file1MF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    SnackzoneVO snackzoneVO_old = SnackzoneProc.read(snackzoneVO.getSnackno());
    if (file1MF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumb_old = snackzoneVO_old.getThumb();
      StringTokenizer thumb_st = new StringTokenizer(thumb_old, "/");
      while (thumb_st.hasMoreTokens()) {
        String fname = upDir + thumb_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = snackzoneVO_old.getFile1();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
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
            thumb_item = Tool.preview(upDir, file1_item, 200, 200); // Thumb 이미지
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
      file1 = snackzoneVO_old.getFile1();
      size1 = snackzoneVO_old.getSize1();
      thumb = snackzoneVO_old.getThumb();
    }
    snackzoneVO.setFile1(file1);
    snackzoneVO.setSize1(size1);
    snackzoneVO.setThumb(thumb);


    count = SnackzoneProc.update(snackzoneVO);

    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("snackno", snackzoneVO.getSnackno());

    mav.setViewName("redirect:/snackzone/update_message.jsp");

    return mav;

  }
  
  /**
   * 삭제 폼 http://localhost:9090/snackzone/snackzone/delete.do
   * 
   * @param snackno
   * @param categoryno
   * @return
   */
  @RequestMapping(value = "/snackzone/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int snackno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/snackzone/delete"); // /webapp/snackzone/delete.jsp

    SnackzoneVO snackzoneVO = SnackzoneProc.read(snackno);
    mav.addObject("snackzoneVO", snackzoneVO);

    return mav;
  }
  
  @RequestMapping(value = "/snackzone/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request,           
                                        int snackno
      ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/snackzone/delete_message"); // /webapp/snackzone/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/snackzone/storage"); // 저장 폴더 절대
                                                                   // 경로

    SnackzoneVO snackzoneVO = SnackzoneProc.read(snackno); // 삭제할 파일 정보를 읽기 위한 
                                                           // 목적

    String thumbs_old = snackzoneVO.getThumb();
    String files_old = snackzoneVO.getFile1();

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

    int count = SnackzoneProc.delete(snackno); // 레코드 삭제

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("snackno", snackzoneVO.getSnackno());
    mav.setViewName("redirect:/snackzone/delete_message.jsp");

    return mav;
  }
  
  /**
   * 검색 목록
   * 
   * @param categoryno
   * @param word
   * @return
   *//*
  @RequestMapping(value = "/snackzone/list_by_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_search(String word) {
    // System.out.println("--> list_by_category(int categoryno, String
    // word_find) GET called.");
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/contents/list_by_categoryno"); //
    // webapp/contents/list_by_categoryno.jsp

    // 검색 기능 추가, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/snackzone/list_by_search");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word); // #{word}

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // 검색 목록
    List<SnackzoneVO> list = SnackzoneProc.list_by_search(hashMap);
    mav.addObject("list", list);

    // mav.addObject("word", word);

    return mav;
  }
*/
}
