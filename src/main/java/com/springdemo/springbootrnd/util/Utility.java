package com.springdemo.springbootrnd.util;

import org.modelmapper.ModelMapper;

public class Utility {
    public static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
