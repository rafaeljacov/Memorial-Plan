package application;

public class Summary {
    int lotPrice, vat, sellPrice, downPay, balance, interest, contract, discount, total, yearly;
    double discountRate;
    double intRate;
    static final int processFee = 2_000;

    Summary(){
        this.lotPrice = switch (Details.category) {
            case "Regular" -> 13_000;
            case "Inner Walk" -> 16_300;
            case "Walk" -> 17_950;
            case "Inner Drive" -> 19_600;
            case "Drive" -> 21_250;
            default -> 0;
        };

        switch (Details.planSelected) {
            case "One Year" -> this.intRate = 0.15;
            case "Two Years" -> this.intRate = 0.25;
            case "Three Years" -> this.intRate = 0.5;
            case "Spot Cash" -> this.discountRate = 0.15;
            case "30 Days" -> this.discountRate = 0.1;
            case "60 Days" -> this.discountRate = 0.05;
        }

        this.vat = (int) Math.round(lotPrice * 0.12);
        this.sellPrice = lotPrice + vat + processFee;
        this.downPay = (int) Math.round(sellPrice * 0.1);
        this.balance = sellPrice - downPay;

        if (intRate != 0) {
            this.interest = (int) (balance * intRate);
            this.contract = sellPrice + interest;
            this.yearly = switch (Details.planSelected) {
                case "One Year" -> (balance + interest) / 12;
                case "Two Years" -> (balance + interest) / 24;
                case "Three Years" -> (balance + interest) / 36;
                default -> 0;
            };
        } else if (discountRate != 0) {
            this.discount = (int) Math.round(sellPrice * discountRate);
            this.total = sellPrice - discount;
        }
    }
}
