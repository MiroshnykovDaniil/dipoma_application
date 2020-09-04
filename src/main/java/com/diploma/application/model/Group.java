package com.diploma.application.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "usr_groups")
public class Group {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank(message = "Group title missed")
    private String title;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotNull
    private User creator;

    @ManyToMany()
    @JoinColumn(name = "paricipants_id")
    private Set<User> participants;


}
