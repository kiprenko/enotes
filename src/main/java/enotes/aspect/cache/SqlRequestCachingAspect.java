package enotes.aspect.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * ВАЖНО: не забудь указать, что это аспект @Aspect, иначе Спринг не выполнит его как аспект, будет думать, что просто класс.
 * Аннотация @Component говорит Спрингу, что это его бин и нужно его обрабатывать, тоже ВАЖНО:)
 * В pom.xml смотри зависимости, которые я юзаю, чтобы подтянуть либы нужные. Сам проект ранится на Spring Boot (Boot это вери изи и есть куча гайдов).
 */
@Aspect
@Component
public class SqlRequestCachingAspect
{

    /**
     * тут мы указываем точку "среза", то есть на вызов каких методов привязываться.
     * В этом случае у нас идёт привязка на все методы, над которыми висит аннотация Cache. Как писать эти точки "среза" есть инфа в лабе, а так же в документации спринга,
     * там много чего вообще.
     * Само тело пустое, так как это просто конфигуратор для спринга. Но обрати внивание, имя метода нам пригодится.
     */
    @Pointcut("@annotation(enotes.annotation.cache.Cache)")
    private void selectSqlRequests()
    {
    }

    /**
     * А это сам метод, который будет выполнятся при вызове методов, что подпадают под наш "срез" (аннотированные Cache).
     * В данном случае у нас аннотация @Around, что означает, что мне нужно обернуть всё выполнение метода какой-то логикой.
     * Есть разные виды аннотаций (@Before, @After, etc), в доках спринга все они есть: https://docs.spring.io/spring/docs/2.5.x/reference/aop.html
     * Как видно, мы передали в аннотацию название метода selectSqlRequests, который является нашим "срезом", чтобы указать, что при вызове методов, подпадающих под срез,
     * нужно выполнить это.
     * Так же стоит быть внимательным с параметром JoinPoint, для разных аннотаций (Before, After, Around и т. д.) он свой. Указываем его на входе,
     * а дальше спринг сам его передаст, и внутри метода мы с ним работаем. В моём коде я вывожу сигнатуру срезанного метода в консоль.
     *
     * @param jp точка среза (якобы наш метод), из неё мы можем получить аргументы, возвращаемое значение и много чего прочего.
     * @return
     * @throws Throwable
     */
    @Around("selectSqlRequests()")
    public Object beforeServiceMethodInvocation(ProceedingJoinPoint jp) throws Throwable
    {
        System.out.println("Do you need some aspects?..");
        System.out.println(jp.getSignature());

        /*так как метод, который я обернул аспектом, должен возвращать что-то, то я запускаю его и возвращаю это значение,
        и мой код ничего и не понял, он вызвал метод, Спринг обернул в прокси его, выполнил код из метода beforeServiceMethodInvocation
        и вернул результат тому, кто вызвал.*/
        return jp.proceed();
    }
}
