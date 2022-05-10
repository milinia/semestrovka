package ru.itis.models.old;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Subscription implements Serializable {

    private Long subId;
    private Long userId;
    private String serviceName;
    private Date endingDate;
    //private final String comment;
}
