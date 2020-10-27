package grocery

import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDate

/*
* for manual testing
* TODO make it work !
 */

fun main(args: Array<String>) {
    val today = LocalDate.now()
    val discountReferenceDate = today
    val pricingDate = today
    println(
"""try pricing e.g.:
add apples 2
add bread 1
price    
""".trimMargin())
    (PricingCommandLineProcessor(PricingCalculator(discountReferenceDate), CurrentStock.all)).run(BufferedReader(InputStreamReader(System.`in`)), System.out, pricingDate)
}