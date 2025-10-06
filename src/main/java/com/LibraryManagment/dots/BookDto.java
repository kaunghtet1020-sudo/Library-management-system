package com.LibraryManagment.dots;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
  private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private Boolean isAvailable;
}
