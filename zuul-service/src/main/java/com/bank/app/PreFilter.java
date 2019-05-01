package com.bank.app;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        if (req.getMethod().equalsIgnoreCase("post")){
            try {
                System.out.println("... pre filter is executed ..."+ req.getRequestURI()+"..."+ IOUtils.toString(req.getReader()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (req.getMethod().equalsIgnoreCase("get")){
            System.out.println("... pre filter is executed ..."+ req.getRequestURI());
        }
        return null;
    }
}
