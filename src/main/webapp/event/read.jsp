<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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
</style>
</head>

<body>
<DIV class='container' style='width: 90%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   

  <ASIDE style='float: left;'>
    <A href='/event/list.do?eventno=${eventVO.eventno }'>목록</A>
    <span style='font-size: 1.2em;'>></span>  
    <A href='./list.do?eventno=${eventVO.eventno }'>${eventVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do?'>목록</A>
    <span class='menu_divide' >│</span> 
    <A href='./update.do?eventno=${eventVO.eventno}'>수정</A>
    <span class='menu_divide' >│</span> 
    <A href='./delete.do?eventno=${eventVO.eventno}'>삭제</A>
  </ASIDE> 
  
  <br><br>
  <div class='menu_line'></div>
  
  <FORM name='frm' method="get" action='./update.do'>    
   <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <span style="font-weight: bold;">
          ${eventVO.title}
        </span>
      </li>
      <li class='li_none'>
        <DIV style='float: left; margin: 8px 10px 0px 0px; width: 40%;'>
          <IMG id='thumb' src='./storage/${eventVO.thumb }' style='width: 100%;'>
        </DIV>
        <DIV>${eventVO.content}</DIV> 
      </li>
    </ul>
   </fieldset>
  </FORM>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body> 

</html>


   