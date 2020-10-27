package grocery

import grocery.CurrentStock.apples
import grocery.CurrentStock.bread
import grocery.CurrentStock.soup
import java.time.LocalDate
import kotlin.math.roundToInt

/**
 * calculates prices
 */
class PricingCalculator(val discountReferenceDate: LocalDate
           /* this is the reference date for the discounts */) {
    // calculate the price in pence
    fun priceInPence(itemLines: List<ItemLine> /* the basket*/ ,
                     pricingDate: LocalDate /* the date at which to price*/ ) : Int {
        var totalInPence= itemLines.map { it.quantity*it.stockItem.costInPence }.sum()
        totalInPence -= calculate2SoupLoafHalfPriceDiscount(itemLines, pricingDate)
        totalInPence -= calculateApplesPriceDiscount(itemLines, pricingDate)
        return totalInPence
    }

    private fun calculate2SoupLoafHalfPriceDiscount(itemLines: List<ItemLine>, pricingDate: LocalDate) : Int {
        if (pricingDate < discountReferenceDate.plusDays(-1)) {
            return 0
        }
        //check before end date
        if (pricingDate >= discountReferenceDate.plusDays(7)) {
            return 0
        }

        val soupCount = countByType(itemLines, soup)
        val loafCount = countByType(itemLines, bread)
        return if (soupCount>=2 && loafCount>0) bread.costInPence/2 else 0
    }

    private fun calculateApplesPriceDiscount(itemLines: List<ItemLine>, pricingDate: LocalDate) : Int {
        // check after start date
        val discountStartDate = discountReferenceDate.plusDays(3)
        if (pricingDate < discountStartDate) {
            return 0
        }
        //check before end date
        var discountEndDate = discountStartDate.plusMonths(2)
        discountEndDate = discountEndDate.plusDays(-discountEndDate.dayOfMonth.toLong())
        if (pricingDate >= discountEndDate ) {
            return 0
        }

        val appleCount = countByType(itemLines, apples)
        return ((appleCount * apples.costInPence).toDouble() * 0.1).roundToInt()
    }

    private fun countByType(itemLines: List<ItemLine>, stockItem: StockItem)
            = itemLines.filter {it.stockItem== stockItem }.map { it.quantity }.sum()
}