/**
 * This interface represents setting a playlist scanning order
 * Inherits Iterable
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Set the scanning order to iterate the playlist
     *
     * @param order order to iterate the playlist
     */
    void setScanningOrder(ScanningOrder order);
}
