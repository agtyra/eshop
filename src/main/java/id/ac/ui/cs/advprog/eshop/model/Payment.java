package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment(method, paymentData);
    }


    private String validatePayment(String method, Map<String, String> paymentData) {
        if (method.equalsIgnoreCase(PaymentMethod.VOUCHER.getValue())) {
            return validateVoucher(paymentData);
        } else if (method.equalsIgnoreCase(PaymentMethod.BANK_TRANSFER.getValue())) {
            return validateBankTransfer(paymentData);
        }
        return PaymentStatus.REJECTED.getValue();
    }

    private String validateVoucher(Map<String, String> data) {
        String code = data.get("voucherCode");
        if (code != null && code.length() == 16 && code.startsWith("ESHOP") && code.replaceAll("\\D", "").length() == 8) {
            return PaymentStatus.SUCCESS.getValue();
        }
        return PaymentStatus.REJECTED.getValue();
    }

    private String validateBankTransfer(Map<String, String> data) {
        String bankName = data.get("bankName");
        String referenceCode = data.get("referenceCode");
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status.");
        }
    }

}
