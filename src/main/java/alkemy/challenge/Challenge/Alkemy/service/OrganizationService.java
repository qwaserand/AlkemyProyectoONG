package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.dto.OrganizationDto;
import alkemy.challenge.Challenge.Alkemy.model.Organization;
import alkemy.challenge.Challenge.Alkemy.repository.OrganizationRepository;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization bringOrganization(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> update(OrganizationDto organizationDto) {
        Organization organization = organizationRepository.findById(1L).get();
        organization.setName(organizationDto.getName());
        organization.setImage(organizationDto.getImage());
        if(organizationDto.getAdress().isBlank())organization.setAddress(organizationDto.getAdress());
        if(organizationDto.getPhone() > 0)organization.setPhone(organizationDto.getPhone());
        if(organizationDto.getLinkdnUrl() != null && !organizationDto.getLinkdnUrl().isBlank()) organization.setLinkdnUrl(organizationDto.getLinkdnUrl());
        if(organizationDto.getFacebookUrl() != null && !organizationDto.getFacebookUrl().isBlank()) organization.setFacebookUrl(organizationDto.getFacebookUrl());
        if(organizationDto.getInstagramUrl() != null && !organizationDto.getInstagramUrl().isBlank()) organization.setInstagramUrl(organizationDto.getInstagramUrl());
        organizationRepository.save(organization);
        return new ResponseEntity(new Message("la organizacion ha sido modificada con exito."), HttpStatus.OK);
    }

    public ResponseEntity<?> updateOng(Organization organization, Long id){

        Optional<Organization> organizationAux = organizationRepository.findOrganizationById(id);

        if (organizationAux.isEmpty()){
            return new ResponseEntity(new Message("no se ha encontrado un testimonio con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }

        organizationAux.get().setName(organization.getName());
        organizationAux.get().setAboutUsText(organization.getAboutUsText());
        organizationAux.get().setImage(organization.getImage());
        organizationAux.get().setAddress(organization.getAddress());
        return ResponseEntity.ok("Organización actualizada!");
    }


}
