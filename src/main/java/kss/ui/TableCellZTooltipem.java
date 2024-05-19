package kss.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;


// Tablecell z domyslnym tooltipem po najechaniu
public class TableCellZTooltipem<S, T> extends TableCell<S, T> {

    public TableCellZTooltipem() {
        super();
    }

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
            tooltip.setShowDelay(Duration.seconds(0.3));
            tooltip.setHideDelay(Duration.seconds(0));
            setTooltip(tooltip);
            setText(text);
        }
    }
}