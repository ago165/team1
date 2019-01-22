package dev.mvc.movielook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.simple.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovielookCont {
  @Autowired
  @Qualifier("dev.mvc.movielook.MovielookProc")
  private MovielookProcInter movielookProc = null;
  public MovielookCont() {
    System.out.println("-->MovielookCont create");
  }
  /**
   * 등록 폼 http://localhost:9090/movie/movielook/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/movielook/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/movielook/create");

  return mav;
  }
  
  @RequestMapping(value="/movielook/create.do", method=RequestMethod.POST)
  public ModelAndView create(MovielookVO movielookVO){
   ModelAndView mav = new ModelAndView();
    int count = movielookProc.create(movielookVO);
  
  mav.addObject("count", count);
  mav.addObject("movielookno", movielookVO.getMovielookno());
     System.out.println("count : " + count); //테스트시 사용 카운트 1 먹는지
  mav.setViewName("redirect:/movielook/create_message.jsp?count=" + count); // /movielook/create_message.jsp
  
  return mav;
  }
  
  // http://localhost:9090/movie/movielook/list_by_movie.do
  @RequestMapping(value="/movielook/list_by_movie.do", method=RequestMethod.GET)
  public ModelAndView list_by_movie() {
    ModelAndView mav = new ModelAndView();
    
    List<Movie_MovielookVO> list_by_movie = movielookProc.list_by_movie();
    mav.addObject("list_by_movie", list_by_movie);
    mav.setViewName("/movielook/list_by_movie"); // /webapp/movielook/list.jsp
    
    return mav;
  }
  


  
  @ResponseBody
  @RequestMapping(value="/movielook/update.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity update(int movielookno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    MovielookVO movielookVO = movielookProc.read(movielookno);
    
    JSONObject json = new JSONObject();
    json.put("movielookno", movielookVO.getMovielookno());
    json.put("starttime", movielookVO.getStarttime());
    json.put("endtime", movielookVO.getEndtime());
    json.put("pay", movielookVO.getPay());
    json.put("point",  movielookVO.getPoint());
    json.put("movieno", movielookVO.getMovieno());
    json.put("memberno", movielookVO.getMemberno());
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  // request.getParameter() 자동 실행
  // 형변환 자동 실행
  // MovielookVO 객체  자동 생성
  // http://localhost:9090/movie/movielook/update.do -> 실행 주소
  @RequestMapping(value="/movielook/update.do", method=RequestMethod.POST)
  public ModelAndView update(MovielookVO movielookVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = movielookProc.update(movielookVO);
 
    mav.setViewName("redirect:/movielook/list_by_movie.do"); 
    
    return mav;
  }
  

  // http://localhost:9090/movie/movielook/delete.do?movielookno=1
  // {"movielookno":1,"name":"여행2"}
  @ResponseBody
  @RequestMapping(value="/movielook/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int movielookno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    MovielookVO movielookVO = movielookProc.read(movielookno);

    
    JSONObject json = new JSONObject();
    json.put("movielookno", movielookVO.getMovielookno());
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  @RequestMapping(value="/movielook/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int movielookno) {
    ModelAndView mav = new ModelAndView();
    
    int count = movielookProc.delete(movielookno);
    mav.setViewName("redirect:/movielook/list_by_movie.do"); 
    
    return mav;
  }
/*  
  @RequestMapping(value = "/movielook/list_by_movie.do", method = RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) {
    System.out.println("--> nowPage: " + nowPage);
   
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/movielook/list_by_movie"); 

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word); // #{word}
    hashMap.put("nowPage", nowPage);
    
   
    // 검색 목록
    List<MovielookVO> list = movielookProc.list_search(hashMap);
    mav.addObject("list", list);
 
    int search_count = movielookProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    String paging = movielookProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }*/
}
