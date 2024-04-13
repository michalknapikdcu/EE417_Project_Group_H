package app.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.view.RedirectView;

/*
 * @author Conor McCarthy
 */

@Controller
public class ContactController {

    private final ContactService contactService;

    // Used to reference service class
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService; // Assign the injected service to the local reference.
    }

    // Post mapping to receive post request from HTML form with endpoint
    // "/api/contacts"
    @PostMapping("/api/contacts")
    public RedirectView addContact(@ModelAttribute ContactDTO contactDTO) {
        try {
            // ContactEntity object to save to the database.
            ContactEntity contact = new ContactEntity();

            // Using DTO to update entity class's values
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setContactNumber(contactDTO.getContactNumber());
            contact.setCountry(contactDTO.getCountry());
            contact.setSubject(contactDTO.getSubject());

            // Calling the contact service to save the contact entity to the database.
            contactService.saveContact(contact);

            // Redirect to page on success
            return new RedirectView("/contact.html");
        } catch (Exception e) {
            // Redirect to page on exception
            return new RedirectView("/contact.html");
        }
    }
}
