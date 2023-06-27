package cn.tuyucheng.taketoday.samples.domain;

import cn.tuyucheng.taketoday.samples.config.ObservedAspectConfiguration;
import io.micrometer.observation.tck.TestObservationRegistry;
import io.micrometer.tracing.test.simple.SimpleTracer;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AutoConfigureObservability
@Import({
      ObservedAspectConfiguration.class,
      EnableTestObservation.ObservationTestConfiguration.class
})
public @interface EnableTestObservation {

   @TestConfiguration
   class ObservationTestConfiguration {

      @Bean
      TestObservationRegistry observationRegistry() {
         return TestObservationRegistry.create();
      }

      @Bean
      SimpleTracer simpleTracer() {
         return new SimpleTracer();
      }
   }
}