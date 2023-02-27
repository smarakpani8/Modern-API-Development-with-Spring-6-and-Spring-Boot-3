package com.packt.modern.api.service;

import com.packt.modern.api.entity.ItemEntity;
import com.packt.modern.api.entity.ProductEntity;
import com.packt.modern.api.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter06 - Modern API Development with Spring and Spring Boot Ed 2
 */
@Service
public class ItemServiceImpl implements ItemService {

  @Override
  public ItemEntity toEntity(Item m) {
    ItemEntity e = new ItemEntity();
    e.setProduct(new ProductEntity().setId(UUID.fromString(m.getId())))
        .setPrice(m.getUnitPrice())
        .setQuantity(m.getQuantity());
    return e;
  }

  @Override
  public List<ItemEntity> toEntityList(List<Item> items) {
    if (Objects.isNull(items)) {
      return List.of();
    }
    return items.stream().map(this::toEntity).collect(toList());
  }

  @Override
  public Item toModel(ItemEntity e) {
    Item m = new Item();
    m.id(e.getProduct().getId().toString())
        .unitPrice(e.getPrice())
        .quantity(e.getQuantity())
        .description(e.getProduct().getDescription())
        .name(e.getProduct().getName())
        .imageUrl(e.getProduct().getImageUrl());
    return m;
  }

  @Override
  public List<Item> toModelList(List<ItemEntity> items) {
    if (Objects.isNull(items)) {
      return List.of();
    }
    return items.stream().map(this::toModel).collect(toList());
  }
}
