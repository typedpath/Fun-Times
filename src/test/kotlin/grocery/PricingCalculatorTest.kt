package grocery

import grocery.CurrentStock.apples
import grocery.CurrentStock.bread
import grocery.CurrentStock.milk
import grocery.CurrentStock.soup
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

/**
 * unit test for the pricing calculator
 */
class PricingCalculatorTest {

    val discountReferenceDate = LocalDate.now().plusDays(-1)
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
    @Test
    fun testDayBeforeAppleDiscountValid() {
        var appleDiscountExpiryDate = discountReferenceDate.plusDays(2).plusMonths(2)
        appleDiscountExpiryDate = appleDiscountExpiryDate.plusDays(-appleDiscountExpiryDate.dayOfMonth.toLong() + 1)
        println("testDayBeforeAppleDiscountExpiry discountReferenceDate=$discountReferenceDate pricingDateEnd=$appleDiscountExpiryDate ")
        test(listOf(ItemLine(apples, 1)), appleDiscountExpiryDate, apples.costInPence)
    }

    @Test
    fun testDayBeforeAppleDiscountExpiry() {
        var pricingDateEnd = discountReferenceDate.plusDays(3).plusMonths(2)
        pricingDateEnd = pricingDateEnd.plusDays(-pricingDateEnd.dayOfMonth.toLong())
        println("testDayBeforeAppleDiscountExpiry discountReferenceDate=$discountReferenceDate pricingDateEnd=$pricingDateEnd ")
        test(listOf(ItemLine(apples, 1)), fiveDaysTime, (apples.costInPence.toDouble() * 0.9).toInt())
    }

    @Test
    fun testDayAfterAppleDiscountExpiry() {
        var appleDiscountExpiryDate = discountReferenceDate.plusDays(3).plusMonths(2)
        appleDiscountExpiryDate = appleDiscountExpiryDate.plusDays(-appleDiscountExpiryDate.dayOfMonth.toLong() + 1)
        println("testDayBeforeAppleDiscountExpiry discountReferenceDate=$discountReferenceDate pricingDateEnd=$appleDiscountExpiryDate ")
        test(listOf(ItemLine(apples, 1)), appleDiscountExpiryDate, apples.costInPence)
    }

    //check bread discount validity period
    @Test
    fun testDayBeforeBreadDiscountValid()  = test(listOf( ItemLine(soup, 2), ItemLine(bread, 1)), discountReferenceDate.minusDays(2), 210)
    @Test
    fun testDayAfterBreadDiscountValid()  = test(listOf( ItemLine(soup, 2), ItemLine(bread, 1)), discountReferenceDate.minusDays(1), 170)
    @Test
    fun testDayAfterBreadDiscountExpiry()  = test(listOf( ItemLine(soup, 2), ItemLine(bread, 1)), discountReferenceDate.plusDays(8), 210)

    private fun test(itemLines: List<ItemLine>, pricingDate: LocalDate, expectedPrice: Int) {
        val price = target.priceInPence(itemLines, pricingDate)
        Assert.assertEquals(expectedPrice, price)
    }


}