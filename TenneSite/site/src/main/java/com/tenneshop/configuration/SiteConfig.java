package com.tenneshop.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.broadleafcommerce.common.extensibility.context.merge.Merge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//import com.community.core.config.StringFactoryBean;
//import com.mycompany.checkout.service.workflow.RecordHeatRangeActivity;

@Configuration
public class SiteConfig {
	
//    @Bean
//    @ConditionalOnProperty("jmx.app.name")
//    public StringFactoryBean blJmxNamingBean() {
//        return new StringFactoryBean();
//    }

    @Merge("blMessageSourceBaseNames")
    public List<String> customMessages() {
        return Arrays.asList("classpath:messages");
    }
    
//    /**
//     * Disables caching only in the 'default' profile which is useful for development.
//     */
//    @Merge("blMergedCacheConfigLocations")
//    @Profile("default")
//    public List<String> removeCachingConfiguration() {
//        return Arrays.asList("classpath:bl-override-ehcache.xml");
//    }
    
    /**
     * Broadleaf Commerce comes with an Image Server that allows you to manipulate images.   For example, the 
     *  demo includes a high resolution image for each product that is reduced in size for browsing operations
     */
    @Merge("blStaticMapNamedOperations")
    public Map<String, Map<String, String>> customImageOperations() {
        Map<String, Map<String, String>> operations = new HashMap<>();
        Map<String, String> browseOperation = new HashMap<>();
        browseOperation.put("resize-width-amount", "400");
        browseOperation.put("resize-height-amount", "400");
        browseOperation.put("resize-high-quality", "false");
        browseOperation.put("resize-maintain-aspect-ratio", "true");
        browseOperation.put("resize-reduce-only", "true");
        operations.put("browse", browseOperation);
        
        Map<String, String> thumbnailOperation = new HashMap<>();
        thumbnailOperation.put("resize-width-amount", "60");
        thumbnailOperation.put("resize-height-amount", "60");
        thumbnailOperation.put("resize-high-quality", "false");
        thumbnailOperation.put("resize-maintain-aspect-ratio", "true");
        thumbnailOperation.put("resize-reduce-only", "true");
        operations.put("thumbnail", thumbnailOperation);
        
        return operations;
    }
    
//    @Merge("blCheckoutWorkflowActivities")
//    public List<?> customCheckoutActivities(RecordHeatRangeActivity recordHeatRangeActivity) {
//        return Arrays.asList(recordHeatRangeActivity);
//    }
    
	/* FIXME:How DemoSite enforce HTTP redirect to HTTPS?
	 * Current solution refer to 
	 * https://stackoverflow.com/questions/26655875/spring-boot-redirect-http-to-https
	 * 
	 * Broadleaf official developer phillipuniverse said they configure via applicationContext-security.xml
	 * but it seems doesn't in new version.
	 * Details at http://forum.broadleafcommerce.org/viewtopic.php?t=3150
	 */
	@Value("${server.port:9443}")
	int httpsServerPort;
	
	/**
     * Spring Boot does not support the configuration of both an HTTP connector and an HTTPS connector via properties.
     * In order to have both, we’ll need to configure one of them programmatically (HTTP).
     * Below is the recommended approach according to the Spring docs:
     * {@link https://github.com/spring-projects/spring-boot/blob/1.5.x/spring-boot-docs/src/main/asciidoc/howto.adoc#configure-ssl}
     * @param httpServerPort
     * @return EmbeddedServletContainerFactory
     */
    @Bean
    public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory(@Value("${http.server.port:9090}") int httpServerPort) {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        
        tomcat.addAdditionalTomcatConnectors(createRedirectConnector(httpServerPort, httpsServerPort));

//        tomcat.addAdditionalTomcatConnectors(createStandardConnector(httpServerPort));
        return tomcat;
    }
    
//    private Connector createStandardConnector(int port) {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(port);
//        return connector;
//    }
    
    private Connector createRedirectConnector(@Value("${http.server.port:9090}") int httpServerPort,
    		int httpsServerPort) {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpServerPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsServerPort);
        return connector;
    }
}
