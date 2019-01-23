<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<!-- Bootstrap core CSS -->

 <link href="../css/style.css" rel="Stylesheet" type="text/css">

<style>
  .menu_name{
    font-size: 30px;
    font-weight: bold;
    border-bottom: 2px solid;
    margin-top: 100px;
  }
  
  .menu_info {
    float: right;
    margin-right: 80px;
  }
  
  .price {
    width:585px;
    padding: 30px;
    font-size: 40px;
    border-bottom: 1px solid #d6d6d6;
    float: right;
  }
  .info{
    border-bottom: 1px solid #d6d6d6;
  }
  .menu_picture{
    float: left;
  }
  
  .pay{
    font-size: 20px;
    font-weight: bolder;
  }
  
  .total{
    margin-left: 350px;
    font-size: 30px;
    font-weight: bold;
  }
</style>
</head>

<body onload="init();">
<script language="JavaScript">

var sell_price;
var amount;

function init () {
  sell_price = document.form.sell_price.value;
  amount = document.form.amount.value;
  document.form.sum.value = sell_price;
  change();
}

function add () {
  hm = document.form.amount;
  sum = document.form.sum;
  hm.value ++ ;

  sum.value = parseInt(hm.value) * sell_price;
}

function del () {
  hm = document.form.amount;
  sum = document.form.sum;
    if (hm.value > 1) {
      hm.value -- ;
      sum.value = parseInt(hm.value) * sell_price;
    }
}

function change () {
  hm = document.form.amount;
  sum = document.form.sum;

    if (hm.value < 0) {
      hm.value = 0;
    }
  sum.value = parseInt(hm.value) * sell_price;
}  

function buy_login() {
  /* if(confirm("로그인이 필요한 서비스 입니다.\n로그인 페이지로 이동하시겠습니까?")){
    location.href="index.jsp";
  }else{
    
  } */
  var answer;
  
  answer = confirm("로그인이 필요한 서비스 입니다.\n로그인 페이지로 이동하시겠습니까?");
  
  if(answer) {
    location.replace('list.jsp'); 
  }else{
    
  }
}

</script>
 
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
<aside style='float: left; margin-top: 70px;'>
    <a href='../index.jsp'><img src='../images/btn_home.png' ></a>
    <span style='font-size: 1.4em'> > </span> 
    <a href='./list.do' style='color: #000;'>스낵존</a>
    <span style='font-size: 1.4em'> > </span> 
    <span style='font-weight: bold; text-decoration: underline;'>${snackzoneVO.name}</span>
</aside><br>
     
  <div class='menu_name'>${snackzoneVO.name }
  <a href= "./update.do?snackno=${snackzoneVO.snackno }" style='float: right; '>수정</a>
  <a href= "./delete.do?snackno=${snackzoneVO.snackno }" style='float: right; '>삭제/</a>
  </div>
    <ul style='list-style:none'>
      <li>
    
        <div class='menu_picture' style='width: 30%;'>
          <img id ='thumb' src='./storage/${snackzoneVO.thumb}' 
            style= 'width: 100%; height: 460px;'>
        </div>
      </li> 
    </ul>
      <div class='menu_info'  style='width: 50%;'>
        <p class='price'>${snackzoneVO.price }원</p>
        <dl class='info'>
          <dt>상품 구성</dt>
            <dd>${snackzoneVO.composition}</dd>
          <dt>유효 기간</dt>
            <dd>${snackzoneVO.term }</dd>
          <dt>원산지</dt>
            <dd>${snackzoneVO.orgin }</dd>
        </dl>
        <div class='pay'>${snackzoneVO.name }</div>
        <form name="form" method="get" >
          <input type=hidden name="sell_price" value="${snackzoneVO.price}">
          <input type="button" value=" - " onclick="del();">
          <input type="text" name="amount" value="1" size="3" onchange="change();" style='text-align:center;'>
          <input type="button" value=" + " onclick="add();">
          

          <input type="text" name="sum" size="3" readonly 
                    style='border:none; font-size: 30px; margin-left: 180px;'>원
            
        </form>
        <br><br>
        
        <a href=''><img onclick="buy_login()" src='./images/buy_button.PNG' style='width: 30%; margin-left: 380px;'></a>
      </div>


</DIV> <!-- content END -->
  <%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->  
  
</body>

</html>