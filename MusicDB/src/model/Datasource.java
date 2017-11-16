package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Java\\MusicDB\\" + DB_NAME;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ATRIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ATRIST_NAME + " = \"";
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTISTS_START = " SELECT * FROM " + TABLE_ARTISTS;
    public static final String QUERY_ARTISTS_SORT = " ORDER BY " + COLUMN_ATRIST_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONGS_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ATRIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONGS_SORT = " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ATRIST_NAME +
            ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ATRIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ATRIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ATRIST_NAME + ", " + COLUMN_SONG_ALBUM +
            ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_VIEW_SONF_INFO_PREP = "SELECT " + COLUMN_ATRIST_NAME + ", " + COLUMN_SONG_ALBUM +
            ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            "(" + COLUMN_ATRIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS +
            "(" + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";
    public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS +
            "(" + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ATRIST_NAME + " = ?";
    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

//    SELECT artists.name, albums.name, songs.track from songs
//    inner join albums ON songs.album = albums._id
//    inner join artists on albums.artist = artists._id
//    where songs.title = "Go Your Own Way"
//    ORDER BY artists.name, albums.name COLLATE NOCASE asc


    private Connection connection;

    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;


    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = connection.prepareStatement(QUERY_VIEW_SONF_INFO_PREP);
            insertIntoArtists = connection.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = connection.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = connection.prepareStatement(INSERT_SONG);
            queryArtist = connection.prepareStatement(QUERY_ARTIST);
            queryAlbum = connection.prepareStatement(QUERY_ALBUM);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }

            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }

            if (queryArtist != null) {
                queryArtist.close();
            }

            if (queryAlbum != null) {
                queryAlbum.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Artist> queryArtist(int sortOrder) {

        StringBuilder query = new StringBuilder(QUERY_ARTISTS_START);
        if (sortOrder != ORDER_BY_NONE) {
            query.append(QUERY_ARTISTS_SORT);
            query.append(getOrder(sortOrder));
        }

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (result.next()) {
                Artist artist = new Artist();
                artist.setId(result.getInt(INDEX_ARTIST_ID));
                artist.setName(result.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumForArtist(String artistName, int sortOrder) {

        StringBuilder query = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        query.append(artistName).append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            query.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            query.append(getOrder(sortOrder));
        }

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query.toString())) {

            List<String> albums = new ArrayList<>();
            while (result.next()) {
                albums.add(result.getString(1));
            }

            return albums;

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistForSong(String songName, int sortOrder) {

        StringBuilder query = new StringBuilder(QUERY_ARTIST_FOR_SONGS_START);
        query.append(songName).append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            query.append(QUERY_ARTIST_FOR_SONGS_SORT);
            query.append(getOrder(sortOrder));
        }

        return qureySongArtist(query);
    }

    public List<SongArtist> querySongInfoView(String title) {
        StringBuilder query = new StringBuilder(QUERY_VIEW_SONG_INFO);
        query.append(title).append("\"");

        try {
            querySongInfoView.setString(1, title);
            ResultSet result = querySongInfoView.executeQuery();
            List<SongArtist> songArtists = new ArrayList<>();
            while (result.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(result.getString(1));
                songArtist.setAlbumName(result.getString(2));
                songArtist.setTrack(result.getInt(3));
                songArtists.add(songArtist);

            }

            return songArtists;

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return null;
        }
    }

    private List<SongArtist> qureySongArtist(StringBuilder query) {
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();
            while (result.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(result.getString(1));
                songArtist.setAlbumName(result.getString(2));
                songArtist.setTrack(result.getInt(3));
                songArtists.add(songArtist);

            }

            return songArtists;

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return null;
        }
    }


    private String getOrder(int sortOrder) {
        if (sortOrder == ORDER_BY_ASC) {
            return "ASC";
        }

        return "DESC";
    }

    public void querySongMetaData() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            ResultSetMetaData meta = result.getMetaData();
            int columnNumbers = meta.getColumnCount();
            for (int i = 1; i <= columnNumbers; i++) {
                System.out.format("A songs táblában a %d  oszlop és a neve %s\n", i, meta.getColumnName(i));
            }

//            List<Song> songs = new ArrayList<>();
//            while (result.next()) {
//                Song song = new Song();
//                song.setId(result.getInt(1));
//                song.setTrack(result.getInt(2));
//                song.setTitle(result.getString(3));
//                song.setArbumId(result.getInt(4));
//                songs.add(song);
//            }
        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            int count = result.getInt("count");
            int min = result.getInt("min_id");
            System.out.format("Count = %d  -  Min = %d\n", count, min);
            return result.getInt(1);

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return -1;
        }
    }

    public boolean createViewForSongArtist() {

        try (Statement statement = connection.createStatement()) {

            statement.execute(CREATE_ARTIST_FOR_VIEW);
            return true;

        } catch (SQLException e) {
            System.out.println("Keresési hiba: " + e.getMessage());
            return false;
        }
    }

    private int insertArtist(String name) throws SQLException {

        queryArtist.setString(1, name);
        ResultSet result = queryArtist.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Nem sikerült az új előadót létrehozni.");
            }

            ResultSet generatedKey = insertIntoArtists.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);
            } else {
                throw new SQLException("Nem sikerült az előadó _id azonosítás.");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {

        queryAlbum.setString(1, name);
        ResultSet result = queryAlbum.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Nem sikerült az új albumot létrehozni.");
            }

            ResultSet generatedKey = insertIntoAlbums.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);
            } else {
                throw new SQLException("Nem sikerült az album _id azonosítás.");
            }
        }
    }

    private void insertSong(String title, String artist, String album, int track) {

        try {
            connection.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            int affectedRows = insertIntoSongs.executeUpdate();
            if (affectedRows == 1) {
                connection.commit();
            } else {
                throw new SQLException("A dalt nem sikerült hozzáadni az adatbázishoz.");
            }
        } catch (SQLException e) {
            System.out.println("Adatbázis hiba: " + e.getMessage());
            try {
                System.out.println("Rollback kezdeményezése");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Hibaaa: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Az auto-commit alaphelyzetbe állítása!");
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
                System.out.println("Az auto-commit alaphelyzetbe állítása nem sikerült: " + e3.getMessage());
            }
        }

    }


}
