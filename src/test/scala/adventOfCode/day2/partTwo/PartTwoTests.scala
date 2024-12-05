package adventOfCode.day2.partTwo


class PartTwoTests extends org.scalatest.funsuite.AnyFunSuite {
  test("Test Case 1: Already Safe Increasing Sequence") {
    val report = "1 2 3 4 5"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 2: Already Safe Decreasing Sequence") {
    val report = "5 4 3 2 1"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 3: Single Violation Fixed by Removing Middle Element") {
    val report = "1 2 6 3 4"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 4: Single Violation at the Beginning") {
    val report = "5 1 2 3 4"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 5: Single Violation at the End") {
    val report = "1 2 3 7"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 6: Multiple Violations Cannot Be Fixed") {
    val report = "1 5 9 2 6"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 7: Zero Difference Between Adjacent Levels") {
    val report = "2 2 3 4 5"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 8: Alternating Increase and Decrease") {
    val report = "1 3 2 4 3"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 9: Removal Creates New Violation") {
    val report = "1 2 3 5 4 6"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 10: Two Equal Adjacent Levels in Decreasing Sequence") {
    val report = "5 4 4 3 2"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 12: Two-Level Report with Valid Difference") {
    val report = "2 5"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 14: Non-Strictly Increasing/Decreasing with Valid Differences") {
    val report = "1 2 2 3 4"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 15: Multiple Possible Removals") {
    val report = "1 4 7 10 13"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 16: Violation Cannot Be Fixed by Removal") {
    val report = "1 5 2 6 3"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 17: Removing an Element Causes Direction Change") {
    val report = "5 4 3 2 3"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 18: Valid Sequence with Maximum Differences") {
    val report = "1 4 7 10"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 19: Invalid Due to Zero Difference at Beginning") {
    val report = "3 3 4 5 6"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 20: Invalid Due to Zero Difference at End") {
    val report = "1 2 3 3"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 21: Violation Due to Too Small Difference") {
    val report = "1 1 1 1"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 22: Violation Due to Too Large Negative Difference") {
    val report = "10 6 2 -2"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 23: Removal Causes Sequence to Switch Direction") {
    val report = "1 4 2 5 3"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 24: Removal at Middle to Fix Increasing Sequence") {
    val report = "1 2 5 3 4"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 25: Valid Decreasing Sequence with Maximum Differences") {
    val report = "10 7 4 1"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 26: bumbo") {
    val report = "88 86 88 89 90 93 95"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 27: bumbo") {
    val report = "25 24 23 22 21 24 26"
    assert(Solution1.isReportSafe(report) === false)
  }

  test("Test Case 28: bumbo") {
    val report = "86 85 84 81 82 78"
    assert(Solution1.isReportSafe(report) === true)
  }

  test("Test Case 29: bumbo") {
    val report = "47 50 47 46 44 41 38 37"
    assert(Solution1.isReportSafe(report) === true)
  }

}
