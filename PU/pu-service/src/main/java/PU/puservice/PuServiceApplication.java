package PU.puservice;

import PU.puservice.logTrace.trace.LogTrace;
import PU.puservice.logTrace.trace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuServiceApplication.class, args);
	}


}
