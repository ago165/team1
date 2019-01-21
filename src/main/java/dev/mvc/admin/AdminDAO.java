package dev.mvc.admin;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.admin.AdminDAO")
public class AdminDAO implements AdminDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public AdminDAO() {
    System.out.println("--> AdminDAO created"); 
  }
  
  @Override
  public int create(AdminVO adminVO) {
    return sqlSessionTemplate.insert("admin.create", adminVO);
  }

  @Override
  public List<AdminVO> list() {
    return sqlSessionTemplate.selectList("admin.list");    
  }
  @Override
  public AdminVO read(int adminno) {
    return sqlSessionTemplate.selectOne("admin.read", adminno);   
  }
  @Override
  public int update(AdminVO adminVO) {
    return sqlSessionTemplate.update("admin.update", adminVO);
    
  }

  @Override
  public int delete(int adminno) {
    return sqlSessionTemplate.delete("admin.delete", adminno);   
  }
}
