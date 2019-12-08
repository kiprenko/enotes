package enotes.aspect.cache;

public aspect CacheAspect {
    pointcut CachingSqlRequest() : call(@annotation(Cache));

    void around() : CachingSqlRequest() {
        System.out.println("CACHING WORKS!");
    }
}