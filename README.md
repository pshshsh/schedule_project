![image](https://github.com/user-attachments/assets/cd08a539-7445-4db6-bea1-5ee9a9aa4099) API 명세서

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

   ERD :
   ![Schedule ERD](https://github.com/user-attachments/assets/c4b265ab-4dca-4f98-9266-3e18fbc41ce7)

   
패키지 구성  
 controller: API 요청 처리

 dto: 데이터 전송 객체 관리

entity: DB와 매핑되는 엔티티  

repository: 데이터베이스 CRUD 처리  

service: 비즈니스 로직 처리  

