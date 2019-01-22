package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("dev.mvc.member.MemberDAO")
public class MemberDAO implements MemberDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis = null;

  public MemberDAO() {
    System.out.println("--> MemberDAO created.");
  }  

  /**
   * 중복 아이디 검사
   * <select id="checkId" resultType="int" parameterType="String"> 
   * @param id
   * @return 중복 아이디 갯수
   */
  @Override
  public int checkId(String id) {
    int count = mybatis.selectOne("member.checkId", id);
    
    return count;
  }
  
// 회원 등록
  @Override
  public int create(MemberVO memberVO) {
    int cnt = mybatis.insert("member.create", memberVO);
    System.out.println("DAO cnt : "+ cnt);
    return cnt;
  }  
  

  
  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("member.search_count", hashMap);
  }
  
  @Override
  public List<MemberVO> list(HashMap<String, Object> hashMap) {
/*    List<MemberVO> list = mybatis.selectList("member.list");
    return list;*/
    return mybatis.selectList("member.list", hashMap);
  }
  
  
  @Override
  public MemberVO read(int memberno) {
    return mybatis.selectOne("member.read", memberno);    
  }
  
  @Override
  public MemberVO readById(String id) {
    MemberVO memberVO = mybatis.selectOne("member.readById", id);
    
    return memberVO;
  }  
  
  @Override
  public int update(MemberVO memberVO) {
    return mybatis.update("member.update", memberVO);
  }  
   
  
  
  
  
  
  
  @Override
  public int delete(int memberno) {
    return mybatis.delete("member.delete", memberno);
  }
  
  
  
}

