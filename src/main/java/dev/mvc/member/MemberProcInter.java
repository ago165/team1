package dev.mvc.member;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpSession;






public interface MemberProcInter {

    /**
     * 중복 아이디 검사
     * @param id
     * @return 중복 아이디 갯수
     */
    public int checkId(String id);
    
    /**
    회원 등록
    @param memberVO
    @return 등록된 회원수 1 or 0
    */
    public int create(MemberVO memberVO);
    
    /**
     * 회원 전체 목록
     * @return
     */
/*    public List<MemberVO> list();*/
    
    // 검색된 레코드 갯수
    public int search_count(HashMap hashMap);
    
    // 검색 목록 + 페이징
    public List<MemberVO> list(HashMap<String, Object> hashMap);
    
    public String paging(int search_count, int nowPage, String s_word);

    
    /**
     * 로그인된 회원 계정인지 검사합니다.
     * @param request
     * @return true: 관리자
     */
    public boolean isMember(HttpSession session);
    
    /**
     * 1개의 레코드 조회
     * @param memberno
     * @return
     */
    public MemberVO read(int memberno);

    /**
     * 아이디 조회
     * @param id
     * @return
     */
    public MemberVO readById(String id);

/**
 * 수정
 * @param memberVO
 * @return
 */
    public int update(MemberVO memberVO);
    
    
    
    
    
    
    
    
    /**
     * 레코드 1건 삭제
     * <delete id="delete" parameterType="int">
     * @param memberno 삭제할 회원 번호
     * @return 삭제된 레코드 갯수
     */
    public int delete(int memberno);
    
    
    
    
    
    
    
    
    
 
}









