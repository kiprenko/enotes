package enotes.aspect.cache;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@Log4j2
@Aspect
@Component
public class SqlRequestCachingAspect {

    private static Map<String, CachedSqlRequest> cachedSqlRequests;
    private static int TIMEOUT;

    private Environment env;

    @Autowired
    public SqlRequestCachingAspect(Environment env) {
        this.env = env;
    }

    @PostConstruct
    private void init() {
        cachedSqlRequests = new HashMap<>();
        TIMEOUT = parseInt(requireNonNull(env.getProperty("aspect.cache.timeout")));
    }

    @Pointcut("@annotation(enotes.annotation.cache.Cache)")
    private void selectSqlRequests() {
    }

    @Around("selectSqlRequests()")
    public Object beforeServiceMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().toLongString();
        if (cachedSqlRequests.containsKey(methodName)) {
            CachedSqlRequest sqlRequest = cachedSqlRequests.get(methodName);

            if (System.currentTimeMillis() - sqlRequest.getLastTimeExecuted() > TIMEOUT) {
                Object cachedValue = jp.proceed();
                sqlRequest.setCachedValue(cachedValue);
                cachedSqlRequests.put(methodName, sqlRequest);
                return cachedValue;
            } else {
                LOGGER.info("Value, requested by method {} was returned from cache. Current cache timeout is {}", methodName, TIMEOUT);
                return sqlRequest.getCachedValue();
            }
        } else {
            Object cachedValue = jp.proceed();
            CachedSqlRequest cachedSqlRequest = new CachedSqlRequest(System.currentTimeMillis(),
                    cachedValue);
            cachedSqlRequests.put(methodName, cachedSqlRequest);
            return cachedValue;
        }
    }
}
