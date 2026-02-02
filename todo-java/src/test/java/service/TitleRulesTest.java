package service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleRulesTest {

    @Test
    void prueba() {
        assertEquals("COMPLETADA","COMPLETADA");

    }

    @Test
    void normalizeTitle_recortaYReduceEspacios(){
        String Resultado = TitleRules.normalizeTitle("Hola  buenas   tardes y   adios");
        assertEquals("Hola buenas tardes y adios", Resultado);
    }

    @Test
    void validateTitle_fallaSiVacio(){
        assertThrows(IllegalArgumentException.class, () -> {
            TitleRules.validateTitle(" ");
        });
    }

     @Test
    void validateTitle_fallaSiDemasiadoLargo(){
        assertThrows(IllegalArgumentException.class, () -> {
            TitleRules.validateTitle("kiaskabdakjnuihejkblkpoeuh37ew6ghfh02yufehshjcausihsp9fywe9pw98");
        });
    }

    @Test
    void validateTitle_okSiCorrecto(){
         TitleRules.validateTitle("Paquito");
    }
}




