package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Deprecated
public class SojWatermark {

  private Long watermark;
  private int subtaskIndex;
}
