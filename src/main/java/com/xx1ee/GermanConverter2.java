package com.xx1ee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GermanConverter2 {
    private int result;
    private int iMass = 0;
    private boolean mod = false;
    private String errorStr = "";
    public String itog;
    public String vvod;
    private GermanNumbers germanNumbers = new GermanNumbers();
    private String[] massWord;
    private List<Numbers> numArray = new ArrayList<>();

    enum NumberTypes
    {
        Unit,
        Tens,
        Eleven_Nineteen,
        Hundrert,
        Und
    }



    class Numbers
    {
        public NumberTypes type;
        public int value;

        public Numbers(NumberTypes type, int value)
        {
            this.type = type;
            this.value = value;
        }
    }
    public GermanConverter2(String vvod) {
        this.vvod = vvod;
        ApplyButton_Click();
    }

    public String ApplyButton_Click()
    {
        massWord = SplitStringIntoWords(vvod);

        if (massWord.length == 1 && massWord[0] == "null") itog = "Ваше число: " + result;
        else if (CheckForUnit(massWord[iMass]))
        {
            for(var num : numArray)
            {
                if (num.type != NumberTypes.Hundrert) result += num.value;
                else result = result * 100;
            }
            if (CheckError2())
            {
                itog = "Ваше число: " + result;
            }
            else
            {
                itog = errorStr;
            }

        }
        else
        {
            if (CheckError2())
            {
                itog = "Ошибка! " + CheckError();
            }
        }
        RerunProgramm();
        return itog;
    }

    private String[] SplitStringIntoWords(String inputString)
    {
        return massWord = inputString.trim().split("\\s+");
    }

    private boolean CheckForUnit(String myNumber)
    {
        for (var number : germanNumbers.unit.entrySet())
        {
            if (myNumber.equals( number.getKey()))
            {
                Numbers num = new Numbers(NumberTypes.Unit, number.getValue());
                numArray.add(num);

                if (!ArrayIsOver())
                {
                    iMass++;
                    return CheckForHelpWord(massWord[iMass]);
                }
                else return true;
            }
        }
        return (CheckForElevenNineteen(massWord[iMass]) || CheckForTens(massWord[iMass]));
    }

    private boolean CheckForHelpWord(String myNumber)
    {
        if (myNumber.equals("hundert") && !mod)
        {
            Numbers num = new Numbers(NumberTypes.Hundrert, 100);
            numArray.add(num);

            if (!ArrayIsOver())
            {
                iMass++;
                mod = true;
                return ((CheckForUnit(massWord[iMass])) || (CheckForTens(massWord[iMass])) || (CheckForElevenNineteen(massWord[iMass]) && (massWord[iMass - 1] != "und")));
            }
            else return true;
        }
        else if (myNumber.equals("und"))
        {
            Numbers num = new Numbers(NumberTypes.Und, 0);
            numArray.add(num);

            if (!ArrayIsOver())
            {
                iMass++;
                return CheckForTens(massWord[iMass]);
            }
            else return false;
        }
        else return false;
    }


    private boolean CheckForTens(String myNumber)
    {
        for (var number : germanNumbers.tens.entrySet())
        {
            if (myNumber.equals(number.getKey()))
            {
                Numbers num = new Numbers(NumberTypes.Tens, number.getValue());
                numArray.add(num);

                return ArrayIsOver();
            }
        }
        return false;
    }

    private boolean CheckForElevenNineteen(String myNumber)
    {
        for (var number : germanNumbers.eleven_nineteen.entrySet())
        {
            if (myNumber.equals(number.getKey()))
            {
                Numbers num = new Numbers(NumberTypes.Eleven_Nineteen, number.getValue());
                numArray.add(num);

                return ArrayIsOver();
            }
        }
        return false;
    }

    private boolean ArrayIsOver()
    {
        if (iMass == massWord.length - 1) return true;
        else
        {
            return false;
        }
    }
    private boolean CheckError2()
    {
        try
        {
            if (numArray.get(0).type == NumberTypes.Eleven_Nineteen || numArray.get(0).type == NumberTypes.Tens)
            {
                for (var number : germanNumbers.tens.entrySet())
                {
                    if (massWord[1].equals(number.getKey()))
                    {
                        Numbers num = new Numbers(NumberTypes.Tens, number.getValue());
                        numArray.add(num);
                    }
                }
                for (var number : germanNumbers.eleven_nineteen.entrySet())
                {
                    if (massWord[1].equals(number.getKey()))
                    {
                        Numbers num = new Numbers(NumberTypes.Eleven_Nineteen, number.getValue());
                        numArray.add(num);
                    }
                }

                itog = "Ошибка! " + Translator(numArray.get(1).type) + " не может идти после " + Translator(numArray.get(0).type) + "!";
                return false;
            }
            else if (numArray.get(0).type == NumberTypes.Unit && numArray.get(1).type == NumberTypes.Hundrert && (numArray.get(2).type == NumberTypes.Eleven_Nineteen || numArray.get(2).type == NumberTypes.Tens) && massWord.length == 4)
            {
                itog= "Ошибка! После " + Translator(numArray.get(2).type) + " не может ничего идти!";
                return false;
            }
        } catch (Exception e) {}

        try
        {
            if (massWord[0].equals("und") || massWord[0].equals("hundert"))
            {
                itog = "Ошибка! " + massWord[0] + " не может идти первым!";
                return false;
            }
            //else if ()
            else if (numArray.get(0).type == NumberTypes.Eleven_Nineteen && (Objects.equals(massWord[1], "und") || massWord[1] == "hundert"))
            {
                itog = "Ошибка! " + massWord[1] + " не может идти после  11-19!";
                return false;
            }
            else if (numArray.get(0).type == NumberTypes.Tens && (Objects.equals(massWord[1], "und") || Objects.equals(massWord[1], "hundert")))
            {
                itog = "Ошибка!" + massWord[1] + " не может идти после  десяток!";
                return false;
            }

            else if (numArray.get(0).type == NumberTypes.Unit && numArray.get(1).type == NumberTypes.Hundrert && Objects.equals(massWord[2], "und"))
            {
                itog = "Ошибка! Und не может идти после  сотен!";
                return false;
            }
            else if (numArray.get(0).type == NumberTypes.Unit && numArray.get(1).type == NumberTypes.Hundrert && numArray.get(2).type == NumberTypes.Unit && (numArray.get(3).type == NumberTypes.Unit || numArray.get(3).type == NumberTypes.Tens || numArray.get(3).type == NumberTypes.Eleven_Nineteen))
            {
                errorStr = "Ошибка! " + Translator(numArray.get(3).type) + " не могут идти после единиц!";
                return false;
            }
            else return true;
        }
        catch (Exception ignored) {}
        return true;
    }
    private String CheckError()
    {
        if (Objects.equals(massWord[iMass], "null")) return "Ноль не может ипользован!";
        try
        {
            if (Objects.equals(massWord[iMass], "und") && iMass == massWord.length - 1 && !Objects.equals(massWord[iMass - 1], "und")) return "После und ничего нет!";
            else if (Objects.equals(massWord[iMass], "und")) return "Und не может идти после " + Translator(numArray.get(numArray.size() - 1).type) + "!";
        }
        catch (Exception e) {}
        try
        {
            if (Objects.equals(massWord[iMass], "hundert")) return "Сотни не могут идти после " + Translator(numArray.get(numArray.size() - 1).type) + "!";
        }
        catch (Exception e) {}

        for (var number : germanNumbers.unit.entrySet())
        {
            if (Objects.equals(massWord[iMass], number.getKey()))
            {
                return "Единицы не могут идти после " + Translator(numArray.get(numArray.size() - 1).type) + "!";
            }
        }
        for (var number : germanNumbers.tens.entrySet())
        {
            if (Objects.equals(massWord[iMass], number.getKey()))
            {
                return "Десятки не могут идти после " + Translator(numArray.get(numArray.size() - 1).type) + "!";
            }
        }
        for (var number : germanNumbers.eleven_nineteen.entrySet())
        {
            if (Objects.equals(massWord[iMass], number.getKey()))
            {
                return "11-19 не могут идти после " + Translator(numArray.get(numArray.size() - 1).type) + "!";
            }
        }
        return "Неправильное написание числа " + massWord[iMass];
    }


    private String Translator(NumberTypes numberTypes)
    {
        if (numberTypes == NumberTypes.Eleven_Nineteen) return "11-19";
        if (numberTypes == NumberTypes.Unit) return "Единицы";
        if (numberTypes == NumberTypes.Tens) return "Десятки";
        if (numberTypes == NumberTypes.Hundrert) return "Сотни";
        return "Und";
    }
    private void RerunProgramm()
    {
        iMass = 0;
        result = 0;
        mod = false;
        numArray.clear();
    }

}

