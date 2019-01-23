<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
$(function(){

});
</script>

<script type="text/javascript">
</script>

<script>

//등록 처리
function create() {
  $.ajax({
    url: "./create_json.do", // 요청을 보낼주소
    type: "post",  // or get
    cache: false,
    dataType: "json", // 응답 데이터 형식, or json
    data: $('#frm_create').serialize(), 
    // Ajax 통신 성공, JSP 정상 처리
    success: function(rdata) { // callback 함수
      var panel = '';
      panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
      panel += '  알림<br>';
      for(index=0; index < rdata.msgs.length; index++) {
        panel += rdata.msgs[index]+'<br>';
      }
      panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
      panel += "</DIV>";
      
      action_cancel();
      
      list();  // 전체 카테고리 목록
      
      $('#main_panel').html(panel);
      $('#main_panel').show();
      
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var panel = '';
      panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
      panel += '  ERROR<br><br>';
      panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
      panel += '  <strong>error</strong><br>'+error + '<hr>';
      panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
      panel += "</DIV>";
      
      $('#main_panel').html(panel);
      $('#main_panel').show();

    }
  });
}
</script>
<style>
  td {
    text-align : center;
  } 
  th {
    text-align : center;
    border-color : #777777;
    border-bottom: dotted 1px #777777;
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
  <form name='frm' id='frm' method="get" action="./list_by_category_search_paging.do">
  <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    >  
    <A href='./list_by_category_search_paging.do?eventno=${eventVO.eventno }'>${eventVO.title }</A>

    <c:if test="${param.word.length() > 0}"> 
      >
      [${param.word}] 검색 목록(${search_count } 건) 
    </c:if>

  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do?eventno=${eventVO.eventno }'>등록</A>
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
    <button type='button' 
                 onclick="location.href='./list_by_categoryno.do?eventno=${eventVO.eventno }'">전체 보기</button>
  </ASIDE>
  </form>
    
  <div class='menu_line' style='clear: both;'></div>       

  <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 50%;"></col>
        
      </colgroup>
      <thead>
        <tr>
          <th>파일</th>
          <th>제목</th>
          <th>등록일</th>
          <th>기간</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="eventVO" items="${list }">
          <tr>
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${eventVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${eventVO.thumb}' > <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/none1.jpg' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
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
  <DIV class='bottom_menu'>${paging }</DIV>
  <br><br>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html>

