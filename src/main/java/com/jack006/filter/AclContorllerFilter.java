package com.jack006.filter;


import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.jack006.common.ApplicationContextHelper;
import com.jack006.common.JsonData;
import com.jack006.common.RequestHolder;
import com.jack006.model.SysUser;
import com.jack006.service.SysCoreService;
import com.jack006.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限拦截器控制类
 *
 * @Author jack
 * @Since 1.0 2020/2/2 20:45
 */
@Slf4j
public class AclContorllerFilter implements Filter {

    // 全局使用的白名单Set
    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();
    // 未授权的跳转的页面
    private final static String noAuthUrl = "/sys/user/noAuth.page";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 定义一个白名单
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrlList);
        // 保证noAuthUrl 可以访问
        exclusionUrlSet.add(noAuthUrl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        Map requestMap = request.getParameterMap();

        // 白名单处理逻辑
        if (exclusionUrlSet.contains(servletPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断是否登录
        SysUser sysUser = RequestHolder.getCurrentUser();
        if (sysUser == null) {
            log.info("{} visit {}, but no login, parameter: {}",JsonMapper.obj2String(sysUser),servletPath, JsonMapper.obj2String(requestMap));
            noAuth(request,response);
            return;
        }
        // 判断是否有权限访问
        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);
        if (!sysCoreService.hasUrlAcl(servletPath)) {
            log.info("{} visit {}, but no login, parameter: {}",JsonMapper.obj2String(sysUser),servletPath, JsonMapper.obj2String(requestMap));
            noAuth(request,response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 无权限访问
    private void noAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.endsWith(".json")) {
            JsonData jsonData = JsonData.fail("没有访问权限，如需访问,请联系管理员");
            response.setHeader("Content-Type","application/json");
            response.getWriter().print(JsonMapper.obj2String(jsonData));
        } else {
            clientRedirect(noAuthUrl,response);
            return;
        }
    }

    private void clientRedirect(String url, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + url + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");

    }

    @Override
    public void destroy() {

    }
}
