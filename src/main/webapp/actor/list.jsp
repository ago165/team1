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




</head>
<jsp:include page="/menu/top.jsp" flush='false' />
<body>
<DIV class='container' style="margin: 80px auto;">

<DIV class='content'>     
<form name='frm' id='frm' method="get" action="./list.do">
  <ASIDE style='float: left; font-size:18px;'>
  <A href='../actor/list.do'>배우 목록</A>

    <c:if test="${param.s_word.length() > 0}"> 
      >
      [${param.s_word}] 검색 목록 (${search_count } 건) 
    </c:if>
    
  </ASIDE>
<ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>

    <span class='menu_divide' >│</span> 
    <A href='./create.do?actorno=${actorVO.actorno }'>등록</A>
    
    
    <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.s_word != '' }">
        <input type='text' name='s_word' id='s_word' value='${param.s_word }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='s_word' id='s_word' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
    <button type='button' 
                 onclick="location.href='./list.do?actorno=${actorVO.actorno }'">전체 보기</button>
  </ASIDE>
</form>
         
  <div style='width: 100%; font-size:18px;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%l"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>배우 사진</th>
          <th>배우 이름</th>        
          <th>배우 출생</th>
          <th>신체</th>
          <th>수정/삭제</th>
          
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="actorVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle;'>
              ${actorVO.actorno}
            </td> 
            <td style='vertical-align: middle;'>         
              <c:choose>
                <c:when test="${actorVO.thumbs != ''}">
                  <IMG id='thumbs' src='./storage/${actorVO.thumbs }'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG src='./images/none1.jpg' style='width: 120px; height: 80px;'>
                </c:otherwise>
              </c:choose>
            </td>
            
            
            <td style='vertical-align: middle;'><a href="./read.do?actorno=${actorVO.actorno}">${actorVO.actorname}</a></td>
              
            <td style='vertical-align: middle;'>${actorVO.actorbrd}</td>
            <td style='vertical-align: middle;'>${actorVO.physical}</td>
            <td style='vertical-align: middle;'>
              <a href="./update.do?actorno=${actorVO.actorno}&actorno=${actorVO.actorno}&nowPage=${param.nowPage}&s_word=${param.s_word}"><img src="./images/repair.jpg" title="수정" border='0'/></a>
              <a href="./delete.do?actorno=${actorVO.actorno}&actorno=${actorVO.actorno}&nowPage=${param.nowPage}&s_word=${param.s_word}"><img src="./images/sak.png" title="삭제"  border='0'/></a>
              
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
    <br><br>
  </div>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" />
</DIV> <!-- container END -->
</body>

</html>
