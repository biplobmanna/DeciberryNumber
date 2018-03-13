import java.io.*;
import java.lang.Math;
public class deciberryNumber{
    public static void main(String []args){
        int N=37; //Change this to change the input
        findDeciberryNumber DN = new findDeciberryNumber();
        DN.find(N);
    }
}
public class findDeciberryNumber{
    private int[] quotientArray;
    private final int MAX_DIGIT = 9;
    private final int MAX_DIGIT_EVEN = 8;
    public findDeciberryNumber(){//constructor to initialise array size
        //For now size of array is as per fixed size
        //This can be later on changed as per the input
        //Currently array size =5, Max input value van be 279
        quotientArray = new int[5];
    }
    private void push(int val,int index){//push the quotient into the array which behaves as a stack at this point
        quotientArray[index]=val;
    }
    private void displayQuotientArray(){//method to display Quotient Array for visualisation purposes
        for(int xi : quotientArray) 
            System.out.print(xi+", ");
    }
    private int resultFromQuotientArray(){//method to calculate the value using the quotient from the array
        //Calculating quotient using formula:
        // x[i]*pow(2,i) for i={0,1,....n}
        int result=0;
        for(int i=0;i<quotientArray.length;i++)
            result+=quotientArray[i]*Math.pow(2,i);
        return result;
    }
    private int formDeciberryNumber(){//Form the Deciberry number using the quotient
        //Form the deciberry number using the array values
        int dn=0;
        for(int i=quotientArray.length-1;i>=0;i--)
            dn=dn*10+quotientArray[i];
        return dn;
    }
    private int findQuotientSuchThatResultNearestToN(int N,int i){//find the quotient when divided by pow(2,i) such that result nearest to N
        //when i=0, if the number is odd, max digit can be 9
        // if number is even, max digit can be 8
        if(i==0){
            if(N%2==0 && N>=MAX_DIGIT_EVEN){
                return MAX_DIGIT_EVEN;
            }
            else if(N>=MAX_DIGIT){
                return MAX_DIGIT;
            }
            else
                return N;
        }
        int divisor = (int)Math.pow(2,i);
        int quotient = (int)N/divisor;
        if(quotient>=MAX_DIGIT)
            return MAX_DIGIT;
        else if(quotient*divisor<N)
            //This will result in the result of quotient array being more than N
            //The correction to this is done via backtracking
            return quotient+1;
        else
            return quotient;
    }
    private int findQuotients(int N,int i){//Find quotients for each pow(2,i)
        int divisor = (int)Math.pow(2,i);
        int xi = findQuotientSuchThatResultNearestToN(N,i);
        if(xi*divisor<N){
            push(xi,i);
            N-=xi*divisor;
            return findQuotients(N,++i);
        }
        else
            push(xi,i);
        return i;
    }
    private void backTrackQuotientArrayForCorrection(int N,int i){//correcting the quotient array so that result from the quotient array matches N
        i--;//reducing the index by 1
        int resultFromQuotient = resultFromQuotientArray();
        int twoPower_i = (int)Math.pow(2,i);
        int difference = resultFromQuotient-N;
        if(difference==0)
            return;
        else if(difference>=twoPower_i)
            quotientArray[i]-=difference/twoPower_i;
        backTrackQuotientArrayForCorrection(N,i);
    }
    public void find(int N){//Function to be called for calculation of DN
        int i = findQuotients(N,0);
        backTrackQuotientArrayForCorrection(N,i);
        System.out.println("\nDN("+N+")="+formDeciberryNumber());
    }
}
