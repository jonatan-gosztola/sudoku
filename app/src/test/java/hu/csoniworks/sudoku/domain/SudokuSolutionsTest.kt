package hu.csoniworks.sudoku.domain

class SudokuSolutionsTest {

    companion object {
        fun getTestGrid() = mutableListOf<MutableList<Int?>>(
            mutableListOf(1, 2, 3, 5, 4, 8, 9, 6, 7),
            mutableListOf(5, 8, 4, 6, 7, 9, 1, 3, 2),
            mutableListOf(7, 9, 6, 1, 2, 3, 4, 5, 8),
            mutableListOf(8, 5, 1, 3, 6, 4, 2, 7, 9),
            mutableListOf(2, 4, 9, 7, 1, 5, 3, 8, 6),
            mutableListOf(6, 3, 7, 9, 8, 2, 5, 4, 1),
            mutableListOf(3, 6, 8, 4, 9, 1, 7, 2, 5),
            mutableListOf(9, 7, 5, 2, 3, 6, 8, 1, 4),
            mutableListOf(4, 1, 2, 8, 5, 7, 6, 9, 3)
        )

        fun getTestGrid2() = mutableListOf<MutableList<Int?>>(
            mutableListOf(null, null, null, null, null, 8, 9, 6, 7),
            mutableListOf(null, null, null, 6, 7, 9, 1, 3, 2),
            mutableListOf(null, null, null, 1, 2, 3, 4, 5, 8),
            mutableListOf(null, 5, 1, 3, 6, 4, 2, 7, 9),
            mutableListOf(null, 4, 9, 7, 1, 5, 3, 8, 6),
            mutableListOf(6, 3, 7, 9, 8, 2, 5, 4, 1),
            mutableListOf(3, 6, 8, 4, 9, 1, 7, 2, 5),
            mutableListOf(9, 7, 5, 2, 3, 6, 8, 1, 4),
            mutableListOf(4, 1, 2, 8, 5, 7, 6, 9, 3)
        )
    }
}
