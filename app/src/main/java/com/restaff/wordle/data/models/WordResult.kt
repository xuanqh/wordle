package com.restaff.wordle.data.models

import java.io.Serializable

class WordResult(
    var slot: Int,
    var guess: String,
    var result: String
): Serializable {
}