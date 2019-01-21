package dev.mvc.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.admin4
 * 파일명           : AdminTool.java 2018. 12. 12.
 * 작성자           : 자신의 성명 또는 별명
 * 작성자 email   : zz@zzz.com
 * 수정내용
 * ------------------------------------------------------------------
 * 수정 이력
 * ------------------------------------------------------------------ 
 * 수정일        수정자  연락처               수정 내용
 * ------------------------------------------------------------------ 
 * 
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
public class AdminTool {
  /**
   * 관리자 계정인지 검사합니다.
   * @param request
   * @return true or false
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
  //  public static synchronized boolean isMember(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession(); // java class에서 session 객체 추출
    String act = (String)session.getAttribute("act"); // 권한 산출
    
    if (act != null){
      if (act.equals("M") || act.equals("Y")){ // 로그인 가능
        sw = true;
      }
    }
    return sw;
  }

  /**
   * 마스터 계정인지 검사합니다.
   * @param request
   * @return true: 마스터 계정
   */
  public static synchronized boolean isMaster(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String act = (String)session.getAttribute("act"); // 역활
    // System.out.println("--> Tool.java act: " + act);
    
    if (act != null){
      if (act.equals("M")){ // 마스터인지 검사
        sw = true;
      }
    }
    return sw;
  }
  
}

 