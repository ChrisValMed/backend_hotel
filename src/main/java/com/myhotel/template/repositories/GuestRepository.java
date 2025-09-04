package com.myhotel.template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhotel.template.entities.GuestResponse;

@Repository
public interface GuestRepository extends JpaRepository<GuestResponse, Long>  {

}
