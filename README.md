Contents
1. Authentification API Server
2. SPA for testing

JustTag
Simple Authentification Server and JAVA MVC MicroFramWork Server
1. Simple Auth API Server
   - Provide JWT with AES and JWE with RSA Security
using JWT(using aes), JWE(using rsa) Security
2. Simple JAVA Micro MVC Framework
   - Fast development and prototyping
   - Easy to learn and use

Env
1. spark microframework
- java 1.8, Jetty WAS Server
2. angulraJS
3. BootsTrap

platform ref:
1. http://openshift.github.io/documentation/oo_cartridge_guide.html#diy
2. https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs

JWE ref:
1. http://jwt.io/
2. https://bitbucket.org/b_c/jose4j/wiki/Home
3. https://github.com/square/js-jose

How to deploy Server
 
1. openshift
1.1. rhc 설치
ex) https://developers.openshift.com/en/getting-started-overview.html
2.2. create app
ex) rhc app create justtagserver diy mongodb-2.4 --repo=justtagserver-os --from-code=https://github.com/junwookapa/justtagserver.git

2. embedded linux
1.1. install java 1.8 and setup environment value
1.2. run justtagserver.jar

3. windows server
1.1. install java 1.8 and setup environment value
1.2. run justtagserver.jar
