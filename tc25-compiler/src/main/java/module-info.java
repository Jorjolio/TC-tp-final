module com.compilador.tc25compiler {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;
    requires org.testng;


    opens com.compilador.tc25compiler to javafx.fxml;
    exports com.compilador.tc25compiler;
}