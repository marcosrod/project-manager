package com.marcosrod.clientapi.modules.client.model;

import com.marcosrod.clientapi.modules.client.dto.ClientRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Client of(ClientRequest request) {
        return new Client(request.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
