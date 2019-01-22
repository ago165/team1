package dev.mvc.movielook;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.movielook.MovielookDAO")
public class MovielookDAO implements MovielookDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public MovielookDAO() {
    System.out.println("--> MovielookDAO created.");
  }

  @Override
  public int create(MovielookVO movielookVO) {
    return sqlSessionTemplate.insert("movielook.create", movielookVO);
  }
  
  @Override
  public List<MovielookVO> list(){
    List<MovielookVO> list = sqlSessionTemplate.selectList("movielook.list");
    return list;
  }

  @Override
  public MovielookVO read(int movielookno) {
    MovielookVO movielookVO = sqlSessionTemplate.selectOne("movielook.read", movielookno);
    return movielookVO;
  }
  
  @Override
  public int update(MovielookVO movielookVO) {
    int count = sqlSessionTemplate.update("movielook.update",movielookVO);
    return count;
  }

  @Override
  public int delete(int movielookno) {
    return sqlSessionTemplate.delete("movielook.delete", movielookno);
  }

  @Override
  public List<Movie_MovielookVO> list_by_movie() {
    List<Movie_MovielookVO> list_by_movie = sqlSessionTemplate.selectList("movielook.list_by_movie");
    return list_by_movie;
  }

/*  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("movielook.search_count", hashMap);
  }

  @Override
  public List<MovielookVO> list_search(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("movielook.list_search", hashMap);
  }*/




}
