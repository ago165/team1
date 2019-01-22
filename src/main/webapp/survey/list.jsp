<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>


<!-- Bootstrap core CSS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->

<script type="text/javascript">
$(function(){

});
</script>

</head>

<body>
<DIV class='container' style='margin : 70px auto; '>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     

<DIV style = 'margin: 0px auto; text-align: center; width: 20%; font-size: 32px; font-weight:bold; text-decoration: underline;'> 설문조사 </DIV>

<form name='frm' id='frm' method="get" action="./list.do">
  <ASIDE style='float: left;'>
    <A href='../survey/list.do'>게시판 목록</A>

    <c:if test="${param.word.length() > 0}"> 
      >
      [${param.word}] 검색 목록(${search_count } 건) 
    </c:if>

  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>

    
      <span class='menu_divide' >│</span> 
      <A href='./create.do?surveyno=${surveyVO.surveyno }'>등록</A>
    
    
    <%-- <input type='hidden' name='surveyno' id='surveyno' value='${surveyVO.surveyno }'> --%>
     
    <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.word != '' }">
        <input type='text' name='word' id='word' value='${param.word }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='word' id='word' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
  </ASIDE>
  </form>
  
  
  <DIV class='menu_line' style='clear: both;'></DIV>  
         
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width:  5%;"></col>
        <col style="width: 45%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col> 
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th></th>
          <th>설문조사 항목</th>
          <th>시작일</th>
          <th>종료일</th>
          <th>참여인원</th>
          <th>진행여부</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="surveyVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${surveyVO.thumbs != ''}">
                  <IMG id='thumb' src='./storage/${surveyVO.thumbs }'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG src='./images/none1.jpg' style='width: 120px; height: 80px;'>
                </c:otherwise>
              </c:choose>
            </td>
            
            
            <td style='vertical-align: middle;'>
              <a href="./read.do?surveyno=${surveyVO.surveyno}">${surveyVO.surveytitle}</a> 
            </td> 

            <td style='vertical-align: middle; text-align: center;'>${surveyVO.startdate}</td>
            <td style='vertical-align: middle; text-align: center;'>${surveyVO.enddate}</td>
            <td style='vertical-align: middle; text-align: center;'>${surveyVO.partycnt}</td>
            <td style='vertical-align: middle; text-align: center;'>${surveyVO.surveystatus}</td>
            <td style='vertical-align: middle;'>
              <a href="./update.do?surveyno=${surveyVO.surveyno}"><img src="./images/update.jpg" title="수정" border='0' style="width:20%"/></a>
              <a href="./delete.do?surveyno=${surveyVO.surveyno}"><img src="./images/delete.png" title="삭제"  border='0' style="width:20%"/></a>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
    <br><br>
  </div>


</DIV> <!-- content END -->

</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>


</html>
