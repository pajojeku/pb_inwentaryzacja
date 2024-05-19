package kss.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

// Tablecell z wyswietlaniem tooltipa po najechaniu myszka
public class TableCellZTooltipem<S, T> extends TableCell<S, T> {

    public TableCellZTooltipem() {
        super();
    }

    // Dodanie do komorki tooltipa w sposob zalezny od tego czy zawiera ona enum czy tez nie
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setTooltip(null);
            setText(null);
        } else {
            String text;
            if (item instanceof Enum) {
                text = ((Enum<?>) item).name();
            } else {
                text = item.toString();
            }
            Tooltip tooltip = new Tooltip(text);

            // Zmiana czasu wyswietlenia i znikniecia tooltipa
            tooltip.setShowDelay(Duration.seconds(0.3));
            tooltip.setHideDelay(Duration.seconds(0));

            // Ustawienie tekstu komorki i tooltipa
            setTooltip(tooltip);
            setText(text);
        }
    }
}