# 데이터 모델 구조
- 게시판(Board)
- 게시글(Post)
- 댓글(Comment)
- 사용자(User)
# 기능 요구사항
## 게시판 기능
- 생성
- 목록 조회
- 수정
- 삭제
## 게시글 기능
- 생성
- 목록 조회
- 상세 조회
- 수정
- 삭제
## 댓글 기능
- 생성
- 수정
- 삭제
## 특정 게시판의 게시글 목록 조회
## 특정 게시글의 댓글 목록 조회

# API 설계
## 게시판 API
1. 게시판 생성
- Method: POST
- Path: /api/boards
- Body:
{
"name": "게시판 이름"
}
- Response
{
"id": 1,
"name": "게시판 이름"
}

2. 게시판 목록 조회
- Method: GET
- Path: /api/boards
- Response
[
{
"id": 1,
"name": "게시판 이름"
}
]
3. 게시판 수정
- Method: PUT
- Path: /api/boards/{boardId}
- Body:
{
"name": "수정된 게시판 이름"
}
- Response
{
"id": 1,
"name": "수정된 게시판 이름"
}
4. 게시판 삭제
- Method: DELETE
- Path: /api/boards/{boardId}
- Response: 204 No Content
## 게시글 API
1. 게시글 생성
- Method: POST
- Path: /api/boards/{boardId}/posts
- Body:
{
"title": "게시글 제목",
"content": "게시글 내용"
}
- Response
{
"id": 1,
"title": "게시글 제목",
"content": "게시글 내용",
"boardId": 1
}
2. 게시글 목록 조회
- Method: GET
- Path: /api/boards/{boardId}/posts
- Response
[
{
"id": 1,
"title": "게시글 제목",
"commentCount": 3
}
]
3. 게시글 상세 조회
- Method: GET
- Path: /api/posts/{postId}
- Response
{
"id": 1,
"title": "게시글 제목",
"content": "게시글 내용",
"boardId": 1,
"comments": [
{
"id": 1,
"content": "댓글 내용"
}
]
}
4. 게시글 수정
- Method: PUT
- Path: /api/posts/{postId}
- Body:
{
"title": "수정된 게시글 제목",
"content": "수정된 게시글 내용"
}
- Response
{
"id": 1,
"title": "수정된 게시글 제목",
"content": "수정된 게시글 내용",
"boardId": 1
}
5. 게시글 삭제
- Method: DELETE
- Path: /api/posts/{postId}
- Response: 204 No Content

## 댓글 API
1. 댓글 생성
- Method: POST
- Path: /api/posts/{postId}/comments
- Body:
 
  {
  "content": "댓글 내용"
  }
- Response
  {
  "id": 1,
  "content": "댓글 내용",
  "postId": 1
  }
2. 댓글 수정
- Method: PUT
- Path: /api/comments/{commentId}
- Body: 
  {
  "content": "수정된 댓글 내용"
  }
- Response
  {
  "id": 1,
  "content": "수정된 댓글 내용",
  "postId": 1
  }
3. 댓글 삭제
- Method: DELETE
- Path: /api/comments/{commentId}
- Response: 204 No Content
4. 특정 게시글의 댓글 목록 조회
- Method: GET
- Path: /api/posts/{postId}/comments
- Response
  [
  {
  "id": 1,
  "content": "댓글 내용"
  }
  ]

## 특정 게시판의 게시글 목록 조회 API
- Method: GET
- Path: /api/boards/{boardId}/posts
- Query Parameters (선택):
- page: 페이지 번호 (기본값: 1)
- limit: 페이지당 게시글 수 (기본값: 10)
- Response
Status Code: 200 OK
Response Body:
{
"boardId": 1,
"boardName": "게시판 이름",
"posts": [
{
"id": 1,
"title": "게시글 제목",
"content": "게시글 내용",
"commentCount": 5,
"createdAt": "2025-01-10T12:00:00Z",
"updatedAt": "2025-01-10T13:00:00Z"
},
{
"id": 2,
"title": "다른 게시글 제목",
"content": "다른 게시글 내용",
"commentCount": 2,
"createdAt": "2025-01-09T15:00:00Z",
"updatedAt": "2025-01-09T16:00:00Z"
}
],
"pagination": {
"currentPage": 1,
"totalPages": 3,
"totalPosts": 25
}
}
## 특정 게시글의 댓글 목록 조회 API
- Method: GET
- Path: /api/posts/{postId}/comments
- Query Parameters (선택):
- page: 페이지 번호 (기본값: 1)
- limit: 페이지당 댓글 수 (기본값: 10)
- Response
Status Code: 200 OK
Response Body:
{
"postId": 1,
"postTitle": "게시글 제목",
"comments": [
{
"id": 1,
"content": "첫 번째 댓글 내용",
"author": {
"id": 101,
"username": "user1"
},
"createdAt": "2025-01-10T12:30:00Z",
"updatedAt": null
},
{
"id": 2,
"content": "두 번째 댓글 내용",
"author": {
"id": 102,
"username": "user2"
},
"createdAt": "2025-01-10T13:00:00Z",
"updatedAt": null
}
],
"pagination": {
"currentPage": 1,
"totalPages": 2,
"totalComments": 15
}
}