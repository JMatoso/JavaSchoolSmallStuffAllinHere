package com.matoso.data;

import com.matoso.entites.Agenda;
import com.matoso.services.AgendaWorker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class DataContext {
    public static class DB {
        private static final List<Agenda> _agenda = new ArrayList<>();

        public static void apply() {
            _agenda.add(Agenda.create("Joaquim Matoso", "matoso@email.com", true, LocalDateTime.now().plusHours(5)));
            _agenda.add(Agenda.create("Jeremias Dizinga", "jeremias@email.com", true, LocalDateTime.now().plusDays(10)));
            _agenda.add(Agenda.create("Bernarda Longuenda", "bernarda@email.com", true, LocalDateTime.now().plusDays(20)));
            _agenda.add(Agenda.create("Sara Samuel", "sara@email.com", true, LocalDateTime.now().plusDays(30)));
        }

        public static boolean any(LocalDateTime date) {
            for (var agenda : _agenda) {
                if (agenda.getDate().isEqual(date)) return true;
            }

            return false;
        }

        public static void add(@NotNull Agenda agenda) {
            if (!any(agenda.getDate())) {
                if (agenda.getDate().isAfter(LocalDateTime.now()) || agenda.getDate().isEqual(LocalDateTime.now())) {
                    _agenda.add(agenda);

                    out.println("The schedule has been scheduled.");
                    AgendaWorker.speech.speak("The schedule has been scheduled.");
                    return;
                }

                out.println("Invalid date.");
                AgendaWorker.speech.speak("Invalid date.");
                return;
            }

            var date = String.format("Already has a schedule on %s %s, %s at %s:%s%n",
                    agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                    agenda.getDate().getHour(), agenda.getDate().getMinute());

            out.printf(date);
            AgendaWorker.speech.speak(date);
        }

        public static @Nullable Agenda get(String email) {
            for (var agenda : _agenda) {
                if (agenda.getEmail().equals(email)) return agenda;
            }

            return null;
        }

        public static List<Agenda> all() {
            return _agenda;
        }

        public static void remove(Agenda agenda) {
            _agenda.remove(agenda);

            out.println("Schedule has been removed.");
            AgendaWorker.speech.speak("Schedule has been removed.");
        }

        public static void update(Agenda agenda) {
            remove(agenda);
            add(agenda);

            out.println("Schedule has been updated.");
            AgendaWorker.speech.speak("Schedule has been updated.");
        }
    }
}
