ref : https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/

The OpenShift `diy` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#diy

개발 중심점
1. backend는 Java단에서 모두 작성한다.
2. Main에서 Init을 하고 각 모듈의 Controller로 기능이 나뉘어 수행된다.
3. Controller는 Spark의 RestAPI에 해당된다.
4. Service는 API의 한 기능 한 트렌젝션에 해당 된다.
 - 모듈단위로 작업한다. 기능내 DAO에 접근 할 수 있다.
5. DAO는 각 Collection의 CRUD 단위이며, 필요시 소속 모듈의 Collection에 접근 할 수 있다. 쿼리단위로 한다.
 - 단일 콜렉션 접근을 원칙으로 한다.
(취소, 150923)6. 각 모듈은 각각의 Collection에 모두 접근 할 수 있다.
7. 다른 모듈의 접근은 API를 통해 접근 한다.
8. 모듈 구성도

- User
 : UserGroup, User, Store
 
- Token
 : Token 
 
- Item
 : ItemGroup, ItemMeta, Item
 
- Task
 : Task, Action, Comment
 
- Notification

How to LocalTest
1. MongoDB 설치
2. 윈도우 경우 : mongod --dbpath {$DB_REPOSITORY_PATH}
 - ex) mongod --dbpath c:/mongodb/test
3. 프로젝트 오른쪽 클릭 -> Run As -> JAVA Application -> Select Type ? Main team.justtag.server.main
4. Running server... with jetty , http://locahost:8080/

Security
1. JWE, JWK를 통한 암호화를 하였으나, JavaScript단에서 보안에 취약함
2. 추가적인 보안을 하려면 RSA, SSL이나 공개키/대칭키 방법을 사용해야 겠음
3. 시간적인 제약상 JWE,JWK를 적용한 정도에서 넘어감

UI
1. bootswatch.com : github 소스
2. font : https://fontlibrary.org/en/font/amburegul