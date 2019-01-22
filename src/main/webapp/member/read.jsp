<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<script type="text/javascript">
var loading = 0;

$(document).ready(function(){ // window.onload = function() { ... }
  imgResize()
});

//<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
function imgResize() {
  loading = loading + 1;
  var files = $('#files');
  var width = files.width();
  // alert(width);
  
  if (files != null) {
    // 이미지 width가 화면의 70%보다 크다면
    if (width > screen.width-(screen.width * 0.3)) {  
      // file1.width(600); // 이미지 축소
      $('#file1Panel').attr('width', '100%');
      file1.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
    } else {
      // 작은 이미지는 그대로 출력
    }
  }
  
  var timer = setInterval(imgResize, 100); // 0.1 초
  
  if (loading == 2) {
    clearInterval(timer) // 타이머 종료, 함수 실행 종료
  }

}


  $(function() {
   // $.cookie('checkID', 'FALSE'); // Cookie 생성
    $('#popup').hide();
  });
  
  function checkId(){
    var frm = $('#frm');
    var params = 'id='+$('#id', frm).val(); // #: id
    // alert('params: ' + params);
    
    $.ajax({
      url: "./checkId.do",
      type: "GET",
      cache: false,
      dataType: "json", // or html
      data: params,
      success: function(data){
        var msg = "";

        if (data.cnt > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = " 이미 사용중인 ID 입니다.";
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = " 사용 가능한 ID 입니다.";
          
          $.cookie('checkID', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){  
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";
        
        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
            // id_span.html(msg);
        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });
 
  }
  
  function send() {
    var check = $.cookie('checkID'); // 쿠키값
    
    if (check != 'TRUE') {
      var msg = "ID 중복확인이 되지 않았습니다.<br>";
      msg += "ID [중복확인] 버튼을 클릭하세요.<br>";

      $('#modal_title').html('ID 체크 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   
      
      return false; // submit 중지
    }
    
    return true; // submit 진행
  }  
</script>


</head> 


<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
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
    
  
  <!-- <ASIDE style='float: left;'>
      <A href='./member/list.do'>회원 목록</A>  
  </ASIDE> -->
  <br><br><br>
  <ASIDE style='float:right; font-size:20px;'>    
    <A href='./create.do' style='color:#333333;'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do' style='color:#333333;'>회원 목록</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();" style='color:#333333;'>새로고침</A>
  </ASIDE>

  <div class='menu_line'></div>
  

  <DIV class='title_line'>회원 정보 조회</DIV><br>

  <FORM name='frm' id='frm' method='get' action='./update.do' >
     <input type = "hidden" name="memberno" value="${memberVO.memberno}">
           <fieldset class="fieldset" style='margin:20px auto; float:right;'>
        <ul>
          <li class="li_none" style='font-size:18px;'>
            
            <DIV><A> 회원 번호 : </A>${memberVO.memberno }</DIV>
            <DIV><A> 아이디 : </A>${memberVO.id }</DIV>
            <DIV><A> 이름 : </A>${memberVO.mname }</DIV>

            <span>
              <c:if test="${memberVO.sizes > 0}">
                사진 : <A href='${pageContext.request.contextPath}/download?dir=/member/storage&filename=${memberVO.files}'>${memberVO.files}</A>
              </c:if>
            </span>
          </li>
          <li class="li_none">    
            <div id='file1Panel'>
              <!-- EL 값을 JSTL 변수에 할당 -->
              <c:set var='files' value="${fn:toLowerCase(memberVO.files)}" />
              
              <c:choose>
                <c:when test="${fn:endsWith(files, '.jpg')}">
                  <IMG id='files' src='./storage/${memberVO.files}'  style='width:30%;'>
                </c:when>
                <c:when test="${fn:endsWith(files, '.png')}">
                  <IMG id='files'  src='./storage/${memberVO.files}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          <li class="li_none" style='font-size:18px;'>
            
            <DIV><A> 전화번호 :  </A>${memberVO.tel }</DIV>
            <DIV><A> 우편번호 : </A>${memberVO.zipcode }</DIV>
            <DIV><A> 주소 :  </A>${memberVO.address1 }</DIV>
            <DIV><A> 상세 주소 :  </A>${memberVO.address2 }</DIV>
             <button type="button" onclick="location.href='./update.do?memberno='+${memberVO.memberno}" class="btn btn-primary btn-md" style='font-size:15px; float:right;'>수정하러 가기!</button>       
          </li>
        </ul>
      </fieldset><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      
 
    
  </FORM>

            
           
 <%-- <input type='hidden' name='memberno' id='memberno' value='${memberVO.memberno }'>
 --%>
      
 

</DIV><br><br> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 


