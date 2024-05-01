import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException {
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }

    public static String calc(String input) throws ScannerException {
        String[] s = input.split(" ");
        boolean bRoman;
        int a, b, res;
        if (s.length == 3) {
            try {
                a = Integer.parseInt(s[0]);
                b = Integer.parseInt(s[2]);
                bRoman = false;
            } catch (NumberFormatException e) {
                try {
                    a = convertRomanToArabian(s[0]);
                    b = convertRomanToArabian(s[2]);
                    bRoman = true;
                } catch (IllegalArgumentException _) {
                    throw new ScannerException("throws Exception");
                }
            }

        } else throw new ScannerException("throws Exception");

        if (((a + b) <= 20) && ((b + a) >= 2)) {
            res = switch (s[1]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "/" -> a / b;
                case "*" -> a * b;
                default -> throw new ScannerException("throws Exception");
            };
        } else throw new ScannerException("throws Exception");

        if (bRoman) {
            if (res > 0) {
                return convertArabianToRoman(res);
            } else throw new ScannerException("throws Exception");
        } else {
            return String.valueOf(res);
        }
    }


    static String convertArabianToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        Roman[] romanEnum = Roman.values();

        while (num != 0) {
            for (int i = 0; romanEnum.length > i; i += 2) {

                int count = 0;
                while (num >= romanEnum[i].getArabic()) {
                    count++;
                    num -= romanEnum[i].getArabic();
                }

                switch (count) {
                    case 4:
                        roman.append(romanEnum[i].name());
                        roman.append(Roman.values()[romanEnum[i].ordinal() - 1].name());
                        break;
                    case 5:
                        roman.append(Roman.values()[romanEnum[i].ordinal() - 1].name());
                        break;
                    case 9:
                        roman.append(Roman.values()[romanEnum[i].ordinal()].name());
                        roman.append(Roman.values()[romanEnum[i].ordinal() - 2].name());
                        break;
                    default:
                        roman.append(romanEnum[i].name().repeat(Math.max(0, count)));
                }
            }
        }

        return roman.toString();
    }

    static int convertRomanToArabian(String roman) {
        int arabic = 0;

        String[] romanArray = roman.split("");
        Roman[] romanEnum = Roman.values();

        for (int i = 0; i < romanArray.length-1; i++) {
            int current = Roman.valueOf(romanArray[i]).ordinal();
            int prev = Roman.valueOf(romanArray[i+1]).ordinal();
            if (current > prev) {
                arabic += romanEnum[prev].getArabic() - romanEnum[current].getArabic();
                roman = roman.replace(romanArray[i] + romanArray[i+1], "");
            }
        }

        if (!roman.isEmpty()) {
            for (String num : roman.split("")){
                arabic += Roman.valueOf(num).getArabic();
            }
        }

        return arabic;
    }
}