package tn.esprit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Data
@AllArgsConstructor
public class EventCalendar {
  private long  id;
   private String title;
    private  String type;
    private Date start;
    private Date end;
}
