package rsoapp.userms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

    private Integer id;
    private Integer userId;
    private String title;
    private Integer price;
    private String description;
    private String cond;
    private String category;
    private AdImagesDto adImagesDto;
    private String location;
    private String phoneNumber;
    private String email;
}
