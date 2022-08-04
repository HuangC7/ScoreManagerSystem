package com.hnchances.hyx.security.config;

import com.hnchances.hyx.security.JWTRealm;
import com.hnchances.hyx.security.NoSessionFilter;
import com.hnchances.hyx.security.StatelessDefaultSubjectFactory;
import com.hnchances.hyx.security.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 处理拦截器资源
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilter.setSecurityManager(defaultSecurityManager());
        // 过滤规则
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 自定义过滤器
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("jwt", new NoSessionFilter());
        shiroFilter.setFilters(filterHashMap);
        // 登录之后才可以请求的接口
        filterMap.put("/api/**", "jwt");

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager defaultSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(Arrays.asList(userRealm(), jwtRealm()));
        // 禁用shiro中的session
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);
        defaultWebSecurityManager.setSubjectFactory(subjectFactory());
        return defaultWebSecurityManager;
    }

    /**
     * 配置自定义的权限登录
     */

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * token的认证和授权
     *
     * @return
     */
    @Bean
    public JWTRealm jwtRealm() {
        return new JWTRealm();
    }

    @Bean
    public StatelessDefaultSubjectFactory subjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    /**
     * 凭证匹配器 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于MD5(MD5(""));
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

}