package test;

import android.test.InstrumentationTestCase;

import com.analisis.grupo7.g7.Escanner;
import com.analisis.grupo7.g7.EventSelect;
import com.analisis.grupo7.g7.LoginActivity;

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

    public void test_validarCarnet_validoSiNoTieneCaracteres() throws Exception{
        Escanner escanner = new Escanner();
        final boolean reality = escanner.validarCarnet("201212947");
        final boolean expected = true;
        assertEquals(reality,expected);
    }

    public void test_validarCarnet_largoValidoDeCadena() throws  Exception{
        Escanner escanner = new Escanner();
        final boolean reality = escanner.validarLargoCarnet("201212947");
        final boolean expected = true;
        assertEquals(reality,expected);
    }

    public void test_validarCarnet_noValidoSiNoTieneCaracteres() throws Exception{
        Escanner escanner = new Escanner();
        final boolean reality = escanner.validarCarnet("201212A47");
        final boolean expected = false;
        assertEquals(reality,expected);
    }

    public void test_validarCarnet_largoInvalidoDeCadena() throws  Exception{
        Escanner escanner = new Escanner();
        final boolean reality = escanner.validarLargoCarnet("20121294710");
        final boolean expected = false;
        assertEquals(reality,expected);
    }

    public void test_validarCarnet_quitarCaracterExtra() throws Exception{
        Escanner escanner = new Escanner();
        final String reality = escanner.quitarExtra("2012129471");
        final String expected = "201212947";
        assertEquals(reality,expected);
    }

    public void test_validarDatosLoginNoNulos_exitoso() throws Exception{
        LoginActivity login = new LoginActivity();
        final boolean reality = login.validarVacios("201212947", "passw");
        final boolean expected = true;
        assertEquals(reality,expected);
    }

    public void test_validarDatosLoginNoNulos_falla() throws Exception{
        LoginActivity login = new LoginActivity();
        final boolean reality = login.validarVacios("201212947","");
        final boolean expected = false;
        assertEquals(reality,expected);
    }

}
