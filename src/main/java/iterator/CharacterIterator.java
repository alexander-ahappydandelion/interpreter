package iterator;

import java.util.Iterator;
import java.util.ListIterator;

public class CharacterIterator implements ListIterator<Character> {
    private final String string;
    private int position;

    public CharacterIterator(String string) {
        this.string = string;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < string.length();
    }

    @Override
    public boolean hasPrevious() {
        return 0 < position;
    }

    @Override
    public Character next() {
        return string.charAt(position++);
    }

    @Override
    public Character previous() {
        return string.charAt(--position);
    }

    @Override
    public int nextIndex() {
        return position;
    }

    @Override
    public int previousIndex() {
        return position - 1;
    }

    @Override
    public void add(Character character) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(Character character) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
