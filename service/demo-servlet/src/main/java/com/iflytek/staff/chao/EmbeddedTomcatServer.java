//package com.iflytek.staff.chao;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.Writer;
//
//public class EmbeddedTomcatServer {
//
//    public static void main(String[] args) throws LifecycleException {
//
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8082);
//
//        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
//
//        Tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
//            @Override
//            protected void service(HttpServletRequest req, HttpServletResponse resp)
//                    throws ServletException, IOException {
//
//                Writer w = resp.getWriter();
//                w.write("Embedded Tomcat servlet.\n");
//                w.flush();
//                w.close();
//            }
//        });
//
//        ctx.addServletMappingDecoded("/*", "Embedded");
//
//        tomcat.start();
//        tomcat.getServer().await();
//    }
//}
