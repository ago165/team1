<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Snackzone</title>

<!-- Bootstrap core CSS -->

 <link href="../css/style.css" rel="Stylesheet" type="text/css">
<style>
h1 {
  padding-bottom: 5px;
  border-bottom: 3px solid
}
</style>

</head>
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     
  <h1>SNACKZONE</h1>

  <form name='frm' id='frm' method="get" action="./list.do">
  <aside style='float: left;'>
    <a href='../index.jsp'><img src='../images/btn_home.png' ></a>
    <span style='font-size: 1.4em'> > </span> <span
      style='font-weight: bold; text-decoration: underline;'>스낵존</span>
  </aside>

  <aside style='float: right;'>
    <a href='./create.do?snackzone=${param.snackno }'>등록</a>
  </aside>
  <br>
  <br>
  <div class='middle_image'>
    <img src='../images/snackbar.jpg'
      style='width: 980px; height: 400px; margin-left: auto; margin-right: auto; display: block;'>
  </div>
  <br>
  
   <div class='search_snack'>
     <c:choose>
       <c:when test="${param.word != '' }">
         <input type="text" name="word" id="word" value='${param.word }' style='width:20%;margin-left: 750px;'>
       </c:when>
       <c:otherwise>
         <input type='text' name='word' id='word' value='' style='width:20%;margin-left: 750px;'>
       </c:otherwise>
     </c:choose>
     <button type='submit'>검색</button>
     <button type='button'
                 onclick="location.href='./list.do'">전체보기</button>
   </div>
  </form>
   
  <br>
  <br>
  <DIV class='menu_line' style='clear: both;'></DIV>
    
    <table class="menu_table" style='width: 100%;'>
    
      <thead></thead>
      
      <tbody>
      <tr>
       <c:forEach var="snackzoneVO" items="${list }">
             
               <td style='vertical-align: middle;'>
               <c:choose>
                <c:when test="${snackzoneVO.thumb !='' }">
                  <a href="./read.do?snackno=${snackzoneVO.snackno }">
                  <img id='thumb' src='./storage/${snackzoneVO.thumb }'></a>
                    <div style='font-size: 20px; font-weight: bold;'>${snackzoneVO.name }</div>
                    
                    <div style='font-size: 15px; color: #666;'>${snackzoneVO.composition }</div>
                    
                    <div>${snackzoneVO.price }원</div>

                </c:when>
                <c:otherwise>
                  <!--  파일이 존재하지 않는 경우 -->
                  <img src='./images/none1.jpg'
                    style='width: 120px; height: 80px;'>
                </c:otherwise>
               </c:choose>
                </td>
              
           </c:forEach>
         </tr>
         
        </tbody>  
     </table>
     <div class='bottom_menu'>${paging }</div>
     <br><br>     
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
</html>