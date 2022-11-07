package com.supermarket.clientservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.supermarket.clientservice.entity.*;
import com.supermarket.clientservice.repository.ClientRepository;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void whenFindById_thenReturnAClient() {
        ClientDetail clientDetail = ClientDetail.builder()
                .id(1)
                .email("avc@gmail.com")
                .phonenumber("918452364")
                .comments("Prueba")
                .build();

        Client client = Client.builder()
                .id(1)
                .name("Juan")
                .surname("Garcia")
                .address("Gran vía")
                .age(18)
                .clientDetail(clientDetail)
                .build();
        Client clientDB = clientRepository.getById(1);
        Assertions.assertThat(clientDB.getName()).isEqualTo(client.getName());
    }
}
