package com.example.kwbruunauktion.auktionSystem.repository;

import com.example.kwbruunauktion.auktionSystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
