<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

<!--     <title>1조 - 영화 예약 시스템</title> -->
  <link href="./css/style.css" rel="Stylesheet" type="text/css">
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css" href="./css/vendor/bootstrap/css/bootstrap.css">

  </head>
 <jsp:include page="/menu/top.jsp" flush='false' />
  <body>

    <!-- Page Content -->
    <div class="container" style="margin: 50px auto;">
<!--       <br><br> -->
      <!-- Portfolio Item Heading -->
      <h1 class="my-4">1조
        <small>영화 예약시스템</small>
      </h1>

      <!-- Portfolio Item Row -->
      <div class="row">

        <div class="col-md-8">
          <img class="img-fluid" src="./menu/images/1.jpg" alt="">
        </div>

        <div class="col-md-4">
          <h3 class="my-3">말모이</h3>
          <p>
까막눈 판수, 우리말에 눈뜨다! vs 조선어학회 대표 정환, ‘우리’의 소중함에 눈뜨다!
1940년대 우리말이 점점 사라져가고 있는 경성.
 극장에서 해고된 후 아들 학비 때문에 가방을 훔치다 실패한 판수.
 하필 면접 보러 간 조선어학회 대표가 가방 주인 정환이다.
 사전 만드는데 전과자에다 까막눈이라니!
 그러나 판수를 반기는 회원들에 밀려 정환은 읽고 쓰기를 떼는 조건으로 그를 받아들인다.
 돈도 아닌 말을 대체 왜 모으나 싶었던 판수는 난생처음 글을 읽으며 우리말의 소중함에 눈뜨고,
 정환 또한 전국의 말을 모으는 ‘말모이’에 힘을 보태는 판수를 통해 ‘우리’의 소중함에 눈뜬다.
 얼마 남지 않은 시간, 바짝 조여오는 일제의 감시를 피해 ‘말모이’를 끝내야 하는데…
 
 우리말이 금지된 시대, 말과 마음이 모여 사전이 되다</p>
          <h3 class="my-3">영화 정보</h3>
          <ul>
            <li>장르 : 드라마</li>
            <li>개봉 : 2019.01.09</li>
            <li>감독 : 엄유나</li>
            <li>배우 : 유해진, 윤계상</li>
          </ul>
        </div>

      </div>
      <!-- /.row -->

      <!-- Related Projects Row -->
      <h3 class="my-4">추가 영화</h3>

      <div class="row">

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="./menu/images/2.jpg" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="./menu/images/3.jpg" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="./menu/images/4.jpg" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="./menu/images/5.jpg" alt="">
          </a>
        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Bootstrap core JavaScript -->

<%--      <jsp:include page="/menu/bottom.jsp" /> --%>

  </body>

</html>