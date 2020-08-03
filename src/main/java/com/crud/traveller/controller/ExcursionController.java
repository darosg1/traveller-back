package com.crud.traveller.controller;

import com.crud.traveller.mapper.ExcursionMapper;
import com.crud.traveller.service.ExcursionDbService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcursionController {
    @Autowired
    ExcursionMapper excursionMapper;
    @Autowired
    ExcursionDbService excursionDbService;


}
