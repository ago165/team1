<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>snackzone_create</title>

<!-- Bootstrap core CSS -->

 <link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />

  <div class='content'>
    <div class='title_line' style='width: 50%; font-size: 30px;' >스낵존 등록</div>
    
    <form name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
      <fieldset class='fieldset_basic'>
        <ul style='list-style:none;'>
          <li class='li_none'>
            <label class='label' for='kind'>스낵종류 번호</label>
            <input type='number' name='kind' id='kind'  value='1' requried="required" min= "1" max="3" step="1" style='width: 3%;'>
          </li>
          <li class='li_none'>
            <label class='label' for='name'>스낵 이름</label>
            <input type='text' name='name' id='name'  value='콤보 세트' requried="required" >
          </li>
          <li class='li_none'>
            <label class='label' for='price'>가격</label>
            <input type='number' name='price' id='price'  value='4000' requried="required" min="0" step="500" style='width: 5%'>원
          </li>
          <li class='li_none'>
            <label class='label' for='composition'>구성</label>
            <input type='text' name='composition' id='composition'  value='팝콘(L)1 + 음료(M)2' requried="required"  style='width:30%'>
          </li>
          <li class='li_none'>
            <label class='label' for='term'>유효 기간</label>
            <input type='text' name='term' id='term'  value='구매 후 6개월 이내' requried="required" style='width:20%' >
          </li>
          <li class='li_none'>
            <label class='label' for='orgin'>원산지</label>
            <input type='text' name='orgin' id='orgin'  value='팝콘: 중국산' requried="required" >
          </li>
          <li class='li_none'>
            <label class='label' for='contents'>내용</label>
            <textarea  rows="10" cols="100" name='contents' id='contents'  value='구매 유의 사항' ></textarea>
          </li>
          <li class='li_none'>
            <label class='label' for='file1MF'>사진</label>
            <input type='file' name='file1MF' id='file1MF'  value=''>
          </li>
          <li class='li_none'>
            <label class='label' for='seqno'>출력 순서</label>
            <input type='number' name='seqno' id='seqno' value='1' required="required" placeholder="${seqno }" min="1" max="10" step="1" style='width: 3%;'>
          </li>
          <li class='li_button'>
            <button type="submit">등록</button>
            <button type="button" onclick="location.href='./list.do'">목록</button>
          </li>
        </ul>
      </fieldset>
    </form>
  </div>
  <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV> 
</body>
</html>