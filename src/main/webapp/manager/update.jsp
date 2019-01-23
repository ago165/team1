<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>관리자 정보 수정</title> 

      <link href="../css/style.css" rel="Stylesheet" type="text/css">
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css" href="../css/vendor/bootstrap/css/bootstrap.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
 -->
</head> 
<jsp:include page="/menu/top.jsp" flush='false' />
<body>
<DIV class='container'>

<DIV class='content'>  
<DIV id='main_panel'></DIV>

  <!-- Modal -->
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id='modal_title'></h4>
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> <!-- Modal END -->
     
<br>
<br>
<br>
  <ASIDE style='float:right; font-size:20px;'>    
    <A href='./create.do' style='color:#333333;'>관리자 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do' style='color:#333333;'>관리자 목록</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();" style='color:#333333;'>새로고침</A>
  </ASIDE>

  <div class='menu_line'></div><br>
  
   <DIV class='title_line'>관리자 정보 수정</DIV><br>
  
  <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='managerno' id='managerno' value='${managerVO.managerno }'>
      <%-- <input type='hidden' name='nowPage' id='nowPage' value='${param.nowPage }'> --%>
      
      <div class="form-group" style='font-size:15px;'> 
        <label for="id" class="col-md-1 control-label">
        아이디</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="id" id="id" rows='1'>${managerVO.id }></textarea>
        </div>
      </div>   
      
      <div class="form-group" style='font-size:15px;'>   
        <label for="mname" class="col-md-1 control-label">이름</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="mname" id="mname"  rows='1'>${managerVO.mname }</textarea>
        </div>
      </div>
      
        <div id='filesPanel' class="form-group">
        <label class="col-md-1 control-label"></label>
        <div class="col-md-11">
          <!-- 파일명을 소문자로 변경 -->
          <c:set var='files' value="${fn:toLowerCase(managerVO.files)}" />
          <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
          <c:choose>
            <c:when test="${fn:endsWith(files, '.jpg')}">
              <IMG id='files' src='./storage/${managerVO.files}' style='width: 20%;'>
            </c:when>
            <c:when test="${fn:endsWith(files, '.png')}">
              <IMG id='files' src='./storage/${managerVO.files}' style='width: 20%;'>
            </c:when>
            <c:when test="${managerVO.files.length() > 0}">
              ${managerVO.files }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group" style='font-size:15px;'>   
        <label for="filesMF" class="col-md-1 control-label">사진</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-md" name='filesMF' id='filesMF' size='40' >
          <div class="form-group" style='font-size:15px;'>  
          <label for="s_word" class="col-md-1 control-label">검색어</label>
         <div class="col-md-11" style="font-size:15px">
          <input type='text' class="form-control input-lg" name='s_word' id='s_word' value='필수'>
          
      </div>
      </div>
      
      
       <div class="form-group" style='font-size:15px;'>   
        <label for="tel" class="col-md-1 control-label">전화 번호</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="tel"id="tel" rows='10'>${managerVO.tel }</textarea>
        </div>
      </div>
      
      
      
      <div class="form-group" style='font-size:15px;'>   
        <label for="zipcode" class="col-md-1 control-label">우편 번호</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-md" name='zipcode' id='zipcode' 
                     value='12345' required="required" style='width: 30%;' placeholder="우편번호">
         <input type="button" onclick="DaumPostcode()" value="우편번호 찾기" class="btn btn-info btn-md" style='font-size:15px;'>
        </div>
      </div>
      
      <div class="form-group" style='font-size:15px;'>   
        <label for="address1" class="col-md-1 control-label">주소</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="address1" id="address1"  rows='10'>${managerVO.address1}</textarea>
        </div>
      </div>
      

      <div class="form-group" style='font-size:15px;'>   
        <label for="address2" class="col-md-1 control-label">상세 주소</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name="address2" id="address2"  rows='10'>${managerVO.address2}</textarea>
        </div>
      </div>  
      
          <div class="form-group">
      <div class="col-md-12">

<!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address1').value = fullAddr;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#address2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- ----- DAUM 우편번호 API 종료----- -->
 
      </div>
    </div>

      <DIV style='text-align: right;'>
        <button type="submit">변경</button>
        <button type="button" onclick="location.href='./list.do'">취소</button>
      </DIV>
  </FORM>




</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
<BR>
<BR>
<BR>
</DIV> <!-- container END -->
</body>

</html> 
