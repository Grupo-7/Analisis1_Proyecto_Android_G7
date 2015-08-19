package test;

import android.test.InstrumentationTestCase;

import com.analisis.grupo7.g7.EventSelect;

public class Tests extends InstrumentationTestCase{

    public void test_testDePrueba() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected,reality);
    }

    public void test_validarSeleccionEvento_falsoParaCadenaVacia() throws Exception{
        EventSelect eventSelect = new EventSelect();
        final boolean reality = eventSelect.validarEvento("");
        final boolean expected = false;
        assertEquals(expected,reality);
    }

    public void test_validarSeleccionEvento_verdaderoParaCadenaNoVacia() throws Exception{
        EventSelect eventSelect = new EventSelect();
        final boolean reality = eventSelect.validarEvento("Evento prueba");
        final boolean expected = true;
        assertEquals(expected,reality);
    }

}
