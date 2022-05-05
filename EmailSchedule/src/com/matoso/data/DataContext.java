package com.matoso.data;

import com.matoso.entites.Agenda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class DataContext {
    public static class DB {
        private static final List<Agenda> _agenda = new ArrayList<>();

        public static boolean any(LocalDateTime date) {
            for (var agenda : _agenda) {
                if(agenda.getDate().isEqual(date)) return true;
            }

            return false;
        }

        public static void add(@NotNull Agenda agenda) {
            if(!any(agenda.getDate())) {
                if(agenda.getDate().isAfter(LocalDateTime.now()) || agenda.getDate().isEqual(LocalDateTime.now())) {
                    _agenda.add(agenda);

                    out.println("Agendado com sucesso!");
                    return;
                }

                out.println("Data inválida!");
                return;
            }

            out.printf("Já há um registro para %s %s, %s às %s:%s%n",
                    agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                    agenda.getDate().getHour(), agenda.getDate().getMinute());
        }

        public static @Nullable Agenda get(String email) {
            for (var agenda : _agenda) {
                if(agenda.getEmail().equals(email)) return agenda;
            }

            return null;
        }

        public static List<Agenda> all() {
            return _agenda;
        }

        public static void remove(Agenda agenda) {
            _agenda.remove(agenda);
            out.println("Registo removido com sucesso!");
        }

        public  static void update(Agenda agenda) {
            remove(agenda);
            add(agenda);

            out.println("Registo atualizado com sucesso!");
        }
    }
}
