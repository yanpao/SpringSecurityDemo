package com.wismap.springsecuritydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>(){
                public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                    fsi.setSecurityMetadataSource(mySecurityMetadataSource());
                    fsi.setAccessDecisionManager(new AffirmativeBased(Arrays.asList(new RoleVoter())));
                    fsi.setRejectPublicInvocations(true);//为何设计要报500错误？
                    return fsi;
                }
            })
            .and()
            .formLogin().loginPage("/login").permitAll().and()
            .logout().and()
            //.rememberMe().key("yanpao").tokenValiditySeconds(6).and()//可以记住，但是过期时间没成功
            .httpBasic().and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public MySecurityMetadataSource mySecurityMetadataSource()
    {
        return new MySecurityMetadataSource();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
