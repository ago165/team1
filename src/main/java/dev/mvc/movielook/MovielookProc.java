package dev.mvc.movielook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.movielook.MovielookProc")
public class MovielookProc implements MovielookProcInter {
  @Autowired
  @Qualifier("dev.mvc.movielook.MovielookDAO")
  private MovielookDAOInter movielookDAO = null;
  
  public MovielookProc() {
    System.out.println("--> MovielookProc created.");
  }

  @Override
  public int create(MovielookVO movielookVO) {
    return movielookDAO.create(movielookVO);
  }

  @Override
  public List<MovielookVO> list() {
    List<MovielookVO> list = movielookDAO.list();
    return list;
  }

  @Override
  public MovielookVO read(int movielookno) {
    return movielookDAO.read(movielookno);
  }

  @Override
  public int update(MovielookVO movielookVO) {
    return movielookDAO.update(movielookVO);
  }

  @Override
  public int delete(int movielookno) {
    return movielookDAO.delete(movielookno);
  }

  @Override
  public List<Movie_MovielookVO> list_by_movie() {
    List<Movie_MovielookVO> list_by_movie = movielookDAO.list_by_movie();
    return list_by_movie;
  }

/*  @Override

 public int search_count(HashMap hashMap) {
   return movielookDAO.search_count(hashMap);
  }
*/
}
