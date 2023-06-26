/**
 * This class represents a song
 * Implements Cloneable
 */
public class Song implements Cloneable{
    private final String name; // name of the song
    private final String artist; // name of the artist
    private Genre genre;
    private int duration; // time in seconds
    private int serialNumber;

    /**
     * Constructs a new song
     *
     * @param name name of the song
     * @param artist name of the artist
     * @param genre genre of the song
     * @param duration time of the song in seconds
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.serialNumber = -1;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Finds if the song should be removed from playlist
     * Only keeps in the playlist the songs that match the given parameters
     *
     * @param artist artist to filter by
     * @param genre genre to filter by
     * @param maxDuration maximum duration to filter by
     * @return should the song be removed
     */
    public boolean removeFromFilter(String artist, Genre genre, int maxDuration) {
        if (artist != null) {
            if (!(artist.equals(this.artist))) return true;
        }
        if (genre != null) {
            if (genre.ordinal() != this.genre.ordinal()) return true;
        }
        if (duration > maxDuration) return true;

        return false;
    }

    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Song))
            return false;
        Song song = (Song)obj;
        return name.equals(song.name) && artist.equals(song.artist);
    }

    @Override
    public int hashCode() {
        return name.hashCode() - artist.hashCode();
    }

    @Override
    public String toString() {
        // (name, artist, genre, length)
        return name + ", "  + artist + ", " + genre + ", " + turnToMinutes();
    }

    /**
     * Converts the duration from seconds to mm:ss format
     *
     * @return time of the song in mm:ss format
     */
    private String turnToMinutes() {
        int minute = duration / 60;
        int seconds = duration % 60;
        if (seconds < 10) return minute + ":0" + seconds;
        return minute + ":" + seconds;
    }

    /**
     * Enum representing possible genres of songs
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
