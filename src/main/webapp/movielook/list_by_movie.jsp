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

<!-- Bootstrap -->
<!--  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
-->
<script type="text/javascript">
$(function() {
  
});

//수정 폼
function update(movielookno) {
  $('#panel_create').hide();
  $('#panel_update').show();
  
  $.ajax({
    url: "./update.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'movielookno='+movielookno,
    success: function(rdata) {
      // alert(rdata);
      var frm_update = $('#frm_update');
      $('#movielookno', frm_update).val(rdata.movielookno);
      $('#starttime', frm_update).val(rdata.starttime);
      $('#endtime', frm_update).val(rdata.endtime);
      $('#pay', frm_update).val(rdata.pay);
      $('#point', frm_update).val(rdata.point);
      $('#movieno', frm_update).val(rdata.movieno);
      $('#memberno', frm_update).val(rdata.memberno);
    },
    error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
      msg += '<strong>status</strong><br>'+status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
      msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
      msg += '예상 종료 시간: 16:00'; */
        
      $('#main_panel').html(msg);
      $('#main_panel').show();
    }
   });

}

function create_update_cancel() {
  $('#panel_update').hide();
  $('#panel_delete').hide();
  $('#panel_create').show();
}


// 삭제 폼
function deleteOne(movielookno) {
  $('#panel_create').hide();
  $('#panel_update').hide();
  $('#panel_delete').show();
  
  $.ajax({
    url: "./delete.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'movielookno='+movielookno,
    success: function(rdata) {
      // alert(rdata);
      var frm_delete = $('#frm_delete');
      $('#movielookno', frm_delete).val(movielookno);
      //  <수정해야할부분>
      var str = '';        
      // 소속된 카테고리 갯수를 출력 예정
        str = '[' + rdata.movielookno + "] 영화 시청을 삭제하시겠습니까?<br>";
        str += "삭제하면 복구 할 수 없습니다.<br>"
      $('#msg_delete').html(str);
    },
    error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
      msg += '<strong>status</strong><br>'+status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
      msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
      msg += '예상 종료 시간: 16:00'; */
        
      $('#main_panel').html(msg);
      $('#main_panel').show();
    }
   });

}
 
</script>
 
</head> 
 
<body>
    <!-- Page Content -->
    <div class="container" style="margin: 100px auto;">
<jsp:include page="/menu/top.jsp" flush='false'/>
<DIV class='content'>
  
  <DIV id='main_panel'></DIV>
  
  <DIV class='title_line' style='font-size : 20px;'>영화 시청하기</DIV>
  
  <%-- 
      <c:if test="${param.word.length() > 0}"> 
      >
      [${param.word}] 검색 목록(${search_count } 건) 
    </c:if>
    <!-- 개발 완료 후 삭제 -->
    <a class="nav-link" style='font-size : 20px;' href="${pageContext.request.contextPath}/movielook/create.do">등록(임시사용)</a>
    
    
       <span class='menu_divide' ></span> 
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
                 onclick="location.href='./list_by_movie.do?movielookno=${movielookVO.movielookno }'">전체 보기</button>
     --%>
    
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center; font-size :12px;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'> 
      <!-- <input type='hidden' name='lang' id='lang' value='en'> --> <!-- ko, en -->
        
      <label for='movielookno'></label>
      <label class='label' for='movielookno'></label>
      영화 시청하기 : <input type='text' name='movielookno' id='movielookno' value='${movielookVO.movielookno }' required="required" autofocus="autofocus">
          
      <label class='label' for='starttime'></label>
      영화 시청 시간 : <input type='text' name='starttime' id='starttime' value='시청시간등록' required="required" autofocus="autofocus">
 
      <label class='label' for='endtime'></label>
       영화 종료 시간 : <input type='text' name='endtime' id='endtime' value='영화 종료 시간' required="required" autofocus="autofocus">

      <label class='label' for='pay'></label>
      영화 결제 수단 : <select name='pay' id='pay'>
        <option value='국민카드' selected="selected">1-국민카드</option>
        <option value='신한카드' selected="selected">2-신한카드</option>
        <option value='카카오페이' selected="selected">3-카카오페이</option>
      </select>
      <label class='label' for='point'></label>
        포인트 : <input type='text' name='point' id='point' value='110' required="required" autofocus="autofocus">
      
         <label class='label' for='movieno'></label>
        영화번호 : <input type='text' name='movieno' id='movieno' value='1' required="required" autofocus="autofocus">

        <label class='label' for='memberno'></label>
        회원번호 : <input type='text' name='memberno' id='memberno' value='1' required="required" autofocus="autofocus">
        
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="create_update_cancel();">취소</button>
    </FORM>
  </DIV>
 
  <DIV id='panel_update' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center; font-size :12px;'>
    <FORM name='frm_update' id='frm_update' method='POST' 
                action='./update.do'>
      <input type='hidden' name='movielookno' id='movielookno' value=''>
 
<!--       
      <label for='classification'>그룹 분류 코드</label>
      <select name='classification' id='classification'>
        <option value='1' selected="selected">1-Blog</option>
        <option value='2'>2-Gallery</option>
        <option value='3'>3-Product</option>
        <option value='9'>9-기타</option>
      </select>
            
      <label for='name'>그룹 이름</label>
      <input type='text' name='name' id='name' size='15' value='' required="required" style='width: 30%;'>
 
      <label for='seqno'>순서</label>
      <input type='number' name='seqno' id='seqno' value='' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label for='visible'>형식</label>
      <select name='visible' id='visible'>
        <option value='Y' selected="selected">Y</option>
        <option value='N'>N</option> 
      </select> 
-->

<!--       <label for='movielookno'>영화 시청하기</label>
      <select name='movielookno' id='movielookno'>
        <option value='1' selected="selected">1-성난황소</option>
        <option value='2'>2-럭키</option>
        <option value='3'>3-영화...</option>
        <option value='9'>9-기타</option>
      </select> -->
      
      <label for='movielookno'></label>
      <label class='label' for='movielookno'></label>
      영화 시청하기 : <input type='text' name='movielookno' id='movielookno' value='${movielookVO.movielookno }' required="required" autofocus="autofocus">
          
      <label class='label' for='starttime'>영화 시청 시간</label>
      영화 시청 시간 : <input type='text' name='starttime' id='starttime' value='2019-01-09 18:00:00' required="required" autofocus="autofocus">
 
      <label class='label' for='endtime'>영화 종료 시간</label>
      영화 종료 시간 : <input type='text' name='endtime' id='endtime' value='2019-01-09 20:00:00' required="required" autofocus="autofocus">
     
     <label class='label' for='pay'>영화 결제 수단</label>
      영화 결제 수단 : <select name='pay' id='pay'>
        <option value='국민카드' selected="selected">1-국민카드</option>
        <option value='신한카드' selected="selected">2-신한카드</option>
        <option value='카카오페이' selected="selected">3-카카오페이</option>
      </select>
      <label class='label' for='point'>포인트</label>
        포인트 : <input type='text' name='point' id='point' value='110' required="required" autofocus="autofocus">
      
         <label class='label' for='movieno'>영화번호</label>
        영화번호 : <input type='text' name='movieno' id='movieno' value='' required="required" autofocus="autofocus">

        <label class='label' for='memberno'>회원번호</label>
        회원번호 : <input type='text' name='memberno' id='memberno' value='1' required="required" autofocus="autofocus">
        
      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="create_update_cancel();">취소</button>
    </FORM>
  </DIV>
 
  <DIV id='panel_delete' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center; font-size :12px;'>
    <FORM name='frm_delete' id='frm_delete' method='POST' 
                action='./delete.do'> 
      <input type='hidden' name='movielookno' id='movielookno' value=''>
 
      <DIV id='msg_delete' style='margin: 20px auto;'></DIV>
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="create_update_cancel();">취소</button>
    </FORM>
  </DIV>
 
  
<TABLE class='table table-striped '>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
  <thead>  
  <TR>
        <TH style='text-align: center ; font-size:13px;'>영화 시청 하기</TH>
        <TH style='text-align: center ; font-size:13px;'>영화 시청 시간</TH>
        <TH style='text-align: center ; font-size:13px;'>영화 종료 시간</TH>
        <TH style='text-align: center ; font-size:13px;'>영화 결제 수단</TH>
        <TH style='text-align: center ; font-size:13px;'>포인트 적립 금액</TH>
        <TH style='text-align: center ; font-size:13px;'>영화 번호</TH>
        <TH style='text-align: center ; font-size:13px;'>영화 제목</TH>
        <TH style='text-align: center ; font-size:13px;'>회원 번호</TH>
        <TH style='text-align: center ; font-size:13px;'>회원 이름</TH>
        <TH style='text-align: center ; font-size:13px;'>기타</TH>
  </TR>
  </thead>
  <c:forEach var="movie_movielookVO" items="${list_by_movie }">
  <TR>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.movielookno }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.starttime }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.endtime }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.pay }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.point }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.movieno }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.title }</TD>
        <TD style='text-align: center ; font-size:13px;'>${movie_movielookVO.memberno }</TD>
        <TD style='text-align: center ; font-size:13px;'>회원이름받기(구현중)</TD>
        
    <TD style='text-align: center ;'>
<%--<A href='../movie/read.do?movieno=${movieVO.movieno}'><IMG src='./images/play.jpeg' title='다시보기' style="width:15px;"></A> --%>
      <A href='${movie_movielookVO.mp4}'><IMG src='./images/play.jpeg' title='다시보기' style="width:15px;"></A> 
      <A href="javascript:update(${movie_movielookVO.movielookno })"><IMG src='./images/update.png' title='수정'></A>
      <A href="javascript:deleteOne(${movie_movielookVO.movielookno })"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
  </TR>
  </c:forEach> 

</TABLE>
 <DIV class='bottom_menu'> ${paging }</DIV>
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" /> 
</body>
 
</html> 