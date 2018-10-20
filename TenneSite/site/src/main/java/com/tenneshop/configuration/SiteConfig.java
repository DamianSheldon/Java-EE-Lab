package com.tenneshop.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteConfig {
	
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
