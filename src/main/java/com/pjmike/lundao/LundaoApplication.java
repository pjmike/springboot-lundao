package com.pjmike.lundao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.pjmike.lundao.dao")
@ComponentScan("com.pjmike.lundao")
public class LundaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LundaoApplication.class, args);
	}
}
