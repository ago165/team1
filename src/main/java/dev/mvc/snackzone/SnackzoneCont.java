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

  // request.getParameter() �ڵ� ����
  // ����ȯ �ڵ� ����
  // CategrpVO ��ü �ڵ� ����
  // http://localhost:9090/ojt/snackzone/create.do
  @RequestMapping(value = "/snackzone/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, SnackzoneVO snackzoneVO) {
    
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    /*System.out.println("-->mav create");*/
    
    /*// -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
 
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    System.out.println(upDir);
    MultipartFile thumbMF = snackzoneVO.getFile1MF();
    System.out.println("thumbMF: " + thumbMF);
    String file1 = ""; // �÷��� ������ ���ϸ�
    String size1 = "";
    long size_1 = thumbMF.getSize();
    String thumb = ""; // �÷��� ������ ���ϸ�
    if (size_1 > 0) {
      file1 = Upload.saveFileSpring(thumbMF, upDir);
      
      System.out.println("file1: " + file1);

      if (Tool.isImage(file1)) {
        thumb = Tool.preview(upDir, file1, 270, 270); // Thumb �̹��� ����
        System.out.println("thumb: " + thumb);
      }
    }
    
    snackzoneVO.setThumb(thumb);
    snackzoneVO.setSize1(size1);
    snackzoneVO.setFile1(file1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    */
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    List<MultipartFile> file1MF = snackzoneVO.getFile1MF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String file1 = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String size1 = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumb = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = file1MF.size(); // ���ε�� ���� ����

    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    // System.out.println("--> ���ε�� ���� ���� count: " + count);

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = file1MF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 200, 200); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            file1 = file1 + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            size1 = size1 + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumb = thumb + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    
    
    count = SnackzoneProc.create(snackzoneVO); 
    
    mav.setViewName("redirect:/snackzone/create_message.jsp?count=" + count); // /webapp/category/create_message.jsp
    mav.addObject("count", count);
    mav.addObject("name", snackzoneVO.getName());
    
    
      //mav.setViewName("/snackzone/create_message"); //categrp/create_message2.jsp
     
    return mav;
  }

  /**
   * ���
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
    // �˻� ����
    //--------------------------------------------------------------------
    
      // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
      mav.setViewName("/snackzone/list");

      // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", word); // #{word}
      hashMap.put("nowPage", nowPage);   
      // System.out.println("categoryno: " + categoryno);
      // System.out.println("word_find: " + word_find);


      // mav.addObject("word", word);

    
    //--------------------------------------------------------------------
    // �˻� ���� ����
    //--------------------------------------------------------------------
    
    // �˻� ���
    List<SnackzoneVO> list = SnackzoneProc.list(hashMap);
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int search_count = SnackzoneProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    System.out.println("search_count:" + search_count);
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
    String paging = SnackzoneProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    

    return mav;
  }
  
  /**
   * ��ȸ
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

    /*Categrp_CategoryVO categoryVO = categoryProc.read(snackzoneVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
*/    
    mav.addObject("snackzoneVO", snackzoneVO);


    return mav;
  }
  
  /**
   * ���� �� http://localhost:9090/snackzone/snackzone/update.do
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
   * - �۸� �����ϴ� ����� ���� - ���ϸ� �����ϴ� ����� ���� - �۰� ������ ���ÿ� �����ϴ� ����� ����
   * 
   * @param request
   * @param snackzoneVO
   * @return
   */
  @RequestMapping(value = "/snackzone/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, SnackzoneVO snackzoneVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/snackzone/storage");
    List<MultipartFile> file1MF = snackzoneVO.getFile1MF(); // Spring�� File ��ü��

    String file1 = ""; // �÷��� ������ ���ϸ�
    String file1_item = ""; // �ϳ��� ���ϸ�
    String size1 = "";
    long size1_item = 0; // �ϳ��� ���� ������
    String thumb = ""; // Thumb ���ϵ�
    String thumb_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = file1MF.size(); // ���ε�� ���� ����

    // ������ ��� ���� ��ȸ
    SnackzoneVO snackzoneVO_old = SnackzoneProc.read(snackzoneVO.getSnackno());
    if (file1MF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumb_old = snackzoneVO_old.getThumb();
      StringTokenizer thumb_st = new StringTokenizer(thumb_old, "/");
      while (thumb_st.hasMoreTokens()) {
        String fname = upDir + thumb_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String files_old = snackzoneVO_old.getFile1();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
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
            thumb_item = Tool.preview(upDir, file1_item, 200, 200); // Thumb �̹���
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
      file1 = snackzoneVO_old.getFile1();
      size1 = snackzoneVO_old.getSize1();
      thumb = snackzoneVO_old.getThumb();
    }
    snackzoneVO.setFile1(file1);
    snackzoneVO.setSize1(size1);
    snackzoneVO.setThumb(thumb);


    count = SnackzoneProc.update(snackzoneVO);

    redirectAttributes.addAttribute("count", count);

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("snackno", snackzoneVO.getSnackno());

    mav.setViewName("redirect:/snackzone/update_message.jsp");

    return mav;

  }
  
  /**
   * ���� �� http://localhost:9090/snackzone/snackzone/delete.do
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

    String upDir = Tool.getRealPath(request, "/snackzone/storage"); // ���� ���� ����
                                                                   // ���

    SnackzoneVO snackzoneVO = SnackzoneProc.read(snackno); // ������ ���� ������ �б� ���� 
                                                           // ����

    String thumbs_old = snackzoneVO.getThumb();
    String files_old = snackzoneVO.getFile1();

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

    int count = SnackzoneProc.delete(snackno); // ���ڵ� ����

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("snackno", snackzoneVO.getSnackno());
    mav.setViewName("redirect:/snackzone/delete_message.jsp");

    return mav;
  }
  
  /**
   * �˻� ���
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

    // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/snackzone/list_by_search");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word); // #{word}

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // �˻� ���
    List<SnackzoneVO> list = SnackzoneProc.list_by_search(hashMap);
    mav.addObject("list", list);

    // mav.addObject("word", word);

    return mav;
  }
*/
}
