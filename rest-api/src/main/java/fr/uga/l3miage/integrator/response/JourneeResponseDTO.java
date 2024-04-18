package fr.uga.l3miage.integrator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "represente la journee")
public class JourneeResponseDTO {
    @Schema(description = "represente la reference de la journee")
    private String reference;

}
