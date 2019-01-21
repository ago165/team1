<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

      <link href="../css/style.css" rel="Stylesheet" type="text/css">
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css" href="../css/vendor/bootstrap/css/bootstrap.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
  var loading = 0;
  
  $(document).ready(function(){ // window.onload = function() { ... }
    imgResize()
  });

  //<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    loading = loading + 1;
    var files = $('#files');
    var width = files.width();
    // alert(width);
    
    if (files != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.3)) {  
        // file1.width(600); // 이미지 축소
        $('#file1Panel').attr('width', '100%');
        file1.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }
    
    var timer = setInterval(imgResize, 100); // 0.1 초
    
    if (loading == 2) {
      clearInterval(timer) // 타이머 종료, 함수 실행 종료
    }

  }
</script>
</head>
<jsp:include page="/menu/top.jsp" flush='false' />
<body>
<DIV class='container'>

<DIV class='content'>   
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     
  <ASIDE style='float: left; font-size:18px;'>
    <A href='../actor/list.do'>배우 목록</A>
    >  
    <A href='./list.do?actorno=${actorVO.actorno }' style='font-weight: bold'>${actorVO.actorname }</A>

  </ASIDE>
  <ASIDE style='float: right;  font-size:18px;'>
    <A href="javascript:location.reload();">새로고침</A>

    <c:if test="${sessionScope.id ne null }">
      <span class='menu_divide'  style='font-size:18px;'>│</span> 
      <A href='./create.do?actorno=${actorVO.actorno }'>등록</A>
     <span class='menu_divide' style='font-size:18px;'>│</span> 
      <A href='./update.do?actorno=${actorVO.actorno }'>수정</A>
      <span class='menu_divide' style='font-size:18px;'>│</span> 
      <A href='./delete.do?actorno=${actorVO.actorno }'>삭제</A>

    </c:if>
        
  </ASIDE> 
  
  <div class='menu_line'></div>
  <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="actorno" value="${actorVO.actorno}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none" style='font-size:18px;'>
            
            <span>${actorVO.actorname}</span>
            <br>
            <span>
              <c:if test="${actorVO.sizes > 0}">
                파일: <A href='${pageContext.request.contextPath}/download?dir=/actor/storage&filename=${actorVO.files}'>${actorVO.files}</A>
              </c:if>
            </span>
          </li>
          <li class="li_none">    
            <div id='file1Panel'>
              <!-- EL 값을 JSTL 변수에 할당 -->
              <c:set var='files' value="${fn:toLowerCase(actorVO.files)}" />
              
              <c:choose>
                <c:when test="${fn:endsWith(files, '.jpg')}">
                  <IMG id='files' src='./storage/${actorVO.files}'  style='width:30%;'>
                </c:when>
                <c:when test="${fn:endsWith(files, '.png')}">
                  <IMG id='files'  src='./storage/${actorVO.files}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          <li class="li_none" style='font-size:18px;'>
            
            <DIV><A> 배우이름 : </A>${actorVO.actorname }</DIV>
            <DIV><A> 배우출생 : </A>${actorVO.actorbrd }</DIV>
            <DIV><A> 배우 수상경력 :  </A>${actorVO.actor_sang }</DIV>
            <DIV><A> 배우 신체 : </A>${actorVO.physical }</DIV>
            <DIV><A> 배우 출연작 :  </A>${actorVO.actorwork }</DIV>
            
          </li>
        </ul>
      </fieldset>
  </FORM>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html>

