package com.example.kwbruunauktion.security;



import com.example.kwbruunauktion.security.error.CustomOAuth2AccessDeniedHandler;
import com.example.kwbruunauktion.security.error.CustomOAuth2AuthenticationEntryPoint;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    System.out.println("CALLED");
    return new BCryptPasswordEncoder();
  }

  // Use this to fine tune the CORS headers, if needed (Not required for this semester)
  //@Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source =
        new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOriginPattern("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //This line is added to make the h2-console work (if needed)
    http.headers().frameOptions().disable();
    http
        .cors().and().csrf().disable()

        .httpBasic(Customizer.withDefaults())
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //REF: https://mflash.dev/post/2021/01/19/error-handling-for-spring-security-resource-server/
        .exceptionHandling((exceptions) -> exceptions
            .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
            .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler())
        )
        .oauth2ResourceServer()
        .jwt()
        .jwtAuthenticationConverter(authenticationConverter());

    http.authorizeHttpRequests((authorize) -> authorize
        //Obviously we need to be able to login without being logged in :-)
        .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

        //Required in order to use the h2-console
        .antMatchers("/h2*/**").permitAll()

        .antMatchers("/").permitAll() //Allow the default index.html file

        //Next two lines only required if you plan to do the cookie/session-demo from within this project
        .antMatchers("/session-demo.html").permitAll()
        .antMatchers("/api/cookie/**").permitAll()

        //Allow anonymous access to this endpoint
        //.antMatchers(HttpMethod.GET,"/api/demo/anonymous").permitAll()

        //necessary to allow for "nice" JSON Errors
        .antMatchers("/error").permitAll()

        //.antMatchers("/", "/**").permitAll()

        .antMatchers(HttpMethod.GET,"/api/cars").hasAnyAuthority("ADMIN","USER")
        .antMatchers(HttpMethod.GET,"/api/cars/all").hasAnyAuthority("ADMIN","USER")
        .antMatchers(HttpMethod.GET,"/api/cars/filter").hasAnyAuthority("ADMIN","USER")

        //SpecificCarModel
        .antMatchers(HttpMethod.GET, "/api/specificcarmodel").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/admin/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/admin/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/users/admin").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/users/admin/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/users/admin").permitAll()
        // Demonstrates another way to add roles to an endpoint
        // .antMatchers(HttpMethod.GET, "/api/demo/admin").hasAuthority("ADMIN")
        .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  /* Initialize static value "secret" */
  @Value("${app.secret-key}")
  private String secretKey;
  public static String tokenSecret;

  @Value("${app.secret-key}")
  public void setStaticValue(String secretKey) {
    SecurityConfig.tokenSecret = secretKey;
  }
  /* End of Initialize static value "secret" */

  @Bean
  public JwtEncoder jwtEncoder() throws JOSEException {
    System.out.println("1) ---> "+tokenSecret);
    SecretKey key = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<SecurityContext>(key);
    return new NimbusJwtEncoder(immutableSecret);
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    System.out.println("2) ---> "+tokenSecret);
    SecretKey originalKey = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();
    return jwtDecoder;
  }


  //TBD --> IS THIS THE RIGHT WAY
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }


}

