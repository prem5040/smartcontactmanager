package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message="Name is required")
    private String name;

    @Email(message="Invalid Email Address")
    private String email;

    @Pattern(regexp="^[0-9]{8,15}$", message="Invalid Phone Number")
    private String phoneNumber;

    private String address;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;

    // We will create annotations to validate our file. 
    // We will validate file size, resolution
    private MultipartFile contactImage;

    private String picture;
}
