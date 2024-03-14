package api.lineCheck.data.utils.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class EntityNames {
    private UUID id;
    private String origin;
    private String name;
    private String deviceToken;
    public EntityNames(UUID id, String origin, String name, String deviceToken) {
        this.id = id;
        this.origin = origin;
        this.name = name;
        this.deviceToken = deviceToken;
    }
}
