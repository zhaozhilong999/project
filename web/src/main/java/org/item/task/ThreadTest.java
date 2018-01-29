package org.item.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ThreadTest {
	 public static void main(String[] args) {  
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);  
         AsynTaskService service = context.getBean(AsynTaskService.class);  
 
         for (int i = 0; i < 10; i++) {  
             service.f1(); // 执行异步任务  
             service.f2();  
         }  
         context.close();  
    } 
}
