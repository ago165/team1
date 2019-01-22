<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8">

<title>배우 등록하기</title> 
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

<DIV class='actor'>     

  <div class='menu_line'></div>
  <DIV class='actor' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">

      <div class="form-group" style=font-size:15px;'>

        <label for=actorname class="col-md-1 control-label">배우명</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='actorname' id='actorname' value='필수' required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group" style=font-size:15px;'>   
        <label for="actorbrd" class="col-md-1 control-label">출생</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='actorbrd' id='actorbrd'  required="required" style='width: 80%;' value='필수'></textarea>
        </div>
      </div>
      <div class="form-group" style=font-size:15px;'>   
        <label for="actor_sang" class="col-md-1 control-label">수상 경력</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='actor_sang' id='actor_sang' value='필수'>
        </div>
      </div>
      <div class="form-group" style="font-size:15px">   
        <label for="physical" class="col-md-1 control-label">신체</label>
        <div class="col-md-11" style="font-size:15px">
          <input type='text' class="form-control input-lg" name='physical' id='physical' value='필수'>
        </div>
      </div>
      <div class="form-group" style=font-size:15px;'>   
        <label for="actorwork" class="col-md-1 control-label">출연작</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='actorwork' id='actorwork' value='필수'>
        </div>
      </div>
      <div class="form-group" style=font-size:15px;'>   
        <label for="filesMF" class="col-md-1 control-label">업로드</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
      <div class="form-group" style="font-size:15px">   
        <label for="s_word" class="col-md-1 control-label">검색어</label>
        <div class="col-md-11" style="font-size:15px">
          <input type='text' class="form-control input-lg" name='s_word' id='s_word' value='필수'>
        </div>
      </div>
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit" style=font-size:15px;'>등록</button>
        <button type="button" onclick="location.href='./list.do?actorno=${actorVO.actorno}'" style=font-size:15px;'>취소[목록]</button>
      </DIV>
    </FORM>
  </DIV>


</DIV> <!-- actor END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" />
</body>

</html> 

