package com.sea.challenge.register.models.dtos;

import com.sea.challenge.register.models.enums.PhoneType;
import com.sea.challenge.register.validators.PhoneNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PhoneNumber(message = "{phone.message}")
public class PhoneDTO {
    private PhoneType phoneType;
    private String phone;

    @Override
    public String toString() {
        return String.format("[phone-type: %s, phone: %s]", this.phoneType, this.phone);
    }
}
