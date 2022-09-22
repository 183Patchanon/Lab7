import java.util.Arrays;
public class Lab_MatrixMul 
{
    public static void main(String[] args) //64050183
    {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length; //row
        int matC_c = matB.data[0].length;   //col
        MyData matC = new MyData(matC_r, matC_c);
        // Q4 construct 2D array for each "thread" with respected to each cell in matC, then start each thread
        Thread t1 = new Thread(new MatrixMulThread(matC_r, matC_c, matA, matB, matC));
        t1.start();
        // Q5 join each thread
        try
        {
            t1.join();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        matC.show();
    }
}

class MatrixMulThread implements Runnable 
{
    int processing_row; int processing_col;
    MyData datA; MyData datB; MyData datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) 
    {
    // Q1 code here
        processing_row = tRow;
        processing_col = tCol;
        datA = a;
        datB = b;
        datC = c;
    }
    public void run() /* Q2 */
    {
    // Q3
        for(int i = 0; i < datC.data.length; i++) //reset ans[][] to 0
        {
            for(int j = 0; j < datC.data[0].length; j++)
            {
                datC.data[i][j] = 0;
            } 
        }    

        for(int i = 0; i < datA.data.length; i++)
        {
            for(int j = 0; j < datB.data[0].length; j++)
            {
                for(int k = 0; k < datB.data.length; k++)
                {
                    datC.data[i][j] += datA.data[i][k] * datB.data[k][j];
                }
            } 
        }
        //System.out.println("perform sum of multiplication of assigned row and col");
    }
} //class

class MyData 
{
    int[][] data;
    MyData(int[][] m) 
    { 
        data = m; 
    }

    MyData(int r, int c) 
    {
        data = new int[r][c];
        for(int[] aRow : data)
        {
            Arrays.fill(aRow, 9);
        }
// 9 will be overwritten anyway
    }

    void show() 
    {
        System.out.println(Arrays.deepToString(data));
    }
} //class