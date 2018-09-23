package com.wrdao.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckIdCard {
    private String cardNumber;
    private Boolean cacheValidateResult;
    private Date cacheBirthDate = null;
    private final static String BIRTH_DATE_FORMAT = "yyyyMMdd";
    private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);
    private final static int NEW_CARD_NUMBER_LENGTH = 18;
    private final static int OLD_CARD_NUMBER_LENGTH = 15;
    private final static int ADULT_AGE = 18;
    private final static char[] VERIFY_CODE = {'1','0','X','9','8','7','6','5','4','3','2'};
    private final static int[] VERIFY_CODE_WEIGHT = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

    public boolean validate(){
        if(null == cacheValidateResult) {
            boolean result = (null != cardNumber);
            result = result && NEW_CARD_NUMBER_LENGTH == cardNumber.length();

            for (int i = 0; result && i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
                char ch = cardNumber.charAt(i);
                result = ch >= '0' && ch <= '9';
            }
            result = result && (calculateVerifyCode(cardNumber) == cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 1));
            try {
                Date birthDate = this.getBirthDate();
                result = result && null != birthDate;
                result = result && birthDate.before(new Date());
                result = result && birthDate.after(MINIMAL_BIRTH_DATE);

                String birthdayPart = this.getBirthDayPart();
                String realBirthdayPart = this.createBirthDateParser().format(birthDate);

                result = result && (birthdayPart.equals(realBirthdayPart));
            } catch (Exception e) {
                result = false;
            }
            cacheValidateResult = result;

        }
        return cacheValidateResult;
    }

    public CheckIdCard(String cardNumber) {
        if (null != cardNumber) {
            cardNumber = cardNumber.trim();
            if (OLD_CARD_NUMBER_LENGTH == cardNumber.length()) {
                cardNumber = convertToNewCardNumber(cardNumber);
            }
        }
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAddressCode(){
        this.checkIfValid();
        return this.cardNumber.substring(0, 6);
    }

    public Date getBirthDate() {
        if (null == this.cacheBirthDate) {
            try {
                this.cacheBirthDate = this.createBirthDateParser().parse(this.getBirthDayPart());
            } catch (Exception e) {
                throw new RuntimeException("");
            }
        }
        return new Date(this.cacheBirthDate.getTime());
    }

    public int getAge() {
        Calendar cal = Calendar.getInstance();
        Date birthDay = getBirthDate();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It`s unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age = age - 1;
                }
            } else {
                age = age - 1;
            }
        }

        return age;
    }

    public boolean isAdult() {
        return ADULT_AGE <= getAge();
    }

    public boolean isMale() {
        return 1 == this.getGenderCode();
    }

    public boolean isFemal() {
        return !this.isMale();
    }

    public char getCardNumberValidCode(CharSequence cardNumber) {
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = cardNumber.charAt(i);
            sum += (ch - '0') * VERIFY_CODE_WEIGHT[i];
        }
        return VERIFY_CODE[sum % 11];
    }

    private int getGenderCode() {
        this.checkIfValid();
        char genderCode = this.cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 2);
        return (genderCode - '0' & 0x1);
    }

    private String getBirthDayPart() {
        return this.cardNumber.substring(6, 14);
    }

    private SimpleDateFormat createBirthDateParser() {
        return new SimpleDateFormat(BIRTH_DATE_FORMAT);
    }

    private void checkIfValid() {
        if (!this.validate()) {
            throw new RuntimeException("号码错误");
        }
    }

    private static char calculateVerifyCode(CharSequence cardNumber) {
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = cardNumber.charAt(i);
            sum += (ch - '0') * VERIFY_CODE_WEIGHT[i];
        }
        return VERIFY_CODE[sum % 11];
    }

    private static String convertToNewCardNumber(String oldCardNumber) {
        StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
        buf.append(oldCardNumber.substring(0, 6));
        buf.append("19");
        buf.append(oldCardNumber.substring(6));
        buf.append(CheckIdCard.calculateVerifyCode(buf));

        return buf.toString();
    }
}
