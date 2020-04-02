package enotes.dto;

public interface DtoConverter<K, T> {

    K convertToDto(T entity);

    T convertToEntity(K dto);
}
