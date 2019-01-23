<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
  $(function(){ 
  
  });
</script>
<style>
.li_none {
  list-style: none;
}

.li_class {
  list-style: none;
}

.li_center {
  text-align: center;
  list-style: none; /* 블렛 기호 생략 */
  margin: 20px auto;
}
.content {
    margin-top : 80px;
  }
.message {
    width: 70%;
    border: none;
    margin: 30px auto;
    text-align: center;
    background-color: #F8F8F8;
    padding: 30px;
    
  }  
</style>
</head> 
<body>
<DIV class='container'>
<c:import url="/menu/top.jsp" />
<DIV class='content'>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 0}">
          <li class='li_none'>글 삭제에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='./list.do?eventno=${param.eventno}'">목록</button>
          </li>             
        </c:when>
        <c:when test="${param.count == 1}">
          <li class='li_none'>글 삭제에 성공했습니다.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick="location.href='./list.do?eventno=${param.eventno}'">목록</button>
          </li>          
        </c:when>
      </c:choose>    
    </UL>
  </fieldset>
</DIV>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

  