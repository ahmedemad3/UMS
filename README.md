# UMS
User Management Utility Module Project


How to use this module in spring application

Steps:
1- inject the project using eclipse from build path
2- from project properties choose Deployment Assembly and choose the project
3- import the resource file "spring-config" by using this "<import resource="classpath*:/META-INF/spring-config.xml" />"

Also you can make this project as jar and add it as external jar but need to do the last step [3].

It can be used in stand alone application or spring boot also by writing this code :-
/** sample code**/
public static final String APPLICATION_CONTEXT = "/META-INF/spring-config.xml";
ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
UserService userService= (UserService) context.getBean("userService");  

and don't forget to import resource using annotations in spring boot.

Enjoy.
