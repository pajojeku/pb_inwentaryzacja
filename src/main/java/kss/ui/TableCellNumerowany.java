package kss.ui;

import javafx.scene.control.TableCell;

// Tablecell wypelniany numerami w zaleznosci od zawartosci tabeli w ktorej sie znajduje
public class TableCellNumerowany<S, T> extends TableCell<S, T> {

    public TableCellNumerowany() {
        super();
    }

    // Metoda wpisujaca w pole numer wiersza
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        
        setText(empty ? "" : String.valueOf(getIndex() + 1));
    }

}
