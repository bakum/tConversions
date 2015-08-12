package ua.bmexp.tconversion;

public class tMatrix implements Cloneable {
    /* ------------------------
       Class variables
     * ------------------------ */

    /** Array for internal storage of elements.
    @serial internal array storage.
     */
    private tNumber[][] A;

    /** Row and column dimensions.
    @serial row dimension.
    @serial column dimension.
     */
    private int m, n;


    /** Construct an m-by-n matrix of zeros.
    @param m    Number of rows.
    @param n    Number of colums.
     */

    public tMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        A = new tNumber[m][n];
    }

    /** Construct an m-by-n constant matrix.
    @param m    Number of rows.
    @param n    Number of colums.
    @param s    Fill the matrix with this scalar value.
     */

    public tMatrix(int m, int n, tNumber s) {
        this.m = m;
        this.n = n;
        A = new tNumber[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = s;
            }
        }
    }

    /** Construct a matrix from a 2-D array.
    @param A    Two-dimensional array of doubles.
    @exception  IllegalArgumentException All rows must have the same length
    @see        #constructWithCopy
     */

    public tMatrix(tNumber[][] A) {
        m = A.length;
        n = A[0].length;
        for (int i = 0; i < m; i++) {
            if (A[i].length != n) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }
        this.A = A;
    }

    /** Construct a matrix quickly without checking arguments.
    @param A    Two-dimensional array of doubles.
    @param m    Number of rows.
    @param n    Number of colums.
     */

    public tMatrix(tNumber[][] A, int m, int n) {
        this.A = A;
        this.m = m;
        this.n = n;
    }

    /* ------------------------
       Public Methods
     * ------------------------ */

    /** Construct a matrix from a copy of a 2-D array.
       @param A    Two-dimensional array of doubles.
       @exception  IllegalArgumentException All rows must have the same length
     */

    public static tMatrix constructWithCopy(tNumber[][] A) {
        int m = A.length;
        int n = A[0].length;
        tMatrix X = new tMatrix(m, n);
        tNumber[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            if (A[i].length != n) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return X;
    }

    /** Make a deep copy of a matrix
     */

    public tMatrix copy() {
        tMatrix X = new tMatrix(m, n);
        tNumber[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return X;
    }

    /** Clone the Matrix object.
     */

    public Object clone() {
        return this.copy();
    }

    /** Access the internal two-dimensional array.
    @return     Pointer to the two-dimensional array of matrix elements.
     */

    public tNumber[][] getArray() {
        return A;
    }

    /** Copy the internal two-dimensional array.
    @return     Two-dimensional array copy of matrix elements.
     */

    public tNumber[][] getArrayCopy() {
        tNumber[][] C = new tNumber[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j];
            }
        }
        return C;
    }

    /** Get row dimension.
    @return     m, the number of rows.
     */

    public int getRowDimension() {
        return m;
    }

    /** Get column dimension.
    @return     n, the number of columns.
     */

    public int getColumnDimension() {
        return n;
    }

    /** Get a single element.
    @param i    Row index.
    @param j    Column index.
    @return     A(i,j)
    @exception  ArrayIndexOutOfBoundsException
     */

    public tNumber get(int i, int j) {
        return A[i][j];
    }

    /** Get a submatrix.
    @param i0   Initial row index
    @param i1   Final row index
    @param j0   Initial column index
    @param j1   Final column index
    @return     A(i0:i1,j0:j1)
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public tMatrix getMatrix(int i0, int i1, int j0, int j1) {
        tMatrix X = new tMatrix(i1 - i0 + 1, j1 - j0 + 1);
        tNumber[][] B = X.getArray();
        try {
            for (int i = i0; i <= i1; i++) {
                for (int j = j0; j <= j1; j++) {
                    B[i - i0][j - j0] = A[i][j];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
        return X;
    }


    /** Get a submatrix.
    @param r    Array of row indices.
    @param c    Array of column indices.
    @return     A(r(:),c(:))
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public tMatrix getMatrix(int[] r, int[] c) {
        tMatrix X = new tMatrix(r.length, c.length);
        tNumber[][] B = X.getArray();
        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    B[i][j] = A[r[i]][c[j]];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
        return X;
    }

    /** Get a submatrix.
    @param i0   Initial row index
    @param i1   Final row index
    @param c    Array of column indices.
    @return     A(i0:i1,c(:))
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public tMatrix getMatrix(int i0, int i1, int[] c) {
        tMatrix X = new tMatrix(i1 - i0 + 1, c.length);
        tNumber[][] B = X.getArray();
        try {
            for (int i = i0; i <= i1; i++) {
                for (int j = 0; j < c.length; j++) {
                    B[i - i0][j] = A[i][c[j]];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
        return X;
    }

    /** Get a submatrix.
    @param r    Array of row indices.
    @param j0   Initial column index
    @param j1   Final column index
    @return     A(r(:),j0:j1)
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public tMatrix getMatrix(int[] r, int j0, int j1) {
        tMatrix X = new tMatrix(r.length, j1 - j0 + 1);
        tNumber[][] B = X.getArray();
        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = j0; j <= j1; j++) {
                    B[i][j - j0] = A[r[i]][j];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
        return X;
    }

    /** Set a single element.
    @param i    Row index.
    @param j    Column index.
    @param s    A(i,j).
    @exception  ArrayIndexOutOfBoundsException
     */

    public void set(int i, int j, tNumber s) {
        A[i][j] = s;
    }

    /** Set a submatrix.
    @param i0   Initial row index
    @param i1   Final row index
    @param j0   Initial column index
    @param j1   Final column index
    @param X    A(i0:i1,j0:j1)
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public void setMatrix(int i0, int i1, int j0, int j1, tMatrix X) {
        try {
            for (int i = i0; i <= i1; i++) {
                for (int j = j0; j <= j1; j++) {
                    A[i][j] = X.get(i - i0, j - j0);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
    }

    /** Set a submatrix.
    @param r    Array of row indices.
    @param c    Array of column indices.
    @param X    A(r(:),c(:))
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public void setMatrix(int[] r, int[] c, tMatrix X) {
        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    A[r[i]][c[j]] = X.get(i, j);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
    }

    /** Set a submatrix.
    @param r    Array of row indices.
    @param j0   Initial column index
    @param j1   Final column index
    @param X    A(r(:),j0:j1)
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public void setMatrix(int[] r, int j0, int j1, tMatrix X) {
        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = j0; j <= j1; j++) {
                    A[r[i]][j] = X.get(i, j - j0);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
    }

    /** Set a submatrix.
    @param i0   Initial row index
    @param i1   Final row index
    @param c    Array of column indices.
    @param X    A(i0:i1,c(:))
    @exception  ArrayIndexOutOfBoundsException Submatrix indices
     */

    public void setMatrix(int i0, int i1, int[] c, tMatrix X) {
        try {
            for (int i = i0; i <= i1; i++) {
                for (int j = 0; j < c.length; j++) {
                    A[i][c[j]] = X.get(i - i0, j);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
    }

    /** Matrix transpose.
    @return    A'
     */

    public tMatrix transpose() {
        tMatrix X = new tMatrix(n, m);
        tNumber[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[j][i] = A[i][j];
            }
        }
        return X;
    }

    /**  Unary minus
    @return    -A
     */

    public tMatrix uminus() {
        tMatrix X = new tMatrix(m, n);
        tNumber[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j].mult(-1);
            }
        }
        return X;
    }

    /** C = A + B
    @param B    another matrix
    @return     A + B
     */

    public tMatrix plus(tMatrix B) {
        checkMatrixDimensions(B);
        tMatrix X = new tMatrix(m, n);
        tNumber[][] C = X.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j].add(B.A[i][j]);
            }
        }
        return X;
    }
    
    /** A = A + B
    @param B    another matrix
    @return     A + B
    */

    public tMatrix plusEquals (tMatrix B) {
       checkMatrixDimensions(B);
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = A[i][j].add(B.A[i][j]);
          }
       }
       return this;
    }
    
    /** C = A - B
    @param B    another matrix
    @return     A - B
    */

    public tMatrix minus (tMatrix B) {
       checkMatrixDimensions(B);
       tMatrix X = new tMatrix(m,n);
       tNumber[][] C = X.getArray();
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             C[i][j] = A[i][j].sub(B.A[i][j]);
          }
       }
       return X;
    }
    
    /** A = A - B
    @param B    another matrix
    @return     A - B
    */

    public tMatrix minusEquals (tMatrix B) {
       checkMatrixDimensions(B);
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = A[i][j].sub(B.A[i][j]);
          }
       }
       return this;
    }
    
    /** Element-by-element multiplication, C = A.*B
    @param B    another matrix
    @return     A.*B
    */

    public tMatrix arrayTimes (tMatrix B) {
       checkMatrixDimensions(B);
       tMatrix X = new tMatrix(m,n);
       tNumber[][] C = X.getArray();
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             C[i][j] = A[i][j].mult(B.A[i][j]);
          }
       }
       return X;
    }
    
    /** Element-by-element multiplication in place, A = A.*B
    @param B    another matrix
    @return     A.*B
    */

    public tMatrix arrayTimesEquals (tMatrix B) {
       checkMatrixDimensions(B);
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = A[i][j].mult(B.A[i][j]);
          }
       }
       return this;
    }
    
    /** Element-by-element right division, C = A./B
    @param B    another matrix
    @return     A./B
    */

    public tMatrix arrayRightDivide (tMatrix B) throws Exception {
       checkMatrixDimensions(B);
       tMatrix X = new tMatrix(m,n);
       tNumber[][] C = X.getArray();
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             C[i][j] = A[i][j].div(B.A[i][j]);
          }
       }
       return X;
    }

    /** Element-by-element right division in place, A = A./B
    @param B    another matrix
    @return     A./B
    */

    public tMatrix arrayRightDivideEquals (tMatrix B) throws Exception {
       checkMatrixDimensions(B);
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = A[i][j].div(B.A[i][j]);
          }
       }
       return this;
    }
    
    /** Element-by-element left division, C = A.\B
    @param B    another matrix
    @return     A.\B
    */

    public tMatrix arrayLeftDivide (tMatrix B) throws Exception {
       checkMatrixDimensions(B);
       tMatrix X = new tMatrix(m,n);
       tNumber[][] C = X.getArray();
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             C[i][j] = B.A[i][j].div(A[i][j]);
          }
       }
       return X;
    }

    /** Element-by-element left division in place, A = A.\B
    @param B    another matrix
    @return     A.\B
    */

    public tMatrix arrayLeftDivideEquals (tMatrix B) throws Exception {
       checkMatrixDimensions(B);
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = B.A[i][j].div(A[i][j]);
          }
       }
       return this;
    }
    
    /** Multiply a matrix by a scalar, C = s*A
    @param s    scalar
    @return     s*A
    */

    public tMatrix times (double s) {
       tMatrix X = new tMatrix(m,n);
       tNumber[][] C = X.getArray();
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             C[i][j] = A[i][j].mult(s);
          }
       }
       return X;
    }

    /** Multiply a matrix by a scalar in place, A = s*A
    @param s    scalar
    @return     replace A by s*A
    */

    public tMatrix timesEquals (double s) {
       for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
             A[i][j] = A[i][j].mult(s);
          }
       }
       return this;
    }
    
    /** Linear algebraic matrix multiplication, A * B
    @param B    another matrix
    @return     Matrix product, A * B
    @exception  IllegalArgumentException Matrix inner dimensions must agree.
    */

    public tMatrix times (tMatrix B) {
       if (B.m != n) {
          throw new IllegalArgumentException("Matrix inner dimensions must agree.");
       }
       tMatrix X = new tMatrix(m,B.n);
       tNumber[][] C = X.getArray();
       tNumber[] Bcolj = new tNumber[n];
       for (int j = 0; j < B.n; j++) {
          for (int k = 0; k < n; k++) {
             Bcolj[k] = B.A[k][j];
          }
          for (int i = 0; i < m; i++) {
             tNumber[] Arowi = A[i];
             tNumber s = new tNumber();
             for (int k = 0; k < n; k++) {
                s = s.add(Arowi[k].mult(Bcolj[k]));
             }
             C[i][j] = s;
          }
       }
       return X;
    }

    /* ------------------------
       Private Methods
     * ------------------------ */

    /** Check if size(A) == size(B) **/

    private void checkMatrixDimensions(tMatrix B) {
        if (B.m != m || B.n != n) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }
    }

}
