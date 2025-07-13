package dev.luisghtz.platzi_pizzeria_jpa.services.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
  private int id;
  private double price;
}
