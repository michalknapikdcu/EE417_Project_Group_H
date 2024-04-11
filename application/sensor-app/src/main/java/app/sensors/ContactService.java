package app.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author Conor McCarthy
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactEntity saveContact(ContactEntity contact) {
        return contactRepository.save(contact);
    }
}
