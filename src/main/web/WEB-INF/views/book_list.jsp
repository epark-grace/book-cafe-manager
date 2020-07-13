<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/static/css/book_list.css">
</head>
<body>
<select id="criteria">
  <option value="title" selected>제목</option>
  <option value="author">작가</option>
</select>
<input type="text" placeholder="검색어를 입력하세요">
<button>검색</button>
<button>도서 등록</button>
<button>책장번호 일괄변경</button>
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
        <td>${book.finished}</td>
        <td>${book.forAdult}</td>
        <td></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<c:if test="${pagination.firstPage != 1}">
  <a href="/book-list/1" title="첫 페이지로"><<</a>
  <a href="/book-list/${currentPage - 10}" title="이전 10페이지로"><</a>
</c:if>
<c:forEach var="currentPage" begin="${pagination.firstPage}" end="${pagination.lastPage}">
  <c:choose>
    <c:when test="${currentPage == pagination.currentPage}">
      <a href="/book-list/${currentPage}" class="current">${currentPage}</a>
    </c:when>
    <c:otherwise>
      <a href="/book-list/${currentPage}">${currentPage}</a>
    </c:otherwise>
  </c:choose>
</c:forEach>
<c:if test="${pagination.pageCount != pagination.lastPage}">
  <a href="/book-list/1" title="다음 10페이지로">></a>
  <a href="/book-list/${currentPage - 10}" title="마지막 페이지로">>></a>
</c:if>

</body>
</html>