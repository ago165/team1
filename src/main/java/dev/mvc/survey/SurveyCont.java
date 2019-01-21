package dev.mvc.survey;

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
public class SurveyCont {
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc = null;
  
  public SurveyCont() {
    System.out.println("--> SurveyCont created.");
  }
  
  // http://localhost:9090/movie4/survey/create.do
  @RequestMapping(value = "/survey/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/create"); // /webapp/survey/create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/movie4/survey/create.do
  @RequestMapping(value="/survey/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request,SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/survey/storage");
    List<MultipartFile> filesMF = surveyVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String sizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    surveyVO.setFiles(files);
    surveyVO.setSizes(sizes);
    surveyVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = surveyProc.create(surveyVO);

    mav.setViewName("redirect:/survey/create_message.jsp?count=" + count); // /survey/create_message.jsp
    
    return mav;
  }
  
/*  // http://localhost:9090/movie4/survey/list.do
  @RequestMapping(value="/survey/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<SurveyVO> list = surveyProc.list();
    mav.addObject("list", list);
    mav.setViewName("/survey/list"); // /webapp/survey/list.jsp
    
    return mav;
  }*/
  
  @RequestMapping(value = "/survey/read.do", method = RequestMethod.GET)
  public ModelAndView read(int surveyno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/read"); // /webapp/survey/read.jsp

    SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);

    return mav;
  }
  
  @RequestMapping(value = "/survey/update.do", method = RequestMethod.GET)
  public ModelAndView update(int surveyno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/update"); // /webapp/contents/update.jsp

    SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);

    ArrayList<FileVO> file_list = surveyProc.getThumbs(surveyVO);

    mav.addObject("file_list", file_list);
    
    return mav;
  }
  
  /**
   * - 글만 수정하는 경우의 구현 - 파일만 수정하는 경우의 구현 - 글과 파일을 동시에 수정하는 경우의 구현
   * 
   * @param request
   * @param surveyVO
   * @return
   */
  @RequestMapping(value = "/survey/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/survey/storage");
    List<MultipartFile> filesMF = surveyVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String sizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    SurveyVO surveyVO_old = surveyProc.read(surveyVO.getSurveyno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = surveyVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = surveyVO_old.getFiles();
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
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      files = surveyVO_old.getFiles();
      sizes = surveyVO_old.getSizes();
      thumbs = surveyVO_old.getThumbs();
    }
    surveyVO.setFiles(files);
    surveyVO.setSizes(sizes);
    surveyVO.setThumbs(thumbs);

    // contentsVO.setMno(1); // 회원 개발후 session으로 변경

    count = surveyProc.update(surveyVO);

    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("surveyno", surveyVO.getSurveyno());

    mav.setViewName("redirect:/survey/update_message.jsp");

    return mav;

  }
  @RequestMapping(value="/survey/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int surveyno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/delete"); // webapp/survey/delete.jsp
    
    SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/survey/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(RedirectAttributes redirectAttributes, 
                                               HttpServletRequest request, 
                                               int surveyno) {
    ModelAndView mav = new ModelAndView();
    
    int count = surveyProc.delete(surveyno);
    redirectAttributes.addAttribute("surveyno", surveyno);
    mav.setViewName("redirect:/survey/delete_message.jsp?count=" + count); // /survey/create_message.jsp
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원
   * @param categoryno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/survey/list.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_survey_search_paging(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    // System.out.println("--> list_by_category() GET called.");
    // System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    // 검색 기능 추가,  /webapp/contents/list_by_category_search_paging.jsp
    mav.setViewName("/survey/list");   
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    // hashMap.put("surveyno", surveyno); // #{surveyno}
    hashMap.put("word", word);                  // #{word}
    hashMap.put("nowPage", nowPage);       
    
    // 검색 목록
    List<SurveyVO> list = surveyProc.list_by_survey_search_paging(hashMap);
    mav.addObject("list", list);
    
   // 검색된 레코드 갯수
    int search_count = surveyProc.search_count(hashMap);
    mav.addObject("search_count", search_count); 
    
/*    SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);*/
    
    // mav.addObject("word", word);
  
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
    String paging = surveyProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
}
