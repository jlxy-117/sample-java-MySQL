package spartan117.sample;

import java.util.logging.Level;
import java.util.logging.Logger;
import spartan117.sample.MQ.ReceiverServiceImpl;
import spartan117.sample.controller.AllController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yecq
 */
@SpringBootApplication

public class Main {
     public static void main(String[] args) {
        // 或者用maven打包成jar，用java -jar XXX.jar启动
        SpringApplication.run(AllController.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ReceiverServiceImpl receiver = (ReceiverServiceImpl)context.getBean("receiver");
        while(true){
            System.out.println("receving..............");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
}
