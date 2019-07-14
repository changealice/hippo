package com.change.hippo.sbdemo;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import com.change.hippo.sbdemo.properties.MyProperties1;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

//import com.change.hippo.sbdemo.properties.MyProperties1;


@EnableSwagger2Doc
//@RestController
@SpringBootApplication
public class SbDemoApplication {


//    @Resource
//    private MyProperties1 myProperties1;

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SbDemoApplication.class, args);

        Binder binder = Binder.get(context.getEnvironment());

        final MyProperties1 my1 = binder.bind("my1", Bindable.of(MyProperties1.class)).get();

        System.out.println(my1);

    }

//    @GetMapping("/demo1")
//    public String demo1() {
//        return "Hello battcn";
//    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        };
    }

}
