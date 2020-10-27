package grocery

import java.io.BufferedReader
import java.io.InputStreamReader

/*
* for manual testing
 */

fun main(args: Array<String>) {
    println(
"""try pricing e.g.:
add apples 2
add bread 1
price    
""".trimMargin())
    (PricingCommandLineProcessor()).run(BufferedReader(InputStreamReader(System.`in`)), System.out)
}