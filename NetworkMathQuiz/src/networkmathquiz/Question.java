/*
    Developed by Kaue Macruz
    Version 1.0
 */
package networkmathquiz;

public class Question implements Comparable<Question>
{
    // Data properties using get() and set() methods
    public String Lop;
    public String op;
    public String Rop;
    public String equal;
    public String Ans;
    

    // parameterised constructor method which initialises data properties
    public Question(String Lop,String op, String Rop, String equal, String Ans)
    {
        this.Lop = Lop;
        this.op = op;
        this.Rop = Rop;
        this.equal = equal;
        this.Ans = Ans;
    }

    // customised ToString() method used to override the Object class ToString() method
    @Override
    public String toString()
    {
        return "Question object - Question: " + Lop + " " + op + " " + Rop + " " + equal + " " + Ans;
    }
      public int compareTo (Question anotherQuestion)
    {
        if (this.Ans == anotherQuestion.Ans)
        {
            return 0;
        }
        else if (Integer.parseInt(this.Ans) < Integer.parseInt(anotherQuestion.Ans))
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
