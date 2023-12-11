package com.game.start.GameStart.REST;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.util.UUID;

public class ImageController {

    public static void main(String[] args) {
        String imageUrl = "https://www.purepc.pl//image//news//2023//12//06_gta_6_moze_na_premiere_zaoferowac_ray_tracing_mnostwo_ciekawostek_z_pierwszego_trailera_gry_od_rockstar_games_0_b.jpg";

        downloadAndSaveImage(imageUrl);
    }

    public static String downloadAndSaveImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            String destinationDirectory = "src/main/resources/static/img";
            try (InputStream in = url.openStream()) {
                String fileName = generateRandomFileName();
                Path destinationPath = Paths.get(destinationDirectory, fileName);

                while (Files.exists(destinationPath)) {
                    fileName = generateRandomFileName();
                    destinationPath = Paths.get(destinationDirectory, fileName);
                }

                Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Uzyskaj relatywną ścieżkę
                String relativePath = getRelativePath(destinationPath, Paths.get("src/main/resources/static"));
                return relativePath;
            }
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas pobierania i zapisywania zdjęcia: " + e.getMessage());
        }
        return "img\\plyta-cd.jpg";
    }

    private static String generateRandomFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }

    private static String getRelativePath(Path targetPath, Path basePath) {
        return basePath.relativize(targetPath).toString();
    }
}
