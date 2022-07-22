package fianl.shop;

import fianl.shop.eom.respository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {



	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
