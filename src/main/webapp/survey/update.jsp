<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>설문 항목 수정</title> 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- Bootstrap core CSS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> 



</head> 

<body>
<DIV class='container' style='margin : 170px auto; '>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     

  <ASIDE style='float: left;'>
    <A href='../survey/list.do'>설문항목 목록</A>
    <span style='font-size: 1.2em;'>></span>   
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <div class='menu_line'></div>
  <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='surveyno' id='surveyno' value='${surveyVO.surveyno }'>
      <br>
       <fieldset class='fieldset_basic'>
        <ul>
         <li class='li_none'>
          <label>설문조사 항목을 수정합니다.</label>
        </li> <br>
        
          <li class='li_none'>
            <label for='title' >설문조사 항목</label>
            <input type='text' name='surveytitle' id='surveytitle' value='${surveyVO.surveytitle }' required="required" autofocus="autofocus"style="width:70%;">
          </li>
          
          <li class='li_none'>
            <label for='title' >시작 날짜</label>
            <input type='date' name='startdate' id='startdate' value='${surveyVO.startdate }' required="required">
          </li>
          
          <li class='li_none'>
            <label for='enddate' >종료 날짜</label>
            <input type='date' name='enddate' id='enddate' value='${surveyVO.enddate }' required="required">
          </li>
          
          <li class='li_none'> 
           <label for='surveystatus'>진행 상황</label>
             <select name='surveystatus'>
               <option value='Y' selected="selected">Y</option>
               <option value='N'>N</option>
              </select>
          

          <br><br>
        <div class="form-group"> 
        <label for="filesMF" style="margin-left:15px;" >업로드 파일</label>
        <div class="col-md-11" >
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
       </div>
       </div> 
       
                 <c:if test="${file_list.size() > 0 }">
              <DIV>
                <c:forEach var ="fileVO"  items="${file_list }">
                  <A href="javascript: panel_img('${fileVO.file }')"><IMG src='./storage/${fileVO.thumb }' style='margin-top: 2px;'></A>
                </c:forEach>
              </DIV>
          </c:if><br>
       
        <div class="form-group">   
        <label for="content"  style="margin-left:15px;">검색어</label><br>
        <div class="col-md-11"> 
          <input type='text' class="form-control input-lg" name='word' id='word' value='여행,동해,서해,남해,단풍,첫눈'>
        </div>
      </div>
      
      <li class='li_right' style="margit-right:15px;">
        <button type="submit">변경된 내용 저장</button>
        <button type="button" onclick="location.href='./list.do?surveyno=${surveyVO.surveyno}'">취소[목록]</button>
      </li>
      </ul>
      </fieldset>
  </FORM>


</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
 
 
