package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.event.EventDAO")
public class EventDAO implements EventDAOInter {
  
  @Autowired 
  private SqlSessionTemplate sqlSessionTemplate = null;
   
  public EventDAO() {
    System.out.println("--> EventDAO crated");
  }
  
  @Override
  public int create(EventVO eventVO) {
    return sqlSessionTemplate.insert("event.create", eventVO);
  }
  
  @Override
  public List<EventVO> list() {
    List<EventVO> list = sqlSessionTemplate.selectList("event.list");
    
    return list;
  }
  
  @Override
  public EventVO read(int eventno) {
    return sqlSessionTemplate.selectOne("event.read", eventno);
  }
  
  @Override
  public int update(EventVO eventVO) {
    return sqlSessionTemplate.update("event.update", eventVO);
  }
  
  @Override
  public int delete(int eventno) {
    return sqlSessionTemplate.update("event.delete", eventno);
  }
  
  @Override
  public int increaseCnt(int eventno) {
    return sqlSessionTemplate.update("event.increaseCnt", eventno);
  }

  @Override
  public int decreaseCnt(int eventno) {
    return sqlSessionTemplate.update("event.decreaseCnt", eventno);
  }
  
  @Override
  public List<EventVO> list_by_category_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("event.list_by_category_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("event.search_count", hashMap);
  }

  @Override
  public List<EventVO> list_by_category_search_paging(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("event.list_by_category_search_paging", hashMap);
  }
}
 
