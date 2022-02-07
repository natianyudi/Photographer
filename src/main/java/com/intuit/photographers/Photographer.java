package com.intuit.photographers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photographer {
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String avatar;
    private EventType event_type;
}
