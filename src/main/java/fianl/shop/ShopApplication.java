package fianl.shop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {



	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	//Entity 직접 반환시, LAZY 부분을 위하여 등록,
	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}
}
