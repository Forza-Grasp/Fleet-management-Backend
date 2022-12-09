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

        .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
        .antMatchers(HttpMethod.PATCH, "/api/auth/reset-password").permitAll()

        //Required in order to use the h2-console
        .antMatchers("/h2*/**").permitAll()

        .antMatchers("/").permitAll() //Allow the default index.html file

        //Next two lines only required if you plan to do the cookie/session-demo from within this project
        .antMatchers("/session-demo.html").permitAll()
        .antMatchers("/api/cookie/**").permitAll()

        //allUsers
            .antMatchers(HttpMethod.GET, "/api/users/all").permitAll()

        //userAdmin
        .antMatchers(HttpMethod.GET, "/api/users/admin").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/admin/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/admin/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/users/admin").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/users/admin/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/users/admin").permitAll()

        //userLeaser
        .antMatchers(HttpMethod.GET, "/api/users/leaser").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/leaser/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/users/leaser/addCarBrand").permitAll()
        .antMatchers(HttpMethod.POST, "/api/users/leaser").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/users/leaser/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/users/leaser").permitAll()

        //userBuyer
        .antMatchers(HttpMethod.GET, "/api/users/buyer").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/buyer/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/users/buyer").permitAll()
        .antMatchers(HttpMethod.POST, "/api/users/buyer/addCarBrand").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/users/buyer/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/users/buyer").permitAll()

        //userEconomy
        .antMatchers(HttpMethod.GET, "/api/users/economy").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/economy/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/economy/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/users/economy").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/users/economy/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/users/economy").permitAll()

        //DamageMatrix
        .antMatchers(HttpMethod.GET, "/api/damageMatrix").permitAll()
        .antMatchers(HttpMethod.GET, "/api/damageMatrix/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/damageMatrix/{id}").permitAll()
        .antMatchers(HttpMethod.GET, "/api/damageMatrix/user/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/damageMatrix").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/damageMatrix/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/damageMatrix").permitAll()

        //SpecificDamage
        .antMatchers(HttpMethod.GET, "/api/specificDamage").permitAll()
        .antMatchers(HttpMethod.GET, "/api/specificDamage/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/specificDamage/{id}").permitAll()
        .antMatchers(HttpMethod.GET, "/api/specificDamage/damageMatrix/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/specificDamage").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/specificDamage/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/specificDamage").permitAll()

        //ColorType
        .antMatchers(HttpMethod.GET,"/api/color-types").permitAll()
        .antMatchers(HttpMethod.GET,"/api/color-types/{id}").permitAll()
        .antMatchers(HttpMethod.DELETE,"/api/color-types/{id}").permitAll()
        .antMatchers(HttpMethod.PUT,"/api/color-types").permitAll()
        .antMatchers(HttpMethod.POST,"/api/color-types").permitAll()



        //necessary to allow for "nice" JSON Errors
        .antMatchers("/error").permitAll()

        //SpecificCarModel
        .antMatchers(HttpMethod.GET, "/api/specific-car-model").permitAll()
        .antMatchers(HttpMethod.GET, "/api/specific-car-model/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/specific-car-model/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/specific-car-model").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/specific-car-model/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/specific-car-model").permitAll()

        //ColorMix
        .antMatchers(HttpMethod.GET, "/api/color-mix").permitAll()
        .antMatchers(HttpMethod.GET, "/api/color-mix/{id}").permitAll()
        .antMatchers(HttpMethod.GET,"/api/color-mix/c-mix/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/color-mix").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/color-mix").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/color-mix/{id}").permitAll()
        
        .antMatchers(HttpMethod.GET,"/api/cars").hasAnyAuthority("ADMIN","USER")
        .antMatchers(HttpMethod.GET,"/api/cars/all").hasAnyAuthority("ADMIN","USER")
        .antMatchers(HttpMethod.GET,"/api/cars/filter").hasAnyAuthority("ADMIN","USER")

        //BrandColorMix
        .antMatchers(HttpMethod.GET, "/api/brand-color-mix").permitAll()
        .antMatchers(HttpMethod.GET, "/api/brand-color-mix/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/brand-color-mix/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/brand-color-mix").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/brand-color-mix").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/brand-color-mix").permitAll()


        //Ownership
        .antMatchers(HttpMethod.GET,"/api/ownership").permitAll()
        .antMatchers(HttpMethod.GET,"/api/ownership/{id}").permitAll()
        .antMatchers(HttpMethod.PUT,"/api/ownership/{id}").permitAll()
        .antMatchers(HttpMethod.DELETE,"/api/ownership/{id}").permitAll()
        .antMatchers(HttpMethod.POST,"/api/ownership").permitAll()

        //BlackList
        .antMatchers(HttpMethod.GET,"/api/blacklist").permitAll()
        .antMatchers(HttpMethod.GET,"/api/blacklist/all").permitAll()
        .antMatchers(HttpMethod.GET,"/api/blacklist/{vinNumber}").permitAll()

        .antMatchers(HttpMethod.POST,"/api/blacklist").permitAll()
        .antMatchers(HttpMethod.PATCH,"/api/blacklist/deactivate/{vinNumber}").permitAll()
        .antMatchers(HttpMethod.PATCH, "/api/blacklist/activate/{vinNumber}").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/blacklist/{vinNumber}").permitAll()

        //Campaign
        .antMatchers(HttpMethod.GET, "/api/campaign").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/{id}").permitAll()

        .antMatchers(HttpMethod.PUT, "/api/campaign/{id}").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/campaign/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/campaign").permitAll()
        .antMatchers(HttpMethod.POST, "/api/campaign/add-lcdv-code").permitAll()

        //Campaign Car
        .antMatchers(HttpMethod.GET, "/api/campaign/car").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/car/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/car/{id}").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/car/campaign/{id}").permitAll()

        .antMatchers(HttpMethod.DELETE, "/api/campaign/car/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "/api/campaign/car").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/campaign/car").permitAll()

        //Campaign Color Price
        .antMatchers(HttpMethod.GET, "api/campaign/color-price").permitAll()
        .antMatchers(HttpMethod.GET, "api/campaign/color-price/all").permitAll()
        .antMatchers(HttpMethod.GET, "api/campaign/color-price/{id}").permitAll()

        .antMatchers(HttpMethod.DELETE, "api/campaign/color-price/{id}").permitAll()
        .antMatchers(HttpMethod.PATCH, "api/campaign/color-price/{id}").permitAll()
        .antMatchers(HttpMethod.POST, "api/campaign/color-price").permitAll()

        //Campaign Bid
        .antMatchers(HttpMethod.GET, "/api/campaign/bids").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/bids/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/bids/{id}").permitAll()
        .antMatchers(HttpMethod.GET, "/api/campaign/bids/campaign/{campaignId}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/campaign/bids").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/campaign/bids/{id}").permitAll()
        .antMatchers(HttpMethod.PATCH, "/api/campaign/bids/{id}").permitAll()

        // LcdvCodes
        .antMatchers(HttpMethod.GET, "/api/lcdv-codes").permitAll()
        .antMatchers(HttpMethod.GET, "/api/lcdv-codes/all").permitAll()
        .antMatchers(HttpMethod.GET, "/api/lcdv-codes/{id}").permitAll()

        .antMatchers(HttpMethod.POST, "/api/campaign/bids").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/campaign/bids/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/api/campaign/bids").permitAll()





        .anyRequest().permitAll());

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

