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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> file1MF = eventVO.getFile1MF(); // Spring�� File ��ü��

    String file1 = ""; // �÷��� ������ ���ϸ�
    String file1_item = ""; // �ϳ��� ���ϸ�
    String size1 = "";
    long size1_item = 0; // �ϳ��� ���� ������
    String thumb = ""; // Thumb ���ϵ�
    String thumb_item = ""; // �ϳ��� Thumb ���ϸ�
    int count = file1MF.size(); // ���ε�� ���� ����

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        if (multipartFile.getSize() > 0) {

          file1_item = Upload.saveFileSpring(multipartFile, upDir);
          size1_item = multipartFile.getSize();

          if (Tool.isImage(file1_item)) {
            thumb_item = Tool.preview(upDir, file1_item, 120, 80); // Thumb �̹���
                                                                   // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            file1 = file1 + "/" + file1_item;
            size1 = size1 + "/" + size1_item;
            thumb = thumb + "/" + thumb_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    int count1 = eventProc.create(eventVO);
    mav.setViewName("redirect:/event/create_message.jsp?count=" + count1); // /webapp/category/create_message.jsp

    return mav;
  }
  
  //http://localhost:9090/movie/event/create_json.do?categrpno=1&title=���&seqno=1&visible=Y&ids=admin
  @ResponseBody
  @RequestMapping(value = "/event/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(EventVO eventVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (eventProc.create(eventVO) == 1) {
     msgs.put("���� ����߽��ϴ�.");
     msgs.put("��ϵ� �� " + eventVO.getTitle());
    } else {
     msgs.put("�� ��Ͽ� �����߽��ϴ�.");
     msgs.put("�ٽ��ѹ� �õ����ּ���.");
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
   * ��ȸ
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
   * ���� ���� ���� �� http://localhost:9090/movie/event/update.do
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
   * - �۸� �����ϴ� ����� ���� - ���ϸ� �����ϴ� ����� ���� - �۰� ������ ���ÿ� �����ϴ� ����� ����
   * 
   * @param request
   * @param eventVO
   * @return
   */
  @RequestMapping(value = "/event/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, EventVO eventVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> file1MF = eventVO.getFile1MF(); // Spring�� File ��ü��

    String file1 = ""; // �÷��� ������ ���ϸ�
    String file1_item = ""; // �ϳ��� ���ϸ�
    String size1 = "";
    long size1_item = 0; // �ϳ��� ���� ������
    String thumb = ""; // Thumb ���ϵ�
    String thumb_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = file1MF.size(); // ���ε�� ���� ����

    // ������ ��� ���� ��ȸ
    EventVO eventVO_old = eventProc.read(eventVO.getEventno());
    if (file1MF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumb_old = eventVO_old.getThumb();
      StringTokenizer thumbs_st = new StringTokenizer(thumb_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String file1_old = eventVO_old.getFile1();
      StringTokenizer files_st = new StringTokenizer(file1_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          file1_item = Upload.saveFileSpring(multipartFile, upDir);
          size1_item = multipartFile.getSize();

          if (Tool.isImage(file1_item)) {
            thumb_item = Tool.preview(upDir, file1_item, 120, 80); // Thumb �̹���
                                                                   // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            file1 = file1 + "/" + file1_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            size1 = size1 + "/" + size1_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumb = thumb + "/" + thumb_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            file1 = file1_item; // file1.jpg
            size1 = "" + size1_item; // 123456
            thumb = thumb_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------

    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      file1 = eventVO_old.getFile1();
      size1 = eventVO_old.getSize1();
      thumb = eventVO_old.getThumb();
    }

    eventVO.setFile1(file1);
    eventVO.setSize1(size1);
    eventVO.setThumb(thumb);

    count = eventProc.update(eventVO);
    redirectAttributes.addAttribute("count", count);

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("eventno", eventVO.getEventno());

    mav.setViewName("redirect:/event/update_message.jsp");

    return mav;

  }

  /**
   * ���� �� http://localhost:9090/movie/event/delete.do
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

    String upDir = Tool.getRealPath(request, "/event/storage"); // ���� ���� ����

    EventVO eventVO = eventProc.read(eventno);

    String thumbs_old = eventVO.getThumb();
    String files_old = eventVO.getFile1();

    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + thumbs_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + files_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    }

    int count = eventProc.delete(eventno); // ���ڵ� ����

    if (count == 1) {
      eventProc.decreaseCnt(eventno); // ��ϵ� �ۼ��� ����

    }

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("eventno", eventVO.getEventno());

    mav.setViewName("redirect:/event/delete_message.jsp");

    return mav;
  }

  /**
   * �˻� ���
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
   * ��� + �˻� + ����¡ ����
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
    
    // ���ڿ� ���ڿ� Ÿ���� ����
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);
    hashMap.put("nowPage", nowPage);
    
    // �˻����
    List<EventVO> list = eventProc.list_by_category_search_paging(hashMap);
    mav.addObject("list", list);

    int search_count = eventProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param categoryno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = eventProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);

    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
