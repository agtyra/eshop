package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private Map<String, String> paymentData;
    private String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment(method, paymentData);
    }

    private String validatePayment(String method, Map<String, String> paymentData) {
        if ("Voucher".equalsIgnoreCase(method)) {
            return validateVoucher(paymentData);
        } else if ("BankTransfer".equalsIgnoreCase(method)) {
            return validateBankTransfer(paymentData);
        }
        return "REJECTED";
    }

    private String validateVoucher(Map<String, String> data) {
        String code = data.get("voucherCode");
        if (code != null && code.length() == 16 && code.startsWith("ESHOP") && code.replaceAll("\\D", "").length() == 8) {
            return "SUCCESS";
        }
        return "REJECTED";
    }

    private String validateBankTransfer(Map<String, String> data) {
        String bankName = data.get("bankName");
        String referenceCode = data.get("referenceCode");
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            return "REJECTED";
        }
        return "SUCCESS";
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status.");
        }
    }

}