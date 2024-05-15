package com.maboroshi.cave.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class anime {
    private  String title;
    private  String subTitle;
    private  String poster;
    private  String director;
    private  String studio;
    private  String typeOf;
    private  String label;
    private  String date;
    private  String time;
}
