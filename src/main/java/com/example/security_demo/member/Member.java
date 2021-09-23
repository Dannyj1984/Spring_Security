package com.example.security_demo.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity(name = "Member")
@Table(
        name = "member", //give table a name
        uniqueConstraints = {
                @UniqueConstraint(name = "member_username_unique", columnNames = "username") // name unique constraint
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
            name = "id",
            updatable = false
    )
    private Long memberId; //PK

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "firstname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstname;

    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
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
    private double handicap;

    @Column(
            name = "wins",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int wins = 0;

    @Column(
            name = "societyHandicap",
            nullable = false,
            columnDefinition = "DECIMAL"
    )
    private double societyHandicap;

    @Column(
            name = "socReduction",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int socReduction = 0;

    @Column(
            name = "logins",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int logins = 0;

    private String password;

    public Member(
                  String username,
                  String firstname,
                  String surname,
                  String email,
                  String role,
                  double handicap,
                  int wins,
                  double societyHandicap,
                  int socReduction,
                  int logins,
                  String password) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.handicap = handicap;
        this.wins = wins;
        this.societyHandicap = societyHandicap;
        this.socReduction = socReduction;
        this.logins = logins;
        this.password = password;
    }
}
