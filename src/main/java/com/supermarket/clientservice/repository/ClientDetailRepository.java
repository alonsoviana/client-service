package com.supermarket.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.clientservice.entity.ClientDetail;

@Repository
public interface ClientDetailRepository extends JpaRepository<ClientDetail, Integer> {

}
