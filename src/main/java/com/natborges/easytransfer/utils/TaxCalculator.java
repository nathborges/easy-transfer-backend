package com.natborges.easytransfer.utils;

public class TaxCalculator {
    public enum TaxEnum {
        tax1(0, 0, 3.0, 0.025),
        tax2(1, 10, 12.0, 0),
        tax3(21, 30, 0, 0.082),
        tax5(31, 40, 0, 0.069),
        tax6(41, 50, 0, 0.047),
        tax7(41, 50, 0, 0.017);


        private long startRange;
        private long endRange;
        private double fixedTax;
        private double variableTax;


        TaxEnum(int startRange, int endRange, double fixedTax, double variableTax) {
            this.startRange = startRange;
            this.endRange = endRange;
            this.fixedTax = fixedTax;
            this.variableTax = variableTax;
        }

        public boolean isInRange(long days) {
            return days >= startRange && days <= endRange;
        }

        public double getFixedTax() {
            return fixedTax;
        }

        public double getVariableTax() {
            return variableTax;
        }

    }
     public static double calculate(long day, double amount) {
            for (TaxEnum tax : TaxEnum.values()) {
                if (tax.isInRange(day)) {
                    Double totalVariableTax = amount * tax.getVariableTax();
                    Double totalFixedTax = tax.getFixedTax();
                    double totalTax = totalVariableTax + totalFixedTax;

                    // Truncate 2 values after dot
                    return Math.floor(totalTax * 100) / 100;
                }
            }
            throw new IllegalArgumentException("Day out of the provided rate intervals");
        }
    }
