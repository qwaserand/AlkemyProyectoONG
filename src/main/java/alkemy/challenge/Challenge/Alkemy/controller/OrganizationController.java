package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.converter.OrganizationDtoConverter;
import alkemy.challenge.Challenge.Alkemy.dto.OrganizationDto;
import alkemy.challenge.Challenge.Alkemy.model.Organization;
import alkemy.challenge.Challenge.Alkemy.model.Slide;
import alkemy.challenge.Challenge.Alkemy.service.OrganizationService;
import alkemy.challenge.Challenge.Alkemy.service.SlideService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationDtoConverter organizationDtoConverter;

    @Autowired
    private SlideService slideService;

    @GetMapping("/public")
    public ResponseEntity<?> bringOrganization() {
        return ResponseEntity.ok(organizationService.bringOrganization(1L));
        // organizationDtoConverter.convertEntityToGetOrganizationDto(organizationService.bringOrganization(id));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<?> bringOrganizationById(@PathVariable Long id) {
        Organization organization = organizationService.bringOrganization(id);
        if (organization == null) {
            return new ResponseEntity(new Message("No se ha encontrado una organización con el ID:  "+id),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(organization.getName());
        // organizationDtoConverter.convertEntityToGetOrganizationDto(organizationService.bringOrganization(id));
    }

    @PutMapping("/public/{id}") //Update
    public ResponseEntity<?> modifyOrganization(@RequestBody @Valid OrganizationDto organizationDto) {
        return organizationService.update(organizationDto);
    }

    @GetMapping("/{id}/slides") //FindById
    public List<Slide> listOrganizationSlides(@PathVariable Long id) {
        return slideService.findSlidesByOrganization(id);
    }
}
