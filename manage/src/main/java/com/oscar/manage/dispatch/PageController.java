package com.oscar.manage.dispatch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PageController implements WebMvcConfigurer {
    private final List<String> pageList = List.of("page1", "page2", "page3"); // 从某个地方获取页面列表

    @GetMapping("/{page}")
    public String getPage(@PathVariable String page, Model model) {
        if (pageList.contains(page)) {
            model.addAttribute("contentTemplate", page + "Template"); // 设置content模板
            return "main";
        } else {
            return "hello"; // 如果页面不存在于pageList中，返回hello.html
        }
    }

    @GetMapping("/{page}/{id}")
    public String getPageWithId(@PathVariable String page, @PathVariable String id, Model model) {
        if (pageList.contains(page)) {
            model.addAttribute("contentTemplate", page + "Form"); // 设置对应的表单模板
            return "main";
        } else {
            return "hello";
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TemplateInterceptor());
    }

    private static class TemplateInterceptor implements HandlerInterceptor {
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            if (modelAndView != null && modelAndView.getViewName().equals("main")) {
                modelAndView.addObject("headerTemplate", "header");
                modelAndView.addObject("footerTemplate", "footer");
            }
        }
    }
}
