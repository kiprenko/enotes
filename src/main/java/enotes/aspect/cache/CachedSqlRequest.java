package enotes.aspect.cache;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CachedSqlRequest implements Serializable
{
    private long lastTimeExecuted;
    private Object cachedValue;
}
