<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<select id="criteria">
  <option value="title" selected>제목</option>
  <option value="author">작가</option>
</select>
<input type="text" placeholder="검색어를 입력하세요">
<button>검색</button>
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
  </tbody>
</table>
</body>
</html>