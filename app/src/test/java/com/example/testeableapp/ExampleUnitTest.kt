package com.example.testeableapp

import org.junit.Test
import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    // PRUEBA 1: Calculo con 37% de propina y redondeo
    @Test
    fun calcularPropina() {
        val result = calculateTip(100.0, 37, true)
        assertEquals(37.0, result, 0.01)
    }

    // PRUEBA 2: Calculo con cantidad negativa
    @Test
    fun calcularPropina_conMontoNegativo() {
        val result = calculateTip(-50.0, 20, false)
        assertEquals(0.0, result, 0.01)
    }

    // PRUEBA 3: Calculo total a pagar por persona
    @Test
    fun calcularTotalPorPersona() {
        val bill = 100.0
        val tip = calculateTip(bill, 20, false)
        val personas = 4
        val total = (bill + tip) / personas
        assertEquals(30.0, total, 0.01)
    }

    // PRUEBA ADICIONAL 1: Redondeo aplicado correctamente
    @Test
    fun redondearPropina_conDecimales() {
        val result = calculateTip(85.5, 15, true)
        assertEquals(13.0, result, 0.01)
    }

    // PRUEBA ADICIONAL 2: Propina con valores altos funciona correctamente
    @Test
    fun calcularPropina_conValoresAltos() {
        val result = calculateTip(amount = 10000.0, tipPercent = 50, roundUp = false)
        assertEquals(5000.0, result, 0.01)
    }

}