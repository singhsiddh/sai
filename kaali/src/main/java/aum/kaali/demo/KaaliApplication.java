package aum.kaali.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KaaliApplication {

	public static void main(String[] args) {
		System.out.println("jai ma Kaali");
		try {
		SpringApplication.run(KaaliApplication.class, args);
		}catch(Throwable th) {
			th.printStackTrace();
			System.out.println("jai ma Kaali Error ");
		}
		
		System.out.println("jai ma Kaali end ");
	}

}
