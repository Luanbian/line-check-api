package api.lineCheck.data.utils.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class EntityNames {
    private UUID id;
    private String origin;
    private String name;
    public EntityNames(UUID id, String origin, String name) {
        this.id = id;
        this.origin = origin;
        this.name = name;
    }
}
