<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
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

.li_center {
  text-align: center;
  list-style: none; /* 블렛 기호 생략 */
  margin: 20px auto;
}

.content {
    margin-top : 80px;
  }
</Style>
</head>

<body>
  <DIV class='container'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content' style='width: 100%;'> 
      <FORM name='frm' method='POST' action='./create.do' 
          enctype="multipart/form-data"  class="form-horizontal">
        <fieldset class='fieldset_no_line' style='width: 100%;'>
          <ul>
            <li class='li_none'><label for='title'>글 제목: </label> 
            <input type='text' name='title' id='title' size='20' value='이벤트'></li>
            <br>
            <li class="li_none">
            <textarea name='content' id='content' rows='6' style='width: 100%;'>이벤트 내용</textarea> </li>
            <br>
            <li class="li_none"><label for='gift'>상품: </label> 
            <input type='text' name='gift' id='gift' size='20' value='상품'></li>
            <br>            
            <!-- <li class='li_none'><label for='thumb'>사진: </label>
              <input type="file" name='thumb' id='thumb' size='20' ></li>
            <br> -->
            <li class='li_none'><label for='word'>검색어 : </label>
              <input type="text" min="1" max="20" name='word' id='' value='이벤트, 크리스마스 이벤트'>
            </li>
            <li class='li_none'><label for='file1MF'>참고 파일: </label>
              <input type="file" name='file1MF' id='file1MF' size='20' multiple="multiple"> (10 MB 이하만 전송 가능) </li>
            <br>
            <li class='li_none'><label for='seqno'>출력 순서: </label>
              <input type="number" min="1" max="20" name='seqno' id='seqno' value='1'> </li>
            <br>
            <li class='li_none'><label for='startdate'>시작 날짜: </label>
              <input type="text" size='20' name='startdate' id='startdate' value='2019-01-01'> </li>
            <li class='li_none'><label for='enddate'>종료 날짜: </label>
              <input type="text" size='20' name='enddate' id='enddate' value='2019-02-01'> </li>
            <br>
            <li class="li_class">
              <button type="submit">등록</button>
            </li>
          </ul>
        </fieldset>
      </FORM>
    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>

