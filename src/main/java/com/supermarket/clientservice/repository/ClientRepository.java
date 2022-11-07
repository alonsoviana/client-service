package com.supermarket.clientservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.clientservice.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
