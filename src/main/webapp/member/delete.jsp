<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>회원 삭제</title> 

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

</head> 
<jsp:include page="/menu/top.jsp" flush='false' />
<body>
<DIV class='container'>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<DIV class='content'>   
     
  <ASIDE style='float: left'>
    <A href='../member/list.do'>회원 목록</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?memberno=${memberVO.memberno }'>등록</A>

  </ASIDE> 
  
  <div class='menu_line'></div>
  <br>
  <br>
  <br>
  <br>
  <FORM name='frm' method='POST' action='./delete.do'
                 onsubmit = 'return send();'>
      <input type='hidden' name='memberno' value='${memberVO.memberno}'>
    
      <div class="form-group" style='font-size:18px;'>   
        <div class="col-md-12" style='text-align: center; margin: 30px; color:#ff0000'>
          삭제 되는 아이디: ${memberVO.id }<br><br>
          삭제하시겠습니까? <br><br>
          삭제하시면 복구 할 수 없습니다.<br><br>
          <button type = "submit" style='color:#333333;'>삭제 진행</button>
          <button type = "button" onclick = "history.back()" style='color:#333333;'>취소</button>
        </div>
      </div>   
  </FORM><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 
