package com.enotes;

import com.enotes.note.Note;
import com.enotes.note.NoteState;
import com.enotes.note.dao.JdbcNoteDao;
import com.enotes.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
