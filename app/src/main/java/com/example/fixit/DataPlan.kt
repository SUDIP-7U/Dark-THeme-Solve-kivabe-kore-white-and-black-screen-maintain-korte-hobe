package com.example.fixit

enum class ValidityUnit { HOURS, DAYS }

data class DataPlan(
    val id: Int,
    val dataAmount: String,
    val validityValue: Int,
    val validityUnit: ValidityUnit,
    val price: Int
)

// Temporary home for sample data. Move to a ViewModel/repository later.
val samplePlans = listOf(
    DataPlan(1, "30 GB", 30, ValidityUnit.DAYS, 299),
    DataPlan(2, "15 GB", 15, ValidityUnit.DAYS, 199),
    DataPlan(3, "5 GB", 7, ValidityUnit.DAYS, 99),
    DataPlan(4, "2 GB", 3, ValidityUnit.DAYS, 70),
    DataPlan(5, "1 GB", 2, ValidityUnit.HOURS, 9),
    DataPlan(6, "50 GB", 30, ValidityUnit.DAYS, 1299),
    DataPlan(7, "55 GB", 15, ValidityUnit.DAYS, 1599),
    DataPlan(8, "100 GB", 7, ValidityUnit.DAYS, 1799),
    DataPlan(9, "20 GB", 3, ValidityUnit.DAYS, 170),
    DataPlan(10, "10 GB", 2, ValidityUnit.HOURS, 90),
    DataPlan(11, "35 GB", 30, ValidityUnit.DAYS, 1299),
    DataPlan(12, "75 GB", 15, ValidityUnit.DAYS, 2299),
    DataPlan(13, "85 GB", 7, ValidityUnit.DAYS, 1199),
    DataPlan(14, "20 GB", 3, ValidityUnit.DAYS, 70),
)
