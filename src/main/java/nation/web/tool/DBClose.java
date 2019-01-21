package nation.web.tool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBClose {
  public void close(Connection con) {
    try {
      con.close();
    } catch (Exception e) {
    }
  }

  public void close(PreparedStatement pstmt) {
    try {
      pstmt.close();
    } catch (Exception e) {
    }
  }
  
  public void close(Connection con, PreparedStatement pstmt) {
    try {
      pstmt.close();
    } catch (Exception e) {
    }
    
    try {
      con.close();
    } catch (Exception e) {
    }
  }
   
  public void close(Connection con, CallableStatement cstmt) {
    try {
      cstmt.close();
    } catch (Exception e) {
    }
    
    try {
      con.close();
    } catch (Exception e) {
    }
  }
  
  public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
    try {
      rs.close();
    } catch (Exception e) {
    }
    
    try {
      pstmt.close();
    } catch (Exception e) {
    }
    
    try {
      con.close();
    } catch (Exception e) {
    }
  }
  
}




