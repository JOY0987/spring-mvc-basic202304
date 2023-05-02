package com.spring.mvc.training.service;

import com.spring.mvc.training.repository.BoardExMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardExService {
    @Autowired
    BoardExMapper mapper;
}

