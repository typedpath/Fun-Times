package grocery

import grocery.CurrentStock.apples
import grocery.CurrentStock.bread
import grocery.CurrentStock.milk
import grocery.CurrentStock.soup
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class PricingCalculatorTest {

    val discountReferenceDate = LocalDate.now()
    val today = LocalDate.now()
    val fiveDaysTime = today.plusDays(5)
    val target = PricingCalculator(discountReferenceDate)

    //Tests from the spec in the readme
    @Test
    fun test3TinsSoup2LoavesBreadToday() =  test(listOf(ItemLine(soup, 3), ItemLine(bread, 2)), today, 315)

    @Test
    fun test6Apples1MilkToday() =  test(listOf(ItemLine(apples, 6), ItemLine(milk, 1)), today, 190)

    @Test
    fun test6Apples1Milk5Days() =  test(listOf(ItemLine(apples, 6), ItemLine(milk, 1)), fiveDaysTime, 184)

    @Test
    fun test3Apples2Soup1Bread() =  test(listOf(ItemLine(apples, 3), ItemLine(soup, 2), ItemLine(bread, 1)), fiveDaysTime, 197)


    // Tests not in spec
    // test discount validity periods

    private fun test(itemLines: List<ItemLine>, pricingDate: LocalDate, expectedPrice: Int) {
        val price = target.priceInPence(itemLines, pricingDate)
        Assert.assertEquals(expectedPrice, price)
    }


}