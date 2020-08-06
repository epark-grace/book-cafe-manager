<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/static/css/tailwind.css">
</head>
<body>
<div class="h-screen grid grid-cols-10 grid-rows-booklist">
  <nav class="border row-span-10 col-span-1">
    <ul>
      <li>도서목록</li>
      <li>신간도서</li>
      <li>추천도서</li>
      <li>분류</li>
      <li>설정</li>
      <li>모드 변경</li>
      <li>로그아웃</li>
    </ul>
  </nav>
  <div class="border row-span-1 col-span-9">
    <select id="criteria">
      <option value="title" selected>제목</option>
      <option value="author">작가</option>
    </select>
    <input type="text" placeholder="검색어를 입력하세요">
    <button>검색</button>
    <button>도서 등록</button>
    <button>책장번호 일괄변경</button>
  </div>
  <div class="border row-span-1 col-span-9">
    <table>
      <thead>
      <tr>
        <td>표지</td>
        <td>제목</td>
        <td>분류</td>
        <td>작가</td>
        <td>출판사</td>
        <td>권수</td>
        <td>책장번호</td>
        <td>칸번호</td>
        <td>완결여부</td>
        <td>연령제한</td>
        <td>수정/삭제</td>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="book" items="${bookList}">
        <tr>
          <td></td>
          <td>${book.title}</td>
          <td>${book.categoryName}</td>
          <td>${book.author}</td>
          <td>${book.publisher}</td>
          <td>${book.volume}</td>
          <td>${book.shelfName}</td>
          <td>${book.rowNumber}</td>
          <c:choose>
            <c:when test="${book.finished == false}">
              <td>미완결</td>
            </c:when>
            <c:otherwise>
              <td>완결</td>
            </c:otherwise>
          </c:choose>
          <c:choose>
            <c:when test="${book.forAdult == false}">
              <td>전체이용가</td>
            </c:when>
            <c:otherwise>
              <td>청소년이용불가</td>
            </c:otherwise>
          </c:choose>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="border row-span-1 col-span-9">
    <c:if test="${pagination.firstPage != 1}">
      <a href="/book-list/1${search}" title="첫 페이지로"><<</a>
      <a href="/book-list/${currentPage - 10}${search}" title="이전 10페이지로"><</a>
    </c:if>
    <c:forEach var="currentPage" begin="${pagination.firstPage}" end="${pagination.lastPage}">
      <c:choose>
        <c:when test="${currentPage == pagination.currentPage}">
          <a href="/book-list/${currentPage}${search}"
             class=>${currentPage}</a>
        </c:when>
        <c:otherwise>
          <a href="/book-list/${currentPage}${search}">${currentPage}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:if test="${pagination.pageCount != pagination.lastPage}">
      <a href="/book-list/1${search}" title="다음 10페이지로">></a>
      <a href="/book-list/${currentPage - 10}${search}" title="마지막 페이지로">>></a>
    </c:if>
  </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.min.js" integrity="sha512-zT3zHcFYbQwjHdKjCu6OMmETx8fJA9S7E6W7kBeFxultf75OPTYUJigEKX58qgyQMi1m1EgenfjMXlRZG8BXaw==" crossorigin="anonymous"></script>
</body>
</html>