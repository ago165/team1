package dev.mvc.manager;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("dev.mvc.manager.ManagerDAO")
public class ManagerDAO implements ManagerDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis = null;

  public ManagerDAO() {
    System.out.println("--> ManagerDAO created.");
  }  

  /**
   * 중복 아이디 검사
   * <select id="checkId" resultType="int" parameterType="String"> 
   * @param id
   * @return 중복 아이디 갯수
   */
  @Override
  public int checkId(String id) {
    int count = mybatis.selectOne("manager.checkId", id);
    
    return count;
  }
  
// 관리자 등록
  @Override
  public int create(ManagerVO managerVO) {
    int cnt = mybatis.insert("manager.create", managerVO);
    System.out.println("DAO cnt : "+ cnt);
    return cnt;
  }  
  

  
  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("manager.search_count", hashMap);
  }
  
  @Override
  public List<ManagerVO> list(HashMap<String, Object> hashMap) {
/*    List<ManagerVO> list = mybatis.selectList("manager.list");
    return list;*/
    return mybatis.selectList("manager.list", hashMap);
  }
  
  
  @Override
  public ManagerVO read(int managerno) {
    return mybatis.selectOne("manager.read", managerno);    
  }
  
  @Override
  public ManagerVO readById(String id) {
    ManagerVO managerVO = mybatis.selectOne("manager.readById", id);
    
    return managerVO;
  }  
  
  @Override
  public int update(ManagerVO managerVO) {
    return mybatis.update("manager.update", managerVO);
  }  
   
  
  
  
  
  
  
  @Override
  public int delete(int managerno) {
    return mybatis.delete("manager.delete", managerno);
  }
  
  
  
}

