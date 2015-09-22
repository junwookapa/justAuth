ref : https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/

The OpenShift `diy` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#diy

개발 중심점
1. backend는 Java단에서 모두 작성한다.
2. Main에서 Init을 하고 각 모듈의 Controller로 기능이 나뉘어 수행된다.
3. Controller는 Spark의 RestAPI에 해당된다.
4. Service는 API의 한 기능 한 트렌젝션에 해당 된다.
5. DAO는 각 Collection의 CRUD 단위이며, 필요시 소속 모듈의 Collection에 접근 할 수 있다. 쿼리단위로 한다.
5. 각 모듈은 각각의 Collection에 모두 접근 할 수 있다.
6. 다른 모듈의 접근은 API를 통해 접근 한다.
7. 모듈 구성도

- User
 : UserGroup, User, Store
 
- Token
 : Token 
 
- Item
 : ItemGroup, ItemMeta, Item
 
- Task
 : Task, Action, Comment
 
- Notification