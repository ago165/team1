<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title>  
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<style>
  td {
    text-align : center;
  } 
  th {
    border-color : #777777;
  }
  .content {
    margin-top : 80px;
  }
</style>
</head>  
<body>

<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
  <ASIDE style='float: left;' >
    <A href='./event/list.do'>게시판 목록</A> >
    <A href='./list.do?eventno=${eventVO.eventno }'>${eventVO.title }</A>
    <c:if test="${param.word.length() > 0}"> 
      > 
    [${param.word}] 검색 목록 (${search_count } 건) 
    </c:if> 
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./create.do?eventno=${eventVO.eventno}">등록</A>
    
    <input type='hidden' name='eventno' id='eventno' value='${eventVO.eventno }'>
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
      <button type='button' onclick="location.href='./list_by_category_search.do?eventno=${eventVO.eventno }'">전체 보기</button>
  </ASIDE> 
  
 <DIV class='menu_line' style='clear: both;'></DIV>        
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%; text-align: center;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 40%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style="border-bottom: dotted 1px #777777;">파일</th>
          <th style="border-bottom: dotted 1px #777777;">제목</th>
          <th style="border-bottom: dotted 1px #777777;">등록일</th>
          <th style="border-bottom: dotted 1px #777777;">기간</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
      
        <c:forEach var="eventVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${eventVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${eventVO.thumb }'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG src='./images/none1.jpg' style='width: 120px; height: 80px;'>
                </c:otherwise>
              </c:choose>
            </td>
            <td> <a href="./read.do?eventno=${eventVO.eventno}">${eventVO.title} </a> </td>     
            <td> ${eventVO.rdate} </td>
            <td> ${eventVO.startdate} ~ ${eventVO.enddate}</td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 
 