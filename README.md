# 나만의 일정 관리 앱 서버 만들기
## 0.목차
[1.요구사항](https://github.com/serim01/todoList_spring?tab=readme-ov-file#1%ED%95%84%EC%88%98%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD)

[2.Use Case Diagram](https://github.com/serim01/todoList_spring?tab=readme-ov-file#2use-case-diagram)

[3.API명세서](https://github.com/serim01/todoList_spring?tab=readme-ov-file#3api-%EB%AA%85%EC%84%B8%EC%84%9C)

[4.ERD](https://github.com/serim01/todoList_spring?tab=readme-ov-file#4erd)

## 1-1.필수요구사항
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

## 1-2.추가 요구사항
<ul>
  <li>예외 발생 처리 기능</li><ul>
  <li>예외 상황에 대한 처리를 위해 <code><a href="https://developer.mozilla.org/ko/docs/Web/HTTP/Status">HTTP 상태 코드</a></code>와 <code>에러 메시지</code>를 포함한 정보를 사용하여 예외를 관리할 수 있습니다.</li><ul>
    <li>필요에 따라 사용자 정의 예외 클래스를 생성하여 예외 처리를 수행할 수 있습니다.</li>
    <li><code><b>@ExceptionHandler</b></code>를 활용하여 공통 예외 처리를 구현할 수도 있습니다.</li>
    <li>예외가 발생할 경우 적절한 HTTP 상태 코드와 함께 사용자에게 메시지를 전달하여 상황을 관리합니다.</li>
  </ul> <br>
  <li>수정, 삭제 시 요청할 때 보내는 <code>비밀번호</code>가 일치하지 않을 때</li>
<li>선택한 일정 정보가 이미 삭제되어 조회할 수 없을 때</li>
<li>삭제하려는 일정 정보가 이미 삭제 상태일 때</li>
  </ul></ul>
  <ul>
  <li>Swagger 활용</li><ul>
    <li>API 명세서를 직접 작성하는 대신 <b><code><a href="https://springdoc.org/#Introduction">Swagger</a></code></b>를 활용하여 자동으로 생성할 수 있습니다.</li>
    <li>개발자는 코드와 함께 API 명세서를 업데이트하고 관리할 수 있어서 <code>개발 생산성</code>을 높일 수 있습니다.</li>
    <li>Swagger UI를 통해 직관적인 인터페이스를 통해 API를 쉽게 이해하고 테스트할 수 있습니다.</li>
</ul><br>
  <ul>
    <li>Swagger UI를 통해 API 목록을 확인할 수 있다.</li>
    <li>Swagger UI를 통해 API 테스트를 할 수 있다.</li>
  </ul></ul>

  <ul><li>파라미터 유효성 검사, null 체크 및 특정 패턴에 대한 검증 수행</li><ul>
    <li>잘못된 입력이나 요청을 미리 방지할 수 있습니다.</li>
    <li>데이터의 <code>무결성을 보장</code>하고 애플리케이션의 예측 가능성을 높여줍니다.</li>
    <li>Spring에서 제공하는 <b><code>@Valid</code></b> 어노테이션을 이용할 수 있습니다.</li>
    <br>
   <li><code>할일 제목</code>은 최대 200자 이내로 제한, 필수값 처리</li>
    <li><code>비밀번호</code>는 필수값 처리</li>
    <li><code>담당자</code>는 이메일 형식을 갖도록 처리</li>
  </ul></ul>
  
  <ul>
    <li>파일 업로드 & 다운로드</li><ul>
      <li>사용자는 파일을 업로드할 수 있습니다. 업로드된 파일은 <code>개인 PC에</code>저장합니다.</li>
    <li>특정 파일 형식만을 업로드할 수 있도록 구현할 수 있습니다.</li>
      <li><code>JPG</code>, <code>PNG</code>, <code>JPEG</code> 등 이미지</li>
    </ul>
  </ul>
  
## 2.Use Case Diagram
![UseCaseDiagram drawio](https://github.com/serim01/Spring_Beginner_Project/assets/50200959/dcd6d85a-1e7a-4fde-81e4-ee64dff968d9)

## 3.API 명세서
[Postman API명세서](https://documenter.getpostman.com/view/34879249/2sA3JQ4KPn)
<table><tbody><tr><th>기능</th><th>Method</th><th>URL</th><th>request body</th><th>response body</th><th><b>예외처리</b></th></tr><tr><td><div>일정전체보기</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>GET</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>/api/todos</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>[<br>{<br>"id": 2,<br>"title": "테스트2",<br>"contents": "테스트 todo",<br>"username": "jenny@gmail.com",<br>"createdAt": "2024-05-16T23:55:30.153901",<br>"modifiedAt": "2024-05-16T23:55:30.153901"<br>},<br>{<br>"id": 1,<br>"title": "테스트1",<br>"contents": "테스트 todo",<br>"username": "serim@gmail.com",<br>"createdAt": "2024-05-16T23:54:49.07674",<br>"modifiedAt": "2024-05-16T23:54:49.07674"<br>}<br>\]</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td></tr><tr><td><div>선택일정보기</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>GET</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>/api/todos/{id}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>{<br>"id": 1,<br>"title": "테스트1",<br>"contents": "테스트 todo",<br>"username": "serim@gmail.com",<br>"createdAt": "2024-05-16T23:54:49.07674",<br>"modifiedAt": "2024-05-16T23:54:49.07674"<br>}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>Bad Request(400) : 선택한 일정이 존재하지 않을 때</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td></tr><tr><td><div>일정생성</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>POST</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>/api/todos</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>{<br>"title": "테스트1",<br>"contents": "테스트 todo",<br>"username": "serim",<br>"password": "123456"<br>}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>{<br>"id": 1,<br>"title": "테스트1",<br>"contents": "테스트 todo",<br>"username": "serim@gmail.com",<br>"createdAt": "2024-05-16T23:54:49.0767396",<br>"modifiedAt": "2024-05-16T23:54:49.0767396"<br>}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>Bad Request(400) : valid 요청에 맞지 않을 때<br>(1. `할일 제목`은 최대 200자 이내로 제한, 필수값 처리<br>2. `비밀번호`는 필수값 처리<br>3. `담당자`는 이메일 형식을 갖도록 처리)</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td></tr><tr><td><div>일정수정</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>PUT</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>/api/todos/{id}/{password}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>{<br>"title": "테스트",<br>"contents": "테스트 todo-수정",<br>"username": "sera@gmail.com"<br>}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>Bad Request(400) : 선택한 일정이 존재하지 않을 때, 비밀번호가 일치하지 않을 때 (각각 사유가 나옴)<br>,<br>valid 요청에 맞지 않을 때<br>(1. `할일 제목`은 최대 200자 이내로 제한, 필수값 처리<br>2. `비밀번호`는 필수값 처리<br>3. `담당자`는 이메일 형식을 갖도록 처리)</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td></tr><tr><td><div>일정삭제</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>DELETE</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>/api/todos/{id}/{password}</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div></div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td><td><div>Bad Request(400) : 선택한 일정이 존재하지 않을 때, 비밀번호가 일치하지 않을 때(각각 사유가 나옴)</div><div contenteditable="false"><div><div><div></div></div></div><div></div></div></td></tr></tbody></table>

## 4.ERD
![image](https://github.com/serim01/Spring_Beginner_Project/assets/50200959/c8c6cf14-753e-46df-9e51-bd623b242389)
