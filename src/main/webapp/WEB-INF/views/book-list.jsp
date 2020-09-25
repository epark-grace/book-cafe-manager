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
              <button data-feature="reset">
                <svg class="stroke-current text-gray-500 w-5 h-5" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M4 4V9H4.58152M19.9381 11C19.446 7.05369 16.0796 4 12 4C8.64262 4 5.76829 6.06817 4.58152 9M4.58152 9H9M20 20V15H19.4185M19.4185 15C18.2317 17.9318 15.3574 20 12 20C7.92038 20 4.55399 16.9463 4.06189 13M19.4185 15H15" stroke="#4A5568" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
              </button>
              <button data-feature="update">
                <svg class="stroke-current text-gray-500 w-5 h-5" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M11 5H6C4.89543 5 4 5.89543 4 7V18C4 19.1046 4.89543 20 6 20H17C18.1046 20 19 19.1046 19 18V13M17.5858 3.58579C18.3668 2.80474 19.6332 2.80474 20.4142 3.58579C21.1953 4.36683 21.1953 5.63316 20.4142 6.41421L11.8284 15H9L9 12.1716L17.5858 3.58579Z" stroke="#4A5568" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
              </button>
              <button data-feature="delete">
                <svg class="stroke-current text-gray-500 w-5 h-5" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M19 7L18.1327 19.1425C18.0579 20.1891 17.187 21 16.1378 21H7.86224C6.81296 21 5.94208 20.1891 5.86732 19.1425L5 7M10 11V17M14 11V17M15 7V4C15 3.44772 14.5523 3 14 3H10C9.44772 3 9 3.44772 9 4V7M4 7H20" stroke="#4A5568" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
              </button>
            </div>
          </td>
          <td>
            <span></span>
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