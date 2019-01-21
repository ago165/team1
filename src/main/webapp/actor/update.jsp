<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>배우 정보 수정</title> 

      <link href="../css/style.css" rel="Stylesheet" type="text/css">
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css" href="../css/vendor/bootstrap/css/bootstrap.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
 -->
</head> 
<jsp:include page="/menu/top.jsp" flush='false' />
<body>
<DIV class='container'>

<DIV class='content'>     
<br>
<br>
  <ASIDE style='float: left'>
    <A href='../actor/list.do' style='font-size:18px;'>배우 목록</A>
  </ASIDE>
  <ASIDE style='float: right'>
    <A href="javascript:location.reload();" style='font-size:18px;'>새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?actorno=${actorVO.actorno }' style='font-size:18px;'>등록</A>


  </ASIDE> 

  <div class='menu_line'></div>
  <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='actorno' id='actorno' value='${actorVO.actorno }'>
      <%-- <input type='hidden' name='nowPage' id='nowPage' value='${param.nowPage }'> --%>
      <div class="form-group" style='font-size:15px;'> 
        <label for="actorname" class="col-md-1 control-label">
        배우명</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="actorname" id="actorname" rows='1'>${actorVO.actorname}></textarea>
        </div>
      </div>   
      <div class="form-group" style='font-size:15px;'>   
        <label for="actorbrd" class="col-md-1 control-label">배우 출생</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="actorbrd" id="actorbrd"  rows='1'>${actorVO.actorbrd}</textarea>
        </div>
      </div>
       <div class="form-group" style='font-size:15px;'>   
        <label for="actorbrd" class="col-md-1 control-label">수상 경력</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="actor_sang"id="actor_sang" rows='10'>${actorVO.actor_sang}</textarea>
        </div>
      </div>
      <div class="form-group" style='font-size:15px;'>   
        <label for="physical" class="col-md-1 control-label">배우 신체</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="physical" id="physical"  rows='1'>${actorVO.physical}</textarea>
        </div>
      </div>
      <div class="form-group" style='font-size:15px;'>   
        <label for="physical" class="col-md-1 control-label">출연작</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="actorwork" id="actorwork"  rows='10'>${actorVO.actorwork}</textarea>
        </div>
      </div>
      
      <div id='filesPanel' class="form-group">
        <label class="col-md-1 control-label"></label>
        <div class="col-md-11">
          <!-- 파일명을 소문자로 변경 -->
          <c:set var='files' value="${fn:toLowerCase(actorVO.files)}" />
          <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
          <c:choose>
            <c:when test="${fn:endsWith(files, '.jpg')}">
              <IMG id='files' src='./storage/${actorVO.files}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(files, '.png')}">
              <IMG id='files' src='./storage/${actorVO.files}' style='width: 20%;'>
            </c:when>
            <c:when test="${actorVO.files.length() > 0}">
              ${actorVO.files }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group" style='font-size:15px;'>   
        <label for="filesMF" class="col-md-1 control-label">파일</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-md" name='filesMF' id='filesMF' size='40' >
     <div class="form-group" style='font-size:15px;'>  
      <label for="s_word" class="col-md-1 control-label">검색어</label>
        <div class="col-md-11" style="font-size:15px">
          <input type='text' class="form-control input-lg" name='s_word' id='s_word' value='필수'>
          
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit">저장</button>
        <button type="button" onclick="location.href='./list.do'">취소</button>
      </DIV>
  </FORM>




</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
<BR>
<BR>
<BR>
</DIV> <!-- container END -->
</body>

</html> 

