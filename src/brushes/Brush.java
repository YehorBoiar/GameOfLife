package brushes;

import renderer.Renderer;

public enum Brush {
    DOT(new boolean[][]{{true}}),
    BIG_DOT(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}}),
    ERASE(new boolean[][]{{false}}),
    BIG_ERASE(new boolean[][] {{false, false, false}, {false, false, false}, {false, false, false}}),
    GLIDER(new boolean[][]{{true, true, true}, {false, false, true}, {false, true, false}}),
    TWICKER(new boolean[][]{{false, false, false}, {true, true, true}, {false, false, false}});

    private final boolean[][] elements;

    Brush(boolean[][] elements) {
        this.elements = elements;
    }

    public void execute(Renderer renderer, int row, int column) {
        renderer.reverseElements(row, column, elements);
    }
}
