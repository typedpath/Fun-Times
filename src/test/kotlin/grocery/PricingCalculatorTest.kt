package grocery

import grocery.CurrentStock.bread
import grocery.CurrentStock.soup
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class PricingCalculatorTest {

    val discountReferenceDate = LocalDate.now()
    val today = LocalDate.now()
    val target = PricingCalculator(discountReferenceDate)

    //Tests from the spec in the readme
    @Test
    fun test3TinsSoup2LoavesBreadToday() {
        val price = target.priceInPence(listOf(ItemLine(soup, 3), ItemLine(bread, 2)), today)
        Assert.assertEquals(315, price)
    }

    // Tests not in spec
    // test discount validity periods

}