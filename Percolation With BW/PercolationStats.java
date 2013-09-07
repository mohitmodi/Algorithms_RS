public class PercolationStats
{
    
    private double [] threshold = null; 
    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    {
        if(N <= 0 || T <= 0)
        {
           throw new java.lang.IllegalArgumentException("Provide N>0 and T>0");
        }
        else
        {
            threshold = new double[T];
            for (int k = 0; k < T; k++)
            {
                Percolation percolate = new Percolation(N);
                double size = N*N;
                int count = 0;
                while(!percolate.percolates()){
                   int i = StdRandom.uniform(1, N+1);
                   int j = StdRandom.uniform(1, N+1);
                   if(i < 1 || i > N | j < 1 || j > N)
                   {
                       throw new java.lang.IndexOutOfBoundsException("Out of Bounds");
                   }
                   else
                   {
                       if(!percolate.isOpen(i,j) && !percolate.percolates())
                       {
                           percolate.open(i,j);
                           count++;
                       }
                       if(percolate.percolates())
                       {
                           threshold[k] = count/size;
                       }
                   } 
                }
                
            }
            
        }
    }
    
    public double mean()                     // sample mean of percolation threshold
    {
        return StdStats.mean(threshold);
    }
    
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(threshold);
    }
    
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        double mn = mean();
        double sd = stddev();
        return (mn - ((1.96 * sd)/Math.sqrt(threshold.length)));
    }
    
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        double mn = mean();
        double sd = stddev();
        return (mn + ((1.96 * sd)/Math.sqrt(threshold.length)));
    }
    
    public static void main(String[] args)   // test client, described below
    {
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        
        PercolationStats stat = new PercolationStats(a,b);
        StdOut.println("mean = " + stat.mean());
        StdOut.println("std dev = " + stat.stddev());
        StdOut.println("95% confidence interval = " + stat.confidenceLo() + " " + stat.confidenceHi()); 
    }
}
