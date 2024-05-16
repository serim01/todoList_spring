# 나만의 일정 관리 앱 서버 만들기
## 0.목차
[1.필수요구사항](https://github.com/serim01/todoList_spring?tab=readme-ov-file#1%ED%95%84%EC%88%98%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD)

[2.Use Case Diagram](https://github.com/serim01/todoList_spring?tab=readme-ov-file#2use-case-diagram)

[3.API명세서](https://github.com/serim01/todoList_spring?tab=readme-ov-file#3api-%EB%AA%85%EC%84%B8%EC%84%9C)

[4.ERD](https://github.com/serim01/todoList_spring?tab=readme-ov-file#4erd)

## 1.필수요구사항
<ul>
<li>일정 작성 기능<ul>
<li><code>할일 제목</code>,<code>할일 내용</code>, <code>담당자</code>, <code>비밀번호</code>, <code>작성일</code>을 저장할 수 있습니다.</li>
<li>저장된 게시글의 정보를 반환 받아 확인할 수 있습니다.<ul>
<li>반환 받은 게시글의 정보에 <code>비밀번호</code>는 제외 되어있습니다.</li>
</ul></li>
</ul></li>
<li>선택한 일정 조회 기능<ul>
<li>선택한 일정의 정보를 조회할 수 있습니다.<ul>
<li>반환 받은 일정 정보에 <code>비밀번호</code>는 제외 되어있습니다.</li>
</ul></li>
</ul></li>
<li>일정 목록 조회 기능<ul>
<li>등록된 일정 전체를 조회할 수 있습니다.<ul>
<li>반환 받은 일정 정보에 <code>비밀번호</code>는 제외 되어있습니다.</li>
</ul></li>
<li>조회된 일정 목록은 <code>작성일</code> 기준 내림차순으로 정렬 되어있습니다.</li>
</ul></li>
<li>선택한 일정 수정 기능<ul>
<li>선택한 일정의 <code>할일 제목</code>,<code>할일 내용</code>, <code>담당자</code>을 수정할 수 있습니다.<ul>
<li>서버에 일정 수정을 요청할 때 <code>비밀번호</code>를 함께 전달합니다.</li>
<li>선택한 일정의 <code>비밀번호</code>와 요청할 때 함께 보낸 <code>비밀번호</code>가 일치할 경우에만 수정이 가능합니다.</li>
</ul></li>
<li>수정된 일정의 정보를 반환 받아 확인할 수 있습니다.<ul>
<li>반환 받은 일정의 정보에 <code>비밀번호</code>는 제외 되어있습니다.</li>
</ul></li>
</ul></li>
<li>선택한 일정 삭제 기능<ul>
<li>선택한 일정을 삭제할 수 있습니다.<ul>
<li>서버에 일정 삭제를 요청할 때 <code>비밀번호</code>를 함께 전달합니다.</li>
<li>선택한 일정의 <code>비밀번호</code>와 요청할 때 함께 보낸 <code>비밀번호</code>가 일치할 경우에만 삭제가 가능합니다.</li>
</ul></li>
</ul></li>
</ul>

## 2.Use Case Diagram
![UseCaseDiagram drawio](https://github.com/serim01/Spring_Beginner_Project/assets/50200959/dcd6d85a-1e7a-4fde-81e4-ee64dff968d9)

## 3.API 명세서
[Postman API명세서](https://documenter.getpostman.com/view/34879249/2sA3JQ4KPn)
<table>
<thead>
<tr>
<th>기능</th>
<th>Method</th>
<th>URL</th>
<th>request body</th>
<th>response body</th>
</tr>
</thead>
<tbody>
<tr>
<td>일정전체보기</td>
<td>GET</td>
<td>/todos</td>
<td></td>
<td>[  <br>{  <br>"id": 3,  <br>"title": "테스트3",  <br>"contents": "테스트 todo",  <br>"userName": "serim",  <br>"date": "2024-05-14"  <br>},  <br>{  <br>"id": 5,  <br>"title": "테스트5",  <br>"contents": "테스트 todo",  <br>"userName": "serim",  <br>"date": "2024-05-14"  <br>}  <br>]</td>
</tr>
<tr>
<td>선택일정보기</td>
<td>GET</td>
<td>/todos/{id}</td>
<td></td>
<td>[  <br>{  <br>"id": 3,  <br>"title": "테스트3",  <br>"contents": "테스트 todo",  <br>"userName": "serim",  <br>"date": "2024-05-14"  <br>}  <br>]</td>
</tr>
<tr>
<td>일정생성</td>
<td>POST</td>
<td>/todos</td>
<td>{  <br>"title": "테스트4",  <br>"contents": "테스트 todo",  <br>"userName": "serim",  <br>"password": "123456",  <br>"date": "2024-05-14"  <br>}</td>
<td>{  <br>"id": 5,  <br>"title": "테스트5",  <br>"contents": "테스트 todo",  <br>"userName": "serim",  <br>"date": "2024-05-14"  <br>}</td>
</tr>
<tr>
<td>일정수정</td>
<td>PUT</td>
<td>/todos/{id}/{password}</td>
<td>{  <br>"title": "테스트2 update",  <br>"contents": "테스트 todo update",  <br>"userName": "sera"  <br>}</td>
<td></td>
</tr>
<tr>
<td>일정삭제</td>
<td>DELETE</td>
<td>/todos/{id}</td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

## 4.ERD
![image](https://github.com/serim01/Spring_Beginner_Project/assets/50200959/c8c6cf14-753e-46df-9e51-bd623b242389)
