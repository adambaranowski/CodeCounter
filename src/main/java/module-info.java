open module minesweeperGame {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports pl.adambaranowski.codecounter.main to javafx.graphics, javafx.controls, javafx.fxml;
}