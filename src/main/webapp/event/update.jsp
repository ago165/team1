<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>블로그 수정</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
	window.onload = function() {
		CKEDITOR.replace('content'); // <TEXTAREA>태그 id 값
	};
</script>
<Style>
.li_none {
  list-style: none;
}

.li_class {
  list-style: none;
}
.content {
    margin-top : 80px;
  }
</Style>
</head>

<body>
  <DIV class='container' style='width: 90%;'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content'>

      <ASIDE style='float: left;'>
        <A href='/event/list.do'>게시판 목록</A> <span
          style='font-size: 1.2em;'>></span> <A href='./list.do'>${eventVO.title }</A>
      </ASIDE>
      <ASIDE style='float: right;'>
        <A href="javascript:location.reload();">새로고침</A> <span
          class='menu_divide'>│</span> <A
          href='./create.do?eventno=${eventVO.eventno }'>등록</A>
      </ASIDE>

      <div class='menu_line'></div>
      <FORM name='frm' method='POST' action='./update.do'
        enctype="multipart/form-data" class="form-horizontal">
        <input type='hidden' name='eventno' id='eventno' value='${eventVO.eventno }'>
        <fieldset class='fieldset_no_line' style='width: 100%;'>
          <ul>
            <li class='li_none'><label for='title'>글 제목: </label> <input type='text' name='title' id='title'
              value='${eventVO.title }'></li>
            <li class='li_none'><textarea class="form-control input-md"
                name='content' id='content' rows='10'>${eventVO.content}</textarea>
            </li>
            <li class="li_none"><label for='gift'>상품: </label> 
            <input type='text' name='gift' id='gift' size='20' value='상품'></li>
            
            <li class='li_none'><label for='file1MF'>참고 파일: </label>
              <IMG id='thumb' src='./storage/${eventVO.thumb }'> <input type="file" name='file1MF' 
                  id='file1MF' size='10' multiple="multiple">(10 MB 이하만 전송 가능) </li>
            <br>
            <li class='li_none'><label for='seqno'>출력 순서: </label>
              <input type="number" min="1" max="20" name='seqno' id='seqno' value='1'> </li>
            <br>
            <li class='li_none'><label for='startdate'>시작 날짜: </label>
              <input type="text" size='20' name='startdate' id='startdate' value='2019-01-01'> </li>
            <li class='li_none'><label for='enddate'>종료 날짜: </label>
              <input type="text" size='20' name='enddate' id='enddate' value='2019-02-01'> </li>
          </ul>
          <DIV style='text-align: right;'>
            <button type="submit">변경된 내용 저장</button>
            <button type="button"
              onclick="location.href='./list.do?eventno=${eventVO.eventno}'">취소[목록]</button>
          </DIV>
        </fieldset>
      </FORM>
    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>


