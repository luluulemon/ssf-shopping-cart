package com.training.visa.groceryServer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.training.visa.groceryServer.util.IOUtil;

@SpringBootApplication
public class GroceryServerApplication {
	private static Logger logger = LoggerFactory.getLogger(GroceryServerApplication.class); 

	public static void main(String[] args) {
		SpringApplication.run(GroceryServerApplication.class, args);

		ApplicationArguments appArgs = new DefaultApplicationArguments(args); 
		List<String> opsVal = appArgs.getOptionValues("dataDir");
						// getOptionValues("foo") -> returns List of arguments for foo, or null
						// eg. --foo=a --foo=b -> ["a", "b"]
		
		if (opsVal!=null)
		{   logger.info("data dir > " + (String) opsVal.get(0));  
			IOUtil.CreateDir((String)opsVal.get(0));
		}
		else{
			logger.warn("No data Directory was provided");
			System.exit(1);
		}
	}

}
