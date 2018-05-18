package com.pjmike.lundao.utils.shiro;

import com.pjmike.lundao.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pjmike
 * @create 2018-05-18 16:40
 */
public class Filter extends BasicHttpAuthenticationFilter {
        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            //获取Authorization字段
            String authorization = httpServletRequest.getHeader("Authorization");
            if (!StringUtils.isBlank(authorization)) {
                JwtTokenShiro token = new JwtTokenShiro(authorization);
                getSubject(request,response).login(token);
                return true;
            } else {
                throw new ServiceException("未授权");
            }
        }
}
