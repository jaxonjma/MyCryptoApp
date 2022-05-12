package service;

import ch.qos.logback.core.net.SyslogOutputStream;
import domain.Currency;
import domain.Money;
import domain.enums.AvailableCurrency;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyChallengeService {

    private final String STRING_REQUIRED="A string is needed to evaluate";

    /** Counting vowels and consonants: Write a program that counts the number
     *  of vowels and consonants in a given string. Do this for the English language,
     *  which has five vowels (a, e, i, o, and u)*/
    public String countVowelsAndConstants(String test){
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;

        test = unaccent(test).replaceAll("[\\W_]", "");
        int numberOfVowels = test.replaceAll("[^aeiouAEIOU]","").length();
        return String.format("The number of vowels is %d and the number of letters is %d ",numberOfVowels,test.length()-numberOfVowels);
    }

    /**Checking whether a string is a palindrome:
     * Write a program that determines whether the given string is a palindrome or not.*/
    public String isPalindrome(String test) {
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;
        test = unaccent(test.toLowerCase());
        String reverseStr = new StringBuilder(test).reverse().toString();
        if(reverseStr.equals(test))
            return "The given string is a palindrome";
        else
            return "The given string is not a palindrome";
    }

    /** Finding the longest common prefix: Write a program that finds the longest common prefix of given strings.
     * This is a personal implementation cause we can use apache commons: StringUtils.getCommonPrefix(test.split(","));
     */
    public String getCommonPrefix(String test) {
        if(StringUtils.isEmpty(test) || test.split(",").length==0)
            return STRING_REQUIRED;
        String[] t= test.split(",");
        Arrays.sort(t, Comparator.comparingInt(String::length));
        String prefix = t[0];

        for (int i = 1; i < t.length; i++) {
            while (t[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (StringUtils.isEmpty(prefix))
                    return "";
            }
        }
        return StringUtils.isEmpty(prefix)?"There is not common prefix":String.format("The longest common prefix is: %s",prefix);
    }

    /**
     * Removing duplicate characters: Write a program that removes the duplicate characters from the given string.
     */
    public String removeDuplicates(String test) {
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;
        return Arrays.stream(test.split(" ")).distinct().collect(Collectors.joining());
    }

    /**
     * Finding the character with the most appearances: Write a program that finds the character with the most appearances in the given string.
     */
    public String mostAppearances(String test) {
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;
        Character r =  test.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        return String.format("The most appearance character is %s",r);
    }

    /**
     * Checking null references in a functional style and imperative code:
     * Write a program that performs the null checks on the given references
     * in a functional style and imperative code.
     */
    public String checkinNullReferences(String test) {

        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;
        return String.format("The new list without nulls is: %s",
                Arrays.stream(test.split(",")).filter(Objects::nonNull)
                      .collect(Collectors.joining(",")));
    }

    /**
     * Checking null references and throwing a customized NullPointerException error:
     * Write a program that performs the null checks on the given references and
     * throws NullPointerException with custom messages.
     */
    public String checkinNullPointer(String test) {

        AtomicReference<Boolean> containNull = new AtomicReference<>();
        containNull.set(Boolean.FALSE);
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;

        Arrays.stream(test.split(",")).forEach(i->
            {
                try {
                    System.out.println(String.format("Iterating over value %",i.toString()));
                }catch (NullPointerException npe){
                    containNull.set(Boolean.TRUE);
                }
            });
        return Boolean.TRUE.equals(containNull.get())
                ?"There is not null elements":"Null elements found";
    }


    /**
     *  Checking null references and throwing the specified exception
     *  (example, IllegalArgumentException): Write a program that performs
     *  the null checks on the given references and throws the specified exception.
     */
    public String checkinNullGetSpecificException(String test) {

        AtomicReference<String> containNull = new AtomicReference<>();
        if(StringUtils.isEmpty(test))
            return STRING_REQUIRED;

        Arrays.stream(test.split(",")).forEach(i->
        {
            try {
                System.out.println(String.format("Iterating over value %",i.toString()));
            }catch (Exception e){
                containNull.set(e.getClass().getName());
            }
        });
        return StringUtils.isEmpty(containNull.get())
                ?"There is not exception to show":String.format("The current exceptoion is: %s",containNull.get());
    }

    /**
     * Checking null references and returning non-null default references:
     * Write a program that performs the null checks on the given reference,
     * and if it is non-null, then return it; otherwise, return a non-null default reference.
     */
    public String nullCheckAndReturnValueOrDefault(List<String> test, String defaultValue){
        return StringUtils.join(
                    test.stream()
                        .map(i -> Objects.isNull(i) ? defaultValue : i)
                        .collect(Collectors.toList()),",");
    }

    /**
     * equals() and hashCode(): Explain and exemplify how equals() and hashCode() methods work in Java.
     */
    public String equalsAndHashcodeDifference(String a, String b) {

        if(StringUtils.isEmpty(a)||StringUtils.isEmpty(b))
            return STRING_REQUIRED;

        return String.format("a & b are %s equal and their respective hashCodes are %s and %s",a.equals(b)?"":"un -",a.hashCode(),b.hashCode());
    }

    /**
     * Avoiding bad data in immutable objects: Write a program that prevents bad data in immutable objects.
     */
    public String avoidingBadDataInmObjs(String... input){
        List<Money> myMoney = new ArrayList<>();
        List<String> errorItems = new ArrayList<>();
        Arrays.stream(input).distinct().forEach(i->{
            if(Arrays.stream(AvailableCurrency.values()).noneMatch(e-> e.name().equalsIgnoreCase(i))){
                errorItems.add(i);
            }else{
                myMoney.add(new Money(0D,new Currency(getEnumFromString(AvailableCurrency.class, i.toUpperCase()))));
            }
        });

        return String.format("The bad data is %s",StringUtils.join(errorItems,","));
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                System.out.println(String.format("Oops string %s doesn't exist",string));
            }
        }
        return null;
    }

    private String unaccent(String src) {
        return Normalizer.normalize(src, Normalizer.Form.NFD)
                         .replaceAll("[^\\p{ASCII}]", "");
    }
}
