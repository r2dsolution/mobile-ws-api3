package com.r2dsolution.comein.ws;

import java.util.function.Function;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.catalog.FunctionTypeUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.ws.function.*;

@SpringBootConfiguration
public class FunctionConfiguration implements ApplicationContextInitializer<GenericApplicationContext> {

	/*
	 * You need this main method (empty) or explicit <start-class>example.FunctionConfiguration</start-class>
	 * in the POM to ensure boot plug-in makes the correct entry
	 */
    public static void main(String[] args) {
    	// empty unless using Custom runtime at which point it should include
    	// FunctionalSpringApplication.run(FunctionConfiguration.class, args);
    }

    @Override
    public void initialize(GenericApplicationContext context) {
    	Function<String, String> function = (str) -> str + str.toUpperCase();
    	
    	context.registerBean("uppercase", FunctionRegistration.class,
				() -> new FunctionRegistration<>(function).type(FunctionTypeUtils.functionType(String.class, String.class)));
    	
    	context.registerBean("uppercase", FunctionRegistration.class,
				() -> new FunctionRegistration<>(new TestFunction()).type(FunctionTypeUtils.functionType(APIGatewayProxyRequestEvent.class, APIGatewayProxyResponseEvent.class)));
  
    }
}