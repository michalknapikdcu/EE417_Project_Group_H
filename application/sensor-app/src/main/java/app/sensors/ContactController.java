package app.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.view.RedirectView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * @author Conor McCarthy
 */
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/api/contacts")
    public RedirectView addContact(@ModelAttribute ContactDTO contactDTO, RedirectAttributes attributes) {
        try {
            // map using DTO class
            ContactEntity contact = new ContactEntity();
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setContactNumber(contactDTO.getContactNumber());
            contact.setCountry(contactDTO.getCountry());
            contact.setSubject(contactDTO.getSubject());

            contactService.saveContact(contact);
            // redirect on success
            return new RedirectView("/contact.html");
        } catch (Exception e) {
            // redirect on fail
            return new RedirectView("/contact.html");
        }
    }
}