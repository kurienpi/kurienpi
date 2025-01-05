@echo off
set PROJECT_NAME=Inventory_System_Project
set BASE_DIR=%cd%\%InvSysPro%

REM Create the main project directory
mkdir "%BASE_DIR%"

REM Create the Maven project structure
mkdir "%BASE_DIR%\src\main\java\com\zorba\model"
mkdir "%BASE_DIR%\src\main\java\com\zorba\dao"
mkdir "%BASE_DIR%\src\main\java\com\zorba\util"
mkdir "%BASE_DIR%\src\main\resources"
mkdir "%BASE_DIR%\src\main\webapp\WEB-INF"
mkdir "%BASE_DIR%\src\main\webapp"
mkdir "%BASE_DIR%\src\test\java\com\zorba"

REM Create essential files
echo ^<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ^> > "%BASE_DIR%\pom.xml"
echo xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" >> "%BASE_DIR%\pom.xml"
echo ^<modelVersion^>4.0.0^</modelVersion^> >> "%BASE_DIR%\pom.xml"
echo ^<groupId^>com.zorba^</groupId^> >> "%BASE_DIR%\pom.xml"
echo ^<artifactId^>zorba-registration-system^</artifactId^> >> "%BASE_DIR%\pom.xml"
echo ^<version^>1.0-SNAPSHOT^</version^> >> "%BASE_DIR%\pom.xml"
echo ^<dependencies^> >> "%BASE_DIR%\pom.xml"
echo ^<!-- Add your dependencies here --> >> "%BASE_DIR%\pom.xml"
echo ^</dependencies^> >> "%BASE_DIR%\pom.xml"
echo ^</project^> >> "%BASE_DIR%\pom.xml"

echo ^<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd"^> > "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo ^<hibernate-configuration^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo ^<session-factory^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<property name="hibernate.connection.driver_class"^>com.mysql.cj.jdbc.Driver^</property^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<property name="hibernate.connection.url"^>jdbc:mysql://localhost:3306/your_database^</property^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<property name="hibernate.connection.username"^>root^</property^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<property name="hibernate.connection.password"^>password^</property^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<property name="hibernate.dialect"^>org.hibernate.dialect.MySQL8Dialect^</property^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo     ^<mapping class="com.zorba.model.User"/^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo ^</session-factory^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"
echo ^</hibernate-configuration^> >> "%BASE_DIR%\src\main\resources\hibernate.cfg.xml"

echo ^<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" version="3.1"^> > "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo     ^<servlet^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo         ^<servlet-name^>RegistrationServlet^</servlet-name^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo         ^<servlet-class^>com.zorba.servlet.RegistrationServlet^</servlet-class^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo     ^</servlet^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo     ^<servlet-mapping^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo         ^<servlet-name^>RegistrationServlet^</servlet-name^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo         ^<url-pattern^>/register^</url-pattern^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo     ^</servlet-mapping^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"
echo ^</web-app^> >> "%BASE_DIR%\src\main\webapp\WEB-INF\web.xml"

REM Create placeholder files for JSP pages
echo ^<!-- Registration JSP placeholder --> > "%BASE_DIR%\src\main\webapp\registration.jsp"
echo ^<!-- Register User JSP placeholder --> > "%BASE_DIR%\src\main\webapp\registerUser.jsp"
echo ^<!-- Success JSP placeholder --> > "%BASE_DIR%\src\main\webapp\success.jsp"
echo ^<!-- Error JSP placeholder --> > "%BASE_DIR%\src\main\webapp\error.jsp"

REM Create placeholder Java files
echo package com.zorba.model; > "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo public class User { >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo     private int id; >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo     private String name; >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo     private String email; >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo     // Getters and setters >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"
echo } >> "%BASE_DIR%\src\main\java\com\zorba\model\User.java"

echo package com.zorba.dao; > "%BASE_DIR%\src\main\java\com\zorba\dao\UserDAO.java"
echo public class UserDAO { >> "%BASE_DIR%\src\main\java\com\zorba\dao\UserDAO.java"
echo     // Database operations for User >> "%BASE_DIR%\src\main\java\com\zorba\dao\UserDAO.java"
echo } >> "%BASE_DIR%\src\main\java\com\zorba\dao\UserDAO.java"

echo package com.zorba.util; > "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo import org.hibernate.SessionFactory; >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo import org.hibernate.cfg.Configuration; >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo public class HibernateUtil { >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo     private static SessionFactory sessionFactory; >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo     static { >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo         try { >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo             sessionFactory = new Configuration().configure().buildSessionFactory(); >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo         } catch (Throwable ex) { >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo             throw new ExceptionInInitializerError(ex); >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo         } >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo     } >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo     public static SessionFactory getSessionFactory() { >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo         return sessionFactory; >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo     } >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"
echo } >> "%BASE_DIR%\src\main\java\com\zorba\util\HibernateUtil.java"

REM Notify completion
echo Project structure created at "%BASE_DIR%"
explorer "%BASE_DIR%"
