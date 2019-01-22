<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="../css/portfolio-item.css" rel="stylesheet">
    
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

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
<DIV class='container' style="margin: 50px auto;">
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

  <!-- <ASIDE style='float: left;'>
      <A href='./manager/list.do'>관리자 목록</A>  
  </ASIDE> -->
  
  <ASIDE style='float:right; font-size:20px;'>    
    <A href='./create.do' style='color:#333333;'>관리자 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do' style='color:#333333;'>관리자 목록</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();" style='color:#333333;'>새로고침</A>
  </ASIDE>

  <div class='menu_line'></div>
  
  <form name='frm' id='frm' method="get" action="./list.do">


  <ASIDE style='float: right; font-size:15px;'>    
    
    <c:choose>
      <c:when test="${param.s_word != '' }">
        <input type='text' name='s_word' id='s_word' value='${param.s_word }' style='width: 50%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='s_word' id='s_word' value='' style='width: 50%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
    <button type='button' 
                 onclick="location.href='./list.do'">전체 목록</button>
  </ASIDE>
  
      <ASIDE style='float: right; font-size:18px;'>
    <c:if test="${param.s_word.length() > 0}"> 
      ☞ ${param.s_word} 검색 결과 : ${search_count } 건&nbsp;&nbsp;
    </c:if>    
  </ASIDE>
  
  </form>
  
<h1>관리자 목록</h1><br>

  <table class="table table-striped" style='width: 100%; font-size:18px;'>

  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 25%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>ID</TH>
    <TH class='th'>성명</TH>
    <TH class='th'>사진</TH>
    <TH class='th'>전화번호</TH>
    <TH class='th'>주소</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>기타</TH>
  </TR>

  <c:forEach var="managerVO" items="${list }">
    <%-- <c:set var="managerno" value ="${managerVO.managerno }" />  --%>
  <TR>
   <%--     <TD class='td'>${managerno}</TD>  --%>
    <TD class='td'><A href="./read.do?managerno=${managerVO.managerno}" style='color:#333333;'>${managerVO.managerno}</A></TD>
    <TD class='td'><A href="./read.do?managerno=${managerVO.managerno}" style='color:#333333;'>${managerVO.id}</A></TD>
    <TD class='td'><A href="./read.do?managerno=${managerVO.managerno}" style='color:#333333;'>${managerVO.mname}</A></TD>
  

     <td style='vertical-align: middle;'>         
              <c:choose>
                <c:when test="${managerVO.thumbs != ''}">
                  <IMG id='thumbs' src='./storage/${managerVO.thumbs }'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG src='./images/none1.jpg' style='width: 120px; height: 80px;'>
                </c:otherwise>
              </c:choose>
            </td>
    
    
    <TD class='td'>${managerVO.tel}</TD>
    <TD class='td'>
      <c:choose>
        <c:when test="${managerVO.address1.length() > 15 }">
          ${managerVO.address1.substring(0, 15) }...
        </c:when>
        <c:otherwise>
          ${managerVO.address1}
        </c:otherwise>
      </c:choose>
    </TD>
    <TD class='td'>${managerVO.mdate.substring(0, 10)}</TD> <!-- 년월일 -->
    <TD class='td'>
      <A href="./passwd_update.do?managerno=${managerVO.managerno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
<%--
      <A href="./update.do?managerno=${managerVO.managerno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?managerno=${managerVO.managerno}"><IMG src='./images/delete.png' title='삭제'></A>
--%>
      <A href="./update.do?managerno=${managerVO.managerno}&managerno=${managerVO.managerno}&nowPage=${param.nowPage}&s_word=${param.s_word}"><img src="./images/update.png" title="수정" border='0'/></A>
      <A href="./delete.do?actorno=${managerVO.managerno}&managerno=${managerVO.managerno}&nowPage=${param.nowPage}&s_word=${param.s_word}"><img src="./images/delete.png" title="삭제"  border='0'/></A>
    
    </TD>
    
  </TR>
  </c:forEach>
  
</TABLE>
    <DIV class='bottom_menu' style='font-size:20px;'>${paging }</DIV>

</DIV><br><!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html>
  