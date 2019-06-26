package com.example.bananacamelapp.model

import android.nfc.Tag
import android.util.Log
import com.example.bananacamelapp.MainActivity

class Banana(var banana: Int, var distance: Int,
             var bananaPerKm: Int, var camelNumber: Int
    ) {

    var maxBanana: Int = 0
     var  distanceTravelled: Int = 0
    var TAG = MainActivity::class.java.simpleName

    fun getMaximumBanana(): Int {
        val numOfStops = banana / 1000 // number of points the camel would stop at
        val bananaAtLastPoint = 1000
        var newTripDeterminant = numOfStops
        // this is to determine the number of trips according to the first documentation, variable t
        if (isMultiple()) {
            //the loop adds the distance to the point where the banana remains 1000
            for (i in 1 until numOfStops) {
                //this is variable, p =  2t - z
                val numOfTrips = (2 * newTripDeterminant)- camelNumber
                var d = 1000/numOfTrips
                Log.i(TAG, "THE DISTANCE lEFT  trr ${numOfTrips}")
                Log.i(TAG, "THE DISTANCE lEFT are ${d}")
                distanceTravelled += 1000 / numOfTrips
                Log.i(TAG, "THE DISTANCE lEFT is  ${distanceTravelled}")
                // this is the addition of the distance travelled from the market to the point where the banana is 1000
                newTripDeterminant--
            }
            val distanceLeft = distance - distanceTravelled // distance left to get to the market
            Log.i(TAG, "THE DISTANCE lEFT ${distance}")
            Log.i(TAG, "THE DISTANCE lEFT ${distanceTravelled}")
            Log.i(TAG, "THE DISTANCE lEFT ${distanceLeft}")
            maxBanana = bananaAtLastPoint - distanceLeft * bananaPerKm
            //maxbanana is 1000 minus the banana that would be eaten during the rest of the journey per km
            return if (maxBanana < 0) {
                -1
            } else maxBanana

        } else {
            val firstModulo = getModulo(banana)
            val bananaFirstConsumed = firstModulo + 1000
            var numberOfTripDeterminant = banana / 1000 + 1
            var numOfTrips = 2 * numberOfTripDeterminant - camelNumber
            val distanceTravelledTest = bananaFirstConsumed / numOfTrips

            // if the banana left is not more than the banana we would spend picking it then we drop it.
            if (firstModulo < distanceTravelledTest * 2) {
                // reduce the number of trips because we've dropped the little banana left.
                numberOfTripDeterminant -= 1
                numOfTrips = 2 * numberOfTripDeterminant - camelNumber
                for (i in 1 until numberOfTripDeterminant) {
                    distanceTravelled += 1000 / numOfTrips
                    numOfTrips -= 2
                }
                val distanceLeft = distance - distanceTravelled // distance left to get to the market
                maxBanana = bananaAtLastPoint - distanceLeft * bananaPerKm
                //maxbanana is 1000 minus the banana that would be eaten during the rest of the journey per km

                return if (maxBanana < 0) {
                    -1
                } else {
                    maxBanana
                }
            } else {
                numOfTrips = 2 * numberOfTripDeterminant - camelNumber
                distanceTravelled += bananaFirstConsumed / numOfTrips
                for (i in 1 until numberOfTripDeterminant - 1) {
                    distanceTravelled += 1000 / numOfTrips
                    numOfTrips = numOfTrips - 2
                }
                val distanceLeft = distance - distanceTravelled // distance left to get to the market
                maxBanana = bananaAtLastPoint - distanceLeft * bananaPerKm
                //maxbanana is 1000 minus the banana that would be eaten during the rest of the journey per km

                return if (maxBanana < 0) {
                    -1
                } else maxBanana


            }

        }

    }

    private fun isMultiple(): Boolean {
        return if (banana % 1000 != 0) return false else true

    }

    private fun getModulo(banana: Int): Int {
        return banana % 1000
    }
}