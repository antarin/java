package com.mazsi;

import model.Artist;
import model.Datasource;
import model.SongArtist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        List<Artist> artists = new ArrayList();

        if (!datasource.open()) {
            System.out.println("Nem sikerült csatlakozni az adatbázishoz.");
            return;
        }

        artists = datasource.queryArtist(Datasource.ORDER_BY_ASC);
        for (Artist artist : artists) {
            System.out.println("Id: " + artist.getId() + ", Name: " + artist.getName());
        }

        System.out.println("===================================");

        List<String> albumsForArtist = datasource.queryAlbumForArtist("Carole King", Datasource.ORDER_BY_ASC);
        for (String item : albumsForArtist) {
            System.out.println("Az album címe: " + item);
        }

        System.out.println("===================================");

        List<SongArtist> songArtists = datasource.queryArtistForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Nincs ilyen szám az adatbázisban.");
        } else {
            for (SongArtist item : songArtists) {
                System.out.println("Az előadó: " + item.getArtistName() + ", az album címe: " + item.getAlbumName() +
                        ", a dal száma: " + item.getTrack());
            }
        }

        System.out.println("===========================================");

        datasource.querySongMetaData();

        System.out.println("===========================================");

        System.out.println("A dalok száma: " + datasource.getCount(Datasource.TABLE_SONGS));

        datasource.createViewForSongArtist();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Adj megy egy szám címet: ");
        String title = scanner.nextLine();

        songArtists = datasource.querySongInfoView(title);
        if (songArtists.isEmpty()) {
            System.out.println("Nincs ilyen előadó!");
        }
        for (SongArtist item : songArtists) {
            System.out.println("| Előadó: " + item.getArtistName() + " | Album: " + item.getAlbumName() + " | Szám: " + item.getTrack() + " |");
        }

        datasource.insertSong("Freedom", "Paddy and the Rats", "Freedom", 1);

        datasource.close();
    }
}
