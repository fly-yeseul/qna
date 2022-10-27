package dev.fly_yeseul.qna.configs;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.interceptors.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(false);
    }
}
