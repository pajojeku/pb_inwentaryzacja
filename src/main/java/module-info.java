module inwentaryzacja {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires nfx.core;
    requires java.desktop;
    requires transitive javafx.graphics;
    requires junit;

    opens kss.app to javafx.fxml;
    opens kss.controllers to javafx.fxml;
    opens kss.model to javafx.base;
    exports kss.app;
    exports kss.model;
}
