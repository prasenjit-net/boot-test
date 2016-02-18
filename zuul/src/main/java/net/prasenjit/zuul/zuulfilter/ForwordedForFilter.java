package net.prasenjit.zuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pp03582 on 2/18/2016.
 */
@Slf4j
@Component
public class ForwordedForFilter extends ZuulFilter {

    private static final String X_FORWARDED_FOR = "X-Forworded-For";

    @Autowired
    private ZuulProperties zuulProperties;

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
        return zuulProperties.isAddProxyHeaders();
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // Rely on HttpServletRequest to retrieve the correct remote address from upstream X-Forwarded-For header
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr();
        // Pass remote address downstream by setting X-Forwarded for header again on Zuul request
        log.debug("X-Forwarded-For set to: {}", remoteAddr);
        ctx.getZuulRequestHeaders().put(X_FORWARDED_FOR, remoteAddr);
        return null;
    }
}
