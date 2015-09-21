import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FigureInWords
{
    public static String  units(int u)
    {
        String[] str1 =
        {
            "","one","two","three","four","five","six","seven",
            "eight","nine",
            "ten","eleven","twelve","thirteen","forteen","fifteen","sixteen",
            "seventeen",
            "eighteen","ninteen"
        }
        ;
        return str1[u];
    }
    public static String tenth(int v)
    {
        String[] str2 =
        {
            "","","twenty","thirty","forty","fifty","sixty",
            "seventy","eighty","ninety"
        }
        ;
        if(v%10==0)
        {
            //round str2
            if(v%9==0)
            {
                System.out.print(str2[9]);
            }
            else
            {
                return str2[v%9];
            }
        }
        else
        {
            // handles str2 with str1
            List<Integer> separation = new ArrayList<>();
            while (v > 0)
            {
                separation.add(v%10);
                v/=10;
            }
            int a= separation.get(0);
            int b=separation.get(1);
            return str2[b]+"-"+units(a);
        }
        return "";
    }
    public static String hunth(int w)
    {
        if(w<100 && w>19)
        {
            return tenth(w);
        }
        else if (w<20 && w>0)
        {
            return units(w);
        }
        else
        {
            List<Integer> separation = new ArrayList<>();
            int c= w;
            while (c > 0)
            {
                separation.add(c%10);
                c/=10;
            }
            int a= separation.get(2);
            int b=w-(a*100);
            try
            {
                String tend=units(b);
                return units(a)+" hundred and "+tend;
            }
            catch (Exception e)
            {
                String tend=tenth(b);
                return units(a)+" hundred and "+tend;
            }
        }
    }
    public static String frontile(int front,String keyword)
    {
        String frontstring=Integer.toString(front);
        int thlen=frontstring.length();
        if(thlen==1)
        {
            String finalfront=units(front);
            return finalfront+keyword;
        }
        if(thlen==2)
        {
            if(front <20)
            {
                String finalfront=units(front);
                return finalfront+keyword;
            }
            else
            {
                String finalfront=tenth(front);
                return finalfront+keyword;
            }
        }
        if(thlen==3)
        {
            String finalfront=hunth(front);
            return finalfront+keyword;
        }
        return "";
    }
    public static String thousanth(int x)
    {
        int back=x%1000;
        int front=x/1000;
        String  backer=hunth(back);
        String finalfront=frontile(front," thousand ");
        // String finalbacker=frontile(back);
        //System.out.println();
        return finalfront+backer;
    }
    public static String millionth(int y)
    {
        int back=y%1000000;
        int front=y/1000000;
        String  backer=thousanth(back);
        String finalfront=frontile(front," million ");
        // String finalbacker=frontile(back);
        //System.out.println();
        return finalfront+backer;
    }
    public static  String word(String inputString)
    {
        int inputInt =Integer.parseInt(inputString) ;
        //creating the int version of input
        int length=inputString.length();
        if (inputInt>0  && inputInt<20)
        {
            //used to convert ones to words
            return units(inputInt);
        }
        if(length==2 && inputInt> 19 && inputInt< 100 )
        {
            //used to convert tens to words
            return tenth(inputInt);
        }
        if(inputInt>99 && inputInt<1000)
        {
            return hunth(inputInt);
        }
        if(length>3 && length<7 )
        {
            return thousanth(inputInt);
        }
        if(inputInt>999999 && inputInt<1000000000)
        {
            return millionth(inputInt);
        }
        return "";
    }
    public static void main(String[] args)
    {
        //main
        Scanner in = new Scanner(System.in);
        int check = 0;
        while(check !=1)
        {
            System.out.print("Enter a number to convert to words (or 'done' to quit):");
            String s = in.nextLine();
            switch (s)
            {
                case "done": System.out.println("Thank you for using our service.");
                check = 1;
                break;
                default :
                try
                {
                    int c=Integer.parseInt(s);
                    if(Math.signum(c)==-1.0)
                    {
                        c=Math.abs(c);
                        String so=Integer.toString(c);
                        System.out.println(" Negative "+word(so)) ;
                    }
                    else
                    {
                        System.out.println(word(s)) ;
                    }
                }
                catch (Exception e)
                {
                    System.out.println("You entered a wrong value.");
                }
            }
        }
    }
}