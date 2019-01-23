<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>내용 수정</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
  <DIV class='container' style='width: 100%;'>
  <jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class='content'>
       
    <aside style='float: right; margin-top: 100px;'>
      <a href='./list.do'>스낵존 목록</a>
      <span style='font-size: 1.2em;'>/</span>
      <a href='./create.do'>등록</a>
    </aside>
    
     <div class='menu_line'></div>
    <form name='frm' method='POST' action='./update.do'
              enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='snackno' id='snackno' value='${snackzoneVO.snackno }'>
      
      <div class="form-group">
        <label for="snack_name" class="snack_name" style='margin-top: 130px; margin-right: 10px;'>상품명</label>
        <span class="snack_name_info">
          <input type='text' class='snack_name_text' name='name' id= 'name' value='${snackzoneVO.name }'
                    required="required" style='width:20%;'>
        </span>
        
       <div class="form-group"></div>
         <label for="snack_price" class="snack_price" style='margin-top: 20px; margin-right:20px;'>가격</label>
         <span class="snack_price_info">
           <input type='number' class='snack_price_text' name='price' id= 'price' value='${snackzoneVO.price }'
                    min= "0" step="500" required="required" style='width:10%;'>
         </span>
       
       <div class="form-group"></div>
         <label for="snack_composition" class="snack_composition" style='margin-top: 20px; margin-right:20px;'>구성</label>
         <span class="snack_composition_info">
           <input type='text' class='snack_composition_text' name='composition' id= 'composition' value='${snackzoneVO.composition }'
                     required="required" style='width:20%;'>
         </span>
        
        <div class="form-group"></div>
         <label for="snack_term" class="snack_term" style='margin-top: 20px; margin-right:20px;'>유효 기간</label>
         <span class="snack_term_info">
           <input type='text' class='snack_term_text' name='term' id= 'term' value='${snackzoneVO.term }'
                     required="required" style='width:20%;'>
         </span>
         
         <div class="form-group"></div>
         <label for="snack_orgin" class="snack_orgin" style='margin-top: 20px; margin-right:20px;'>원산지</label>
         <span class="snack_term_info">
           <input type='text' class='snack_orgin_text' name='orgin' id= 'orgin' value='${snackzoneVO.orgin }'
                     required="required" style='width:20%;'>
         </span>
          
         <div class="form-group"></div>
         <label for="snack_contents" class="snack_contents" style='margin-bottom: 100px; margin-right:20px;'>내용</label>
         <span class="snack_term_info">
           <textarea  rows="10" cols="100" name='contents' id='contents'  value='${snackzoneVO.contents }' ></textarea>
         </span>
      
      
        <div class="form-group">   
        <label for="snack_thumb" class="snack_thumb">파일</label>
        <div class="snack_thumb_info">
          <img id= 'thumb' src='./storage/${snackzoneVO.thumb }'><input type="file" class="snack_thumb_text" name='file1MF' id='file1MF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>    
         
         <div class = 'up_button'>
           <button type="submit">수정</button>
           <button type="button" onclick="location.href='./read.do?snackno=${snackzoneVO.snackno}'">취소</button>
         </div>
          
      </div>
      
    </form>
    
  </DIV> <!-- content END -->
  <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV> <!-- container END -->
  
</body>
</html>