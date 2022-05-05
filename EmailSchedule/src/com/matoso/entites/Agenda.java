package com.matoso.entites;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter

public class Agenda {
    private int id;
    private String name;
    private String email;
    private boolean isImportant;
    private LocalDateTime date;

    public Agenda() {
        this.isImportant = false;
        this.id = new Random().nextInt(100);
    }

    public static @NotNull Agenda create(String name, String email, boolean isImportant, LocalDateTime date) {
        var agenda = new Agenda();

        agenda.setName(name);
        agenda.setEmail(email);
        agenda.setImportant(isImportant);
        agenda.setDate(date);

        return agenda;
    }
}
