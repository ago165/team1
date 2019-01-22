<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- Bootstrap core CSS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> 
 
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<DIV class='container' style='margin: 70px auto; '>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 50%;'>
  

  
<DIV class='title_line' style='width: 40%; margin:50px auto;'>카테고리 등록</DIV>
 
<FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
  <!-- 개발시 임시 값 사용 -->
  <input type='hidden' name='surveyno' id='surveyno' value='1'>
 
  <fieldset class='fieldset_basic'>
    <ul>
      <li class='li_none'>
        <label>설문조사 항목을 등록합니다.</label>
      </li>
    
      <li class='li_none'>
        <label for='title'>설문조사 항목</label>
        <input type='text' name='surveytitle' id='surveytitle' value='' required="required" autofocus="autofocus" style="width:70%;">
      </li>

       <li class='li_none'>
        <label for='startdate'>시작 날짜</label>
        <input type='date' name='startdate' id='startdate' value='' required="required">
      </li>
      
       <li class='li_none'>
        <label for='enddate'>종료 날짜</label>
        <input type='date' name='enddate' id='enddate' value='' required="required">
      </li>
      
      <li class='li_none'>
        <label for='surveystatus'>진행 상황</label>
        <select name='surveystatus'>
          <option value='Y' selected="selected">Y</option>
          <option value='N'>N</option>
        </select><br><br>
        
        <div class="form-group"> 
        <label for="filesMF" style="margin-left:15px;" >업로드 파일</label>
        <div class="col-md-11" >
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
       </div>
       </div> 
       
        <div class="form-group">   <br>
        <label for="content"  style="margin-left:17px;">검색어</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='word' id='word' value='여행,동해,서해,남해,단풍,첫눈'>
        </div>
      </div>
       
      <li class='li_right' style="margit-right:15px;">
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
 
 
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html> 

