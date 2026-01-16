package hu.csoniworks.sudoku.domain.sudokuengine.calc

/**
 * EASY Sudoku generator
 *
 * Guarantees:
 * - Exactly ONE solution
 * - Solvable using ONLY:
 *   - Naked singles
 *   - Hidden singles
 */
class EasySudokuPuzzleGenerator(
    val size: Int = 9
) {

    /**
     * Makes an easily solvable puzzle from a given solution grid.
     */
    fun generateEasySudoku(
        solution: List<List<Int?>>,
        emptyCellLimit: Int = 81,
    ): List<List<Int?>> {

        val puzzle = SudokuCalculationsUtil.copyGrid(solution)

        // shuffle cells
        val cellIndexes = mutableListOf<Pair<Int, Int>>()
        for (row in 0 until size)
            for (col in 0 until size)
                cellIndexes += row to col

        cellIndexes.shuffle()

        var emptyCellCount = 0

        // iterate shuffled cells
        for ((row, col) in cellIndexes) {
            if (emptyCellCount >= emptyCellLimit) {
                continue
            }

            // Tries to empty a cell and checks whether the puzzle remains easily solvable.
            // If not, the cell is restored.
            val backup = puzzle[row][col]
            puzzle[row][col] = null

            if (!hasUniqueSolution(puzzle)) {
                puzzle[row][col] = backup
                continue
            }

            if (!isEasySolvable(puzzle)) {
                puzzle[row][col] = backup
                continue
            }

            emptyCellCount++


        }

        return puzzle
    }

    /**
     * Returns whether the grid has only one solution.
     */
    private fun hasUniqueSolution(
        grid: List<List<Int?>>
    ): Boolean {
        val work = SudokuCalculationsUtil.copyGrid(grid)
        return countSolutions(work, limit = 2) == 1
    }

    /**
     * Recursive function for counting the solutions of a puzzle grid.
     */
    private fun countSolutions(
        grid: MutableList<MutableList<Int?>>,
        limit: Int
    ): Int {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (grid[row][col] == null) {
                    var count = 0
                    for (number in 1..size) {
                        if (SudokuCalculationsUtil.isValid(grid, row, col, number)) {
                            grid[row][col] = number
                            count += countSolutions(grid, limit)
                            if (count >= limit) {
                                grid[row][col] = null
                                return count
                            }
                            grid[row][col] = null
                        }
                    }
                    return count
                }
            }
        }

        return 1
    }

    /**
     * Tries to solve a puzzle with naked single and hidden single techniques.
     * Returns whether is it solvable with these techniques.
     */
    private fun isEasySolvable(
        grid: List<List<Int?>>
    ): Boolean {
        val work = SudokuCalculationsUtil.copyGrid(grid)

        while (true) {
            var progress = false

            // Naked singles
            for (row in 0 until size) {
                for (col in 0 until size) {
                    if (work[row][col] == null) {
                        val candidates = (1..size).filter {
                            SudokuCalculationsUtil.isValid(work, row, col, it)
                        }
                        if (candidates.size == 1) {
                            work[row][col] = candidates.first()
                            progress = true
                        }
                    }
                }
            }

            // Hidden singles
            for (number in 1..9) {
                // Iterates on numbers, checks if the given number has only one valid place in the row/column/group
                // spots: indexes of possible (valid) fits for the number

                // Rows
                for (row in 0 until size) {
                    val spots = (0 until size).filter { column ->
                        work[row][column] == null && SudokuCalculationsUtil.isValid(
                            work,
                            row,
                            column,
                            number
                        )
                    }
                    if (spots.size == 1) {
                        work[row][spots.first()] = number
                        progress = true
                    }
                }

                // Columns
                for (column in 0 until size) {
                    val spots = (0 until size).filter { row ->
                        work[row][column] == null && SudokuCalculationsUtil.isValid(
                            work,
                            row,
                            column,
                            number
                        )
                    }
                    if (spots.size == 1) {
                        work[spots.first()][column] = number
                        progress = true
                    }
                }

                // Blocks

                val groups = if (size == 9) 3 else 1

                for (rowGroup in 0 until groups) {
                    for (columnGroup in 0 until groups) {
                        val spots = mutableListOf<Pair<Int, Int>>()
                        for (row in rowGroup * 3 until rowGroup * 3 + 3)
                            for (column in columnGroup * 3 until columnGroup * 3 + 3)
                                if (work[row][column] == null && SudokuCalculationsUtil.isValid(
                                        work,
                                        row,
                                        column,
                                        number
                                    )
                                )
                                    spots += row to column

                        if (spots.size == 1) {
                            val (r, c) = spots.first()
                            work[r][c] = number
                            progress = true
                        }
                    }
                }
            }

            if (!progress) break
            // found a cell, which can not be solved with naked single or hidden single technique
        }

        // work grid is now filled with naked single and hidden single techniques.
        // If there's any empty cell, it cannot be solved with that two techniques.
        return SudokuCalculationsUtil.isComplete(work)
    }

    fun getHint(grid: List<List<Int?>>): Pair<Int, Int> {
        val work = SudokuCalculationsUtil.copyGrid(grid)

        // get empty cells
        // iterate
        TODO()
    }
}
