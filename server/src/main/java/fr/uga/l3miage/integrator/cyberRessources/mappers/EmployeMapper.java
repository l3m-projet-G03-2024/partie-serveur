package fr.uga.l3miage.integrator.cyberRessources.mappers;

import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeMapper {
    EmployeResponseDTO toResponse(EmployeEntity employeEntity);
}
