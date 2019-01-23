<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>블로그 삭제</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">

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
    margin-top : 100px;
  }
.form-group {
  
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
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   
     
  <ASIDE style='float: left;'>
    <A href='/event/list.do'>게시판 목록</A>
    >  
    <A href='./list.do?eventno=${eventVO.eventno }'>${eventVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?eventno=${eventVO.eventno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do?eventno=${eventVO.eventno }'>목록</A>

  </ASIDE> 
  
  <div class='menu_line'></div>

  <FORM name='frm' method='POST' action='./delete.do'>
      <input type='hidden' name='eventno' value='${eventVO.eventno}'>
      <br><br>
      <div class="form-group">   
        <div class="col-md-12" style='text-align: center; margin: 30px;'>
                    삭제 되는글: ${eventVO.title }<br><br>
                     삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br><br>
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>   
  </FORM>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

  