package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("Voucher"),
    BANK_TRANSFER("BankTransfer");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.getValue().equalsIgnoreCase(param)) {
                return true;
            }
        }
        return false;
    }
}
