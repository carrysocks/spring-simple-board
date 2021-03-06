# spring-simple-board
Spring으로 구현한 게시판입니다.  
간단한 기능으로 시작해서 기능을 추가중입니다.

기능
---------

*회원가입  
*로그인(진행중)  
*회원 목록  
~*회원 마이페이지~    
*게시글 작성  
*게시글 나열  
*게시글 검색  
*게시글 삭제  
*게시글 상세보기  
~*게시글 페이징~    
*댓글 달기      
~*댓글 삭제~     
~*css 추가~  
~*리팩토링(역할과 구현 구분, 테스트 코드 작성)~      
 

기술 스택
----

+ 사용 기술
spring, thymeleaf


구조
-----

계층형 구조 사용
+ controller, web: 웹 계층  
+ service: 비즈니스 로직, 트랜잭션 처리
+ repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용 
+ domain: 엔티티가 모여 있는 계층, 모든 계층에서 사용

패키지 구조
+ CrudBoard.board 
```bash
├── controller
├── domain
├── repository
├── service
└── web
``` 

도메인 설계
-----

![image](https://user-images.githubusercontent.com/39540655/168005521-c275f97e-41d0-45f3-9c03-6c691ad132da.png)


테이블 설계
---
![image](https://user-images.githubusercontent.com/39540655/168009485-d6c4b6fb-084d-483e-a0cf-004245de07aa.png)


예제
-----
