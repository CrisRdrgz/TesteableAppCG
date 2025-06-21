package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TipCalculatorUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Prueba 1: Redondear propina y validar cambio de calculo
    @Test
    fun testRedondeoPropina() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputMonto").performTextInput("100")
        composeTestRule.onNodeWithTag("switchRedondear").performClick()
        composeTestRule.onNodeWithTag("textoPropina")
            .assertTextContains("Propina: $", substring = true)
    }

    // Prueba 2: Cambiar slider del porcentaje y verificar calculo
    @Test
    fun testCambiarSliderPorcentaje() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputMonto").performTextInput("100")
        composeTestRule.onNodeWithTag("sliderPorcentaje")
            .performTouchInput { swipeRight() }
        composeTestRule.onNodeWithTag("textoPropina")
            .assertTextContains("Propina: $", substring = true)
    }

    // Prueba 3: Validar visibilidad de elementos UI
    @Test
    fun testElementosVisibles() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputMonto").assertIsDisplayed()
        composeTestRule.onNodeWithTag("sliderPorcentaje").assertIsDisplayed()
        composeTestRule.onNodeWithTag("inputPersonas").assertIsDisplayed()
    }

    // Prueba adicional 1: Cambio en numero de personas afecta el calculo
    @Test
    fun testCambioNumeroPersonas() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputMonto").performTextInput("120")
        // Simula dos clics en el boton "+"
        repeat(3) {
            composeTestRule.onAllNodesWithText("+")[0].performClick()
        }

        composeTestRule.onNodeWithTag("textoTotal")
            .assertTextContains("por persona", substring = true)
    }

    // Prueba adicional 2:  verifica que el calculo del total por persona cambia cuando se aumenta el numero de personas

    @Test
    fun testTextoTotalActualizaConMontoYPersonas() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        // Ingresar un monto
        composeTestRule.onNodeWithTag("inputMonto")
            .performTextInput("100")

        // Simular tres clics en el boton "+"
        repeat(2) {
            composeTestRule.onAllNodesWithText("+")[0].performClick()
        }

        // Verificar que el texto "por persona" aparece en el total
        composeTestRule.onNodeWithTag("textoTotal")
            .assertTextContains("por persona", substring = true)
    }
}