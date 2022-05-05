package com.matoso.data;

import com.matoso.entites.Agenda;

import java.time.LocalDateTime;

public class Seed {
    public static void apply() {
        DataContext.DB.add(Agenda.create("Joaquim Matoso", "matoso@email.com", true, LocalDateTime.now().plusHours(5)));
        DataContext.DB.add(Agenda.create("Jeremias Dizinga", "jeremias@email.com", true, LocalDateTime.now().plusDays(10)));
        DataContext.DB.add(Agenda.create("Bernarda Longuenda", "bernarda@email.com", true, LocalDateTime.now().plusDays(20)));
        DataContext.DB.add(Agenda.create("Sara Samuel", "sara@email.com", true, LocalDateTime.now().plusDays(30)));
    }
}
