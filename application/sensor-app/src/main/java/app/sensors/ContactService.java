package app.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author Conor McCarthy
 */

//  Service class for managing contacts.
//  This class interacts directly with the ContactRepository to save data to database

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Saves a ContactEntity to the database

    public ContactEntity saveContact(ContactEntity contact) {
        return contactRepository.save(contact);
    }

}
