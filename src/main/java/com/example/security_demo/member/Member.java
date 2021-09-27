package com.example.security_demo.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity(name = "Member")
@Table(
        name = "member", //give table a name
        uniqueConstraints = {
                @UniqueConstraint(name = "member_username_unique", columnNames = "username"), // name unique constraint
                @UniqueConstraint(name = "member_email_unique", columnNames = "email") // name unique constraint

        }
)
public class Member {

    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1 //How much will the sequence increase each time. 1 = 1,2,3 etc
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "member_sequence"
    )
    @Column(
            name = "memberid",
            updatable = false
    )
    private Long memberId; //PK

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "{hoaxify.constraints.username.NotNull.message}")
    @Size(min = 4, max=255)
    private String username;

    @Column(
            name = "firstname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "{hoaxify.constraints.name.NotNull.message}")
    @Size(min = 4, max=255)
    private String firstname;

    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "{hoaxify.constraints.name.NotNull.message}")
    @Size(min = 4, max=255, message = "{javax.validation.constraints.Size.message}")
    private String surname;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "{hoaxify.constraints.email.NotNull.message}")
    @Size(min = 6, max=255, message = "{javax.validation.constraints.Size.message}")
    @Email(message = "{hoaxify.constraints.email.invalid.message}")
    private String email;

    @Column(
            name = "role",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String role;

    @Column(
            name = "handicap",
            nullable = false,
            columnDefinition = "DECIMAL(3,1)"
    )
    //Use @Range for non String fields
    @Range(min = 1, max=4, message = "Please enter a handicap for this member")
    private double handicap;

    @Column(
            name = "wins",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int wins = 0;

    @Column(
            name = "societyhandicap",
            nullable = false,
            columnDefinition = "DECIMAL(3,1)"
    )
    private double societyHandicap;

    @Column(
            name = "socreduction",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int socReduction = 0;

    @NotNull
    @Size(min = 11, max=11, message = "Please enter a valid mobile number which should be 11 digits long")
    @Column(
            name = "mobile",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String mobile;


    @Size(min = 9, max=11, message = "Please enter a valid CDH number which should be 10 digits long")
    @Column(
            name = "cdh",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String cdh;

    @Column(
            name = "logins",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int logins = 0;

    @Column(
            name = "password"
    )
    @NotNull
    @Size(min = 8, max=255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message="{hoaxify.constraints.password.Pattern.message}")
    private String password;

    public Member(@NotNull(message = "{hoaxify.constraints.username.NotNull.message}") @Size(min = 4, max = 255) String username, @NotNull(message = "{hoaxify.constraints.name.NotNull.message}") @Size(min = 4, max = 255) String firstname, @NotNull(message = "{hoaxify.constraints.name.NotNull.message}") @Size(min = 4, max = 255, message = "{javax.validation.constraints.Size.message}") String surname, @NotNull(message = "{hoaxify.constraints.email.NotNull.message}") @Size(min = 6, max = 255, message = "{javax.validation.constraints.Size.message}") @Email(message = "{hoaxify.constraints.email.invalid.message}") String email, String role, @Range(min = 1, max = 4, message = "Please enter a handicap for this member") double handicap, int wins, double societyHandicap, int socReduction, @NotNull @Size(min = 11, max = 11, message = "Please enter a valid mobile number which should be 11 digits long") String mobile, @Size(min = 9, max = 11, message = "Please enter a valid CDH number which should be 10 digits long") String cdh, int logins, @NotNull @Size(min = 8, max = 255) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraints.password.Pattern.message}") String password) {

        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.handicap = handicap;
        this.wins = wins;
        this.societyHandicap = societyHandicap;
        this.socReduction = socReduction;
        this.mobile = mobile;
        this.cdh = cdh;
        this.logins = logins;
        this.password = password;
    }


}
