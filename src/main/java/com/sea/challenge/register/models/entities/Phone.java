package com.sea.challenge.register.models.entities;

import com.sea.challenge.register.models.enums.PhoneType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_phones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long phoneId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType phoneType;

    @Column(length = 2, nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String prefix;

    @Column(length = 4, nullable = false)
    private String suffix;

    @Override
    public String toString() {
        return String.format("(%s) %s-%s", this.ddd, this.prefix, this.suffix);
    }
}
