/**
 * This interface represents playlist filters
 * Inherits Iterable
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * Filter the playlist by an artist name
     *
     * @param artist artist to filter by
     */
    void filterArtist(String artist);

    /**
     * Filter the playlist by a genre
     *
     * @param genre genre to filter by
     */
    void filterGenre(Song.Genre genre);

    /**
     * Filter the playlist by the duration of the songs
     *
     * @param maxDuration maximum duration in seconds of the songs
     */
    void filterDuration(int maxDuration);
}
