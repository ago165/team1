<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>설문 항목 삭제</title> 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- Bootstrap core CSS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> 


<script type="text/javascript">

</script>

</head> 

<body>
<DIV class='container' style='margin : 270px auto; '>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   
  
  <FORM name='frm' method='POST' action='./delete.do'>

       <input type='hidden' name='surveyno' value='${surveyVO.surveyno}'>
     <fieldset class='fieldset_basic'>
    
      <div class="form-group">   
        <div class="col-md-12" style='text-align: center; margin: 30px;'>
          삭제 되는 항목 : ${surveyVO.surveytitle }<br><br> 
          삭제하시겠습니까? <br><br>삭제하시면 복구 할 수 없습니다.<br><br>
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div> 
      </fieldset>  
  </FORM>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

  