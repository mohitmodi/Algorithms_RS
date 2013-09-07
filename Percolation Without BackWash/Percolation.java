public class Percolation
{
     
     private boolean [] grid = null;
     private WeightedQuickUnionUF wquf = null;
     private int N = 0;
     public Percolation(int size)              // create N-by-N grid, with all sites blocked
     {
         N = size;
         grid = new boolean [N*N + 2];
         grid[0] = true;
         grid[N*N + 1] = true;
         for (int i = 1; i <= N*N; i++)
         {
             grid[i] = false;
         }
         
         wquf = new WeightedQuickUnionUF(N*N + 2);
         /*for (int i = 1; i <= N; i++)
         {
             wquf.union(0,i);
         }
         
         for (int i = N*N; i > N*N - N; i--)
         {
             wquf.union(N*N + 1, i);
         } */  
     }
     
     public void open(int i, int j)         // open site (row i, column j) if it is not already
     {
         if (i < 1 || i > N | j < 1 || j > N)
         {
                throw new java.lang.IndexOutOfBoundsException("Out of Bounds");
         }
         int pos = (i-1)*N + j;
         grid[pos] = true;
         if (pos <= N)
         {
             wquf.union(0, pos);
         }
         if (pos > N*N - N && pos <= N*N)
         {
             wquf.union(N*N+1, pos);
         }
         int up = 0;
         int down = 0;
         int left = 0;
         int right = 0;
         //Check For upper row
         if (i == 1 && i != N)
         {
             down = pos + N;
             if (grid[down])
             {
                 wquf.union(pos, down);
             }
             if (j != 1)
             {
                 left = pos - 1;
                 if (grid[left])
                 {
                     wquf.union(pos, left);
                 }
             }
             if (j != N)
             {
                 right = pos + 1;
                 if (grid[right])
                 {
                     wquf.union(pos, right);
                 }
                 
             }
         }
         
         //Check For bottom row
         if (i == N && i != 1)
         {
             up = pos - N;
             if (grid[up])
             {
                 wquf.union(pos, up);
             }
             if (j != 1)
             {
                 left = pos - 1;
                 if (grid[left])
                 {
                     wquf.union(pos, left);
                 }
             }
             if (j != N)
             {
                 right = pos + 1;
                 if (grid[right])
                 {
                     wquf.union(pos, right);
                 }
                 
             }
         }
          
         if (i != 1 && i != N)
         {
             up = pos - N;
             if (grid[up])
             {
                 wquf.union(pos, up);
             }
             down = pos + N;
             if (grid[down])
             {
                 wquf.union(pos, down);
             }
             if (j != 1)
             {
                 left = pos - 1;
                 if (grid[left])
                 {
                     wquf.union(pos, left);
                 }
             }
             if (j != N)
             {
                 right = pos + 1;
                 if (grid[right])
                 {
                     wquf.union(pos, right);
                 }
                 
             }
         }
     }
     
     public boolean isOpen(int i, int j)    // is site (row i, column j) open?
     {
         if (i < 1 || i > N | j < 1 || j > N)
         {
                throw new java.lang.IndexOutOfBoundsException("Out of Bounds");
         }
         int pos = (i-1)*N + j;
         return grid[pos];
     }
     
      public boolean isFull(int i, int j)    // is site (row i, column j) full?
      {
          if (i < 1 || i > N | j < 1 || j > N)
          {
                throw new java.lang.IndexOutOfBoundsException("Out of Bounds");
          }
          int pos = (i-1)*N + j;
          if (isOpen(i, j))
          {
             return  wquf.connected(0, pos);
          }
          
          return false;
      }
      
      public boolean percolates()            // does the system percolate?
      {
          return wquf.connected(0, N*N + 1);
      }
}
     
     