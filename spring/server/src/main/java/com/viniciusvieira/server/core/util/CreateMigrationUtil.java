package com.viniciusvieira.server.core.util;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class CreateMigrationUtil {
    public static void main(String[] args) {
        createMigration("teste-nova-migration");
    }

    private static void createMigration(String migrationName) {
        String pattern = "yyyyMMddHHmmss";
        LocalDateTime dateTime = LocalDateTime.now();
        String dateNewFormat = dateTime.format(DateTimeFormatter.ofPattern(pattern));

        String fileName = "V" + dateNewFormat + "__" + migrationName;
        File file = new File("src/main/resources/db/migration/" + fileName + ".sql");
        try {
            file.createNewFile();
            log.info("Arquivo para migration criado com sucesso...");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao tentar criar o novo arquivo de migration", e);
        }
    }
}
