package com.hcltech.Service;

import com.hcltech.dto.AddressDto;
import com.hcltech.model.Address;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AddressService {
    public AddressService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;


    public Set<AddressDto> toDto(Set<Address> addresses){
        return addresses.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public AddressDto toDto(Address address) {
        if(address==null) return null;
        return modelMapper.map(address, AddressDto.class);
    }

    public Set<Address> toEntity(Set<AddressDto> addressDtos){
        return  addressDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    public Address toEntity(AddressDto addressDto) {
        if(addressDto==null) return null;
        return modelMapper.map(addressDto, Address.class);
    }


}
