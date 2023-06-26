import java.util.*;

/**
 * This class represents a song playlist
 * Implements Cloneable, FilteredSongIterable, OrderedSongIterable
 */
public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList<Song> songs;
    private ArrayList<Song> backupPlaylist; // saves the pointer of the original playlist
    private int numberOfSongs; // current amount of songs
    private int total; // total amount of songs that are/were in the playlist

    // filters
    private String filterByArtist;
    private Song.Genre filterByGenre;
    private int filterByDuration;

    /**
     * Constructs a new playlist
     */
    public Playlist() {
        numberOfSongs = 0;
        total = 0;
        songs = new ArrayList<>();
        backupPlaylist = songs;
    }

    /**
     * This class represents an Iterator
     * Implements Iterator
     */
    private class PlaylistIterator implements Iterator<Song>{
        private ArrayList<Song> playlist;
        private int index;

        /**
         * Constructs a new playlist iterator
         *
         * @param playlist playlist to iterate
         */
        public PlaylistIterator(ArrayList<Song> playlist) {
            this.playlist = playlist;
            index = 0;
        }

        @Override
        public Song next() {
            Song val = playlist.get(index);
            index++;
            return val;
        }

        @Override
        public boolean hasNext() {
            return playlist.size() > index;
        }
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(songs);
    }

    /**
     * Adds a new song to the playlist
     *
     * @param song song to add
     */
    public void addSong(Song song) {
        if (songs.contains(song)) throw new SongAlreadyExistsException();
        songs.add(song);
        numberOfSongs++;
        total++;
        song.setSerialNumber(total);
    }

    /**
     * Removes a song from the playlist
     *
     * @param song song to remove
     * @return was the remove successful
     */
    public boolean removeSong(Song song) {
        int index = songs.indexOf(song);
        if (index == -1) return false;
        songs.remove(index);
        numberOfSongs--;
        song.setSerialNumber(-1);
        return true;
    }

    @Override
    public void filterArtist(String artist) {
        filterByArtist = artist;
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        filterByGenre = genre;
    }


    @Override
    public void filterDuration(int maxDuration) {
        filterByDuration = maxDuration;
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        ArrayList<Song> filtered = filter();

        Comparator<Song> compareBySerialNumber = Comparator.comparing( Song::getSerialNumber );
        Comparator<Song> compareByName = Comparator.comparing( Song::getName );
        Comparator<Song> compareByArtist = Comparator.comparing( Song::getArtist);
        Comparator<Song> compareByDuration = Comparator.comparing( Song::getDuration );
        Comparator<Song> compareByAlphabet = compareByName.thenComparing(compareByArtist);
        Comparator<Song> compareByLength = compareByDuration.thenComparing(compareByAlphabet);

        switch (order) {
            case ADDING:
                Collections.sort(filtered, compareBySerialNumber);
                break;

            case NAME:
                Collections.sort(filtered, compareByAlphabet);
                break;

            case DURATION:
                Collections.sort(filtered, compareByLength);
                break;

        }
        songs = filtered; // switches the main list to be the filtered one
        filterByDuration = 0;
        filterByGenre = null;
        filterByArtist = null;

    }

    /**
     * Filters the playlist according to the given parameters in previous methods
     *
     * @return filtered playlist
     */
    private ArrayList<Song> filter() {
        songs = backupPlaylist; // restores the original list as the main list
        ArrayList<Song> pl = shallowClone();
        int i = 0;
        int size = this.numberOfSongs;
        while(i < size) {
            Song song = pl.get(i);
            if (song.removeFromFilter(filterByArtist, filterByGenre, filterByDuration)) {
                pl.remove(song);
                size--;
                continue;
            }
            i++;
        }
        return pl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Playlist))
            return false;

        Playlist pl = (Playlist)obj;
        int count = 0;
        for (int i = 0; i < numberOfSongs; i++) {
            for (int j = 0; j < pl.numberOfSongs; j++) {
                if (songs.get(i).equals(pl.songs.get(j))) count++;
            }
        }
        return count == numberOfSongs;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        for (Song song : songs) {
            hashcode += song.hashCode();
        }
        return hashcode;
    }

    @Override
    public String toString() {
        // [(name, artist, genre, length), ... , (name, artist, genre, length)]
        int i = 0;
        String str = "[";
        for (Song song: songs) {
            if (i == 0) str += "(" + song.toString() + ")";
            else str += ", " + "(" + song.toString() + ")";
            i++;
        }
        return str + "]";
    }

    /**
     * Shallow clones the list of songs
     * 
     * @return new list of songs
     */
    private ArrayList<Song> shallowClone() {
        ArrayList<Song> newPL = new ArrayList<>();
        for (Song song: songs) {
            newPL.add(song);
        }
        return newPL;
    }

    @Override
    public Playlist clone() {
        try {
            Playlist copy = new Playlist();
            for (Song song: songs) {
                Song songCopy = song.clone();
                if (songCopy == null) throw new NullPointerException();
                copy.addSong(songCopy);
            }
            return copy;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
