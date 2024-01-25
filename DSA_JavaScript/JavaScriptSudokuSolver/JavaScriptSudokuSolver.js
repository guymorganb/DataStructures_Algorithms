const puzzle = [
  [0, 0, 0, 0, 0, 0, 2, 0, 0],
  [0, 8, 0, 0, 0, 7, 0, 9, 0],
  [6, 0, 2, 0, 0, 0, 5, 0, 0],
  [0, 7, 0, 0, 6, 0, 0, 0, 0],
  [0, 0, 0, 9, 0, 1, 0, 0, 0],
  [0, 0, 0, 0, 2, 0, 0, 4, 0],
  [0, 0, 5, 0, 0, 0, 6, 0, 3],
  [0, 9, 0, 4, 0, 0, 0, 7, 0],
  [0, 0, 6, 0, 0, 0, 0, 0, 0],
];

if (solveSudoku(puzzle)) {
  console.log(puzzle);
} else {
  console.log("No solution found")
}







const solveSudoku = (board) => {
  const emptyCell = findEmptyCell(board);
  
  if (emptyCell === null) {
    // All cells are filled, puzzle is solved
    return true;
  }
  
  const [row, col] = emptyCell;
  
  for (let num = 1; num <= 9; num++) {
    if (isValidMove(board, row, col, num)) {
      board[row][col] = num;
      
      if (solveSudoku(board)) {
        return true;
      }
      
      board[row][col] = 0;
    }
  }
  
  return false;
}

const findEmptyCell = (board) => {
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      if (board[row][col] === 0) {
        return [row, col];
      }
    }
  }
  
  return null;
}

const isValidMove = (board, row, col, num) => {
  // Check row
  for (let i = 0; i < 9; i++) {
    if (board[row][i] === num) {
      return false;
    }
  }
  
  // Check column
  for (let i = 0; i < 9; i++) {
    if (board[i][col] === num) {
      return false;
    }
  }
  
  // Check 3x3 box
  const boxRow = Math.floor(row / 3) * 3;
  const boxCol = Math.floor(col / 3) * 3;
  
  for (let i = boxRow; i < boxRow + 3; i++) {
    for (let j = boxCol; j < boxCol + 3; j++) {
      if (board[i][j] === num) {
        return false;
      }
    }
  }
  
  return true;
}
