package pl.pswiniarska.ConferenceRoomsReservationSystem.organization;

import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.*;

@RestController
@RequestMapping("/organizations")
public class OrganizationsController {
    private final OrganizationsService organizationsService;

    public OrganizationsController(OrganizationsService organizationsService) {
        this.organizationsService = organizationsService;
    }

    @GetMapping
    List<Organizations> getAll() {
        return organizationsService.findAll();
    }

    @GetMapping("/getByName")
    List<Organizations> getByName(@RequestParam(required = true) String name) {
        return organizationsService.findByName(name);
    }

    @GetMapping("/dsc")
    List<Organizations> getAllDSC() {
        return organizationsService.findAllDSC();
    }

    @GetMapping("/asc")
    List<Organizations> getAllASC() {
        return organizationsService.findAllASC();
    }

    @GetMapping("/getBy")
    List<Organizations> getBy(@RequestParam(required = false) Map<String, String> params) {
        return organizationsService.findBy(params);
    }

    @PostMapping
    Organizations add(@Validated(value = AddOrganization.class) @RequestBody Organizations organizations) {
        return organizationsService.add(organizations);
    }

    @DeleteMapping("/{name}")
    Organizations delete(@PathVariable String name) {
        return organizationsService.delete(name);

    }

    @PutMapping("/{name}")
    Organizations update(@PathVariable String name, @Validated(value = UpdateOrganization.class) @RequestBody Organizations organizations) {
        return organizationsService.update(name, organizations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
