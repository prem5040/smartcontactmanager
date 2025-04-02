package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;



    @RequestMapping("/add")
    // add contact page: handler
    public String addContactView(Model model) {
        ContactForm contactForm =new ContactForm();
        contactForm.setName("Prem Kumar Yadav");
        contactForm.setFavorite(true);
        
        model.addAttribute("contactForm",contactForm);

        return "user/add_contact";
    }
    
    //Handling the data that will be recieved from submitting the form

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result , 
    Authentication authentication, HttpSession session){
        //Processing form data

        
        
        // 1. Validating form

        if(result.hasErrors())
        {
            // To list all the errors while submitting a form (for reference)
            // result.getAllErrors().forEach(error->logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
            .content("Correct Following Errors")
            .type(MessageType.red)
            .build());
            return "user/add_contact";
        }

        
        
        String username =Helper.getEmailOfLoggedInUser(authentication);
        //form-> contact
        User user = userService.getUserByEmail(username);

        // Process contact Picture 

        // Processing/ Uploading Image
        String fileURL= imageService.uploadImage(contactForm.getContactImage());

        // logger.info("File Info : {}", contactForm.getContactImage().getOriginalFilename());
        
        Contact contact =new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);

        // 2. save to database
        contactService.save(contact);
        System.out.println(contactForm);

        // 3. Set the contact picture url

        // 4. Set the message for displayed view
        session.setAttribute("message", Message.builder()
            .content("New Contact Successfully Added.")
            .type(MessageType.green)
            .build());

        return "redirect:/user/contacts/add";
    }

}
