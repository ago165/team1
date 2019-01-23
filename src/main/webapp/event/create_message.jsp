<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none' style='margin-top: 100px;'>새로운 글을 등록했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>새로운 글 등록에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br><br>
        <button type='button' onclick="location.href='./create.do'">새로운 글 등록</button>
        <button type='button' onclick="location.href='./list.do'">목록</button>
      </LI>
     </UL>
  </fieldset>
</DIV>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

   