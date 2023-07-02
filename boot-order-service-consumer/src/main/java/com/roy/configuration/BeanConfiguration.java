package com.roy.configuration;

import com.roy.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 13:42 2023/5/2
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("roy");
        return person;
    }
}
