package com.example.springintro.configorations;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class BeanConf {

     @Bean
             public BufferedReader reader(){
          return   new BufferedReader(new InputStreamReader(System.in));
     }

}
