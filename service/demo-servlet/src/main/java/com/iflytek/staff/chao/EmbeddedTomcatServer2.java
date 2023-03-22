//package com.iflytek.staff.chao;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.Writer;
//
//public class EmbeddedTomcatServer2 {
//
//    public static void main(String[] args) throws LifecycleException {
//
//        String docBase = "src/main/webapp/";
//
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8082);
//
//        tomcat.addWebapp("/", new File(docBase).getAbsolutePath());
//
//        tomcat.start();
//        tomcat.getServer().await();
//    }
//}
