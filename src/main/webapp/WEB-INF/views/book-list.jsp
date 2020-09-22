<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/dist/css/tailwind.css">
</head>
<body>
<div class="h-screen grid grid-cols-10 grid-rows-booklist">
  <nav class="border row-span-3 col-span-1">
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
        <td></td>
      </tr>
      </thead>
      <tbody id="book-list">
      <c:forEach var="book" items="${bookList}">
        <tr class="hover:text-blue-900" data-id="${book.id}">
          <td></td>
          <td contenteditable="true">${book.title}</td>
          <td>
            <select>
              <c:forEach var="category" items="${categories}">
                <c:choose>
                  <c:when test="${book.categoryId != category.id}">
                    <option value="${category.id}">${category.name}</option>
                  </c:when>
                  <c:when test="${book.categoryId == category.id}">
                    <option value="${category.id}" selected>${category.name}</option>
                  </c:when>
                </c:choose>
              </c:forEach>
            </select>
          <td contenteditable="true">${book.author}</td>
          <td contenteditable="true">${book.publisher}</td>
          <td contenteditable="true">${book.volume}</td>
          <td contenteditable="true">${book.shelfName}</td>
          <td contenteditable="true">${book.rowNumber}</td>
          <td>
            <select>
              <c:choose>
                <c:when test="${book.finished == false}">
                    <option value="false" selected>미완결</option>
                    <option value="true">완결</option>
                </c:when>
                <c:otherwise>
                  <option value="false">미완결</option>
                  <option value="true" selected>완결</option>
                </c:otherwise>
              </c:choose>
            </select>
          </td>
          <td>
            <select>
            <c:choose>
              <c:when test="${book.forAdult == false}">
                <option value="false" selected>전체이용가</option>
                <option value="true">청소년이용불가</option>
              </c:when>
              <c:otherwise>
                <option value="false">전체이용가</option>
                <option value="true" selected>청소년이용불가</option>
              </c:otherwise>
            </c:choose>
            </select>
          </td>
          <td>
            <div class="invisible">
              <button data-feature="update">
                <svg class="fill-current text-gray-500 w-5 h-5" viewBox="0 0 20 20">
                  <path d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z"></path>
                </svg>
              </button>
              <button data-feature="delete">
                <svg class="fill-current text-gray-500 w-5 h-5" viewBox="0 0 20 20">
                  <path fill-rule="evenodd"
                        d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                        clip-rule="evenodd"></path>
                </svg>
              </button>
            </div>
          </td>
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
<script src="/dist/js/vendors.bundle.js"></script>
<script src="/dist/js/book-list.bundle.js"></script>
</body>
</html>