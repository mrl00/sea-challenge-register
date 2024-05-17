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

    @Column(length = 11, nullable = false)
    private String phone;

    @Override
    public String toString() {
        String ddd = this.phone.substring(0,2);

        String prefix = this.phoneType == PhoneType.CELLPHONE ?
                this.phone.substring(2,7):
                this.phone.substring(2,6);

        String suffix = this.phoneType == PhoneType.CELLPHONE ?
                this.phone.substring(7,11) :
                this.phone.substring(6,10);

        return String.format("(%s) %s-%s", ddd, prefix, suffix);
    }
}
