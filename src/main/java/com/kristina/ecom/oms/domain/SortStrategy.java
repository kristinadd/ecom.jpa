package com.kristina.ecom.oms.domain;

import java.util.List;

import com.kristina.ecom.pms.domain.Computer;

public interface SortStrategy {
  void sort(List<Computer> cart);
}
