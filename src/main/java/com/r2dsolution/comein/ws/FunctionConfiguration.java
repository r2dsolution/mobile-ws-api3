package com.r2dsolution.comein.ws;

import java.util.function.Function;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.cloud.function.context.catalog.FunctionTypeUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.r2dsolution.comein.ws.function.*;

@SpringBootConfiguration
@Import(ComeInAWSConfig.class)
public class FunctionConfiguration {//implements ApplicationContextInitializer<GenericApplicationContext> {

	/*
	 * You need this main method (empty) or explicit <start-class>example.FunctionConfiguration</start-class>
	 * in the POM to ensure boot plug-in makes the correct entry
	 */
    public static void main(String[] args) {
    	// empty unless using Custom runtime at which point it should include
    	 FunctionalSpringApplication.run(FunctionConfiguration.class, args);
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
      }

//      @Override
//      public void initialize(GenericApplicationContext context) {
//        context.registerBean("demo", FunctionRegistration.class,
//            () -> new FunctionRegistration<>(uppercase())
//                .type(FunctionTypeUtils.functionType(String.class, String.class)));
//      }
}