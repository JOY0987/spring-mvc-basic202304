package com.spring.mvc.etc;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    private String actorName;
    private int actorAge;
    private boolean hasPhone;
}
