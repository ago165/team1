<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">
  $(function(){

  });
</script>

</head> 

<body>
<div class="container" style="margin: 100px auto;">
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 50%;'>

<DIV class='title_line' style='width: 40%;'>영화 시청하기</DIV>

<FORM name='frm' method='POST' action='./create.do'>
  <fieldset class='fieldset_basic'>
    <ul>
<!--       
      <li class='li_none'>
        <label class='label' for='classification'>영화 시청 하기</label>
        <select name='classification' id='classification'>
          <option value='1' selected="selected">1-Blog</option>
          <option value='2'>2-Gallery</option>
          <option value='3'>3-Product</option>
          <option value='9'>9-기타</option>
        </select>
      </li> -->
    <li class='li_none'>
      <label class='label' for='movielookno'>영화 시청 하기</label>
      <select name='movielookno' id='movielookno'>
        <option value='1' selected="selected">1-성난황소</option>
      </select>
    </li> 
      <li class='li_none'>
        <label class='label' for='starttime'>영화 시청 시간</label>
        <input type='text' name='starttime' id='starttime' value='2019-01-09 18:00:00' required="required" autofocus="autofocus">
      </li>
      <li class='li_none'>
        <label class='label' for='endtime'>영화 종료 시간</label>
        <input type='text' name='endtime' id='endtime' value='2019-01-09 20:00:00' required="required" autofocus="autofocus">
      </li>
    <li class='li_none'>
      <label class='label' for='pay'>영화 결제 수단</label>
      <select name='pay' id='pay'>
        <option value='국민카드' selected="selected">1-국민카드</option>
        <option value='신한카드' selected="selected">2-신한카드</option>
        <option value='카카오페이' selected="selected">3-카카오페이</option>
      </select>
    </li> 
      <li class='li_none'>
        <label class='label' for='point'>포인트</label>
        <input type='text' name='point' id='point' value='110' required="required" autofocus="autofocus">
      </li>
     <li class='li_none'>
        <label class='label' for='movieno'>영화번호</label>
        <input type='text' name='movieno' id='movieno' value='1' required="required" autofocus="autofocus">
      </li>
     <li class='li_none'>
        <label class='label' for='memberno'>회원번호</label>
        <input type='text' name='memberno' id='memberno' value='1' required="required" autofocus="autofocus">
      </li>
<!--      <li class='li_none'>
        <label class='label' for='word'>회원번호</label>
        <input type='text' name='word' id='word' value='1' required="required" autofocus="autofocus">
      </li> -->
      <li class='li_right'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list_by_movie.do'">목록</button>
      </li>      

    </ul>
  </fieldset>
</FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
  
   
