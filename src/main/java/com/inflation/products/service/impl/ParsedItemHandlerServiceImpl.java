package com.inflation.products.service.impl;

import com.inflation.products.model.ParsedItemModel;
import com.inflation.products.service.ParsedItemHandlerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParsedItemHandlerServiceImpl implements ParsedItemHandlerService {
  @Override
  public void save(ParsedItemModel item) {}
}
