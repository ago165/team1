package dev.mvc.snackzone;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.snackzone.SnackzoneDAO") // DBMS 저장소 접근
public class SnackzoneDAO implements SnackzoneDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null; 
   
  public SnackzoneDAO() {  
	  System.out.println("--> SnackzoneDAO created.");
  }

  @Override
  public int create(SnackzoneVO snackzoneVO) {
    return sqlSessionTemplate.insert("snackzone.create", snackzoneVO);
  }
  
  @Override
  public List<SnackzoneVO> list(HashMap<String, Object> hashMap) {
    List<SnackzoneVO> list = sqlSessionTemplate.selectList("snackzone.list", hashMap);
    return list;
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("snackzone.search_count", hashMap);
  }

  @Override
  public SnackzoneVO read(int snackno) {
    SnackzoneVO snackzoneVO = sqlSessionTemplate.selectOne("snackzone.read", snackno);
    return snackzoneVO;
  }

  @Override
  public int update(SnackzoneVO snackzoneVO) {
    return sqlSessionTemplate.update("snackzone.update", snackzoneVO);
  }

  @Override
  public int delete(int snackno) {
    return sqlSessionTemplate.delete("snackzone.delete", snackno);
  }

  /*@Override
  public List<SnackzoneVO> list_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("snackzone.list", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("snackzone.search_count", hashMap);
  }*/
  
  
}
