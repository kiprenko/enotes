package enotes.aspect.cache;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Aspect
@Component
public class SqlRequestCachingAspect {

    private static Map<String, CachedSqlRequest> cachedSqlRequests;

    private static final int TIMEOUT = 3000;

    @PostConstruct
    private void init() {
        cachedSqlRequests = new HashMap<>();
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
