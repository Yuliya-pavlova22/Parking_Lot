package parking
data class Car(val num: String, val color: String)

fun main() {
    var theEnd = false
    var parking : MutableMap<Int, Car?> = mutableMapOf()

    while (!theEnd) {
        var strInput = readln()!! + " "+"/" + " "+"/"
        var str = strInput.split(' ')

        when (str[0]) {
            "exit" -> {
                theEnd = true
                return
            }
            "status" -> {
                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }
                if (parking.count { it -> it.value == null } == parking.size) println("Parking lot is empty.") else {
                    parking.forEach {
                        if (it.value != null)
                            println("${it.key} ${it.value}")
                    }
                }
            }
            "create" -> {
                parking = initialParking(str[1].toInt())
                println("Created a parking lot with ${str[1]} spots.")
            }
            "park" ->{

                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }
                if (parking.containsValue(null)) {
                    var numFirst = parking.filterValues { it == null }
                    println("${str[2]} car parked in spot ${numFirst.keys.first()}.")
                    parking[numFirst.keys.first()] = Car(str[1], str[2].lowercase())
                } else println("Sorry, the parking lot is full.")
            }
            "leave" -> {
                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }
                var parkNum = 0
                parkNum = str[1].toInt()
                if (parking.get(parkNum) != null){
                    println("Spot ${str[1]} is free.")
                    parking[str[1].toInt()] = null
                } else
                    println("There is no car in spot ${str[1]}.")

            }
            "reg_by_color" ->{
                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }

                var regByColorResult=
                    parking
                        .filterValues { it -> it != null }
                        .filterValues { it -> it!!.color == str[1].lowercase() }

                var numbersOnly = regByColorResult.map { it.value!!.num }

                if (numbersOnly.isEmpty()) {
                    println("No cars with color ${str[1]} were found.")
                    continue
                } else println(numbersOnly.joinToString())
            }
            "spot_by_color" -> {
                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }
                var spotByColorResult=
                    parking
                        .filterValues { it -> it != null }
                        .filterValues { it -> it!!.color == str[1].lowercase() }

                var spotOnly = spotByColorResult.map { it.key!! }

                if (spotOnly.isEmpty()) {
                    println("No cars with color ${str[1]} were found.")
                    continue
                } else println(spotOnly.joinToString())


            }
            "spot_by_reg" -> {
                if (parking.isEmpty()) {
                    println("Sorry, a parking lot has not been created.")
                    continue
                }
                var SetByColorResult=
                    parking
                        .filterValues { it -> it != null }
                        .filterValues { it -> it!!.num == str[1] }

                var spotOnly = SetByColorResult.map { it.key!! }

                if (spotOnly.isEmpty()) {
                    println("No cars with registration number ${str[1]} were found.")
                    continue
                } else println(spotOnly.joinToString())
            }
    }

        }
    }

fun initialParking(i: Int): MutableMap<Int, Car?> {
    var result: MutableMap<Int, Car?> = mutableMapOf()
    for (j in 1..i) {
        result[j] = null
    }
    return result
}
