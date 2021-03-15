package com.cosmos.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.cosmos.dao.*;
import com.cosmos.dto.*;

@Service
public class CodeServiceImple implements CodeService {
	@Autowired
	CodeDao dao;
	
	@Override
	public Code[] getCodeList(String category) {
		return dao.getCodeList(category);
	}

}
