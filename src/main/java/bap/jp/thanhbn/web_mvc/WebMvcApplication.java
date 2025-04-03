package bap.jp.thanhbn.web_mvc;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import bap.jp.thanhbn.web_mvc.batch.ui.ControllScheduled;

@SpringBootApplication
public class WebMvcApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WebMvcApplication.class, args).getBean(ControllScheduled.class);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(WebMvcApplication.class).headless(false).run(args);
		ControllScheduled appFrame = context.getBean(ControllScheduled.class);
		appFrame.InitUI();
	}

}
