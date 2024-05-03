    package fr.uga.l3miage.integrator.cyberCommandes.mappers;

    import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;

    import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
    import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
    import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
    import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
    import org.mapstruct.*;

    import java.util.List;
    import java.util.Set;

    @Mapper
    public interface JourneeMapper {
        List<JourneeDetailResponseDTO> toJourneeDetailResponseDTOS(List<JourneeEntity> journeeEntities);
        @Mapping(target = "entrepot", source = "entrepotEntity")
        JourneeDetailResponseDTO toJourneeDetailResponseDTO(JourneeEntity journeeEntity);

        JourneeEntity toEntity(JourneeCreationRequest journeeRequest) ;

        JourneeResponseDTO toJourneeResponseDTO(JourneeEntity journeeEntity) ;

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateJourneeFromDTO(JourneeUpdateRequest journeeUpdateRequest, @MappingTarget JourneeEntity journeeEntity);


    }
