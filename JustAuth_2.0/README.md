Work to do..
1. Change build tools from maven to gradle
2. Java JPA, Hibernate
3. DI, AOP(Google Guice)
4. Finally, to create a Docker Container.

1. 환경
OS : Window 10, Ubuntu 16.04
IDE : Eclipse MARS.2, IntellijIC-2016 
DB : Maria 10.1.13, Mongo DB 3.2.5
라이브러리 :  Gradle에 표기

2. 개발 목적

공부내용들

1. DI
※ 참고사이트
(1) guice github - https://github.com/google/guice/tree/master/extensions
			     - 나중에는 내가 직접 di만들어보자 
			     http://www.journaldev.com/2403/google-guice-dependency-injection-example-tutorial
(2) provider - https://github.com/google/guice/wiki/InjectingProviders
(3) 멀티바인딩 2가지
Multibinder<IPersistence<?>> binder = Multibinder.newSetBinder(binder(), new TypeLiteral<IPersistence<?>>(){});
MapBinder<String, IPersistence<?>> factoryMap = MapBinder.newMapBinder(binder(), new TypeLiteral<String>(){}, new TypeLiteral<IPersistence<?>>(){});

3. AOP 

4. Annotation, APT(Annotation Processing Tools)

※ 참고사이트
(1) http://pluu.github.io/blog/android/2015/12/24/annotation-processing-api

5. Singleton
※ 참고사이트
(1) https://blog.seotory.com/2016/03/19/java-singleton-pattern
(2) http://www.jpstory.net/2013/05/singleton-pattern/

6. Generic
※ 참고사이트
(1) http://shonm.tistory.com/category/JAVA/%EC%A0%9C%EB%84%A4%EB%A6%AD%20%EA%B4%80%EB%A0%A8%20%EC%A0%95%EB%A6%AC

7. JWT/JWE 

.. TBD..
(1) REST쪽을 JAVA EE 혹은 표준으로 만든 후 쓰기편한 WAS 붙이기....
(2) 일단 Guice 쓰고 나중에 APT로 만들기
(3) Gradle Mult project 관련 https://medium.com/@ddljulian/gradle-eclipse-java-%EB%A9%80%ED%8B%B0-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%B9%8C%EB%93%9C-36663506c843#.m10b58a8u
(4) JWE/JWT 모듈 직접 만들기
