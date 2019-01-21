package dev.mvc.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminDAO")
  
  private AdminDAOInter adminDAO = null;
  
  public AdminProc() {
    System.out.println("--> AdminProc created.");
  }

  @Override
  public int create(AdminVO adminVO) {
    return adminDAO.create(adminVO);
  }
  @Override
  public List<AdminVO> list() {
    return adminDAO.list();
  }

  @Override
  public AdminVO read(int adminno) {
    return adminDAO.read(adminno);
  }
  @Override
  public int update(AdminVO adminVO) {
    return adminDAO.update(adminVO);
  }

  @Override
  public int delete(int adminno) {
    return adminDAO.delete(adminno);
  }

}
