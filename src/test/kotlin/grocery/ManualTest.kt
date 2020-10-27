package grocery

import java.io.BufferedReader
import java.io.InputStreamReader

/*
* for manual testing
* TODO make it work !
 */

fun main(args: Array<String>) {
    println(
"""try pricing e.g.:
add apples 2
add bread 1
price    
""".trimMargin())
    (PricingCommandLineProcessor(PricingCalculator(), CurrentStock.all)).run(BufferedReader(InputStreamReader(System.`in`)), System.out)
}