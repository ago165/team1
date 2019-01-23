<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>삭제</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<style>
  .form-group{
    margin-top: 150px;
  }
</style>
</head>
<body>
<DIV class='container' style='width: 100%;'>
  <jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class='content'>
       
    <aside style='float: right; margin-top: 100px;'>
      <a href='./list.do'>스낵존 목록</a>
      <span style='font-size: 1.2em;'>/</span>
      <a href='./create.do'>등록</a>
    </aside>
    
    <div class='menu_line'></div>
    
    <form name='frm' method='POST' action='./delete.do'>
      <input type="hidden" name='snackno' value='${snackzoneVO.snackno }'>
      
       <div class="form-group">
         <div class="snack_delete"style='text-align: center; margin: 30px;'>
            삭제 되는글: ${snackzoneVO.name }<br><br>
            삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br>
            <button type = "submit">삭제 진행</button>
            <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>
    </form>
    
</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->    
</body>
</html>