API 명세서

1) 일정 등록  
   Method : POST  
   URL : /schedules  
   Request : 요청 body  
   Response : 등록된 일정 정보  
   상태 코드 : 200 정상 등록

2) 일정 조회  
   Method : GET  
   URL : /schedules/{id}  
   Request : 요청 param (id)  
   Response : 단건 일정 정보  
   상태 코드 : 200 정상 조회

3) 회원 일정 조회  
   Method : GET  
   URL : /schedules?user={userId}  
   Request : 요청 param (userId)  
   Response : 다건 일정 정보  
   상태 코드 : 200 정상 조회

4) 일정 수정  
   Method : PUT  
   URL : /schedules/{id}  
   Request : 요청 body  
   Response : 수정된 일정 정보  
   상태 코드 : 200 정상 수정

5) 일정 삭제  
   Method : DELETE  
   URL : /schedules/{id}  
   Request : 요청 param (id)  
   Response : 없음  
   상태 코드 : 200 정상 삭제  
