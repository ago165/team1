<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">영화예약시스템</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp" style='font-size:15px;'>Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" style='font-size:15px;'>영화(장호재)</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/actor/list.do" style='font-size:15px;'>배우(강영모)</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/movielook/list.do" style='font-size:15px;'>상영(조원기)</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#" style='font-size:15px;'>스넥존(남동훈)</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#" style='font-size:15px;'>이벤트(안혜인)</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#" style='font-size:15px;'>설문조사(박준영)</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#" style='font-size:15px;'>회원(손영준)</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>