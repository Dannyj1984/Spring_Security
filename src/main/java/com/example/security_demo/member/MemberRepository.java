package com.example.security_demo.member;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberRepository extends JpaRepository<Member, Long> { // must extend JPARepository or CrudRepository and then pass the type e.g Member and the data type of the ID e.g Long

    Member findByUsername(String username);

    Member deleteByUsername(String username);



}
