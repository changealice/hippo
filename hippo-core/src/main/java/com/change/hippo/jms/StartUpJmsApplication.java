package com.change.hippo.jms;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:39
 */
public class StartUpJmsApplication {


    public static void main(String[] args) {
        startup();
    }

    private static void startup() {
        AnnotationConfigApplicationContext application =
                new AnnotationConfigApplicationContext(JmsApplication.class);
        MessageSender messageSender = application.getBean(MessageSender.class);

        instantAndSend(messageSender);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void instantAndSend(MessageSender messageSender) {
        for (int i = 0, len = 10000; i < len; i++) {
            Product product = new Product();
            product.setName("changejava" + i);
            product.setProductId("13123");
            product.setQuantity(i);
            messageSender.sendMessage(product);
        }

    }

}
