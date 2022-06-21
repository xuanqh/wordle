package com.restaff.wordle.data.models

enum class ResultStatus(val status: String) {
    correct("correct"),
    present("present"),
    absent("absent"),
    none("none")
}