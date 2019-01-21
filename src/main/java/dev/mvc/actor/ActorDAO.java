package dev.mvc.actor;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





@Repository("dev.mvc.actor.ActorDAO")
public class ActorDAO implements ActorDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ActorDAO() {
    System.out.println("--> ActorDAO created");
  }
  
  @Override
  public int create(ActorVO actorVO) {
    int cnt =  sqlSessionTemplate.insert("actor.create", actorVO);  
    System.out.println("DAO cnt: " + cnt);
    return cnt;
        
  }
  
/*  @Override
  public List<ActorVO> list(HashMap hashMap) {
    return sqlSessionTemplate.selectList("actor.list", hashMap);
  }*/
  
  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("actor.search_count", hashMap);
  }
  
  @Override
  public List<ActorVO> list(HashMap<String, Object> hashMap) {
    // return sqlSessionTemplate.selectList("contents.list_by_category_search_paging", hashMap);
    return sqlSessionTemplate.selectList("actor.list", hashMap);
  }
  @Override
  public ActorVO read(int actorno) {
    return sqlSessionTemplate.selectOne("actor.read", actorno);
  }
  
  @Override
  public int update(ActorVO actorVO) {
    return sqlSessionTemplate.update("actor.update", actorVO);
  }

  @Override
  public int delete(int actorno) {
    return sqlSessionTemplate.delete("actor.delete", actorno) ;
  }

/*  @Override
  public List<ActorVO> list_by_actor_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("actor.list_by_actor_search", hashMap);
  }*/

}
